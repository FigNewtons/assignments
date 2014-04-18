/**
 * 
 * <b>LinkedList.java</b><br>
 * This is my single-linked list implementation using the
 * list interface. 
 * 
 *
 */

class LinkedList<E> implements List<E>
{
	private Link<E> head;
	private Link<E> tail;
	protected Link<E> current;
	private int size; 

	LinkedList(){
		head = new Link<E>(null);
		current = tail = head;
		size = 0;
	}

	public void clear(){
		head.setNext(null);
		head= new Link<E>(null);
		current = tail = head;
		size = 0;
	}

	public void insert(E item){
		current.setNext(Link.get(item, current.next()));
		if(current == tail)
			tail = current.next();
		
		size++;	
	}

	public void append(E item){
		tail = tail.setNext(Link.get(item, null));
		size++;
	}
	
	public E remove(){
		if(current.next() == null)
			return null;
		
		E item = current.next().element();
		if(current.next() == tail)
			tail = current;
		
		current.setNext(current.next().next());
		size--;
		
		return item;
	}

	public void moveToStart(){
		current = head;
	}
	
	public void moveToEnd(){
		current = tail;
	}
	
	public void prev(){
		if(current == head)
			return;
		
		Link<E> temp = head;
		while(temp.next() != current)
			temp = temp.next();
		
		current = temp;
	}
	
	public void next(){
		if(current != tail)
			current = current.next();
	}
	
	public int length(){
		return size;
	}
	
	public int currentPos(){
		Link<E> temp = head;
		int index;
		for(index = 0; current != temp; index++)
			temp = temp.next();
		
		return index;
	}
	
	public void moveToPos(int pos){
		assert(pos >= 0) && (pos <= size): "Position out of range";
		current = head;
		for(int i = 0; i < pos; i++)
			current = current.next();
	}
	
	public E getValue(){
		if(current.next() == null)
			return null;
		
		return current.next().element();
	}
}
