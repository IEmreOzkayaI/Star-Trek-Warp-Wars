package tools;

public class Stack
{
    private Object arr[];
    private int top;
    private int capacity;
 
    // Constructor to initialize the stack
    public Stack(int size)
    {
        arr = new Object[size];
        capacity = size;
        top = -1;
    }
 
    // Utility function to add an element `x` to the stack
    public void push(Object x)
    {
        if (isFull())
        {
            System.exit(-1);
        }
 
        arr[++top] = x;
    }
 
    // Utility function to pop a top element from the stack
    public Object pop()
    {
        // check for stack underflow
        if (isEmpty())
        {
            return false;
        }
 
 
        // decrease stack size by 1 and (optionally) return the popped element
        return arr[top--];
    }
 
    // Utility function to return the top element of the stack
    public Object peek()
    {
        if (!isEmpty()) {
            return arr[top];
        }
        else {
            System.exit(-1);
        }
 
        return -1;
    }
 
    // Utility function to return the size of the stack
    public int size() {
        return top + 1;
    }
 
    // Utility function to check if the stack is empty or not
    public boolean isEmpty() {
        return top == -1;               // or return size() == 0;
    }
 
    // Utility function to check if the stack is full or not
    public boolean isFull() {
        return top == capacity - 1;     // or return size() == capacity;
    }
}