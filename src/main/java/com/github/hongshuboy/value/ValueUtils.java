package com.github.hongshuboy.value;

import com.github.hongshuboy.value.configuration.impl.SingleHashConfiguration;
import com.github.hongshuboy.value.core.ValueEngine;
import com.github.hongshuboy.value.configuration.Configuration;
import com.github.hongshuboy.value.core.Type;

import java.lang.reflect.InvocationTargetException;

/**
 *          _                                _
 *        | |                              | |
 *       | |__    ___   _ __    __ _  ___ | |__   _   _
 *      | '_ \  / _ \ | '_ \  / _` |/ __|| '_ \ | | | |
 *     | | | || (_) || | | || (_| |\__ \| | | || |_| |
 *    |_| |_| \___/ |_| |_| \__, ||___/|_| |_| \__,_|
 *                          __/ |
 *                         |___/
 * <br/>[本工具原理是使用Getter和Setter方法进行赋值，因此需要传入的对象有这类方法]
 *
 * @author hongshuboy
 * Email hongshuboy@gmail.com
 * GitHub https://github.com/hongshuboy
 * Date: 2020-08-03 12:47
 */
public class ValueUtils {

    /**
     * 为一个对象的所有引用类型设置默认值，默认值的设置来自于的设置{@link Configuration}
     *
     * @param object        需要设置默认值的对象
     * @param configuration 配置类，它将决定默认值为多少，可以使用{@link Configuration#setDefaultConfig(Type, Object)}来设置
     * @return 设置好默认值的对象，因为是引用对象，也可以忽略该返回值
     */
    public static <T> T setDefaultValue(T object, Configuration configuration) {
        final ValueEngine<T> valueEngine = new ValueEngine<>(object, configuration);
        try {
            return valueEngine.setDefaultValue();
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static <T> T setDefaultValue(T object) {
        return setDefaultValue(object, SingleHashConfiguration.getInstance());
    }
}
