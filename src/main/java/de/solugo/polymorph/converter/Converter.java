package de.solugo.polymorph.converter;

import de.solugo.polymorph.Mapper;
import de.solugo.polymorph.util.Scope;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static de.solugo.polymorph.util.TypeUtil.isAssignable;

public abstract class Converter<S, T, R> {

    protected final Type sourceType;
    protected final Type targetType;

    public Converter() {
        final ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
        this.sourceType = type.getActualTypeArguments()[0];
        this.targetType = type.getActualTypeArguments()[1];
    }

    public Converter(final Type sourceType, final Type targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }


    public boolean applies(final Type sourceType, final Type targetType) {
        return isAssignable(this.sourceType, sourceType) && isAssignable(this.targetType, targetType);
    }

    public abstract R convert(final Mapper mapper, final Scope scope);

}
