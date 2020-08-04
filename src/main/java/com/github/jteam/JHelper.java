package com.github.jteam;

import com.github.jteam.comfiguration.Configuration;
import com.github.jteam.value.SetDefaultValue;
import com.github.jteam.value.Type;

/**
 * @author hongshuboy
 * 2020-08-03 12:47
 */
public class JHelper {

    /**
     * 为一个对象的所有引用类型设置默认值，默认值的设置来自于的设置{@link Configuration}
     *
     * @param object    需要设置默认值的对象
     * @param configuration 配置类，它将决定默认值为多少，可以使用{@link Configuration#setDefaultConfig(Type, Object)}来设置
     * @return  设置好默认值的对象，因为是引用对象，也可以忽略该返回值
     */
    public static <T> T setDefaultValue(T object, Configuration configuration){
        return SetDefaultValue.setDefaultValue(object, configuration);
    }

    public static <T> T setDefaultValue(T object){
        return setDefaultValue(object, Configuration.SINGLE_CONFIGURATION);
    }
}
