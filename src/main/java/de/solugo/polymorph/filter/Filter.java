package de.solugo.polymorph.filter;

import de.solugo.polymorph.Mapper;
import de.solugo.polymorph.util.FilterChain;
import de.solugo.polymorph.util.Scope;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class Filter {

    private final Type sourceType;
    private final Type targetType;

    public Filter() {
        final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.sourceType = type.getActualTypeArguments()[0];
        this.targetType = type.getActualTypeArguments()[1];
    }

    public Filter(final Type sourceType, final Type targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }


    public boolean applies(final Type sourceType, final Type targetType) {
        return this.sourceType == sourceType && this.targetType == targetType;
    }

    public abstract <T> T filter(final Mapper mapper, final Scope scope, final FilterChain chain);

}
