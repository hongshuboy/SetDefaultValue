package com.github.jteam.value.core;

import java.lang.annotation.*;

/**
 * 使用注解直接对属性设置默认值，优先级最高
 * @author hongshuboy
 * date  2021/1/13 下午 4:46
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValue {
    String value();
}
