package com.github.jteam.value.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangpeng
 * date  2021/1/12 ÉÏÎç 10:28
 */
public class DevTest {
    @Test
    public void test1() throws Exception{
        final Senior senior = new Senior();
        senior.setId(1);
        System.out.println("senior = " + senior);
        final Class<Senior> seniorClass = Senior.class;
        final Class<? super Senior> superclass = seniorClass.getSuperclass();
        final Method getId = superclass.getDeclaredMethod("getId");
        System.out.println("getId.invoke(senior) = " + getId.invoke(senior));
    }

    @Test
    public void test2() throws Exception{
        final Senior senior = new Senior();
        final Class<Senior> seniorClass = Senior.class;
        final Field grade = seniorClass.getDeclaredField("grade");
        System.out.println("grade.getName() = " + grade.getName());
    }
}
