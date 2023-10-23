package com.payroll_requirements.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ReturnData<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    //静态函数，用于返回成功情况和R
    public static <T> ReturnData<T> success(String msg, T object) {
        ReturnData<T> r = new ReturnData<T>();
        r.msg = msg;
        r.data = object;
        r.code = 1;
        return r;
    }

    //静态函数，用于返回失败情况和R
    public static <T> ReturnData<T> error(String msg) {
        ReturnData r = new ReturnData();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public ReturnData<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
