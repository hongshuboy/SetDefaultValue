package com.github.jteam.value.core;

import java.util.*;

/**
 * @Author: hongshuboy
 * @Date: 2020/10/13 上午 11:26
 */
public class SimpleConfigTemplate {
    public static void arrayConfig(Map<String, Object> configMap) {
        //array
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
     * 使用反射方式的Collection Config
     * @param configMap
     */
    public static void collectionConfig(Map<String, Object> configMap) {
        configMap.put(Type.LIST.getType(), ArrayList.class);
        configMap.put(Type.ARRAYLIST.getType(), ArrayList.class);
        configMap.put(Type.LINKED_LIST.getType(), LinkedList.class);
        configMap.put(Type.MAP.getType(), TreeMap.class);
        configMap.put(Type.HASH_MAP.getType(), HashMap.class);
        configMap.put(Type.TREE_MAP.getType(), TreeMap.class);
        configMap.put(Type.SET.getType(), TreeSet.class);
        configMap.put(Type.SORTED_SET.getType(), TreeSet.class);
        configMap.put(Type.TREE_SET.getType(), TreeSet.class);
        configMap.put(Type.HASH_SET.getType(), HashSet.class);
    }

    /**
     * 单例方式的Collection Config（不推荐）<br />
     *    - 如果你不清楚这会造成什么问题，请不要手动改为使用这种方式<br />
     *    - 如果要使用单例空集合，推荐使用Collections.emptyList()等进行覆盖
     * @param configMap
     */
    public static void collectionConfigSingleton(Map<String, Object> configMap) {
        configMap.put(Type.LIST.getType(), new ArrayList<>());
        configMap.put(Type.ARRAYLIST.getType(), new ArrayList<>());
        configMap.put(Type.LINKED_LIST.getType(), new LinkedList<>());
        configMap.put(Type.MAP.getType(), new TreeMap<>());
        configMap.put(Type.HASH_MAP.getType(), new HashMap<>());
        configMap.put(Type.TREE_MAP.getType(), new TreeMap<>());
        configMap.put(Type.SET.getType(), new TreeSet<>());
        configMap.put(Type.SORTED_SET.getType(), new TreeSet<>());
        configMap.put(Type.TREE_SET.getType(), new TreeSet<>());
        configMap.put(Type.HASH_SET.getType(), new HashSet<>());
    }
}
