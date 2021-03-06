package com.nahfang.studycard.fragment.record;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.nahfang.studycard.R;
import com.nahfang.studycard.common.SingleVMFragment;
import com.nahfang.studycard.databinding.FragmentRecordBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecordFragment extends SingleVMFragment<FragmentRecordBinding,RecordViewModel> {

    public static final String ORDER_CARD = "order";
    public static final String RANDOM_CARD = "random";
    public static final String SWITCHSECTOR_SP = "switchsector";
    public static final String SWITCHSECTOR_SP_String = "switchsectorss";



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
            viewmodel.getCardCount();
            initTime();
            initSwitch();
            mBinding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()) {
                        if (mBinding.switch2.isChecked()) {
                            mBinding.switch2.setChecked(false);
                        }
                        setSPswitchsector(ORDER_CARD);
                        //??????????????????
                    }else {
                        mBinding.switch2.setChecked(true);
                    }
                }
            });
            mBinding.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()) {
                        if (mBinding.switch1.isChecked()) {
                            mBinding.switch1.setChecked(false);
                        }
                        setSPswitchsector(RANDOM_CARD);
                        //??????????????????
                    } else {
                        mBinding.switch1.setChecked(true);
                    }
                }
            });
            mBinding.btRecycle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.recoverFragment);
                }
            });
        }


    @Override
    protected void initObserver() {
        viewmodel.count_card.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                mBinding.countCard.setText("???????????? ???" + aLong);
            }
        });

    }

    @Override
    protected Class<RecordViewModel> getViewModelClassType() {
        return RecordViewModel.class;
    }
    private void setSPswitchsector (String mSwitch) {
            SharedPreferences.Editor editor = getContext().getSharedPreferences(SWITCHSECTOR_SP,Context.MODE_PRIVATE).edit();
            editor.putString(SWITCHSECTOR_SP_String,mSwitch);
            editor.apply();
    }

    private void initSwitch() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SWITCHSECTOR_SP, Context.MODE_PRIVATE);
        String switchs = sharedPreferences.getString(SWITCHSECTOR_SP_String,ORDER_CARD);
        switch (switchs) {
            case RANDOM_CARD:mBinding.switch2.setChecked(true); break;
            case ORDER_CARD:mBinding.switch1.setChecked(true); break;
        }
    }

    private void initTime () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        Date date = new Date(System.currentTimeMillis());
        String t = simpleDateFormat.format(date);
        int time = Integer.parseInt(t);
        if(time >= 0 && time < 6) {
            mBinding.textGreet.setText("?????????"+time+"????????????????????????");
        } else if (time >= 6 && time < 12) {
            mBinding.textGreet.setText("?????????????????????????????????????????????");
        } else if (time >= 12 && time <14) {
            mBinding.textGreet.setText("??????????????????????????????");
        } else if (time >=14 && time <18) {
            mBinding.textGreet.setText("??????????????????????????????????????????");
        } else if (time >=18 && time < 22) {
            mBinding.textGreet.setText("??????????????????????????????????????????????????????");
        } else {
            mBinding.textGreet.setText("?????????????????????????????????");
        }
    }
}