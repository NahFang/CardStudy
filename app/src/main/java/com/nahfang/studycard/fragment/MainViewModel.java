package com.nahfang.studycard.fragment;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.nahfang.studycard.common.BaseViewModel;
import com.nahfang.studycard.databinding.FragmentMainBinding;

import java.util.ArrayList;

public class MainViewModel extends BaseViewModel {
    public ArrayList<Fragment> fragments = new ArrayList<>();

    // TODO: Implement the ViewModel
}