package com.github.jteam.value;

import com.github.jteam.comfiguration.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hongshuboy
 * 2020-08-03 12:50
 */
public class SetDefaultValue {
    /**
     * 为一个对象的所有引用类型设置默认值，默认值的设置来自于的设置{@link Configuration}
     *
     * @param object    需要设置默认值的对象
     * @param configuration 配置类，它将决定默认值为多少，可以使用{@link Configuration#setDefaultConfig(Type, Object)}来设置
     * @return  设置好默认值的对象，因为是引用对象，也可以忽略该返回值
     */
    public static <T> T setDefaultValue(T object, Configuration configuration) {
        final Class<?> aClass = object.getClass();
        final Field[] fields = aClass.getDeclaredFields();
        List<String> fieldsList = new ArrayList<>(fields.length + 1);
        for (Field field : fields) {
            fieldsList.add(field.getName());
        }
        fieldsList.forEach((fieldName) -> {
            try {
                final Method method = aClass.getDeclaredMethod("get" + toBigCamelCase(fieldName));
                final Object o = method.invoke(object);
                if (Objects.isNull(o)) {
                    final Method setMethod = aClass.getDeclaredMethod("set" + toBigCamelCase(fieldName), method.getReturnType());
                    setMethod.invoke(object, configuration.getDefaultValue(method.getReturnType().getName()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return object;
    }

    private static String toBigCamelCase(String str) {
        if (str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }
}
