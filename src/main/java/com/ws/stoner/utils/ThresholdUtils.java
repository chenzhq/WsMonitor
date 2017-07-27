package com.ws.stoner.utils;

import java.text.DecimalFormat;

/**
 * Created by zkf on 2017/7/25.
 */
/*
阀值解析 & 单位转换
 */
public class ThresholdUtils {

    //阀值解析 返回阀值
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

    //返回比较符号
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

    // 返回原始数值
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

    //单位转换 返回转换后的带单位的数值
    public static String transformValueUnits(String valueInfo,String units) {
        String valueUnits = "";
        String multiple = "";
        String UpUnits = units.toUpperCase();
        if("B".equals(UpUnits) || "BPS".equals(UpUnits) || "BYTE".equals(UpUnits)) {
            Float value = Float.parseFloat(valueInfo.trim());
            if(value > 1024) {
                value = value / 1024;
                multiple = "K";
                if(value > 1024) {
                    value = value / 1024;
                    multiple = "M";
                    if(value > 1024) {
                        value = value / 1024;
                        multiple = "G";
                        if(value > 1024) {
                            value = value / 1024;
                            multiple = "T";
                        }
                    }
                }
            }
            valueUnits = new DecimalFormat(".00").format(value) + multiple + units.toUpperCase();
        }else if("UPTIME".equals(UpUnits) || "S".equals(UpUnits)) {
            if(Float.parseFloat(valueInfo.trim()) < 0.001) {
                valueUnits = "< 1 毫秒";
            }else {
                Long sec = Long.parseLong(valueInfo.trim());
                int days = (int) (sec / (24 * 3600));
                int hours = (int) (sec / 3600 % 24);
                int minute = (int) (sec / 60 % 60);
                StringBuilder timeStringBuilder = new StringBuilder();
                timeStringBuilder.append(days == 0 ? "" : days + "天");
                timeStringBuilder.append(hours == 0 ? "" : hours + "小时");
                timeStringBuilder.append(minute == 0 ? "" : minute + "分钟");
                valueUnits = timeStringBuilder.toString();
            }
        }else if("UNIXTIME".equals(UpUnits)) {
            //未处理解析
            valueUnits = valueInfo + units;
        }else if("%".equals(UpUnits)) {
            valueUnits = new DecimalFormat(".00").format(Float.parseFloat(valueInfo.trim())) + units;
        }else {
            valueUnits = valueInfo + units;
        }
        return valueUnits;
    }
}
