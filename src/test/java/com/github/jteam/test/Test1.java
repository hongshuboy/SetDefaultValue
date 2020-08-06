package com.github.jteam.test;

import com.github.jteam.JHelper;
import com.github.jteam.configuration.Configuration;
import com.github.jteam.configuration.impl.HashConfiguration;
import com.github.jteam.value.Type;
import org.junit.Test;

/**
 * @author wp
 * 2020-08-04 08:40
 */
public class Test1 {

    /**
     * 默认赋值方法
     * 最简单的使用方式，一行代码即可让对象的属性获得初始化的默认值
     */
    @Test
    public void test1() {
        final Student student = new Student();

//      自动赋值
        JHelper.setDefaultValue(student);

        System.out.println(student);
    }

    /**
     * 自定义默认值
     * 假如我们想对Integer类型设置的默认值是1，那么可以单独进行设置
     */
    @Test
    public void test2() {
        final Student student = new Student();

        final Configuration configuration = new HashConfiguration();
        configuration.setDefaultConfig(Type.INTEGER, 1);
        configuration.setDefaultConfig(Type.STRING, "hi");
        configuration.setDefaultConfig(Type.INTEGER_ARRAY, new Integer[]{1, 2});

        JHelper.setDefaultValue(student, configuration);

        System.out.println(student);
    }

    /**
     * 设置忽略字段
     * 假如我们想对某些字段跳过，不让工具自动赋默认值，比如{XxxEntity}实体类，因为保存到数据库id自增的关系
     * 我们希望id字段能保持为null，不要自动赋值，就可以使用{@link Configuration#setIgnoreFields(String...)}
     *
     * 1.设置INTEGER类型赋值为1（默认框架赋值为0）
     * 2.自动赋值时，忽略ids和schoolNamE两个字段（可写1至多个字段，自动会忽略大小写进行匹配）
     */
    @Test
    public void test3() {
        final Student student = new Student();

        final Configuration configuration = new HashConfiguration();
        configuration.setDefaultConfig(Type.INTEGER, 1);
        configuration.setDefaultConfig(Type.STRING, "hi");
        configuration.setDefaultConfig(Type.INTEGER_ARRAY, new Integer[]{1, 2});
        /**
         *  这里虽然设置的是ids和schOOlNamE字段，但实际比较的是ids和schoolName（会自动忽略大小写）
         *  同时，类中的字段在匹配时，也会被忽略大小写，所以这里可以完全不管大小写，均可匹配到属性
         */
        configuration.setIgnoreFields("id", "schOOlNamE");

        JHelper.setDefaultValue(student, configuration);

        System.out.println(student);
    }
}
