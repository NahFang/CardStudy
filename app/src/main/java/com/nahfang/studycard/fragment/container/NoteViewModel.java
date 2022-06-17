package com.nahfang.studycard.fragment.container;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.nahfang.studycard.application;
import com.nahfang.studycard.bean.cardsBean;
import com.nahfang.studycard.common.BaseViewModel;
import com.nahfang.studycard.components.db.dataBase;

import java.util.ArrayList;

public class NoteViewModel extends BaseViewModel {
    private ArrayList<cardsBean> mCardsArralist = new ArrayList<>();
    MutableLiveData<ArrayList<cardsBean>> _cardsArray = new MutableLiveData<>();
    LiveData<ArrayList<cardsBean>> cardsArray = _cardsArray;

    public void getCards () {
        //后续决定是否在这里判断只在第一次查询数据库
        //进行数据库的操作
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mCardsArralist.clear();
                    mCardsArralist.addAll(application.dataBase.cardsDao().getAllCards());
                    _cardsArray.postValue(mCardsArralist);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void addCardCategory (cardsBean cardsBean) {
        mCardsArralist.add(cardsBean);
        _cardsArray.setValue(mCardsArralist);
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    application.dataBase.cardsDao().insertCategory(cardsBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //进行数据库操作
    }
    public void removeCardCategory (cardsBean cardsBean) {
        mCardsArralist.remove(cardsBean);
        _cardsArray.setValue(mCardsArralist);
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                application.dataBase.cardsDao().deleteCategory(cardsBean);
                application.dataBase.cardDao().deleteAllCardFollowName(cardsBean.getName_cards());
            }
        });

    }
}