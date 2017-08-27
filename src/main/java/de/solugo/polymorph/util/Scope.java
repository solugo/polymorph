package de.solugo.polymorph.util;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Scope {

    private final Map<String, Object> properties = new HashMap<>();

    public Scope() {
        this( null);
    }

    public Scope( final Scope parent) {
        this.parent = parent;
    }

    @Getter
    private final Scope parent;

    @Getter
    @Setter
    private Type sourceType;

    @Getter
    @Setter
    private Object sourceValue;

    @Getter
    @Setter
    private Path sourcePath;

    @Getter
    @Setter
    private Type targetType;

    @Getter
    @Setter
    private Object targetValue;

    @Getter
    @Setter
    private Path targetPath;

    @SuppressWarnings("unchecked")
    public <T> T getProperty(@NotNull final String name) {
        return (T) this.properties.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(@NotNull final Class<T> clazz) {
        return (T) this.properties.get(clazz.getName());
    }

    public void setProperty(@NotNull final String name, final Object value) {
        this.properties.put(name, value);
    }

    public void setProperty(@NotNull final Object value) {
        this.properties.put(value.getClass().getName(), value);
    }

}
