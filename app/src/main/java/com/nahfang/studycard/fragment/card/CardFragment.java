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
import com.nahfang.studycard.databinding.FragmentCardBinding;

import java.util.ArrayList;

/**
 * cardFragmet
 * 本页面主要用于随机提供卡片给用户记忆
 * 存在一个bug  侧拉的菜单由于固定在该fragment上导致向右侧拉时会与上一级的Viewpager冲突,并且翻页后drawer不绘制
 * 并不完美的解决方案 ： 为该控件设置不许上层容器拦截 （内部拦截法），仍存在不绘制的问题，故不采用Viewpager
 * 该项目可能传到你手上了，希望你能想到更好的办法 如果求知急切但是找不到办法 可以联系我，说不定那时候我能解决了 哈哈~
 * created by nahfang  qq: 2216812142
 */

public class CardFragment extends SingleVMFragment<FragmentCardBinding, CardViewModel> implements View.OnClickListener, DrawerLayout.DrawerListener, View.OnTouchListener {
    private LinearLayout l = null;
    private DrawerRVAdapter drawerRVAdapter;


    @Override
    protected void initObserver() {
        mBinding.setViewModle(viewmodel);
        drawerRVAdapter = new DrawerRVAdapter();
        drawerRVAdapter.setCardViewModel(viewmodel);
        viewmodel.listCategory.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                drawerRVAdapter.setDatalist(strings);
            }
        });
        viewmodel.name_categorys.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBinding.textCategory.setText(s);
                mBinding.mainDrawerlayout.close();
            }
        });
    }


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_card;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBinding.btShowCategory.setOnClickListener(this);
        l = (LinearLayout) getParentFragment().getView().findViewById(R.id.navigation_bottom);
        mBinding.mainDrawerlayout.addDrawerListener(this);
        mBinding.mainDrawerlayout.setOnTouchListener(this);
        mBinding.btRefresh.setOnClickListener(this);
        viewmodel.getCategoty();
        mBinding.recyclerviewDrawer.setAdapter(drawerRVAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerviewDrawer.setLayoutManager(linearLayoutManager);



    }

   /* @Override
    protected void initViewModel() {
        viewModel = getViewModel(this,CardViewModel.class);
    }

    @Override
    protected ArrayList<BaseViewModel> getArrayViewModel() {
        ArrayList<BaseViewModel> arr = new ArrayList<>();
        arr.add(viewModel);
        return arr;
    }*/

    @Override
    protected Class<CardViewModel> getViewModelClassType() {
        return CardViewModel.class;
    }




    @Override
    public void onClick(View view) {
        if (view.equals(mBinding.btShowCategory)) {
            mBinding.mainDrawerlayout.openDrawer(Gravity.LEFT);
        }else if(view.equals(mBinding.btRefresh)){

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