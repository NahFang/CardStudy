package com.nahfang.studycard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  与Fragment的配合使用的Adapter
 *  弃用： 由于出现暂时无法解决的问题
 *  created by nahfang 2022
 */

public class ViewPagerAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> fragments;

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public void setData(ArrayList<Fragment> fragments){
        this.fragments = fragments;
    }
}
