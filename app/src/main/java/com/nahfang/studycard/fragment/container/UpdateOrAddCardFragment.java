package com.nahfang.studycard.fragment.container;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nahfang.studycard.R;
import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.common.BaseFragment;
import com.nahfang.studycard.common.SingleVMFragment;
import com.nahfang.studycard.databinding.FragmentUpdateOrAddCardBinding;


public class UpdateOrAddCardFragment extends SingleVMFragment<FragmentUpdateOrAddCardBinding,CardsInsideViewModel> {

    public static final String ADD_FACTOR = "add";
    public static final String UPDATE_FACTOR = "update";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater,container,savedInstanceState);
    }
    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_update_or_add_card;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments().getString("source").equals(ADD_FACTOR)) {
            //在这里将传过来的数据绑定到view
        }
        mBinding.editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = mBinding.editContent.getText().toString();
                mBinding.countText.setText("字数 " + content.length());
            }

        });
        mBinding.btFinishAddCard.setOnClickListener((view1)->{
            //在这里完成数据库操作
            viewmodel.addCard(new cardBean());
            Navigation.findNavController(view1).popBackStack();
        });
    }

    @Override
    protected void initObserver() {

    }

    @Override
    protected Class<CardsInsideViewModel> getViewModelClassType() {
        return CardsInsideViewModel.class;
    }
}