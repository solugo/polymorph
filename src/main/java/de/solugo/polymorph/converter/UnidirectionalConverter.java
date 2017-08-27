package de.solugo.polymorph.converter;

import java.lang.reflect.Type;

public abstract class UnidirectionalConverter<S, T> extends Converter<S, T, T> {

    public UnidirectionalConverter() {
    }

    public UnidirectionalConverter(final Type sourceType, final Type targetType) {
        super(sourceType, targetType);
    }

}
