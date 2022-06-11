package com.nahfang.studycard.fragment.container;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nahfang.studycard.R;
import com.nahfang.studycard.common.SingleVMFragment;
import com.nahfang.studycard.databinding.FragmentContainerBinding;

public class NoteFragment extends SingleVMFragment<FragmentContainerBinding, NoteViewModel> {


    private cardsAdapter adapter;
    @Override
    protected void initObserver() {
        adapter = new cardsAdapter();
        adapter.setViewModel(viewmodel);
        adapter.setFragment(this);
        viewmodel.cardsArray.observe(this,(arrayList)->{
            adapter.setArrayList(arrayList);
            adapter.notifyDataSetChanged();

        });
    }

    public Bitmap getCurrentBitmap () {
        View activityView = getActivity().getWindow().getDecorView();
        activityView.setDrawingCacheEnabled(true);
        activityView.destroyDrawingCache();
        activityView.buildDrawingCache();
        Bitmap bmp = activityView.getDrawingCache();
        return bmp;
    }


    private void initRV () {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mBinding.noteRv.setLayoutManager(staggeredGridLayoutManager);
        mBinding.noteRv.setAdapter(adapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       return super.onCreateView(inflater,container,savedInstanceState);
    }


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_container;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewmodel.getCards();
        initRV();
    }



    @Override
    protected Class<NoteViewModel> getViewModelClassType() {
        return NoteViewModel.class;
    }
}