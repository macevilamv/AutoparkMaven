package by.incubator.autopark.collections;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private Object[] buffer;
    private final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int capacity;

    public MyQueue() {
        capacity = DEFAULT_CAPACITY;
        buffer = new Object[DEFAULT_CAPACITY];
    }

    public MyQueue(int capacity) {
        this.capacity = capacity;
        Object[] buffer = new Object[capacity];
    }

    public void enqueue(T object) {
        buffer[size++] = object;

        if (size >= capacity) {
            buffer = expandBuffer();
        }
    }

    public T dequeue() {
        if (size <= 0)
            throw new NoSuchElementException("Error! Attempt to remove element from empty queue.");

        Object content = buffer[0];
        size--;
        buffer = trimBuffer();

        return (T) content;
    }

    public Object peek() {
        if (size <= 1)
            throw new NoSuchElementException("Error! There is no next element.");

        return buffer[1];
    }

    public int size() {
        return size;
    }

    private Object[] trimBuffer() {
        Object[] resizedBuffer = new Object[size];
        System.arraycopy(buffer, 1, resizedBuffer, 0, size);

        return resizedBuffer;
    }

    private Object[] expandBuffer() {
        Object[] expandedBuffer = new Object[(int) (size + (size * 0.25))];
        this.capacity = (int) (size + (size * 0.25));
        System.arraycopy(buffer, 0, expandedBuffer, 0, size);

        return expandedBuffer;
    }
}
