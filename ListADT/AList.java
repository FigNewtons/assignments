import java.util.ArrayList;

/**
 * 
 * <b>AList.java</b><br>
 * This is the array implementation of the List ADT for ITCS 2214
 * 
 * @author Daniel Gruszczynski 
 * @version 1.0
 * @date February 20, 2014
 * 
 *
 */

public class AList<E extends Comparable<E>> implements List<E>
{
	/** When unspecified by the user, the default array capacity is 10 */
	private static final int DEFAULT_SIZE = 10;
	/** List capacity */
	private int maxSize;	
	/** Current size of the list */
	private int listSize;	
	/** Current position in the list */
	private int currentPos; 		
	/** The list array -- an array list is used to avoid the cast exceptions
	 *  that occurs when using an Object array. */
	private ArrayList<E> listArray;	
	
	/**
	 *  Default Constructor -- will create an array of size 10
	 */
	AList()
	{
		this(DEFAULT_SIZE);
	}
	
	/**
	 * Other Constructor -- will create an array of a specified size
	 * @param size The capacity of the array
	 */
	AList(int size)
	{
		maxSize = size;
		listSize = 0;
		currentPos = 0;	
		listArray = new ArrayList<E>(size);
	}
	
	/**
	 *  Resets the current position and list size to zero. This does not
	 *  delete previous list items until they are overridden when new items
	 *  are inserted or appended. 
	 *  @return none
	 */
	public void clear()
	{
		listSize = 0;
		currentPos = 0;
	}
	
	/**
	 * Inserts an item into the list where the current position is.
	 * 
	 * @param item An item of type E that the user wants to insert
	 * @return none
	 */
	public void insert(E item)
	{
		assert listSize < maxSize: "List capacity exceeded";
		for(int index = listSize; index > currentPos; index--)
			listArray.set(index, listArray.get(index - 1));
		listArray.set(currentPos, item);
		listSize++;
	}
	
	/**
	 * Appends an item into the end of the list
	 * 
	 * @param item An item of type E
	 * @return none
	 */
	public void append(E item)
	{
		assert listSize < maxSize : "List capacity exceeded";
		listArray.add(listSize++, item);
	}
	
	/**
	 *  Pops and returns the item at the current position from the list
	 *  
	 *  @return item of type E
	 */
	public E remove()
	{
		if(currentPos < 0 || currentPos >= listSize)
			return null;
		
		E item = listArray.get(currentPos);
		
		for(int index = currentPos; index < listSize - 1; index++)
			listArray.set(index, listArray.get(index + 1));
		
		return item;
	}
	
	/**
	 *  Moves current position to the front of the list.
	 *  
	 *  @returns none
	 */
	public void moveToStart()
	{
		currentPos = 0;
	}
	
	/**
	 *  Moves current position to the back of the list.
	 *  
	 *  @returns none
	 */
	public void moveToEnd()
	{
		currentPos = listSize;
	}
	
	/**
	 *  Moves the current position by one (to the left).
	 *  
	 *  @returns none
	 */
	public void prev()
	{
		if(currentPos != 0)
			currentPos--;
	}
	
	/**
	 *  Moves the current position by one (to the right).
	 *  
	 *  @returns none
	 */
	public void next()
	{
		if(currentPos < listSize)
			currentPos++;
	}

	/**
	 * 	Gives the current size of the list.
	 * 
	 *  @returns int length of the current list 
	 */
	public int length()
	{
		return listSize;
	}
	
	/**
	 * 	Gives the current position of the list placeholder.
	 * 
	 *  @returns int index of the current list position
	 */
	public int currentPos()
	{
		return currentPos;
	}
	
	/**
	 *  Moves the current position to pos.
	 *  
	 *  @param pos User-specified index (int)
	 *  @returns none
	 */
	public void moveToPos(int pos)
	{
		assert (pos >= 0) && (pos <= listSize): "Position out of range";
		currentPos = pos;
	}
	
	/**
	 *  Retrieves the item at the current position
	 * 
	 *  @return item of type E
	 */
	public E getValue()
	{
		assert (currentPos >= 0) && (currentPos < listSize): "No current element";
		return listArray.get(currentPos);
	}

	/**
	 *  Sorts the list using the bubble sort algorithm
	 * 
	 *  @return none
	 */
	public void bubbleSort() 
	{
		for(int i = 0; i < listSize; i++)
		{
			for(int j = listSize - 1; i < j; j--)
			{
				if(listArray.get(j).compareTo(listArray.get(j-1)) < 0)
				{
					E swap = listArray.get(j);
					listArray.set(j, listArray.get(j - 1));
					listArray.set(j - 1, swap);
				}
			}
		}
	}
	
	/**
	 * Traverses the list (from start to end) and stops when the item is found.
	 * 
	 * @param item 	 The item the user is searching for
	 * @return count The number of times a search comparison is made 
	 */
	public int linearSearch(E item)
	{
		boolean found = false;
		int count = 0;
		for (int i = 0; i < listSize && !found; i++)
		{
			if(listArray.get(i).compareTo(item) == 0)
				found = true;
			
			count++;
		}
		
		return count;
	}
	
	/**
	 *  Searches a sorted list by binary searching. 
	 *  
	 *  @param item 	The item the user is searching for.
	 *  @return count	The number of times a search comparison is made
	 */
	public int binarySearch(E item)
	{
		int begin = 0, count = 0;
		int end = listSize - 1;
		int middle;
		boolean found = false;
				
		while(!found && begin <= end)
		{
			middle = (begin + end)/2;
			
			if(listArray.get(middle).compareTo(item) == 0)
				found = true;
			else if(listArray.get(middle).compareTo(item) > 0)
				end = middle - 1;
			else
				begin = middle + 1;
			
			count++;
		}
		
		return count;
	}
}
