package com.github.jiangxch.hassakei.common.util;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author: jiangxch
 * @date: 2020/7/7 14:39
 */
public class JsonUtil {
    private static Gson gson = new Gson();

    private static Gson gsonDealingNull = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(JsonElement element, Type typeOfT) {
        return gson.fromJson(element, typeOfT);
    }

    public static JsonElement toJsonTree(Object obj) {
        return gson.toJsonTree(obj);
    }

    public static JsonElement parseToJsonObject(String obj) {
        JsonParser parser = new JsonParser();
        return parser.parse(obj).getAsJsonObject();
    }

    public static Gson getDisableHtmlEscapInstance() {
        return new GsonBuilder().disableHtmlEscaping().create();
    }

    public static JsonObject toJsonObject(Object obj) {
        return gson.toJsonTree(obj).getAsJsonObject();
    }

    public static String toJsonSerializingNull(Object obj) {
        return gsonDealingNull.toJson(obj);
    }

    public static <T> T fromJsonSerializingNull(String json, Class<T> tClass) {
        return gsonDealingNull.fromJson(json, tClass);
    }

    public static <T> T fromJsonSerializingNull(String json, Type tType) {
        return gsonDealingNull.fromJson(json, tType);
    }
}
