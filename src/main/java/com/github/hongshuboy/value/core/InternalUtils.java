package com.github.hongshuboy.value.core;

import java.lang.reflect.Method;

/**
 * 内部工具类
 *
 * @author hongshuboy
 * Date: 2021/1/12 上午 11:27
 */
public class InternalUtils {
    private static final String GET = "get";
    private static final String SET = "set";

    public static Method getGetterMethod(Class<?> aClass, String fieldName) throws NoSuchMethodException {
        return aClass.getMethod(GET.concat(toBigCamelCase(fieldName)));
    }

    public static Method getSetterMethod(Class<?> aClass, String fieldName, Class<?> paramType) throws NoSuchMethodException {
        return aClass.getMethod(SET.concat(toBigCamelCase(fieldName)), paramType);
    }

    /**
     * ת��Ϊ���շ�
     *
     * @param str exampleInput
     * @return ExampleInput
     */
    private static String toBigCamelCase(String str) {
        if (str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }
}
