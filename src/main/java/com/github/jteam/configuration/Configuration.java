package com.github.jteam.configuration;

import com.github.jteam.value.Type;

/**
 * @author hongshuboy
 * 2020-08-05 12:39
 */
public interface Configuration {
    /**
     * 设置默认值
     *
     * @param type  使用Type枚举类设置，如Type.Integer
     * @param value 为Type设置的默认值
     */
    Configuration setDefaultConfig(Type type, Object value);

    /**
     * 设置默认值
     *
     * @param type  若Type枚举类没有提供的类型，可以使用此方法在此设置
     * @param value 为Type设置的默认值
     */
    Configuration setDefaultConfig(String type, Object value);

    /**
     * 获取为某一属性设置的默认值
     *
     * @param type 需要查看的属性，如Type.Integer
     * @return 为该属性设置的默认值
     */
    default Object getDefaultValue(Type type) {
        return getDefaultValue(type.getType());
    }

    /**
     * 获取为某一属性设置的默认值
     *
     * @param type 需要查看的属性
     * @return 为该属性设置的默认值
     */
    Object getDefaultValue(String type);

    /**
     * 设置需要跳过的字段名（将自动忽略大小写）
     *
     * @param fields 如name，则将不对name字段赋默认值
     * @return Configuration
     */
    Configuration setIgnoreFields(String... fields);

    /**
     * 是否会跳过赋值该字段
     *
     * @param fieldName 属性名
     * @return boolean
     */
    boolean containsIgnoreField(String fieldName);

    /**
     * 用户自定义，根据属性名设置默认值
     *
     * @param fieldName 属性名
     * @param value     为该属性设置的默认值
     * @return Configuration
     */
    Configuration setUserDefaultFieldValueConfig(String fieldName, Object value);

    /**
     * 用户为该属性设置的默认值
     *
     * @param fieldName 属性名
     * @return 默认值
     */
    Object getUserDefaultFieldValue(String fieldName);

    /**
     * 用户是否单独配置了该属性
     *
     * @param fieldName 需要为全类名的字符串形式，例如List.class.getName()
     * @return boolean
     */
    boolean containsUserFieldValueConfig(String fieldName);
}
