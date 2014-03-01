/**
 * 
 * <b>Link.java</b><br>
 * This is the link class for a single-linked list implementation. 
 * 
 * @author Daniel Gruszczynski
 * @version 1.0
 * @date February 23, 2014
 * 
 */

class Link<E>
{
	private E element;		// node value 
	private Link<E> next;	// pointer to next node

	Link(E item, Link<E> nextVal)
	{
		element = item;
		next = nextVal;
	}

	Link(Link<E> nextVal)
	{
		next = nextVal;
	}
	
	Link<E> next()
	{
		return next;
	}

	Link<E> setNext(Link<E> nextVal)
	{
		next = nextVal;
		return next;
	}
	
	E element()
	{
		return element;
	}

	E setElement(E item)
	{
		return element = item;
	}	
	
	static Link freeList = null;
	
	static <E> Link<E> get(E item, Link<E> nextVal)
	{
		if(freeList == null)
			return new Link<E>(item, nextVal);
		
		Link<E> temp = freeList;
		freeList = freeList.next();
		temp.setElement(item); 
		temp.setNext(nextVal);
		
		return temp;
	}
	
	void release()
	{
		element = null;		// drop reference to element
		next = freeList;	// drop reference to next node
		freeList = this;	// points to itself
	}
}