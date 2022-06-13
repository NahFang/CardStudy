package com.nahfang.studycard.common;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nahfang.studycard.R;

/**
 * basefragment
 * 所有fragment 派生于它
 * created by nahfang
 */
abstract public class BaseFragment<Binding extends ViewDataBinding> extends Fragment {

    protected Binding mBinding = null;
    protected View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return obtainRootView(inflater, container);

    }
    protected View obtainRootView(LayoutInflater inflater, ViewGroup container){
        if (mBinding == null) {
            mBinding = DataBindingUtil.inflate(inflater, getRootViewResId(), container, false);
            mBinding.setLifecycleOwner(this);
        }
        mView = mBinding.getRoot();
        return mView;
    }
    abstract protected int getRootViewResId();

    public void setToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void setLog(String s, String name){
        Log.d(name,s);
    }
}