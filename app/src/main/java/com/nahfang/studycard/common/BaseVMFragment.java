package com.nahfang.studycard.common;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.nahfang.studycard.databinding.FragmentMainBinding;

import java.util.ArrayList;

public abstract class BaseVMFragment<Binding extends ViewDataBinding> extends BaseFragment<Binding> {

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        initViewModel();
        initBaseObserver();
        initObserver();
        super.onViewCreated(view,savedInstanceState);
    }

    //初始化ViewModel
    abstract protected void initViewModel();

    //通过反射获取ViewModel
    protected  <VM extends BaseViewModel> VM getViewModel (ViewModelStoreOwner owner, Class<VM> clazz) {
        VM viewmodel= new ViewModelProvider(owner,new ViewModelProvider.NewInstanceFactory()).get(clazz);
        return viewmodel ;
    }

    //获取ViewModel集合
    abstract protected ArrayList<BaseViewModel> getArrayViewModel();

    //基础订阅
    private final void initBaseObserver(){
        ArrayList<BaseViewModel> arr = getArrayViewModel();
        for (BaseViewModel vm : arr) {
            initBaseObserver(vm);
        }
    }

    private final void initBaseObserver (BaseViewModel viewModel) {
        //在这里进行基础的订阅
    }

    //普通订阅
    abstract protected void initObserver();

}
