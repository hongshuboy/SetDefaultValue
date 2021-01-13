package com.github.jteam.value.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射的目标类所有的属性包装
 *
 * @author hongshuboy
 * date  2021/1/12 上午 10:41
 */
public class FieldWrapper {
    private Class<?> cls;
    private boolean isSupperClass;
    private Field field;
    private String fieldName;
    private Method setterMethod;
    private Method getterMethod;

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

    public Method getSetterMethod() {
        return setterMethod;
    }

    public void setSetterMethod(Method setterMethod) {
        this.setterMethod = setterMethod;
    }

    public Method getGetterMethod() {
        return getterMethod;
    }

    public void setGetterMethod(Method getterMethod) {
        this.getterMethod = getterMethod;
    }

    public FieldWrapper(Class<?> cls, boolean isSupperClass, Field field) throws NoSuchMethodException {
        this.cls = cls;
        this.isSupperClass = isSupperClass;
        this.field = field;
        this.fieldName = field.getName();
        this.setterMethod = InternalUtils.getSetterMethod(this.getCls(), this.getFieldName(), field.getType());
        this.getterMethod = InternalUtils.getGetterMethod(this.getCls(), this.getFieldName());
    }
}
