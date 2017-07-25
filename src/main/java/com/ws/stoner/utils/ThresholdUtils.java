package com.ws.stoner.utils;

/**
 * Created by zkf on 2017/7/25.
 */
/*
阀值解析器
 */
public class ThresholdUtils {

    public static String getThresholdValue(String expression) {
        //expression='{16252}>1000'
        String thresholdValue = "";
        if(expression.indexOf(">") != -1 && expression.indexOf(">=") == -1) {
            // '>'
            thresholdValue = expression.substring(expression.indexOf(">")+1);
        }else if(expression.indexOf("<") != -1 && expression.indexOf("<=") == -1) {
            // '<'
            thresholdValue = expression.substring(expression.indexOf("<")+1);
        }else if(expression.indexOf("<>") != -1) {
            // '<>'
            thresholdValue = expression.substring(expression.indexOf("<>")+1);
        }else if(expression.indexOf("=") != -1 && expression.indexOf("<=") == -1 && expression.indexOf(">=") == -1) {
            // '='
            thresholdValue = expression.substring(expression.indexOf("=")+1);
        }else if(expression.indexOf(">=") != -1) {
            // '>='
            thresholdValue = expression.substring(expression.indexOf(">=")+1);
        }else if(expression.indexOf("<=") != -1) {
            // '<='
            thresholdValue = expression.substring(expression.indexOf("<=")+1);
        }
        return thresholdValue;
    }
}
