package com.nahfang.studycard.fragment.card;

import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.nahfang.studycard.R;
import com.nahfang.studycard.common.SingleVMFragment;
import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.components.dialog.ContentDialog;
import com.nahfang.studycard.databinding.FragmentCardBinding;

import java.util.ArrayList;
import java.util.Objects;

/**
 * cardFragmet
 * 本页面主要用于按照用户选择的算法，提供卡片给用户记忆
 * 存在一个bug  侧拉的菜单由于固定在该fragment上导致向右侧拉时会与上一级的Viewpager冲突,并且翻页后drawer不绘制
 * 并不完美的解决方案 ： 为该控件设置不许上层容器拦截 （内部拦截法），仍存在不绘制的问题，故不采用Viewpager
 * created by nahfang
 */

public class CardFragment extends SingleVMFragment<FragmentCardBinding, CardViewModel> implements View.OnClickListener, DrawerLayout.DrawerListener, View.OnTouchListener {
    private LinearLayout l = null;
    private DrawerRVAdapter drawerRVAdapter;


    @Override
    protected void initObserver() {
        mBinding.setViewModle(viewmodel);
        drawerRVAdapter = new DrawerRVAdapter();
        drawerRVAdapter.setCardViewModel(viewmodel);
        viewmodel.listCategory.observe(this, strings -> {
            drawerRVAdapter.setDatalist(strings);
            String nameFromLocal = viewmodel.getCategoryNameFromLocal();
            if(!nameFromLocal.equals(viewmodel.INIT_VALUE_SP)) {
                viewmodel.ToggleCategory(nameFromLocal);
            } else if (!Objects.requireNonNull(viewmodel.listCategory.getValue()).isEmpty()) {
                String name = viewmodel.listCategory.getValue().get(0);
                viewmodel.ToggleCategory(name);
            }
        });
        viewmodel.name_category.observe(this, s -> {
            mBinding.textCategory.setText(s);
            mBinding.mainDrawerlayout.close();
            viewmodel.setCategoryNameInLocal(s);
            viewmodel.getCards(s);
        });

        viewmodel.cards.observe(this, cardBeans -> viewmodel.getCard());
        viewmodel.card.observe(this, cardBean -> mBinding.cardContent.setText(cardBean.getTitle()));
    }


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_card;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDrawerLogic();
        assert getParentFragment() != null;
        l = (LinearLayout) getParentFragment().getView().findViewById(R.id.navigation_bottom);
        mBinding.btRefresh.setOnClickListener(this);
        mBinding.btShowanswer.setOnClickListener(this);
    }

    private void initDrawerLogic () {
        mBinding.btShowCategory.setOnClickListener(this);
        mBinding.textCategory.setOnClickListener(this);
        mBinding.mainDrawerlayout.addDrawerListener(this);
        mBinding.mainDrawerlayout.setOnTouchListener(this);
        viewmodel.getCategorys();
        mBinding.recyclerviewDrawer.setAdapter(drawerRVAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerviewDrawer.setLayoutManager(linearLayoutManager);
    }


    @Override
    protected Class<CardViewModel> getViewModelClassType() {
        return CardViewModel.class;
    }




    @Override
    public void onClick(View view) {
        if (view.equals(mBinding.btShowCategory) || view.equals(mBinding.textCategory)) {
            mBinding.mainDrawerlayout.openDrawer(Gravity.LEFT);
        }else if(view.equals(mBinding.btRefresh)){
            viewmodel.getCard();
        }else if (view.equals(mBinding.btShowanswer)) {
            if(viewmodel.cards.getValue() != null) {
                ContentDialog dialog = new ContentDialog.Builder(getContext())
                        .setMessage(viewmodel.mCard.getContent())
                        .setCancelable(true)
                        .setOutsiCancel(false)
                        .create();
                dialog.show();
            } else {
                setToast("快去建立属于你的卡片吧！");
            }

        }
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        if(drawerView.equals(mBinding.mainDrawer)) {
            l.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {
        if(drawerView.equals(mBinding.mainDrawer)){
            l.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view.equals(mBinding.mainDrawerlayout) ){
            mBinding.mainDrawerlayout.getParent().requestDisallowInterceptTouchEvent(true);

        }
        return false;
    }




}