package com.github.jteam.value.core;

import com.github.jteam.value.configuration.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Value Engine
 * @author hongshuboy
 * 2020-08-03 12:50
 */
public class ValueEngine <T>{
    private final T object;
    private final Configuration configuration;

    /**
     * 为一个对象的所有引用类型设置默认值，默认值的设置来自于的设置{@link Configuration}
     *
     * @param object        需要设置默认值的对象
     * @param configuration 配置类，它将决定默认值为多少，可以使用{@link Configuration#setDefaultConfig(Type, Object)}来设置
     * @return 设置好默认值的对象，因为是引用对象，也可以忽略该返回值
     */
    public T setDefaultValue() {
        final Class<?> aClass = object.getClass();
        //封装所有属性
        final List<ReflectWrapper> wrappers = getAllAttr(object, aClass);


//        List<String> fieldsList = new ArrayList<>(fields.size() + 1);
//        for (Field field : fields) {
//            fieldsList.add(field.getName());
//        }
        /*fieldsList.forEach((fieldName) -> {
            try {
                if (!configuration.containsIgnoreField(fieldName)) {
                    final Method method = aClass.getDeclaredMethod(GET + toBigCamelCase(fieldName));
                    final Object value = method.invoke(object);
                    if (Objects.isNull(value) || configuration.getUserDefaultFieldValue(fieldName) != null) {
                        setDefaultValue(object, configuration, aClass, fieldName, method);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/


//        for (Field field : fields) {
//            try {
//                if (!field.isAccessible()) {
//                    field.setAccessible(true);
//                }
//                final Object o = field.get(object);
//                if (o == null) {
//                    field.set(object, configuration.getDefaultValue(field.getType().getName()));
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
        return object;
    }

    private List<ReflectWrapper> getAllAttr(T object, Class<?> aClass) {
        List<ReflectWrapper> fields = new LinkedList<>();
        while (aClass != null) {
            try {
                fields.addAll(getSuperWrapperFields(aClass));
            } catch (Exception e) {
                e.printStackTrace();
            }
            aClass = aClass.getSuperclass();
        }
        return fields;
    }

    private Collection<ReflectWrapper> getSuperWrapperFields(Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (aClass == null) return null;
        final List<ReflectWrapper> list = new LinkedList<>();
        final Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            final Method getterMethod = InternalUtils.getGetterMethod(aClass, field.getName());
            final Object o = getterMethod.invoke(object);
            //设置默认值
//            if (Objects.isNull(o)) {
                /*Object defaultValue = null;
                //用户为这个属性设置了默认值
                if (configuration.getUserDefaultFieldValue(field.getName()) != null) {
                    defaultValue = configuration.getUserDefaultFieldValue(field.getName());
                }
                final Method setterMethod = aClass.getDeclaredMethod(InternalUtils.getSetterMethodName(field.getName()));
                setDefaultValue(aClass, field.getName(), InternalUtils.getSetterMethod(aClass, field.getName()));
            }
        }
        return null;
    }

    private void setDefaultValue(Class<?> aClass, String fieldName, Method setterMethod) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        final Method setMethod = aClass.getDeclaredMethod(SET + InternalUtils.toBigCamelCase(fieldName), method.getReturnType());
        Object value;
        //优先根据用户配置的字段名进行匹配
        value = configuration.getUserDefaultFieldValue(fieldName);
        //使用configMap，根据Type获取默认值
        if (value == null) {
            value = configuration.getDefaultValue(method.getReturnType().getName());
        }
        if (value instanceof Class) {
            setMethod.invoke(object, ((Class<?>) value).newInstance());
        } else {
            setMethod.invoke(object, value);
        }
    }

    public ValueEngine(T object, Configuration configuration) {
        this.object = object;
        this.configuration = configuration;
    }
}
