package com.ws.stoner.utils;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by pc on 2017/8/8.
 */
public class BaseUtils {

    /**
     * 泛型嵌套list转换为二维数组
     * @param src List<List<T>> 原嵌套list （子list的长度必须相等）
     * @return T[][] 转换后的二维数组
     */
    public static <T> T[][] listsToArrays(List<List<T>> src, Class<T> type) {
        if (src == null || src.isEmpty()) {
            return null;
        }

        // 初始化泛型二维数组
        // JAVA中不允许这样初始化泛型二维数组： T[][] dest = new T[src.size()][];
        T[][] dest = dest = (T[][]) Array.newInstance(type, src.size(), src.get(0).size());

        for (int i = 0; i < src.size(); i++) {
            for (int j = 0; j < src.get(i).size(); j++) {
                dest[i][j] = src.get(i).get(j);
            }
        }

        return dest;
    }
}
