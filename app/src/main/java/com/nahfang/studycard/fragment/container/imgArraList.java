package com.nahfang.studycard.fragment.container;

import com.nahfang.studycard.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class imgArraList {
    private ArrayList<Integer> arrayList;
    public imgArraList () {
        arrayList = new ArrayList<>();
        arrayList.add(R.drawable.ic_cards);
        arrayList.add(R.drawable.ic_cards_1);
        arrayList.add(R.drawable.ic_cards_2);
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }
}
