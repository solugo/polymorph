package de.solugo.polymorph.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeUtil {

    public static boolean isAssignable(final Type source, final Type target) {
        Class currentSource = TypeUtil.getRawType(source);
        Class currentTarget = TypeUtil.getRawType(target);

        while (currentTarget != currentSource) {
            if (currentTarget == currentSource) {
                return true;
            } else if (currentTarget != Object.class) {
                currentTarget = currentTarget.getSuperclass();
            } else {
                return false;
            }
        }

        final Type[] sourceArguments = TypeUtil.getArguments(source);
        final Type[] targetArguments = TypeUtil.getArguments(target);

        if (sourceArguments.length != targetArguments.length) {
            return false;
        }

        for (int index =0 ; index < sourceArguments.length; index++) {
            if (!TypeUtil.isAssignable(sourceArguments[index], targetArguments[index])) {
                return false;
            }
        }

        return true;
    }

    public static Type[] getArguments(final Type type) {
        if (type instanceof ParameterizedType) {
            return ParameterizedType.class.cast(type).getActualTypeArguments();
        }
        return new Type[0];
    }

    public static Class getRawType(final Type type) {
        if (type instanceof Class) {
            return Class.class.cast(type);
        }
        if (type instanceof ParameterizedType) {
            return TypeUtil.getRawType(ParameterizedType.class.cast(type).getRawType());
        }
        throw new RuntimeException(String.format("Could not identify raw type for %s", type));
    }

}
