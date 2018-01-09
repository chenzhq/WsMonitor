package com.ws.stoner.utils;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
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

    /**
     * 比较两个list元素的值是否相等
     * @param <T>
     * @param a
     * @param b
     * @return
     */
    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if(a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

    public static String getDurationStringByTime(LocalDateTime beginTime, LocalDateTime endTime) {
        Duration duration = Duration.between(beginTime, endTime);
        Long sec = duration.getSeconds();
        int days = (int) (sec / (24 * 3600));
        int hours = (int) (sec / 3600 % 24);
        int minute = (int) (sec / 60 % 60);
        StringBuilder timeStringBuilder = new StringBuilder();
        timeStringBuilder.append(days == 0 ? "" : days + "天");
        timeStringBuilder.append(hours == 0 ? "" : hours + "小时");
        timeStringBuilder.append(minute == 0 ? "" : minute + "分");
        return timeStringBuilder.toString();
    }
}
