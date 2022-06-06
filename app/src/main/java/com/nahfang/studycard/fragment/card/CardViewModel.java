package com.nahfang.studycard.fragment.card;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nahfang.studycard.common.BaseViewModel;

import java.util.ArrayList;

public class CardViewModel extends BaseViewModel {
    private ArrayList<String> arr_category_drawer = new ArrayList<>();
    private MutableLiveData<ArrayList<String>> _listCategory = new MutableLiveData<>();
    public LiveData<ArrayList<String>> listCategory = _listCategory;

    private MutableLiveData<String> _name_categorys = new MutableLiveData<>();
    public LiveData<String> name_categorys = _name_categorys;

    public void getCategoty() {
        arr_category_drawer.add("计算机网络");
        arr_category_drawer.add("计算机网络");
        arr_category_drawer.add("计算机网络");
        arr_category_drawer.add("计算机网络");
        arr_category_drawer.add("计算机网络");
        arr_category_drawer.add("计算机网络");
        _listCategory.setValue(arr_category_drawer);
    }

    //切换分类
    public void ToggleCategory(String s_cateGory) {
        _name_categorys.setValue(s_cateGory);
    }
    // TODO: Implement the ViewModel

}