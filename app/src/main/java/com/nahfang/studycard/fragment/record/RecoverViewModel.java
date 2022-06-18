package com.nahfang.studycard.fragment.record;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nahfang.studycard.application;
import com.nahfang.studycard.bean.ArributeValue;
import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.bean.cardsBean;
import com.nahfang.studycard.common.BaseViewModel;
import com.nahfang.studycard.fragment.container.imgArraList;
import com.nahfang.studycard.utils.algorithmUtil;

import java.util.ArrayList;

public class RecoverViewModel extends BaseViewModel {
    private ArrayList<cardBean> mCards = new ArrayList<>();
    private MutableLiveData<ArrayList<cardBean>> _arr_card = new MutableLiveData<>();
    public LiveData<ArrayList<cardBean>> arr_card = _arr_card;

    private ArrayList<String> arr_category = new ArrayList<>();

    public void getCategorys () {
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                arr_category.clear();
                arr_category.addAll(application.dataBase.cardsDao().getAllCardsName());
            }
        });
    }

    public void getCards () {
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCards.clear();
                mCards.addAll(application.dataBase.cardDao().getAllDeletedCard());
                _arr_card.postValue(mCards);
            }
        });
    }

    public void recoverCard (cardBean cardBean) {
        mCards.remove(cardBean);
        _arr_card.setValue(mCards);
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                application.dataBase.cardDao().recoverCardFollowCid(ArributeValue.DELETE_FALSE,cardBean.getCid());
                if (!arr_category.contains(cardBean.getCategory_name())) {
                    buildCategory(cardBean.getCategory_name());
                }
            }
        });
    }

    public void recoverAllCard () {
        //在这开启等待框
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int lentgh = mCards.size();
                for (int i = 0; i < lentgh; i++) {
                    cardBean card = mCards.get(i);
                    application.dataBase.cardDao().recoverCardFollowCid(ArributeValue.DELETE_FALSE,card.getCid());
                    if (!arr_category.contains(card.getCategory_name())) {
                        arr_category.add(card.getCategory_name());
                        buildCategory(card.getCategory_name());
                    }
                }
                mCards.clear();
                //在这关闭等待框
                _arr_card.postValue(mCards);
            }
        });
    }

    //只能异步操作
    private void buildCategory (String name) {
        cardsBean cardsBean = new cardsBean();
        cardsBean.setName_cards(name);
        cardsBean.setSrc_cards(algorithmUtil.get_Random(new imgArraList().getArrayList()));
        application.dataBase.cardsDao().insertCategory(cardsBean);
    }

    public void deleteCard (cardBean cardBean) {
        mCards.remove(cardBean);
        _arr_card.setValue(mCards);
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                application.dataBase.cardDao().deleteCard(cardBean);
            }
        });
    }

    public void deleteAllCard () {
        application.threadPoolExecutor.execute(new Runnable() {
            //在这开启等待框
            @Override
            public void run() {
                int length = mCards.size();
                for (int i = 0; i < length; i++) {
                    cardBean card = mCards.get(i);
                    application.dataBase.cardDao().deleteCard(card);
                }
                mCards.clear();
                //在这关闭等待框
                _arr_card.postValue(mCards);
            }
        });
    }

}