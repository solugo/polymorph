package de.solugo.polymorph.converter;

import de.solugo.polymorph.Mapper;
import de.solugo.polymorph.util.Scope;

import java.lang.reflect.Type;

import static de.solugo.polymorph.util.TypeUtil.isAssignable;

public abstract class BidirectionalConverter<S, T> extends Converter<S, T, Object> {

    public BidirectionalConverter() {
    }

    public BidirectionalConverter(Type sourceType, Type targetType) {
        super(sourceType, targetType);
    }

    @Override
    public boolean applies(final Type sourceType, final Type targetType) {
        boolean applies = false;
        applies = applies || isAssignable(this.sourceType, sourceType) && isAssignable(this.targetType, targetType);
        applies = applies || isAssignable(this.sourceType, targetType) && isAssignable(this.targetType, sourceType);
        return applies;
    }

    @Override
    public Object convert(final Mapper mapper, final Scope scope) {
        if (scope.getSourceType() == this.sourceType) {
            return this.convertStoT(mapper, scope);
        } else {
            return this.convertTtoS(mapper, scope);
        }
    }

    public abstract T convertStoT(final Mapper mapper, final Scope scope);

    public abstract S convertTtoS(final Mapper mapper, final Scope scope);

}
