package com.github.jteam.test;

import com.github.jteam.comfiguration.Configuration;
import com.github.jteam.JHelper;
import com.github.jteam.value.Type;
import org.junit.Test;

/**
 * @author wp
 * 2020-08-04 08:40
 */
public class Test1 {

    @Test
    public void test1() {
        final Student student = new Student();

        JHelper.setDefaultValue(student, Configuration.SINGLE_CONFIGURATION);

        System.out.println(student);
    }

    @Test
    public void test2() {
        final Student student = new Student();

        final Configuration configuration = new Configuration();
        configuration.setDefaultConfig(Type.INTEGER, 1);
        configuration.setDefaultConfig(Type.STRING, "hi");
        configuration.setDefaultConfig(Type.INTEGER_ARRAY, new Integer[]{1});

        JHelper.setDefaultValue(student, configuration);

        System.out.println(student);
    }
}
