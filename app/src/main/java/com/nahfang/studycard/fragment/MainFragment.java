package com.nahfang.studycard.fragment;



import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.View;
import com.nahfang.studycard.R;
import com.nahfang.studycard.ViewPagerAdapter;
import com.nahfang.studycard.common.BaseVMFragment;
import com.nahfang.studycard.common.BaseViewModel;
import com.nahfang.studycard.databinding.FragmentMainBinding;
import java.util.ArrayList;


public class MainFragment extends BaseVMFragment<FragmentMainBinding> implements View.OnClickListener {

    MainViewModel viewModel = null;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments.add(new CardFragment());
        fragments.add(new ContainerFragment());
        fragments.add(new RecordFragment());
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //为viewpager设置adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.setData(fragments);
        mBinding.viewpagerMain.setAdapter(adapter);
        mBinding.viewpagerMain.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mBinding.viewpagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
            public void onPageSelected(int position){
               super.onPageSelected(position);
               switch (position){
                   case 0 : mBinding.navigationBottom.setVisibility(View.VISIBLE); break;
                   case 1 : mBinding.navigationBottom.setVisibility(View.VISIBLE); break;
                   case 2 : mBinding.navigationBottom.setVisibility(View.GONE); break;
               }
           }
        });
        mBinding.buttonCard.setOnClickListener(this);
        mBinding.buttonContainer.setOnClickListener(this);
        mBinding.buttonRecord.setOnClickListener(this);


    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onClick(View view) {
        if (mBinding.buttonCard.equals(view)) {
            mBinding.viewpagerMain.setCurrentItem(0);
        } else if (mBinding.buttonContainer.equals(view)) {
            mBinding.viewpagerMain.setCurrentItem(1);
        }else if (mBinding.buttonRecord.equals(view)) {
            mBinding.viewpagerMain.setCurrentItem(2);
        }
    }

    @Override
    protected void initViewModel() {
        viewModel = getViewModel(this,MainViewModel.class);
    }

    @Override
    protected ArrayList<BaseViewModel> getArrayViewModel() {
        ArrayList<BaseViewModel> arr = new ArrayList<>();
        arr.add(viewModel);
        return arr;
    }

    @Override
    protected void initObserver() {

    }


}