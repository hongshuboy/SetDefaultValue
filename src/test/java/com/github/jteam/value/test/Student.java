package com.github.jteam.value.test;

import java.util.Arrays;
import java.util.List;

/**
 * 测试类，注意num字段是基本类型，本身就有默认值，本工具不会对基本类型做任何操作
 *
 * @author hongshuboy
 * 2020-08-03 14:25
 */
public class Student {
    private Integer id;
    private Integer age;
    private String name;
    private Integer[] ids;
    private String schoolName;
    private int num;
    private List<String> friends;
    private List<String> family;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getFamily() {
        return family;
    }

    public void setFamily(List<String> family) {
        this.family = family;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", ids=" + Arrays.toString(ids) +
                ", schoolName='" + schoolName + '\'' +
                ", num=" + num +
                ", friends=" + friends +
                ", family=" + family +
                '}';
    }
}
