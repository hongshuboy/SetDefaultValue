package com.github.jteam.value;

/**
 * @author hongshuboy
 * 2020-08-03 13:53
 */
public enum Type {
    BYTE(Byte.class.getName()),
    BOOLEAN(Boolean.class.getName()),
    CHARACTER(Character.class.getName()),
    SHORT(Short.class.getName()),
    INTEGER(Integer.class.getName()),
    FLOAT(Float.class.getName()),
    LONG(Long.class.getName()),
    DOUBLE(Double.class.getName()),
    STRING(String.class.getName()),
    //array
    BYTE_ARRAY(Byte[].class.getName()),
    BOOLEAN_ARRAY(Boolean[].class.getName()),
    CHARACTER_ARRAY(Character[].class.getName()),
    SHORT_ARRAY(Short[].class.getName()),
    INTEGER_ARRAY(Integer[].class.getName()),
    FLOAT_ARRAY(Float[].class.getName()),
    LONG_ARRAY(Long[].class.getName()),
    DOUBLE_ARRAY(Double[].class.getName()),
    STRING_ARRAY(String[].class.getName());

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
