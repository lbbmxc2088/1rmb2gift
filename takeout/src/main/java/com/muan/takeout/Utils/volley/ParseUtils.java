package com.muan.takeout.Utils.volley;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ${Muan} on 2016/5/27.
 */
public class ParseUtils {
    public static <T> T parse_cus(String jsonstr, Class<T> cls) {
        Gson gson = new Gson();
        T obj = gson.fromJson(jsonstr, cls);
        return obj;
    }

    public static <T> List<T> parse_cus_list(String jsonstr, Class<T[]> cls) {
        T[] arr = new Gson().fromJson(jsonstr, cls);
        return Arrays.asList(arr);
    }
}
