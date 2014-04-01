/**
 * ITCS 2214 - Project 5A
 * BST.java
 *
 * This is the binary search tree node implementation
 * of the BinNode ADT.
 *
 * @author Daniel Gruszczynski
 * @date March 28, 2014
 * @version 1.0
 */

class BSTNode<Key, E> implements BinNode<E>
{
	private Key key; // Key for the node 
	private E element; // Element for the node
	private BSTNode<Key, E> left; // Pointer to left child node
	private BSTNode<Key, E> right; // Pointer to right child node

	// ----------  Constructors  ----------
	public BSTNode(){
		left = right = null;
	}

	public BSTNode(Key k, E item){
		left = right = null;
		key = k;
		element = item;
	}

	public BSTNode(Key k, E item, BSTNode<Key, E> l, BSTNode<Key, E> r){
		left = l;
		right = r;
		key = k;
		element = item;
	}
	
    // Returns node key
	public Key key(){
		return key;	
	}
 
	// Set node key
	public void setKey(Key k){
		key = k;
	}

	// Returns node element
	public E element(){
		return element;
	} 

	// Set node element 
	public void setElement(E item){
		element = item;
	}

	// Return left child 
	public BSTNode<Key, E> left(){
		return left;
	}
 
	// Set left child
	public void setLeft(BSTNode<Key, E> l){
		left = l;
	}

    // Return right child
	public BSTNode<Key, E> right(){
		return right;
	}

	// Set right child
	public void setRight(BSTNode<Key, E> r){
		right = r;
	}

	// Return true is node is a leaf (no left and right child)
	public boolean isLeaf(){
		return (left == null) && (right == null);
	}
}
