package com.github.jteam.value.test;

/**
 * @author wangpeng
 * date  2021/1/11 обнГ 1:37
 */
public class Senior extends Student {
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Senior{" +
                "grade='" + grade + '\'' +
                '}' + super.toString();
    }
}
