package com.heaton.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhanggonglin on 2018/10/29.
 */

public class BaseResponse<T> {
    @SerializedName("status")
    public int status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T data;

    /**
     * 是否请求成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return status == 0;
    }

    public boolean isTokenFail() {
        return status == 2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
