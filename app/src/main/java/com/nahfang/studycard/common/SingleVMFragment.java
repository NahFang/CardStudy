package com.nahfang.studycard.common;

import androidx.databinding.ViewDataBinding;
import java.util.ArrayList;

/**
 *
 * 单ViewModel的Fragment由此派生
 *
 * @param <Binding>
 * @param <VM>
 */

abstract public class SingleVMFragment<Binding extends ViewDataBinding,VM extends BaseViewModel> extends BaseVMFragment<Binding>{
    protected VM viewmodel = null;

    @Override
    final protected void initViewModel(){
        viewmodel = getViewModel(getActivity(),getViewModelClassType());
    }
    @Override
    final protected ArrayList<BaseViewModel> getArrayViewModel(){
        ArrayList<BaseViewModel> arr = new ArrayList<>();
        arr.add(viewmodel);
        return arr;
    }

    abstract protected Class<VM> getViewModelClassType();



}
