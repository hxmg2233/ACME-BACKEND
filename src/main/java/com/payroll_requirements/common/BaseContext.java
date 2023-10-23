package com.payroll_requirements.common;

/**
 * @author Administrator
 * @version 1.0
 * @description: 基于ThreadLocal封装的工具类，用于保存和获取当前登录用户的id
 * @date 2023/10/20 21:03
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
