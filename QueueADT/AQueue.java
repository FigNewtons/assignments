import java.util.ArrayList;

/* 
 * The array based implementation for a queue
 * is circular. 
 * 
 */

public class AQueue<E> implements Queue<E>
{
	private static final int DEFAULT_SIZE = 10;
	
	private int maxSize;	// max size of queue
	private int front; 		// front of queue
	private int end;		// back of queue
	private ArrayList<E> list; 
	
	public AQueue()
	{
		this(DEFAULT_SIZE);
	}
	
	public AQueue(int size)
	{
		// Have n+1 distinct spots so that full and empty states aren't the same
		maxSize = size + 1;  
		front = 1;
		end = 0;
		list = new ArrayList<E>(maxSize);
	}
	
	public void clear()
	{
		// This is the empty state of the queue 
		front = 1;
		end = 0;
	}
	
	// Append item to end of the current list
	public void enqueue(E item)
	{
		// It's plus two because you have to skip the empty slot 
		assert((end + 2) % maxSize) != front : "The queue is full"; 
		end = (end + 1) % maxSize;
		list.set(end, item);
	}
	
	// Take item from front of list and dereference it
	public E dequeue()
	{
		assert length() != 0: "The queue is empty";
		E item = list.get(front);
		front = (front + 1) % maxSize;
		return item;
	}
	
	// Return the value at front of the list
	public E frontValue()
	{
		assert length() != 0: "The queue is empty";
		return list.get(front);
	}
	
	// Returns current size of the queue
	public int length()
	{
		return ((end + maxSize) - front + 1) % maxSize;
	}
	
}
