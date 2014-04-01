/**
 * ITCS 2214 - Project 5A
 * Dictionary.java
 * 
 * This is the Dictionary ADT for the binary search tree.
 * @author Daniel Gruszczynski
 * @date March 28, 2014
 * @version 1.0
 */

public interface Dictionary <Key, E>
{
	public void clear(); // Dereferences dictionary
	public void insert(Key k, E item); // Inserts item
	public E remove(Key k); // Removes item with Key k
	public E removeAny();   // Removes any item 
	public E find(Key k); // Search for an item with Key k in dictionary
	public int size(); // Returns number of items in dictionary 
}
