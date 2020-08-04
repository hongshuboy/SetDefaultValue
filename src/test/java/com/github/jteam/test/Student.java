package com.github.jteam.test;

import lombok.*;

/**
 * @author wp
 * 2020-08-03 14:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private Integer age;
    private String name;
    private Integer[] ids;
    private int num;

}
