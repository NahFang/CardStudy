package com.nahfang.studycard.utils;

import com.nahfang.studycard.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * 这里将本应用的某些算法抽离到工具类中，方便在不同的地方使用
 * 主要使用的场景为卡片的随机抽取
 */

public final class algorithmUtil {
    //随机算法
    public static <T> T get_Random(ArrayList<T> arr) {
        T result = null;
        int arr_size = arr.size();

        if(arr_size == 0) return null;

        Random ran = new Random();
        int index = ran.nextInt(arr_size) - 1;
        if(index >=0 && index < arr.size()) {
            result = arr.get(index);
        }
        return result;
    }
    //顺序算法
    public static <T> T get_Order (ArrayList<T> arr) {
        return arr.get(0);
    }
    //后续待完善
}
