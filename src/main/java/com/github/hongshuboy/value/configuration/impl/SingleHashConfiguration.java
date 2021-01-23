package com.github.hongshuboy.value.configuration.impl;

import com.github.hongshuboy.value.configuration.Configuration;
import com.github.hongshuboy.value.core.Type;

import java.util.concurrent.locks.ReentrantLock;

/**
 * SingleHashConfiguration 单例模式的默认配置类
 *
 * @author hongshuboy
 * Date: 2020/10/13 上午 11:36
 */
public final class SingleHashConfiguration extends HashConfiguration {
    private static final String SINGLE_CONFIGURATION_IS_NOT_MODIFIABLE = "single_configuration is not modifiable";
    private static SingleHashConfiguration singleHashConfiguration = null;
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    protected void addConfig() {
    }


    @Override
    public Configuration setDefaultConfig(Type type, Object value) {
        throw new RuntimeException(SINGLE_CONFIGURATION_IS_NOT_MODIFIABLE);
    }

    @Override
    public Configuration setIgnoreFields(String... fields) {
        throw new RuntimeException(SINGLE_CONFIGURATION_IS_NOT_MODIFIABLE);
    }

    public static SingleHashConfiguration getInstance() {
        if (singleHashConfiguration == null) {
            lock.lock();
            try {
                if (singleHashConfiguration == null) {
                    singleHashConfiguration = new SingleHashConfiguration();
                }
            } finally {
                lock.unlock();
            }
        }
        return singleHashConfiguration;
    }

    private SingleHashConfiguration() {
    }
}
