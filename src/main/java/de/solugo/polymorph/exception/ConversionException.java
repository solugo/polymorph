package de.solugo.polymorph.exception;

import java.lang.reflect.Type;

public class ConversionException extends RuntimeException {
    public ConversionException(final Type sourceType, final Type targetType) {
        super(String.format(
                "Could not convert %s to %s",
                sourceType.getTypeName(),
                targetType.getTypeName()
        ));
    }
}
