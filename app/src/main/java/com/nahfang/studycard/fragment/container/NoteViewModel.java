package com.nahfang.studycard.fragment.container;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nahfang.studycard.bean.cardsBean;
import com.nahfang.studycard.common.BaseViewModel;

import java.util.ArrayList;

public class NoteViewModel extends BaseViewModel {
    private ArrayList<cardsBean> mCardsArralist = new ArrayList<>();
    MutableLiveData<ArrayList<cardsBean>> _cardsArray = new MutableLiveData<>();
    LiveData<ArrayList<cardsBean>> cardsArray = _cardsArray;

    public void getCards () {
        //进行数据库的操作
        mCardsArralist.clear();
        cardsBean cardsBean = new cardsBean();
        cardsBean.setName_cards("测试");
        mCardsArralist.add(cardsBean);
        _cardsArray.setValue(mCardsArralist);
    }
    public void addCardCategory (cardsBean cardsBean) {
        mCardsArralist.add(cardsBean);
        _cardsArray.setValue(mCardsArralist);
        //进行数据库操作
    }
    public void removeCardCategory (cardsBean cardsBean) {
        mCardsArralist.remove(cardsBean);
        _cardsArray.setValue(mCardsArralist);
        //进行数据库操作
    }
}