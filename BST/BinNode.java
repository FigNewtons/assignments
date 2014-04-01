/**
 * ITCS 2214 - Project 5A
 * BinNode.java
 *
 * This is the ADT for binary tree nodes.
 * @author Daniel Gruszczynski
 * @date March 28, 2014 
 * @version 1.0
 **/

public interface BinNode<E>
{
	public E element(); // Returns element
	public void setElement(E item); // Sets node value
	public BinNode<E> left(); // Returns left child
	public BinNode<E> right(); // Returns right child
	public boolean isLeaf(); // Returns true if it is a leaf node
}
