package com.heaton.test;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zhanggonglin on 2018/8/3.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //先将返回的json数据解析到Response中，如果status==0，则解析到我们的实体基类中
        BaseResponse httpResult = gson.fromJson(response, BaseResponse.class);
        if (httpResult.getStatus() == 0) {
            return gson.fromJson(response, type);
        } else {
            httpResult.setData(null);
            return (T) httpResult;
        }
    }
}


