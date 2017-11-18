package com.ws.stoner.utils;

import com.ws.bix4j.access.item.Item;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.host.HostDetailItemVO;
import com.ws.stoner.model.view.itemvalue.ItemValueUnit;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zkf on 2017/7/25.
 */
/*
阀值解析 & 单位转换
 */
public class ThresholdUtils {

    //阀值解析 返回阀值 expression='{16252}>200M'  ==> 200M
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

    //阀值解析 返回带 比较符号的 阀值 expression='{16252}>200M'  ==> >200M
    public static String getThresholdValueSymbol(String expression) {
        //expression='{16252}>1000'  expression='{16252}>200M'
        String thresholdValue = "";
        if(expression.indexOf(">") != -1 && expression.indexOf(">=") == -1) {
            // '>'
            thresholdValue = expression.substring(expression.indexOf(">"));
        }else if(expression.indexOf("<") != -1 && expression.indexOf("<=") == -1) {
            // '<'
            thresholdValue = expression.substring(expression.indexOf("<"));
        }else if(expression.indexOf("<>") != -1) {
            // '<>'
            thresholdValue = expression.substring(expression.indexOf("<>"));
        }else if(expression.indexOf("=") != -1 && expression.indexOf("<=") == -1 && expression.indexOf(">=") == -1) {
            // '='
            thresholdValue = expression.substring(expression.indexOf("="));
        }
        return thresholdValue.trim();
    }

    //返回比较符号
    public static String getThresholdSymbol(String expression) {
        //expression='{16252}>1000'  expression='{16252}>200M'
        String symbol = "";
        if(expression.indexOf("<>") != -1) {
            // '<>'
            symbol = "<>";
        }else if(expression.indexOf("<") != -1 || expression.indexOf("<=") != -1) {
            // '<'
            symbol = "<";
        }else if(expression.indexOf(">") != -1 || expression.indexOf(">=") != -1) {
            // '>'
            symbol = ">";
        } else if(expression.indexOf("=") != -1 && expression.indexOf("<=") == -1 && expression.indexOf(">=") == -1) {
            // '='
            symbol = "=";
        }
        return symbol;
    }

    // 返回原始数值    20 kB ==>>  20 *1024 B  map< K , 20 *1024 >
    public static Map<String,Float> getTransformValue(String thresholdValue) {
        Map<String,Float> valueUnit = new HashMap<>();
        thresholdValue = thresholdValue.trim();
      if(thresholdValue.indexOf("K") != -1) {
          valueUnit.put("K",Float.parseFloat(thresholdValue.substring(0,thresholdValue.indexOf("K"))));
      }else if(thresholdValue.indexOf("M") != -1) {
          valueUnit.put("M",Float.parseFloat(thresholdValue.substring(0,thresholdValue.indexOf("M"))));
      }else if(thresholdValue.indexOf("G") != -1) {
          valueUnit.put("G",Float.parseFloat(thresholdValue.substring(0,thresholdValue.indexOf("G"))));
      }else if(thresholdValue.indexOf("T") != -1) {
          valueUnit.put("T",Float.parseFloat(thresholdValue.substring(0,thresholdValue.indexOf("T"))));
      }else if(thresholdValue.indexOf("s") != -1) {
          valueUnit.put("s",Float.parseFloat(thresholdValue.substring(0,thresholdValue.indexOf("s"))));
      }else if(thresholdValue.indexOf("m") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("m"));
          valueUnit.put("s",Float.parseFloat(time) * 60);
      }else if(thresholdValue.indexOf("h") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("h"));
          valueUnit.put("s",Float.parseFloat(time) * 3600);
      }else if(thresholdValue.indexOf("d") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("d"));
          valueUnit.put("s",Float.parseFloat(time) * 3600 *24);
      }else if(thresholdValue.indexOf("w") != -1) {
          String time = thresholdValue.substring(0,thresholdValue.indexOf("w"));
          valueUnit.put("s",Float.parseFloat(time) * 3600 *24 * 7);
      }else {
          valueUnit.put("value",Float.parseFloat(thresholdValue));
      }
      return valueUnit;
    }

    //单位转换 返回转换后的带单位的数值 map<单位，值>  20 *1024 B  ==>>  20 kB  适用于文本展示数值
    public static ItemValueUnit transformValueUnits(String valueInfo, String units) {
        ItemValueUnit valueUnits = null;
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
            valueUnits = new ItemValueUnit(
                    new DecimalFormat("#0.00").format(value),
                    multiple+units.toUpperCase()
            );

        }else if("UPTIME".equals(UpUnits) || "S".equals(UpUnits)) {
            if(Float.parseFloat(valueInfo.trim()) < 0.001) {
                valueUnits = new ItemValueUnit("< 1 毫秒",units);
            }else if(Float.parseFloat(valueInfo.trim()) < 1) {
                valueUnits = new ItemValueUnit(
                        Float.parseFloat(valueInfo.trim()) * 1000 + "毫秒",
                        units
                );
            }else if(Float.parseFloat(valueInfo.trim()) >= 1) {
                Long sec = Long.parseLong(valueInfo.trim());
                int days = (int) (sec / (24 * 3600));
                int hours = (int) (sec / 3600 % 24);
                int minute = (int) (sec / 60 % 60);
                StringBuilder timeStringBuilder = new StringBuilder();
                timeStringBuilder.append(days == 0 ? "" : days + "天");
                timeStringBuilder.append(hours == 0 ? "" : hours + "小时");
                timeStringBuilder.append(minute == 0 ? "" : minute + "分钟");
                valueUnits = new ItemValueUnit(timeStringBuilder.toString(),units);

            }
        }else if("UNIXTIME".equals(UpUnits)) {
            //未处理解析
            valueUnits = new ItemValueUnit(valueInfo,units);
        }else if("%".equals(UpUnits)) {
            valueUnits = new ItemValueUnit(
                    String.valueOf(new DecimalFormat("#0.00").format(Float.parseFloat(valueInfo.trim()))),
                    units
            );
        }else {

            valueUnits = new ItemValueUnit(valueInfo,units);
        }
        return valueUnits;
    }

    //获取转换的图形数据
    public static ItemValueUnit transformGraphValue(String valueInfo,String units) {
        String upUnits = units.toUpperCase();
        ItemValueUnit valueUnits;
        if("B".equals(upUnits) || "BPS".equals(upUnits) || "BYTE".equals(upUnits)) {
            valueUnits = ThresholdUtils.transformValueUnits(valueInfo,units);
        }else {
            //图形中非 （B） 流量数据 还未做处理
            valueUnits = new ItemValueUnit(valueInfo,units);
        }
       return valueUnits;
    }

}
