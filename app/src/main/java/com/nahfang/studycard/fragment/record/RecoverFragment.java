package com.nahfang.studycard.fragment.record;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nahfang.studycard.R;
import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.common.SingleVMFragment;
import com.nahfang.studycard.components.empty.EmptyView;
import com.nahfang.studycard.databinding.FragmentRecoverBinding;
import com.nahfang.studycard.fragment.container.ScaleInTransformer;

import java.util.ArrayList;

public class RecoverFragment extends SingleVMFragment<FragmentRecoverBinding,RecoverViewModel> {

    private ViewpagerAdapter_recover adapter_recover;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_recover;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewpager2();
        setListener();
        viewmodel.getCategorys();
        viewmodel.getCards();
    }
    private void setListener () {
        mBinding.btAllRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewmodel.recoverAllCard();
            }
        });

        mBinding.btAllDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewmodel.deleteAllCard();
            }
        });
    }

    @Override
    protected void initObserver() {

        adapter_recover = new ViewpagerAdapter_recover();
        adapter_recover.setViewModel(viewmodel);

        viewmodel.arr_card.observe(this, new Observer<ArrayList<cardBean>>() {
            @Override
            public void onChanged(ArrayList<cardBean> cardBeans) {
                if(cardBeans.isEmpty()) {
                    mBinding.empty.setVisibility(View.VISIBLE);
                    mBinding.viewpagerCardsRecover.setVisibility(View.GONE);
                } else {
                    adapter_recover.setArrayList(cardBeans);
                    mBinding.empty.setVisibility(View.GONE);
                    mBinding.viewpagerCardsRecover.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    @Override
    protected Class getViewModelClassType() {
        return RecoverViewModel.class;
    }

    private void initViewpager2 () {
        //viewpager2
        mBinding.viewpagerCardsRecover.setAdapter(adapter_recover);
        mBinding.viewpagerCardsRecover.setOffscreenPageLimit(1);
        RecyclerView recyclerView = (RecyclerView) mBinding.viewpagerCardsRecover.getChildAt(0);
        int padding = 60;
        recyclerView.setPadding(padding,0,padding,0);
        recyclerView.setClipToPadding(false);
        //viewpager切换动画
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new ScaleInTransformer());
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        mBinding.viewpagerCardsRecover.setPageTransformer(compositePageTransformer);
    }
}