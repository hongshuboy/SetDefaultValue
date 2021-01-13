package com.github.jteam.value.core;

import com.github.jteam.value.configuration.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Value Engine
 *
 * @author hongshuboy
 * 2020-08-03 12:50
 */
public class ValueEngine<T> {
    private final T object;
    private final Configuration configuration;
    private final static Map<String, Object> primitiveTypeMap;

    static {
        Map<String, Object> tmpMap = new HashMap<>(1 << 4);
        tmpMap.put(byte.class.getName(), 0b00);
        tmpMap.put(boolean.class.getName(), false);
        tmpMap.put(char.class.getName(), ' ');
        tmpMap.put(short.class.getName(), 0);
        tmpMap.put(int.class.getName(), 0);
        tmpMap.put(float.class.getName(), 0f);
        tmpMap.put(long.class.getName(), 0L);
        tmpMap.put(double.class.getName(), 0.0);
        primitiveTypeMap = Collections.unmodifiableMap(tmpMap);
    }

    /**
     * 为一个对象的所有引用类型设置默认值，默认值的设置来自于的设置{@link Configuration}
     *
     * @param object        需要设置默认值的对象
     * @param configuration 配置类，它将决定默认值为多少，可以使用{@link Configuration#setDefaultConfig(Type, Object)}来设置
     * @return 设置好默认值的对象，因为是引用对象，也可以忽略该返回值
     */
    public T setDefaultValue() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        final Class<?> aClass = object.getClass();
        //封装所有属性
        final List<FieldWrapper> wrappers = getAllAttr(object, aClass);

        for (FieldWrapper fieldWrapper : wrappers) {
            final Method getterMethod = fieldWrapper.getGetterMethod();
            final Object o = getterMethod.invoke(object);
            if (Objects.isNull(o)) {
                setDefaultValueReference(fieldWrapper);
            } else if (configuration.getUserDefaultFieldValue(fieldWrapper.getFieldName()) != null
                    && isPrimitiveAndDefaultValue(o)) {
                //基本数据类型
                setDefaultValuePrimitive(fieldWrapper);
            }
        }
        return object;
    }

    /**
     * 是否是基本数据类型并且是默认值
     */
    private boolean isPrimitiveAndDefaultValue(Object o) {
        final Object findValue = primitiveTypeMap.get(o.getClass().getName());
        return findValue != null && o == findValue;
    }

    private List<FieldWrapper> getAllAttr(T object, Class<?> aClass) {
        List<FieldWrapper> fields = new LinkedList<>();
        while (aClass != null) {
            try {
                fields.addAll(getAllWrappedFields(aClass));
            } catch (Exception e) {
                e.printStackTrace();
            }
            aClass = aClass.getSuperclass();
        }
        return fields;
    }

    private Collection<FieldWrapper> getAllWrappedFields(Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (aClass == null) return null;
        final List<FieldWrapper> list = new LinkedList<>();
        final Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            list.add(new FieldWrapper(aClass, aClass == object.getClass(), field));
        }
        return list;
    }

    /**
     * 引用类型的默认值赋值
     */
    private void setDefaultValueReference(FieldWrapper fieldWrapper) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object value;
        //优先根据用户配置的字段名进行匹配
        value = configuration.getUserDefaultFieldValue(fieldWrapper.getFieldName().toLowerCase());
        //使用configMap，根据Type获取默认值
        if (value == null) {
            value = configuration.getDefaultValue(fieldWrapper.getField().getType().getName());
        }
        if (value instanceof Class) {
            fieldWrapper.getSetterMethod().invoke(object, ((Class<?>) value).newInstance());
        } else {
            fieldWrapper.getSetterMethod().invoke(object, value);
        }
    }

    /**
     * 基本数据类型的默认值赋值
     */
    private void setDefaultValuePrimitive(FieldWrapper fieldWrapper) throws InvocationTargetException, IllegalAccessException {
        fieldWrapper.getSetterMethod().invoke(object, configuration.getUserDefaultFieldValue(fieldWrapper.getFieldName()))
    }

    public ValueEngine(T object, Configuration configuration) {
        this.object = object;
        this.configuration = configuration;
    }
}
