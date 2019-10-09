package ru.otus.collections;


import java.util.*;
import java.util.function.Consumer;

public class DIYArrayList<E> implements List<E> {

    private int size = 0;
    private Object[] elementData = {};
    private transient int modCount = 0;
    private static final int DEFAULT_CAPACITY = 10;

    static <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }

    DIYArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public E get(int index) {
        return (E) elementData[index];
    }


    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }


    public void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        Arrays.sort((E[]) elementData, 0, size, c);
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
        modCount++;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public  ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, this.size + (this.size >> 1));
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = DIYArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                DIYArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            final int size = DIYArrayList.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] es = elementData;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept(elementAt(es, i));
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * An optimized version of AbstractList.ListItr
     */
    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = DIYArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                DIYArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                DIYArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

