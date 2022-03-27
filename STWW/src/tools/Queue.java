package tools;

public class Queue {
	private Object[] arr; // array to store queue elements
	private int front; // front points to the front element in the queue
	private int rear; // rear points to the last element in the queue
	private int capacity; // maximum capacity of the queue
	private int count; // current size of the queue

	// Constructor to initialize a queue
	public Queue(int size) {
		arr = new Object[size];
		capacity = size;
		front = 0;
		rear = -1;
		count = 0;
	}

	// Utility function to dequeue the front element
	public Object dequeue() {
		// check for queue underflow
		if (isEmpty()) {
			System.out.println("Underflow\nProgram Terminated");
			System.exit(-1);
		}

		Object x = arr[front];


		front = (front + 1) % capacity;
		count--;

		return x;
	}

	// Utility function to add an item to the queue
	public void enqueue(Object item) {
		// check for queue overflow
		if (isFull()) {
			System.out.println("Overflow\nProgram Terminated");
			System.exit(-1);
		}


		rear = (rear + 1) % capacity;
		arr[rear] = item;
		count++;
	}

	// Utility function to return the front element of the queue
	public Object peek() {
		if (isEmpty()) {
			System.out.println("Underflow\nProgram Terminated");
			System.exit(-1);
		}
		return arr[front];
	}

	// Utility function to return the size of the queue
	public int size() {
		return count;
	}

	// Utility function to check if the queue is empty or not
	public boolean isEmpty() {
		return (size() == 0);
	}

	// Utility function to check if the queue is full or not
	public boolean isFull() {
		return (size() == capacity);
	}
}