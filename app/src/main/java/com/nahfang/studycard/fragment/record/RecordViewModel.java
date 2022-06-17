package com.nahfang.studycard.fragment.record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nahfang.studycard.application;
import com.nahfang.studycard.common.BaseViewModel;

public class RecordViewModel extends BaseViewModel {
    private MutableLiveData<Long> _count_card = new MutableLiveData<>();
    public LiveData<Long> count_card = _count_card;

    public void getCardCount () {
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                long count = application.dataBase.cardDao().getCardCount();
                _count_card.postValue(count);
            }
        });
    }
}