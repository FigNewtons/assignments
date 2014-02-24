/**
 * 
 * <b>DLink.java</b><br>
 * This is the double link class for a double-linked list implementation. 
 * 
 * @author Daniel Gruszczynski
 * @version 1.0
 * @date February 23, 2014
 * 
 */

class DLink<E>
{
	private E element;
	private DLink<E> prev;
	private DLink<E> next;
	
	DLink(E item, DLink<E> prev, DLink<E> next)
	{
		element = item;
		this.prev = prev;
		this.next = next;		
	}
	
	DLink(DLink<E> prev, DLink<E> next)
	{
		this.prev = prev;
		this.next = next;
	}
	
	DLink<E> prev()
	{
		return prev;
	}
	
	DLink<E> setPrev(DLink<E> prev)
	{
		return this.prev = prev;
	}
	
	DLink<E> next()
	{
		return next; 
	}
	
	DLink<E> setNext(DLink<E> next)
	{
		return this.next = next;
	}
	
	E element()
	{
		return element;
	}
	
	E setElement(E item)
	{
		return element = item;
	}
}