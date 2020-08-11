package com.github.jteam.value;

import com.github.jteam.configuration.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hongshuboy
 * 2020-08-03 12:50
 */
public class SetDefaultValue {
    private static final String GET = "get";
    private static final String SET = "set";

    /**
     * 为一个对象的所有引用类型设置默认值，默认值的设置来自于的设置{@link Configuration}
     *
     * @param object        需要设置默认值的对象
     * @param configuration 配置类，它将决定默认值为多少，可以使用{@link Configuration#setDefaultConfig(Type, Object)}来设置
     * @return 设置好默认值的对象，因为是引用对象，也可以忽略该返回值
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
                if (!configuration.containsIgnoreField(fieldName)) {
                    final Method method = aClass.getDeclaredMethod(GET + toBigCamelCase(fieldName));
                    final Object value = method.invoke(object);
                    if (Objects.isNull(value)) {
                        setDefaultValue(object, configuration, aClass, fieldName, method);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return object;
    }

    private static <T> void setDefaultValue(T object, Configuration configuration, Class<?> aClass, String fieldName, Method method) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final Method setMethod = aClass.getDeclaredMethod(SET + toBigCamelCase(fieldName), method.getReturnType());
        if (configuration.containsFieldValueConfig(fieldName)){
            setMethod.invoke(object, configuration.getDefaultFieldValue(fieldName));
        }else{
            setMethod.invoke(object, configuration.getDefaultValue(method.getReturnType().getName()));
        }
    }

    private static String toBigCamelCase(String str) {
        if (str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }
}
