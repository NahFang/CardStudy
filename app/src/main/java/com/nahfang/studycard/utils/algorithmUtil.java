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
        Random ran = new Random();
        int index = ran.nextInt(arr_size);
        result = index > 0 ? arr.get(index) : arr.get(0);
        return result;
    }
    //后续待完善
}
