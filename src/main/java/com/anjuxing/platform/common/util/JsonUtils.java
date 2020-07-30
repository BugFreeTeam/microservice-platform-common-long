package com.anjuxing.platform.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 */
public class JsonUtils {

    static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 对象转JSON格式数据
     * @param object
     * @return
     */
    public static String beanToJson(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 对象集合转JSON格式数据
     * @param list
     * @return
     */
    public static String beanListToJson(List list){
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * JSON格式数据转对象
     * @param json
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> bean){
        try {
            return mapper.readValue(json, bean);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON格式数据转对象
     * @param json
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(byte[] json, Class<T> bean){
        try {
            return mapper.readValue(json, bean);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON格式数据转对象集合
     * @param json
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToBeanList(String json, Class<T> bean){
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, bean);
            return mapper.readValue(json, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON格式数据转对象数组
     * @param json
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Object[] jsonToBeanArray(String json, Class<T> bean){
        try {
            ArrayType arrayType = mapper.getTypeFactory().constructArrayType(bean);
            return mapper.readValue(json, arrayType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON格式数据转List，对象默认LinkedHashMap
     * @param json
     * @return
     */
    public static List jsonToList(String json){
        try {
            return mapper.readValue(json, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON格式数据转Map
     * @param json
     * @return
     */
    public static Map jsonToMap(String json){
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
