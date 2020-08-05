package com.github.jteam.configuration.impl;

import com.github.jteam.configuration.Configuration;
import com.github.jteam.value.Type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wp
 * 2020-08-05 12:37
 */
public class HashConfiguration implements Configuration {
    public static final Configuration SINGLE_CONFIGURATION = new HashConfiguration(false);
    private final boolean modify;//是否可以被修改
    private static final String IGNORE_FIELD = "ignoreField";
    private final HashMap<String, Object> configMap = new HashMap<>();

    {
        configMap.put(Type.BYTE.getType(), 0b00);
        configMap.put(Type.BOOLEAN.getType(), false);
        configMap.put(Type.CHARACTER.getType(), ' ');
        configMap.put(Type.SHORT.getType(), 0);
        configMap.put(Type.INTEGER.getType(), 0);
        configMap.put(Type.FLOAT.getType(), 0f);
        configMap.put(Type.LONG.getType(), 0L);
        configMap.put(Type.DOUBLE.getType(), 0.0);
        configMap.put(Type.STRING.getType(), "");
        //数组
        configMap.put(Type.BYTE_ARRAY.getType(), new Byte[0]);
        configMap.put(Type.BOOLEAN_ARRAY.getType(), new Boolean[0]);
        configMap.put(Type.CHARACTER_ARRAY.getType(), new Character[0]);
        configMap.put(Type.SHORT_ARRAY.getType(), new Short[0]);
        configMap.put(Type.INTEGER_ARRAY.getType(), new Integer[0]);
        configMap.put(Type.FLOAT_ARRAY.getType(), new Float[0]);
        configMap.put(Type.LONG_ARRAY.getType(), new Long[0]);
        configMap.put(Type.DOUBLE_ARRAY.getType(), new Double[0]);
        configMap.put(Type.STRING_ARRAY.getType(), new String[0]);
        configMap.put(IGNORE_FIELD, new HashSet<String>());
    }

    /**
     * 设置默认值
     *
     * @param type  使用Type枚举类设置，如Type.Integer
     * @param value 为Type设置的默认值
     */
    @Override
    public Configuration setDefaultConfig(Type type, Object value) {
        modifiable();
        configMap.put(type.getType(), value);
        return this;
    }

    /**
     * 获取为某一属性设置的默认值
     *
     * @param type 需要查看的属性，如Type.Integer
     * @return 为该属性设置的默认值
     */
    @Override
    public Object getDefaultValue(Type type) {
        return configMap.get(type.getType());
    }

    /**
     * 获取为某一属性设置的默认值，用户请使用 {@link #getDefaultValue(Type)}
     *
     * @param type 需要查看的属性
     * @return 为该属性设置的默认值
     */
    @Override
    public Object getDefaultValue(String type) {
        return configMap.get(type);
    }

    private void modifiable() {
        if (!modify) {
            throw new RuntimeException("single_configuration is not modifiable");
        }
    }

    public HashConfiguration() {
        this.modify = true;
    }

    public HashConfiguration(Boolean modify) {
        this.modify = modify;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<String> getIgnoreFields() {
        return (Set<String>) configMap.get(IGNORE_FIELD);
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
        modifiable();
        Set<String> ignoreKeys = (Set<String>) configMap.get(IGNORE_FIELD);
        ignoreKeys.addAll(Arrays.asList(fields));
        configMap.put(IGNORE_FIELD, ignoreKeys.stream().map(String::toLowerCase).collect(Collectors.toSet()));
        return this;
    }
}
