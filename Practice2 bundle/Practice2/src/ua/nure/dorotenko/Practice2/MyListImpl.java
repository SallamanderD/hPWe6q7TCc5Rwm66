package ua.nure.dorotenko.Practice2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl implements MyList, ListIterable {
    private int count;
    private Object[] array = new Object[10];

    @Override
    public void add(Object e) {
        if (array.length == count) {
            array = Arrays.copyOf(array, count + 10);
        }
        array[count++] = e;
    }

    @Override
    public void clear() {
        array = new Object[10];
        count = 0;
    }

    @Override
    public boolean remove(Object o) {
        if(o == null){
            for (int i = 0; i < count; i++){
                if(array[i] == null){
                    System.arraycopy(array, i + 1, array, i, count - i - 1);
                    count--;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < count; i++){
                if(array[i].equals(o)){
                    System.arraycopy(array, i + 1, array, i, count - i - 1);
                    count--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, count);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < count; i++) {
            if (o == null) {
                if (array[i] == null) {
                    return true;
                }
            } else {
                if (array[i] == null) {
                    continue;
                }
                if (array[i].equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
        Object[] arr = c.toArray();
        for (int i = 0; i < count; i++) {
            for (int k = 0; k < c.size(); k++) {
                if (!this.contains(arr[k])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (count == 0) {
            return "[ ]";
        }
        for (int i = 0; i < count - 1; i++) {
            result.append(array[i] + ", ");
        }
        result.append(array[count - 1] + "]");
        return result.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        protected int cursor = -1;
        protected boolean allowEditing = true;

        public boolean hasNext() {
            if (cursor + 1 < count) {
                return true;
            }
            return false;
        }

        public Object next() {
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }
            allowEditing = false;
            return array[++cursor];
        }

        public void remove() {
            if (allowEditing) {
                throw new IllegalStateException();
            } else {
                MyListImpl.this.remove(array[cursor]);
                allowEditing = true;
                cursor--;
            }
        }
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {
        @Override
        public boolean hasPrevious() {
            if (cursor > -1) {
                return true;
            }
            return false;
        }

        @Override
        public Object previous() {
            if (!hasPrevious()) {
                throw new IllegalStateException();
            }
            allowEditing = false;
            return array[cursor--];
        }

        @Override
        public void set(Object e) {
            if (cursor == -1) {
                throw new IllegalStateException();
            }
            if (allowEditing) {
                throw new IllegalStateException();
            }
            array[cursor] = e;
            allowEditing = true;
        }
    }
}
