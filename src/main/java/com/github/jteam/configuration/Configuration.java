package com.github.jteam.configuration;

import com.github.jteam.value.Type;

import java.util.Set;

/**
 * @author wp
 * 2020-08-05 12:39
 */
public interface Configuration {
    Configuration setDefaultConfig(Type type, Object value);
    Object getDefaultValue(Type type);
    Object getDefaultValue(String type);
    Configuration setIgnoreFields(String... fields);
    Set<String> getIgnoreFields();
}
