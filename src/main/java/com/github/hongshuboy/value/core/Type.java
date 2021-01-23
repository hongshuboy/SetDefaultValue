package com.github.hongshuboy.value.core;

/**
 * 支持的类型枚举
 *
 * @author hongshuboy
 * 2020-08-03 13:53
 */
public enum Type {

    /**
     * byte
     * @see Type#BYTE
     */
    BYTE_(byte.class.getName()),
    /**
     * boolean
     * @see Type#BOOLEAN
     */
    BOOLEAN_(boolean.class.getName()),
    /**
     * char
     * @see Type#CHARACTER
     */
    CHAR_(char.class.getName()),
    /**
     * short
     * @see Type#SHORT
     */
    SHORT_(short.class.getName()),
    /**
     * int
     * @see Type#INTEGER
     */
    INT_(int.class.getName()),
    /**
     * long
     * @see Type#LONG
     */
    LONG_(long.class.getName()),
    /**
     * float
     * @see Type#FLOAT
     */
    FLOAT_(float.class.getName()),
    /**
     * double
     * @see Type#DOUBLE
     */
    DOUBLE_(double.class.getName()),

    /**
     * BYTE
     * @see Type#BYTE_
     */
    BYTE(Byte.class.getName()),
    /**
     * BOOLEAN
     * @see Type#BOOLEAN_
     */
    BOOLEAN(Boolean.class.getName()),
    /**
     * CHARACTER
     * @see Type#CHAR_
     */
    CHARACTER(Character.class.getName()),
    /**
     * SHORT
     * @see Type#SHORT_
     */
    SHORT(Short.class.getName()),
    /**
     * INTEGER
     * @see Type#INT_
     */
    INTEGER(Integer.class.getName()),
    /**
     * long
     * @see Type#LONG_
     */
    LONG(Long.class.getName()),
    /**
     * FLOAT
     * @see Type#FLOAT_
     */
    FLOAT(Float.class.getName()),
    /**
     * DOUBLE
     * @see Type#DOUBLE_
     */
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
    STRING_ARRAY(String[].class.getName()),
    //collection
    LIST(java.util.List.class.getName()),
    ARRAYLIST(java.util.ArrayList.class.getName()),
    LINKED_LIST(java.util.LinkedList.class.getName()),
    MAP(java.util.Map.class.getName()),
    HASH_MAP(java.util.HashMap.class.getName()),
    TREE_MAP(java.util.TreeMap.class.getName()),
    SET(java.util.Set.class.getName()),
    HASH_SET(java.util.HashSet.class.getName()),
    TREE_SET(java.util.TreeSet.class.getName()),
    SORTED_SET(java.util.SortedSet.class.getName());


    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
