package com.nahfang.studycard.fragment.container;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nahfang.studycard.application;
import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.common.BaseViewModel;

import java.util.ArrayList;

public class CardsInsideViewModel extends BaseViewModel {
   private MutableLiveData<String> _CardsName = new MutableLiveData<>();
   public LiveData<String> CardsName = _CardsName;

   public void setCardsName (String cardsName) {
       //check
       if (cardsName.equals("") || cardsName == null) {
          throw new NullPointerException();
       }
       _CardsName.setValue(cardsName);
   }
    private String oldName = "";
    public ArrayList<cardBean> mCardArrayList = new ArrayList<>();
    private MutableLiveData<ArrayList<cardBean>> _cardArrayList = new MutableLiveData<>();
    public LiveData<ArrayList<cardBean>> cardArrayList = _cardArrayList;

   public void getCardsFromName (String cardsName) {

      //在这里根据name 从数据库拉数据
       //如果两次的name相同，则不进行数据库查询
       if(!cardsName.equals(oldName)) {
           application.threadPoolExecutor.execute(new Runnable() {
               @Override
               public void run() {
                   mCardArrayList.clear();
                   mCardArrayList.addAll(application.dataBase.cardDao().getCardsFollowCategory(cardsName));
                   _cardArrayList.postValue(mCardArrayList);
                   oldName = cardsName;
               }
           });
       }
   }
   public void updateCard (int position,String content) {
       cardBean cardBean = mCardArrayList.get(position);
       cardBean.setContent(content);
       mCardArrayList.set(position,cardBean);
       application.threadPoolExecutor.execute(new Runnable() {
           @Override
           public void run() {
               application.dataBase.cardDao().updateCard(cardBean);
           }
       });
       _cardArrayList.setValue(mCardArrayList);
       //在这里完成对卡片的修改 进行数据库操作
   }
   public void addCard (cardBean cardBean) {
       //在这里完成卡片的添加 数据库操作
       mCardArrayList.add(cardBean);
       _cardArrayList.setValue(mCardArrayList);
       application.threadPoolExecutor.execute(new Runnable() {
           @Override
           public void run() {
               application.dataBase.cardDao().insertCard(cardBean);
           }
       });
   }

}