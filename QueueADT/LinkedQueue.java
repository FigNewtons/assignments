
public class LinkedQueue<E> implements Queue<E>
{
	private Link<E> front;
	private Link<E> end;
	private int size;
	
	public LinkedQueue()
	{
		front = end = new Link<E>(null);
		size = 0;
	}
	
	// Reinitialize queue
	public void clear()
	{
		front = end = new Link<E>(null);
		size = 0;
	}
	
	// Append link to end of queue
	public void enqueue(E item)
	{
		end.setNext(new Link<E>(item, null));
		end = end.next();
		size++;
	}
	
	// Remove front link of queue
	public E dequeue()
	{
		assert size != 0 : "The queue is empty";
		E item = front.next().element();
		front.setNext(front.next().next());
		
		if (front.next() == null)
			end = front;
		size--;
		return item;
	}

	// Return the element at the front of queue
	public E frontValue()
	{
		assert size != 0 : "The queue is empty";
		return front.next().element();
	}
	
	// Length of the queue
	public int length()
	{
		return size;
	}
	
	// Returns true if queue is empty
	public boolean isEmpty()
	{
		return size == 0;
	}
}

