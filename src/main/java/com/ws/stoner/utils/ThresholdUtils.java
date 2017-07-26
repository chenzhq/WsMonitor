package com.ws.stoner.utils;

/**
 * Created by zkf on 2017/7/25.
 */
/*
阀值解析器
 */
public class ThresholdUtils {

    public static String getThresholdValue(String expression) {
        //expression='{16252}>1000'  expression='{16252}>200M'
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
        }
        return thresholdValue.trim();
    }

    public static String getThresholdSymbol(String expression) {
        //expression='{16252}>1000'  expression='{16252}>200M'
        String symbol = "";
        if(expression.indexOf(">") != -1 && expression.indexOf(">=") == -1) {
            // '>'
            symbol = ">";
        }else if(expression.indexOf("<") != -1 && expression.indexOf("<=") == -1) {
            // '<'
            symbol = "<";
        }else if(expression.indexOf("<>") != -1) {
            // '<>'
            symbol = "<>";
        }else if(expression.indexOf("=") != -1 && expression.indexOf("<=") == -1 && expression.indexOf(">=") == -1) {
            // '='
            symbol = "=";
        }
        return symbol;
    }

    public static String getTransformValue(String thresholdValue) {
        String value = "";
        thresholdValue = thresholdValue.trim();
      if(thresholdValue.indexOf("K") != -1) {
          value = thresholdValue.substring(0,thresholdValue.indexOf("K"));
      }else if(thresholdValue.indexOf("M") != -1) {
          value = thresholdValue.substring(0,thresholdValue.indexOf("M"));
      }else if(thresholdValue.indexOf("G") != -1) {
          value = thresholdValue.substring(0,thresholdValue.indexOf("G"));
      }else if(thresholdValue.indexOf("T") != -1) {
          value = thresholdValue.substring(0,thresholdValue.indexOf("T"));
      }else if(thresholdValue.indexOf("s") != -1) {
          value = thresholdValue.substring(0,thresholdValue.indexOf("s"));
      }else if(thresholdValue.indexOf("m") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("m"));
          value = String.valueOf(Long.parseLong(time) * 60);
      }else if(thresholdValue.indexOf("h") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("h"));
          value = String.valueOf(Long.parseLong(time) * 3600);
      }else if(thresholdValue.indexOf("d") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("d"));
          value = String.valueOf(Long.parseLong(time) * 3600 * 24);
      }else if(thresholdValue.indexOf("w") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("d"));
          value = String.valueOf(Long.parseLong(time) * 3600 * 24 * 7);
      }else {
          value = thresholdValue;
      }
      return value;
    }
}
