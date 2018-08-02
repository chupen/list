package ua.nure.Teteruk.Practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MyListImpl implements MyList, ListIterable {
    Object[] arr = new Object[0];

    public void add(Object e) {
        if (arr.length == 0) arr = new Object[1];
        else {
            Object[] temp = arr;
            arr = new Object[temp.length + 1];
            for (int i = 0; i < temp.length; i++) {
                arr[i] = temp[i];
            }
        }
        arr[arr.length - 1] = e;
    }

    public void clear() {
        arr = new Object[0];
    }

    public boolean remove(Object o) {
        boolean removed = false;
        int i = 0;
        for (; i < arr.length; i++) {
            if (o == null) {
                if (o == arr[i]) {
                    removed = true;
                    break;
                }
            } else {
                if (o.equals(arr[i])) {
                    removed = true;
                    break;
                }
            }
        }
        if (removed) {
            Object[] temp = arr;
            arr = new Object[temp.length - 1];
            for (int j = 0; j < i; j++) {
                arr[j] = temp[j];
            }
            for (; i < arr.length; i++) {
                arr[i] = temp[i + 1];
            }
        }
        return removed;
    }

    public Object[] toArray() {
        return arr;
    }

    public int size() {
        return arr.length;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == o) return true;
        }
        return false;
    }

    public boolean containsAll(MyList c) {
        for (int i = 0; i < c.toArray().length; i++) {
            if (!contains(c.toArray()[i])) return false;
        }
        return true;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            if (i != (arr.length - 1)) s += ", ";
        }
        s += "]";
        return s;
    }

    //        ~~~~~~~~~~~~~~~~~~~PART2~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        int cursor;
        int lastRet = -1;
        // returns true if the iteration has more elements

        public boolean hasNext() {
            return cursor != size();
        }
        // returns the next element in the iteration

        public Object next() {
            if (cursor >= size())
                throw new NoSuchElementException();
            cursor++;
            lastRet = cursor;
            return arr[cursor - 1];
        }

        // removes from the underlying collection the last element
        //returned by this iterator
        public void remove() {
            if (lastRet == -1)
                throw new IllegalStateException();
            Object[] temp = new Object[arr.length - 1];
            int i = 0;
            for (; i < arr.length - 1; i++) {
                if (i == lastRet - 1) break;
                temp[i] = arr[i];
            }
            for (; i < arr.length - 1; i++) {
                temp[i] = arr[i + 1];
            }
            arr = temp;
            lastRet = -1;
            cursor--;
        }
    }

    //        ~~~~~~~~~~~~PART3~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public Object previous() {
            if (cursor <= 0)
                throw new NoSuchElementException();
            cursor--;
            lastRet = cursor;
            return arr[cursor];
        }

        public void set(Object e) {
            if (lastRet == -1)
                throw new IllegalStateException();
            arr[lastRet] = e;
            lastRet = -1;
        }

        public void remove() {
            /*if (lastRet == -1)
                throw new IllegalStateException();
            Object[] temp = new Object[arr.length - 1];
            int i = 0;
            for (; i < arr.length - 1; i++) {
                if (i == lastRet - 1) break;
                temp[i] = arr[i];
            }
            for (; i < arr.length - 1; i++) {
                temp[i] = arr[i + 1];
            }
            arr = temp;
            lastRet = -1;*/
            super.remove();
            cursor+=2;
        }
    }
}
