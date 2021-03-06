/**
 * 
 * <b>LinkedList.java</b><br>
 * This is my single-linked list implementation using the
 * list interface. 
 * 
 * @author Daniel Gruszczynski
 * @version 1.0
 * @date February 23, 2014
 */

class LinkedList<E extends Comparable<E>> implements List <E>
{
	private Link<E> head;
	private Link<E> tail;
	protected Link<E> current;
	private int size; 

	LinkedList()
	{
		head = new Link<E>(null);
		current = tail = head;
		size = 0;
	}

	public void clear()
	{
		head.setNext(null);
		head= new Link<E>(null);
		current = tail = head;
		size = 0;
	}

	public void insert(E item)
	{
		current.setNext(Link.get(item, current.next()));
		if(current == tail)
			tail = current.next();
		
		size++;	
	}

	public void append(E item)
	{
		tail = tail.setNext(Link.get(item, null));
		size++;
	}
	
	public E remove()
	{
		if(current.next() == null)
			return null;
		
		E item = current.next().element();
		if(current.next() == tail)
			tail = current;
		
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
		if(current == head)
			return;
		
		Link<E> temp = head;
		while(temp.next() != current)
			temp = temp.next();
		
		current = temp;
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
		Link<E> temp = head;
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
		if(current.next() == null)
			return null;
		
		return current.next().element();
	}
	

	public void reverse()
	{	
		int posHolder = currentPos();

		for(int i = length(); i >= 0 ; i--)
		{
			moveToPos(i);
			E temp = remove();
			append(temp);
		}
		moveToPos(posHolder);
	}
	
	public void reverse2()
	{
		int posHolder = currentPos();
		
		Link<E> newTail = head.next();
		
		if(isEmpty())
			return;
	
		Link<E> p = head.next();
		Link<E> backup = null;
		while(p != tail)
		{
			Link<E> temp = p.next();
			p.setNext(backup);
			backup = p;
			p = temp;
		}
		head.setNext(p);
		tail = newTail;
		
		moveToPos(posHolder);
	}
	
	public void print()
	{
		int posHolder = currentPos();
		
		moveToPos(1);
		while(current != tail)
		{
			System.out.print(getValue() + " ");
			next();
		}
		System.out.print("\n");
		moveToPos(posHolder);
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	public void bubbleSort()
	{
		int temp = currentPos();
		
		for(int i = 0; i < size; i++)
		{
			for(int j = size - 1; i < j; j--)
			{
				moveToPos(j - 1);
				E prev = getValue();
				next();
				E curr = getValue();
				
				if(curr.compareTo(prev) < 0)
				{
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
		
		moveToStart();
		while((currentPos() != size) && !found)
		{
			if(item == getValue())
				found = true;
			
			count++;
			next();
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
				end = middle - 1;
			else
				begin = middle + 1;
			
			count++;
		}
		
		return count;
	}
}