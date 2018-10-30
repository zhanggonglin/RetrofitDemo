package com.heaton.test;

import com.heaton.test.entity.Translation;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zhanggonglin on 2018/10/29.
 */

public interface GetRequestInterface {
    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Call<Translation> getCall();
    // @GET注解的作用:采用Get方法发送网络请求 // getCall() = 接收网络请求数据的方法 // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
}
