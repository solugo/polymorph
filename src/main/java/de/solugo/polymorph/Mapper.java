package de.solugo.polymorph;

import de.solugo.polymorph.converter.Converter;
import de.solugo.polymorph.converter.PassThroughConverter;
import de.solugo.polymorph.converter.StringAndNumberConverter;
import de.solugo.polymorph.util.ConverterList;
import de.solugo.polymorph.util.FilterChain;
import de.solugo.polymorph.util.FilterList;
import de.solugo.polymorph.util.Scope;
import lombok.Getter;

import java.lang.reflect.Type;

public class Mapper {

    public static Mapper createDefault() {
        final Mapper mapper = new Mapper();
        mapper.getConverters().add(new PassThroughConverter(String.class));
        mapper.getConverters().add(new PassThroughConverter(Integer.class));
        mapper.getConverters().add(new PassThroughConverter(Float.class));
        mapper.getConverters().add(new PassThroughConverter(Double.class));
        mapper.getConverters().add(new PassThroughConverter(Boolean.class));
        mapper.getConverters().add(new PassThroughConverter(Byte.class));
        mapper.getConverters().add(new PassThroughConverter(Byte.class));
        mapper.getConverters().add(new StringAndNumberConverter());
        return mapper;
    }

    @Getter
    private final FilterList filters = new FilterList();

    @Getter
    private final ConverterList converters = new ConverterList();

    public <T> T map(
            final Object sourceValue,
            final Type targetType
    ) {
        return this.map(sourceValue, null, null, targetType);
    }

    public <T> T map(
            final Object sourceValue,
            final T targetValue
    ) {
        return this.map(sourceValue, null, targetValue, null);
    }

    public <T> T map(
            final Object sourceValue,
            final Type sourceType,
            final T targetValue,
            final Type targetType
    ) {
        final Scope scope = new Scope();
        scope.setSourceValue(sourceValue);
        if (sourceType != null) {
            scope.setSourceType(sourceType);
        } else if (sourceValue != null) {
            scope.setSourceType(sourceValue.getClass());
        }
        scope.setTargetValue(targetValue);
        if (targetType != null) {
            scope.setTargetType(targetType);
        } else if (targetValue != null) {
            scope.setTargetType(targetValue.getClass());
        }
        return this.map(scope);
    }

    @SuppressWarnings("unchecked")
    public <T> T map(final Scope scope) {
        Type sourceType = scope.getSourceType();
        Type targetType = scope.getTargetType();

        final Converter converter = this.converters.find(sourceType, targetType);
        if (converter != null) {
            final FilterChain chain = new FilterChain(this.filters.find(sourceType, targetType), converter);
            return (T) chain.apply(this, scope);
        } else {
            throw new RuntimeException(String.format(
                    "Could not convert %s to %s",
                    sourceType.getTypeName(),
                    targetType.getTypeName())
            );
        }

    }


}
