package com.github.jteam.value.core;

import java.lang.annotation.*;

/**
 * ʹ��ע��ֱ�Ӷ���������Ĭ��ֵ�����ȼ����[�ݲ�֧��]
 *
 * @author hongshuboy
 * Date: date  2021/1/13 ���� 4:46
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValue {
    String value();
}