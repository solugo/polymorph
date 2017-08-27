package de.solugo.polymorph.util;

import de.solugo.polymorph.filter.Filter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FilterList extends ConversionAwareList<Filter, List<Filter>> {
    private final List<Filter> list = new ArrayList<>();

    @Override
    protected List<Filter> findInternal(Type sourceType, Type targetType) {
        final List<Filter> result = new ArrayList<>();
        for (final Filter filter : this.list) {
            if (filter.applies(sourceType, targetType)) {
                result.add(filter);
            }
        }
        return result;
    }

}
