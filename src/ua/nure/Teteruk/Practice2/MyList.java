package ua.nure.Teteruk.Practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface MyList extends Iterable<Object> {
    void add(Object e);

    void clear();

    boolean remove(Object o);

    Object[] toArray();

    int size();

    boolean contains(Object o);

    boolean containsAll(MyList c);
}