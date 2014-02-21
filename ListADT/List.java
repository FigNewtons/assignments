/**
 * 
 *  <b>List.java</b><br>
 * 	This is an interface for a list ADT for ITCS 2214.
 * 
 * 	@author Daniel Gruszczynski
 * 	@version 1.0 
 * 	@date February 20, 2013
 * 
 * 
 */

public interface List<E> {
	
	public void clear(); // clears entire list

	public void insert(E item); // inserts items at current position

	public void append(E item); // appends item to end of list

	public E remove(); // removes and returns current item from list

	public void moveToStart(); // move current position to start of list

	public void moveToEnd(); // move current position to end of list

	public void prev(); // move current position to the left (if possible)

	public void next(); // move current position to the right(if possible)

	public int length(); // returns list size

	public int currentPos(); // returns current position

	public void moveToPos(int pos); // moves to given position in list

	public E getValue(); // returns current value

	public void bubbleSort(); // sorts list using bubble sort algorithm

	public int linearSearch(E item); //finds item in list in O(n) 
	
	public int binarySearch(E item); //finds item in sorted list in O(log n)
}
