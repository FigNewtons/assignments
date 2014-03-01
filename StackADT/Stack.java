/**
 * 
 * <b>Stack.java</b><br>
 * This is a stack interface for ITCS 2214.
 * 
 * LIFO - last in, first out
 * 
 * @author Daniel Gruszczynski
 * @version 1.0
 * @date February 27, 2014
 * 
 */
public interface Stack<E> 
{
	public void clear(); // Reinitializes stack via dereferencing 
	
	public void push(E item); // Inserts item into the top of the stack
	
	public E pop();	// Removes and returns top item from the stack
	
	public E topValue(); // Copies and returns the top value from the stack
	
	public int length(); // Returns the stack size
}
