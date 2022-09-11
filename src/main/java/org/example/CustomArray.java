package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Custom array for training
 */
public class CustomArray <T> implements CustomArrayI <T> {
    private int size;
    private T[] elementData;

    public CustomArray() {

        this.elementData = (T[]) new Object[]{};
        this.size = 0;
    }

    public CustomArray(int capacity) {

        this.elementData = (T[]) new Object[capacity];
        this.size = 0;
    }

    public CustomArray(Collection<T> c) {

        Object[] array = c.toArray();
        this.size = array.length;
        this.elementData = (T[]) Arrays.copyOf(array, size);
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
    public boolean add(T item) {

        ensureCapacity(size + 1);
        elementData[size++] = item;
        return true;
    }

    @Override
    public boolean addAll(T[] items) {

        checkItems(Arrays.asList(items));
        ensureCapacity(size + items.length);

        for (T item : items) {
            add(item);
        }

        return items.length != 0;
    }

    @Override
    public boolean addAll(Collection<T> items) {

        checkItems(items);

        Object[] array = items.toArray();
        ensureCapacity(size + array.length);
        System.arraycopy(array, 0, elementData, size, array.length);
        size += array.length;
        return array.length != 0;
    }

    @Override
    public boolean addAll(int index, T[] items) {

        checkIndex(index);
        checkItems(Arrays.asList(items));

        ensureCapacity(size + items.length);

        if ((size - index) > 0)
            System.arraycopy(elementData, index, elementData, index + items.length, (size - index));
        System.arraycopy(items, 0, elementData, index, items.length);
        size += items.length;
        return items.length != 0;
    }

    @Override
    public T get(int index) {

        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public T set(int index, T item) {

        checkIndex(index);

        T oldValue = (T) elementData[index];
        elementData[index] = item;
        return oldValue;
    }

    @Override
    public void remove(int index) {

        checkIndex(index);
        fastRemove(index);
    }

    @Override
    public boolean remove(T item) {

        if (item == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (item.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(T item) {

        if (item == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (item.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {

        int capacity = elementData.length;
        if (capacity - newElementsCount < 0)
            capacity = newElementsCount;
        elementData = Arrays.copyOf(elementData, capacity);
    }

    @Override
    public int getSize() {

        return elementData.length;
    }

    @Override
    public void reverse() {
        for (int i = 0, mid = size / 2, j = size - 1; i < mid; i++, j--) {
            List<T> list = Arrays.asList(elementData);
            list.set(i, list.set(j, list.get(i)));
        }
    }

    @Override
    public Object[] toArray() {

        return Arrays.copyOf(elementData, size);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[ ");
        for (int i = 0; i < size; ++i) {
            stringBuilder.append(elementData[i]);
            stringBuilder.append(" ");
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {

        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds: " + index + " Size " + size);
    }

    private void checkItems(Collection<T> items) {

        if (items.isEmpty())
            throw new IllegalArgumentException("You entered illegal argument: " + items);
    }

    private void fastRemove(int index) {

        int capacity = size - index - 1;
        if (capacity > 0)
            System.arraycopy(elementData, index + 1, elementData, index, capacity);
        elementData[--size] = null;

    }
}
