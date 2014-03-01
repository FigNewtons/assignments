import java.util.ArrayList;

class AStack<E> implements Stack<E>
{
	private static final int DEFAULT_SIZE = 10;
	
	private int topIndex;
	private int size;
	private ArrayList<E> list; 
	
	AStack()
	{
		this(DEFAULT_SIZE);
	}
	
	AStack(int size)
	{
		this.size = size;
		list = new ArrayList<E>(size);
		topIndex = 0;		
	}
	
	public void clear()
	{
		topIndex = 0;
	}
	
	public void push(E item)
	{
		assert topIndex != size : "The stack is full";
		list.set(topIndex++, item);
	}
	
	public E pop()
	{
		assert topIndex != 0 : "The stack is empty";
		return list.remove(--topIndex);
	}
	
	public E topValue()
	{
		assert topIndex != 0 : "The stack is empty";
		return list.get(topIndex - 1);
	}
	
	public int length()
	{
		return size;
	}
	
}