package de.solugo.polymorph.converter;

import de.solugo.polymorph.Mapper;
import de.solugo.polymorph.exception.ConversionException;
import de.solugo.polymorph.util.Scope;

import java.lang.reflect.Type;

public class StringAndNumberConverter extends BidirectionalConverter<String, Number> {

    @Override
    public Number convertStoT(final Mapper mapper, final Scope scope) {
        final Type type = scope.getTargetType();
        final Object value = scope.getSourceValue();
        if (value != null) {
            if (type == Integer.class) {
                return Integer.valueOf(String.class.cast(value));
            }
            if (type == Long.class) {
                return Long.valueOf(String.class.cast(value));
            }
            if (type == Float.class) {
                return Float.valueOf(String.class.cast(value));
            }
            if (type == Double.class) {
                return Double.valueOf(String.class.cast(value));
            }
            if (type == Byte.class) {
                return Byte.valueOf(String.class.cast(value));
            }
            throw new ConversionException(scope.getSourceType(), scope.getTargetType());
        } else {
            return null;
        }
    }

    @Override
    public String convertTtoS(final Mapper mapper, final Scope scope) {
        if (scope.getSourceValue() != null) {
            return scope.getSourceValue().toString();
        } else {
            return null;
        }
    }

}
