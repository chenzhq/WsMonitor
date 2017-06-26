package com.ws.bix4j.access;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by chenzheqi on 2017/6/26. <br>
 * Zabbix getAPI中的select参数使用此类定义序列化方法 <br>
 * 使用方法：如果需要返回所有属性或者数量，则传入只包含一个字符串的数组，例如 new String[]{"count"} <br>
 * 如果需要返回特定属性，则传入包含所有必要属性的数组 <br>
 * 示例见HostGetRequest.java <br>
 *
 */
public class SelectParamSerializer implements ObjectSerializer{

    private static final Logger logger = LoggerFactory.getLogger(SelectParamSerializer.class);
    /**
     * fastjson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     *
     * @param serializer
     * @param object     src the object that needs to be converted to Json.
     * @param fieldName  parent object field name
     * @param fieldType  parent object field type
     * @param features   parent object field serializer features
     * @throws IOException
     */
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (fieldType.getTypeName().equals("java.lang.String[]")) {
            String[] strings = (String[]) object;
            if (strings[0].equals("count") || strings[0].equals("extend")) {
               serializer.write(strings[0]);
            } else {
                serializer.write(strings);
            }
        }
    }
}
