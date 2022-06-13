package com.nahfang.studycard.fragment.record;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nahfang.studycard.R;
import com.nahfang.studycard.common.SingleVMFragment;
import com.nahfang.studycard.databinding.FragmentRecordBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecordFragment extends SingleVMFragment<FragmentRecordBinding,RecordViewModel> {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_record;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            initTime();
        }

    @Override
    protected void initObserver() {

    }

    @Override
    protected Class<RecordViewModel> getViewModelClassType() {
        return RecordViewModel.class;
    }

    private void initTime () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        Date date = new Date(System.currentTimeMillis());
        String t = simpleDateFormat.format(date);
        int time = Integer.parseInt(t);
        if(time >= 0 && time < 6) {
            mBinding.textGreet.setText("现在是"+time+"点，早点休息哦。");
        } else if (time >= 6 && time < 12) {
            mBinding.textGreet.setText("上午好，我们的未来都是光明的。");
        } else if (time >= 12 && time <14) {
            mBinding.textGreet.setText("中午好，记得吃饭哦。");
        } else if (time >=14 && time <18) {
            mBinding.textGreet.setText("下午好，相信努力就会有收获。");
        } else if (time >=18 && time < 22) {
            mBinding.textGreet.setText("晚上好，有没有整理好今天的知识点呢？");
        } else {
            mBinding.textGreet.setText("时间差不多啦，该睡觉咯");
        }
    }
}