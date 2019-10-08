package ru.otus.collections;


import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private int size = 0;
    private Object elementData[] = {};
    protected transient int modCount = 0;

    public DIYArrayList() {
        this.elementData = new Object[100];
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
        elementData[size++] = e;
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
        throw new UnsupportedOperationException("not yet implemented");
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
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public static void main(String[] args) {
        List<String> arrlist = new DIYArrayList<>();
        arrlist.add("B");
        arrlist.add("A");
        arrlist.add("D");
        arrlist.add("C");
        for(int i = 0; i < arrlist.size(); i++) {
            System.out.println(arrlist.get(i));
        }
        System.out.println("----------------");

        Collections.addAll(arrlist, "BB","AA", "CC", "DD");
        for(int i = 0; i < arrlist.size(); i++) {
            System.out.println(arrlist.get(i));
        }
        System.out.println("----------------");

        List<String> arrlist2 = new DIYArrayList<>();
        for(int i = 0; i<21; i++) {
            arrlist2.add("element" + i);
        }
        for(int i = 0; i < arrlist2.size(); i++) {
            System.out.println(arrlist2.get(i));
        }
        System.out.println("----------------");

        Collections.copy(arrlist2, arrlist);
        for(int i = 0; i < arrlist2.size(); i++) {
            System.out.println(arrlist2.get(i));
        }
        System.out.println("----------------");

        Collections.sort(arrlist2, Collections.reverseOrder());
        for(int i = 0; i < arrlist2.size(); i++) {
            System.out.println(arrlist2.get(i));
        }
        System.out.println("----------------");

        arrlist.remove(1);
    }
}

