package de.solugo.polymorph.util;

import de.solugo.polymorph.converter.Converter;

import java.lang.reflect.Type;

public class ConverterList extends ConversionAwareList<Converter, Converter> {

    @Override
    protected Converter findInternal(Type sourceType, Type targetType) {
        for (final Converter converter :  this.list) {
            if (converter.applies(sourceType, targetType)) {
                return converter;
            }
        }
        return null;
    }
}
