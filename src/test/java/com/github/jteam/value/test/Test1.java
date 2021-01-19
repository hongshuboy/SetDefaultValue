package com.github.jteam.value.test;

import com.github.jteam.value.ValueUtils;
import com.github.jteam.value.configuration.Configuration;
import com.github.jteam.value.configuration.impl.HashConfiguration;
import com.github.jteam.value.core.Type;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 测试类
 *
 * @author hongshuboy
 * Date: 2020-08-04 08:40
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
        ValueUtils.setDefaultValue(student);

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
        configuration.setDefaultConfig(Type.LIST, Arrays.asList("tom", "mike"));

        ValueUtils.setDefaultValue(student, configuration);

        System.out.println(student);
    }

    /**
     * 设置忽略字段
     * 假如我们想对某些字段跳过，不让工具自动赋默认值，比如{XxxEntity}实体类，因为保存到数据库id自增的关系
     * 我们希望id字段能保持为null，不要自动赋值，就可以使用{@link Configuration#setIgnoreFields(String...)}
     * <p>
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
        /*
         *  这里虽然设置的是ids和schOOlNamE字段，但实际比较的是ids和schoolName（会自动忽略大小写）
         *  同时，类中的字段在匹配时，也会被忽略大小写，所以这里可以完全不管大小写，均可匹配到属性
         */
        configuration.setIgnoreFields("id", "schOOlNamE");

        ValueUtils.setDefaultValue(student, configuration);

        System.out.println(student);
    }

    /**
     * 根据字段，设置特定的值
     * 字段名同样忽略大小写
     * - [1.2新增]：支持对基础类型赋值
     */
    @Test
    public void test4() {
        final Student student = new Student();

        final Configuration configuration = new HashConfiguration();
        //注意此处num的类型是基础类型int 这里同样支持
        configuration.setUserDefaultFieldValueConfig("num", 100);
        configuration.setUserDefaultFieldValueConfig("schOOlNamE", "Harvard University");

        ValueUtils.setDefaultValue(student, configuration);

        System.out.println(student);
    }

    /**
     * [1.2新增]
     * 集合类型默认使用反射生成新对象，避免后续操作时会互相影响
     */
    @Test
    public void test5() {
        //1.2新增测试字段List<String> family 和 friends类型一致
        final Student student = new Student();

        ValueUtils.setDefaultValue(student);

        System.out.println(student);

        student.getFriends().add("tom");
        student.getFamily().add("mike");

        //此处可以看到二者互相没有影响，正常使用
        System.out.println(student);
    }

    /**
     * [1.2新增]
     * 若用户自定义的值为Class，则会使用反射来生成，普通对象不做处理，直接使用
     */
    @Test
    public void test6() {
        final Student student = new Student();

        final Configuration configuration = new HashConfiguration();
//        configuration.setDefaultConfig(Type.LIST, new LinkedList<>());
        configuration.setDefaultConfig(Type.LIST, LinkedList.class);
        ValueUtils.setDefaultValue(student, configuration);

        student.getFriends().add("tom");
        student.getFamily().add("mike");

        System.out.println(student.getFamily().hashCode());
        System.out.println(student.getFriends().hashCode());
        System.out.println(student);
    }

    /**
     * [2.1新增]
     * 增加对继承的支持, 使用和以前一样, 毫无影响
     * Senior extends Student
     */
    @Test
    public void test7() {
        final Senior senior = new Senior();
        ValueUtils.setDefaultValue(senior);
        System.out.println(senior);
    }
}
