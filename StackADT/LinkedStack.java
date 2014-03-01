public class LinkedStack<E> implements Stack<E>
{
	private Link<E> top;
	private int size;
	
	LinkedStack()
	{
		top = null;
		size = 0;
	}
	
	// Reinitialize the stack via dereferencing current stack
	public void clear()
	{
		top = null;
		size = 0;
	}
	
	// Add a link to the top of the stack
	public void push(E item)
	{
		top = new Link<E>(item, top);
		size++;
	}
	
	// Remove and return the top link (with its value)
	public E pop()
	{
		assert top != null : "The stack is empty";
		E item = top.element();	// get top value
		top = top.next();		// move top to next item
		size--;					// decrease size
		return item;
	}
	
	// Return the top link value
	public E topValue()
	{
		assert top != null : "The stack is empty";
		return top.element();
	}
	
	// Return the stack length 
	public int length()
	{
		return size;
	}
}