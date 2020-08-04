package com.github.jteam.comfiguration;

import com.github.jteam.value.Type;

import java.util.HashMap;

/**
 * 配置类，用于配置框架为各类型赋值时的默认值，若对默认值没有特殊要求，请使用<code>SINGLE_CONFIGURATION</code><br/>
 * 若有特殊要求，如Integer为null时，希望框架自动赋值为1（默认为0），可以使用 {@link #setDefaultConfig(Type, Object)}
 *
 * @author hongshuboy
 * 2020-08-03 12:55
 */
public class Configuration {
    public static final Configuration SINGLE_CONFIGURATION = new Configuration(false);
    private Boolean modify;//是否可以被修改
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
    }

    /**
     * 设置默认值
     *
     * @param type  使用Type枚举类设置，如Type.Integer
     * @param value 为Type设置的默认值
     */
    public void setDefaultConfig(Type type, Object value) {
        modifiable();
        configMap.put(type.getType(), value);
    }

    /**
     * 获取为某一属性设置的默认值
     *
     * @param type 需要查看的属性，如Type.Integer
     * @return 为该属性设置的默认值
     */
    public Object getDefaultValue(Type type) {
        return configMap.get(type.getType());
    }

    /**
     * 获取为某一属性设置的默认值，用户请使用 {@link #getDefaultValue(Type)}
     *
     * @param type 需要查看的属性
     * @return 为该属性设置的默认值
     */
    public Object getDefaultValue(String type) {
        return configMap.get(type);
    }

    private void modifiable() {
        if (!modify) {
            throw new RuntimeException("single_configuration is not modifiable");
        }
    }

    public Configuration() {
        this.modify = true;
    }

    public Configuration(Boolean modify) {
        this.modify = modify;
    }
}
