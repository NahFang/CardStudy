package com.nahfang.studycard.fragment.card;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nahfang.studycard.application;
import com.nahfang.studycard.common.BaseViewModel;
import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.fragment.record.RecordFragment;
import com.nahfang.studycard.utils.algorithmUtil;

import java.util.ArrayList;

public class CardViewModel extends BaseViewModel {

    //用于在获取本地存储分类时，当本地不存在该类型值时的默认值
    public static final String INIT_VALUE_SP = "<#no>";

    private ArrayList<String> arr_category_drawer = new ArrayList<>();
    private MutableLiveData<ArrayList<String>> _listCategory = new MutableLiveData<>();
    public LiveData<ArrayList<String>> listCategory = _listCategory;

    private MutableLiveData<String> _name_category = new MutableLiveData<>();
    public LiveData<String> name_category = _name_category;

    private ArrayList<cardBean> arr_cards = new ArrayList<>();
    private MutableLiveData<ArrayList<cardBean>> _cards = new MutableLiveData<>();
    public LiveData<ArrayList<cardBean>> cards = _cards;

    public cardBean mCard = new cardBean();
    private MutableLiveData<cardBean> _card = new MutableLiveData<>();
    public LiveData<cardBean> card = _card;

    private String getAlgorithm () {
        SharedPreferences sharedPreferences = application.context.getSharedPreferences(RecordFragment.SWITCHSECTOR_SP, Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(RecordFragment.SWITCHSECTOR_SP_String,RecordFragment.RANDOM_CARD);
        return  result;
    }

    public void getCard () {
        //按照用户给的算法设置，获取一个card
        if(arr_cards.isEmpty()) {
            Toast.makeText(application.context, "本分类内容已复习完成，可切换分类继续复习噢", Toast.LENGTH_SHORT).show();
        } else {
            switch (getAlgorithm()) {
                case RecordFragment.ORDER_CARD:mCard = algorithmUtil.get_Order(arr_cards);break;
                case RecordFragment.RANDOM_CARD:mCard = algorithmUtil.get_Random(arr_cards);break;
            }
            arr_cards.remove(mCard);
            mCard.setIsRead(true);
            _card.setValue(mCard);
        }

    }

    public void getCards (String category_name) {
        //根据传入的类别名称在数据库中查询
        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                arr_cards.clear();
                arr_cards.addAll(application.dataBase.cardDao().getCardsFollowCategory(category_name));
                _cards.postValue(arr_cards);
            }
        });


    }

    public void getCategorys() {
        //后期获取数据

        application.threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                arr_category_drawer.clear();
                arr_category_drawer.addAll(application.dataBase.cardsDao().getAllCardsName());
                _listCategory.postValue(arr_category_drawer);
            }
        });
    }

    //切换分类
    public void ToggleCategory (String s_cateGory) {
        _name_category.setValue(s_cateGory);
        //添加其它设置逻辑
    }

    public void setCategoryNameInLocal (String name) {
        SharedPreferences.Editor editor = application.context.getSharedPreferences("category",
                application.context.MODE_PRIVATE).edit();
        editor.putString("categoryName", name);
        editor.apply();
    }

    public String getCategoryNameFromLocal() {
        SharedPreferences preC = application.context.getSharedPreferences("category", application.context.MODE_PRIVATE);
        String s = preC.getString("categoryName", INIT_VALUE_SP);
        if(checkCategory(s)) {
            return s;
        } else {
            if(arr_category_drawer.isEmpty()) {
                setCategoryNameInLocal(INIT_VALUE_SP);
                return INIT_VALUE_SP;
            }
        }
        return INIT_VALUE_SP;
    }
    private boolean checkCategory (String name) {
        return arr_category_drawer.contains(name);
    }

}