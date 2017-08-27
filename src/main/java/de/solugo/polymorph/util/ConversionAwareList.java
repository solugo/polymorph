package de.solugo.polymorph.util;

import lombok.Data;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class ConversionAwareList<T, R> extends OrderedList<T> {
    protected Map<Conversion, R> cache = new WeakHashMap<>();

    public R find(final Type sourceType, final Type targetType) {
        return this.cache.computeIfAbsent(
                new Conversion(sourceType, targetType),
                c -> this.findInternal(sourceType, targetType)
        );
    }

    @Override
    protected void onChange() {
        this.cache.clear();
    }

    protected abstract R findInternal(final Type sourceType, final Type targetType);

    @Data
    private class Conversion {
        private final Type sourceType;
        private final Type targetType;
    }
}
