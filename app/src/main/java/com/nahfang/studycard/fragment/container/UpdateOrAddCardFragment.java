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
    private String source;
    private int position;

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
        if (getArguments().getString("source").equals(UPDATE_FACTOR)) {
            this.source = UPDATE_FACTOR;
            int position = getArguments().getInt("position");
            this.position = position;
            mBinding.textTitle.setVisibility(View.VISIBLE);
            cardBean cardBean = viewmodel.mCardArrayList.get(position);
            mBinding.textTitle.setText(cardBean.getTitle());
            mBinding.editTitle.setVisibility(View.GONE);
            mBinding.editContent.setText(cardBean.getContent());
        } else if (getArguments().getString("source").equals(ADD_FACTOR)) {
            this.source = getArguments().getString("source");
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
            if(source.equals(ADD_FACTOR)) {
                cardBean cardBean = new cardBean();
                cardBean.setContent(mBinding.editContent.getText().toString());
                cardBean.setTitle(mBinding.editTitle.getText().toString());
                cardBean.setCategory_name(viewmodel.CardsName.getValue());
                viewmodel.addCard(cardBean);
                Navigation.findNavController(view1).popBackStack();
            } else if (source.equals(UPDATE_FACTOR)) {
                String content = mBinding.editContent.getText().toString();
                viewmodel.updateCard(position,content);
                Navigation.findNavController(view1).popBackStack();
            }

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