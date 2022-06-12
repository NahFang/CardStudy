package com.nahfang.studycard.fragment.container;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nahfang.studycard.R;
import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.common.SingleVMFragment;
import com.nahfang.studycard.databinding.FragmentCardsInsideBinding;

import java.util.ArrayList;

public class CardsInsideFragment extends SingleVMFragment<FragmentCardsInsideBinding,CardsInsideViewModel> {

    private ViewpagerAdapter_cards adapter_cards;

    @Override
    protected void initObserver() {
        /*viewmodel.CardsName.observe(this, name -> {
            viewmodel.getCardsFromName(name);
        });*/
        adapter_cards = new ViewpagerAdapter_cards();
        viewmodel.cardArrayList.observe(this, new Observer<ArrayList<cardBean>>() {
            @Override
            public void onChanged(ArrayList<cardBean> cardBeans) {
                adapter_cards.setArrayList(cardBeans);
                adapter_cards.notifyDataSetChanged();
            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_cards_inside;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewpager2();

        //viewmodel.setCardsName(getArguments().getString("name"));
        viewmodel.getCardsFromName(getArguments().getString("name"));

        mBinding.btAddCard.setOnClickListener((view1) -> {
            //打开对应的界面
            Bundle bundle = new Bundle();
            bundle.putString("source",UpdateOrAddCardFragment.ADD_FACTOR);
            Navigation.findNavController(view1).navigate(R.id.updateOrAddCardFragment,bundle);
        });

    }



    @Override
    protected Class<CardsInsideViewModel> getViewModelClassType() {
        return CardsInsideViewModel.class;
    }

    private void initViewpager2 () {
        //viewpager2
        mBinding.viewpagerCards.setAdapter(adapter_cards);
        mBinding.viewpagerCards.setOffscreenPageLimit(1);
        RecyclerView recyclerView = (RecyclerView) mBinding.viewpagerCards.getChildAt(0);
        int padding = 60;
        recyclerView.setPadding(padding,0,padding,0);
        recyclerView.setClipToPadding(false);
        //viewpager切换动画
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new ScaleInTransformer());
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        mBinding.viewpagerCards.setPageTransformer(compositePageTransformer);
    }
}