package de.solugo.polymorph.util;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderedList<T> {

    protected List<T> list = new ArrayList<>();

    public void add(final T item) {
        this.addBefore(item, (Class) null);
    }

    public void addBefore(@NotNull final T item, final T target) {
        if (target != null) {
            for (int index = 0; index < this.list.size(); index++) {
                if (this.list.get(index) == target) {
                    this.list.add(index, item);
                    return;
                }
            }
        }

        this.list.add(item);
        this.onChange();
    }

    public void addBefore(@NotNull final T item, final Class<T> clazz) {
        if (clazz != null) {
            for (int index = 0; index < this.list.size(); index++) {
                if (clazz.isAssignableFrom(this.list.get(index).getClass())) {
                    this.list.add(index, item);
                    return;
                }
            }
        }

        this.list.add(item);
        this.onChange();
    }

    public void addAfter(@NotNull final T item, final T target) {
        if (target != null) {
            for (int index = this.list.size() - 2; index >= 0; index--) {
                if (this.list.get(index) == target) {
                    this.list.add(index, item);
                    return;
                }
            }
        }

        this.list.add(0, item);
        this.onChange();
    }

    public void addAfter(@NotNull final T item, final Class<T> clazz) {
        if (clazz != null) {
            for (int index = this.list.size() - 2; index >= 0; index--) {
                if (clazz.isAssignableFrom(this.list.get(index).getClass())) {
                    this.list.add(index, item);
                    return;
                }
            }
        }

        this.list.add(0, item);
        this.onChange();
    }

    public void remove(final T item) {
        this.list.remove(item);
        this.onChange();
    }

    public void remove(final Class<T> clazz) {
        final Iterator<T> iterator = this.list.iterator();
        while (iterator.hasNext()) {
            if (clazz.isAssignableFrom(iterator.next().getClass())) {
                iterator.remove();
            }
        }
        this.onChange();
    }

    protected void onChange() {
    }

}
