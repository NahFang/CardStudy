package com.nahfang.studycard.fragment.container;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nahfang.studycard.application;
import com.nahfang.studycard.bean.ArributeValue;
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
    public String oldName = "";
    public ArrayList<cardBean> mCardArrayList = new ArrayList<>();
    private MutableLiveData<ArrayList<cardBean>> _cardArrayList = new MutableLiveData<>();
    public LiveData<ArrayList<cardBean>> cardArrayList = _cardArrayList;

   public void getCardsFromName (String cardsName) {

       /**
        * 在这里根据name 从数据库拉数据
        * 如果两次的name相同，则不进行数据库查询
        * 突然发现这样不行，当从回收站恢复后，如果不再次请求则无法看见恢复后的卡片，但是为了能节约数据库的开销，也是没有办法只能后续想想解决方案
        * 解决方案 ： 在onPause()方法中将oldname 设置了 “ ”  然后在内部页面添加卡片监听器处 将oldname改为当前
        */

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
               try {
                   application.dataBase.cardDao().updateCard(cardBean);
               } catch (Throwable e) {
                   e.printStackTrace();
               }

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
   public void deleteCard (cardBean cardBean) {
       mCardArrayList.remove(cardBean);
       _cardArrayList.setValue(mCardArrayList);
       application.threadPoolExecutor.execute(new Runnable() {
           @Override
           public void run() {
               try {
                   application.dataBase.cardDao().deleteCardOnLogic(ArributeValue.DELETE_TURE,cardBean.getCid());
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }
       });
   }

}