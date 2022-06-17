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

    private ViewpagerAdapter_cards adapter_cards = null;

    private int initFactor = -1;

    @Override
    protected void initObserver() {

        viewmodel.CardsName.observe(this, name -> {
            viewmodel.getCardsFromName(name);
        });

        if (adapter_cards == null) {
            adapter_cards = new ViewpagerAdapter_cards();
        }

        viewmodel.cardArrayList.observe(this, new Observer<ArrayList<cardBean>>() {
            @Override
            public void onChanged(ArrayList<cardBean> cardBeans) {
                adapter_cards.setArrayList(cardBeans);
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
        /**\
         * 记一次意想不到的BUG
         *
         * 其实这里是存在一个问题的，也就是在fragment里使用recyclerview/viewpager2 时出现的
         * 问题，我们知道在为adapter改变数据时，我们需要保证数据集合不变，但是我们忽略的一点
         * 也就是控件本身也要不变，但是在fragment中，我们在onCreateView中创建了控件，当我们切换之后
         * 实际上该方法会在次运行，而导致该控件被创建，也就是说，即使你保证了数据集合不变
         * 调用notifyDataSetChanged()还是会无效。
         *
         * 解决方案 1 每次在方法中重新为控件设置adapter
         *
         * 解决方法2 让rootview不再重建。 （推荐）
         *
         * 实际上我解决这个问题用到的方法是结合了上面两种的，因为我发现我单使用第二种方法时会
         * 因为我设置的动画而导致UI渲染出现错误。
         *
         * 我的解决方法 ： 在基类中让DataBinding只生成一次，让adapter保持唯一（数据集合唯一）
         * 但是我会在onActivityCreated方法中再次为recyclerveiw/viewpager绑定一次adapter
         * 以完成notifyDataSetChanged()的效果。
         */

        initViewpager2();

        viewmodel.setCardsName(getArguments().getString("name"));
       /* viewmodel.getCardsFromName(getArguments().getString("name"));*/

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