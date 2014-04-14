/**
 * 
 * <b>Stack.java</b><br>
 * This is a queue interface for ITCS 2214.
 * 
 * FIFO - first in, first out
 * 
 * @author Daniel Gruszczynski
 * @version 1.0
 * @date February 27, 2014
 * 
 */

public interface Queue<E> 
{
	public void clear(); // Reinitializes queue via dereferencing 
	
	public void enqueue(E item); // Appends item to end of queue
	
	public E dequeue();	// Removes and returns item in front of queue
	
	public E frontValue(); // Returns item value in front of queue
	
	public int length(); // Returns queue length
	
	public boolean isEmpty(); // Returns true if queue is empty (length == 0)
}
