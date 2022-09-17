package by.incubator.autopark.collections;

public class MyStack <T> {
    private Object[] buffer;
    private final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int capacity;

    public MyStack() {
        capacity = DEFAULT_CAPACITY;
        buffer = new Object[DEFAULT_CAPACITY];
    }

    public MyStack(int capacity) {
        this.capacity = capacity;
        buffer = new Object[capacity];
    }

    public void push(T object) {
        if ((capacity - size - 1) >= 0) {
            buffer[size++] = object;
        } else {
            System.out.println("Garage is full!");;
        }
    }

    public T peek() {
        if (size == 0)
            throw new IllegalArgumentException("Error! Garage is empty!");

        return (T) buffer[size - 1];
    }

    public T pop() {
        if (size == 0)
            throw new IllegalArgumentException("Error! Garage is empty!");

        T rmObj = (T) buffer[size - 1];
        size--;

        return rmObj;
    }

    public int size() {
        return size;
    }

}
