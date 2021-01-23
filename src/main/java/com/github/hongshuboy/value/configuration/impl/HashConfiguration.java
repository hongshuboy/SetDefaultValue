package com.github.hongshuboy.value.configuration.impl;

import com.github.hongshuboy.value.core.SimpleConfigTemplate;
import com.github.hongshuboy.value.configuration.Configuration;
import com.github.hongshuboy.value.core.Type;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Hash Configuration
 *
 * @author hongshuboy
 * Date: 2020-08-05 12:37
 */
public class HashConfiguration implements Configuration {
    /**
     * Type Config Map
     * key  ->  type        value  ->  object or class
     */
    protected final Map<String, Object> configMap = new HashMap<>();
    /**
     * FieldName Config Map
     * key  ->  fileName    value  ->  object or class
     */
    private final Map<String, Object> userConfigFieldMap = new HashMap<>();
    /**
     * ignore field name set
     */
    private Set<String> ignoreSet = new HashSet<>();

    {
        initConfig();
        addConfig();
    }

    //you can override this method to set your configuration
    protected void addConfig() {
        //  configMap.put(xxxType, xxxDefaultValue);
    }

    //can be override
    protected void initConfig() {
        configMap.clear();
        configMap.put(Type.BYTE.getType(), 0b00);
        configMap.put(Type.BOOLEAN.getType(), false);
        configMap.put(Type.CHARACTER.getType(), 0);
        configMap.put(Type.SHORT.getType(), 0);
        configMap.put(Type.INTEGER.getType(), 0);
        configMap.put(Type.FLOAT.getType(), 0f);
        configMap.put(Type.LONG.getType(), 0L);
        configMap.put(Type.DOUBLE.getType(), 0.0);
        configMap.put(Type.STRING.getType(), "");
        //array
        SimpleConfigTemplate.arrayConfig(configMap);
        //collection
        SimpleConfigTemplate.collectionConfig(configMap);
    }


    /**
     * 设置默认值
     *
     * @param type  使用Type枚举类设置，如Type.Integer
     * @param value 为Type设置的默认值
     */
    @Override
    public Configuration setDefaultConfig(Type type, Object value) {
        configMap.put(type.getType(), value);
        return this;
    }

    @Override
    public Configuration setDefaultConfig(String type, Object value) {
        configMap.put(type, value);
        return this;
    }

    /**
     * 获取为某一属性设置的默认值
     *
     * @param type 需要查看的属性
     * @return 为该属性设置的默认值
     */
    @Override
    public Object getDefaultValue(String type) {
        return configMap.get(type);
    }

    public HashConfiguration() {
    }

    @Override
    public boolean containsIgnoreField(String fieldName) {
        return ignoreSet.contains(fieldName.toLowerCase());
    }

    /**
     * 设置需要跳过的字段名（将自动忽略大小写）
     *
     * @param fields 如name，则将不对name字段赋默认值
     * @return Configuration
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public Configuration setIgnoreFields(String... fields) {
        ignoreSet.addAll(Arrays.asList(fields));
        ignoreSet = ignoreSet.stream().map(String::toLowerCase).collect(Collectors.toSet());
        return this;
    }

    @Override
    public Configuration setDefaultValueByFieldName(String fieldName, Object value) {
        userConfigFieldMap.put(fieldName.toLowerCase(), value);
        return this;
    }

    @Override
    public Object getDefaultValueByFieldName(String fieldName) {
        return userConfigFieldMap.get(fieldName.toLowerCase());
    }

    @Override
    public boolean containsFieldNameConfig(String fieldName) {
        return userConfigFieldMap.containsKey(fieldName.toLowerCase());
    }
}
