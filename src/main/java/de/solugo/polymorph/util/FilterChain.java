package de.solugo.polymorph.util;

import de.solugo.polymorph.Mapper;
import de.solugo.polymorph.converter.Converter;
import de.solugo.polymorph.filter.Filter;

import java.util.Iterator;
import java.util.List;

public class FilterChain<T> {

    private final List<Filter> filters;
    private final Converter converter;

    public FilterChain(final List<Filter> filters, final Converter converter) {
        this.filters = filters;
        this.converter = converter;
    }

    public T apply(final Mapper mapper, final Scope scope) {
        if (!filters.isEmpty()) {
            final Iterator<Filter> iterator = this.filters.iterator();
            final T result = iterator.next().filter(mapper,scope, this);
            iterator.remove();
            return result;
        } else {
            return (T) converter.convert(mapper, scope);
        }
    }
}
