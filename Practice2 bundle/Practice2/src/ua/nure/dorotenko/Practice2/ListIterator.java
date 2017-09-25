package ua.nure.dorotenko.Practice2;

import java.util.Iterator;

public interface ListIterator extends Iterator<Object> {
    boolean hasPrevious();
    Object previous();
    void set(Object e);
    void remove();
}
