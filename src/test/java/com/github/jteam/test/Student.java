package com.github.jteam.test;

import java.util.Arrays;

/**
 * 测试类，注意num字段是基本类型，本身就有默认值，本工具不会对基本类型做任何操作
 * @author wp
 * 2020-08-03 14:25
 */
public class Student {
    private Integer age;
    private String name;
    private Integer[] ids;
    private String schoolName;
    private int num;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Student() {
    }

    public Student(Integer age, String name, Integer[] ids, String schoolName, int num) {
        this.age = age;
        this.name = name;
        this.ids = ids;
        this.schoolName = schoolName;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", ids=" + Arrays.toString(ids) +
                ", schoolName='" + schoolName + '\'' +
                ", num=" + num +
                '}';
    }
}
