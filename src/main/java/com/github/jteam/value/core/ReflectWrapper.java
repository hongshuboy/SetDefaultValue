package com.github.jteam.value.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * �����Ŀ�������е����԰�װ
 *
 * @author hongshuboy
 * date  2021/1/12 ���� 10:41
 */
public class ReflectWrapper {
    private Class<?> cls;
    private boolean isSupperClass;
    private Field field;
    private String fieldName;
    private Method setter;
    private Method getter;

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public boolean isSupperClass() {
        return isSupperClass;
    }

    public void setSupperClass(boolean supperClass) {
        isSupperClass = supperClass;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }
}
