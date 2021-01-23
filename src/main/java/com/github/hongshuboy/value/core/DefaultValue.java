package com.github.hongshuboy.value.core;

import java.lang.annotation.*;

/**
 * 注解配置方法, 暂时不支持 <br />
 * No support at the moment
 *
 * @author hongshuboy
 * Date: date  2021/1/13 下午 4:46
 */
@Deprecated
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValue {
    String value();
}
