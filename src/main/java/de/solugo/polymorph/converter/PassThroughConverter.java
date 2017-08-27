package de.solugo.polymorph.converter;

import de.solugo.polymorph.Mapper;
import de.solugo.polymorph.util.Scope;

import java.lang.reflect.Type;

public class PassThroughConverter<T> extends UnidirectionalConverter<T, T> {

    public PassThroughConverter(final Type type) {
        super(type, type);
    }

    @Override
    public T convert(final Mapper mapper, final Scope scope) {
        return (T) scope.getSourceValue();
    }

}
