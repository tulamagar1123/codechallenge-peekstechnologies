package com.app.a20221207_tula_nycschools.helper;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonParser {

    private static Gson gson = new Gson();

    public static <T> List<T> toList(String json, Class<T> typeClass) {
        return gson.fromJson(json, new ListOfJson<>(typeClass));
    }
    public static <T> T toObject(String json, Class clazz) {
        try {
            return (T) gson.fromJson(json, clazz);
        }
        catch (Exception ex){
            return null;
        }
    }

    public static JSONObject toJSON(Object object) throws JSONException {
        String jsonData = new Gson().toJson(object);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject;
    }


}