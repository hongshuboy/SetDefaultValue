package com.github.jteam.value.core;

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