/**
 * 
 * <b>DLinkedList.java</b><br>
 * This is my double-linked list implementation using the
 * list interface. 
 * 
 * @author Daniel Gruszczynski
 * @version 1.0
 * @date February 23, 2014
 * 
 * 
 */

class DLinkedList<E extends Comparable<E>> implements List<E>
{
	private DLink<E> head;
	private DLink<E> tail;
	protected DLink<E> current;
	private int size;
	
	DLinkedList()
	{
		head = new DLink<E>(null, null);
		tail = new DLink<E>(null, null);
		
		head.setNext(tail);
		tail.setPrev(head);
		
		current = head;
		size = 0;
	}
	
	public void clear()
	{
		head.setNext(null);
		
		head = new DLink<E>(null, null);
		tail = new DLink<E>(null, null);
		
		head.setNext(tail);
		tail.setPrev(head);
		
		current = head;
		size = 0;
	}
	
	public void insert(E item)
	{
		current.setNext(new DLink<E>(item, current, current.next()));
		current.next().next().setPrev(current.next());		// You have to skip twice because of the insert
		size++;
	}
	
	public void append(E item)
	{

		tail.setPrev(new DLink<E>(item, tail.prev(), tail));
		tail.prev().prev().setNext(tail.prev());
		size++;
	}
	
	public E remove()
	{
		if(current.next() == tail)
			return null;
		
		E item = current.next().element();
		current.next().next().setPrev(current);
		current.setNext(current.next().next());
		size--;
		
		return item;
	}
	
	public void moveToStart()
	{
		current = head;
	}
	
	public void moveToEnd()
	{
		current = tail;
	}
	
	public void prev()
	{
		if(current != head)
			current = current.prev();
	}
	
	public void next()
	{
		if(current != tail)
			current = current.next();
	}
	
	public int length()
	{
		return size;
	}
	
	public int currentPos()
	{
		DLink<E> temp = head;
		int index;
		
		for(index = 0; current != temp; index++)
			temp = temp.next();
		
		return index;
	}
	
	public void moveToPos(int pos)
	{
		assert(pos >= 0) && (pos <= size): "Position out of range";
		current = head;
		for(int i = 0; i < pos; i++)
			current = current.next();
	}
	
	public E getValue()
	{
		if(current.next() == tail)
			return null;
		
		return current.next().element();
	}
	
	public void bubbleSort()
	{
		int temp = currentPos();
		for(int i = 0; i < size; i++)
		{
			for(int j = size; i + 1 < j; j--)
			{
				moveToPos(j);
				E prev = current.prev().element();
				E curr = current.element();
				
				if(curr.compareTo(prev) < 0)
				{
					prev();
					E tempVal = remove();
					prev();
					insert(tempVal);
				}
			}
		}
		moveToPos(temp);
	}
	
	public int linearSearch(E item)
	{
		int temp = currentPos();
		boolean found = false;
		int count = 0;
		
		moveToPos(1);
		while((current != tail) && !found)
		{
			if (current.element() == item)
				found = true;
			
			current = current.next();
			count++;
		}
		
		moveToPos(temp);
		return count;
	}
	
	public int binarySearch(E item)
	{
		int begin = 0, count = 0;
		int end = size - 1;
		int middle;
		boolean found = false;
		
		while(!found && begin <= end)
		{
			middle = (begin + end)/ 2;
			moveToPos(middle);
			if(getValue().compareTo(item) == 0)
				found = true;
			else if(getValue().compareTo(item) > 0)
				end = middle;
			else
				begin = middle + 1;
			
			count++;
		}
		
		return count;	
	}
	
}