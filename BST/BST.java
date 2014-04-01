/**
 * ITCS 2214 - Project 5A
 * BST.java
 *
 * This is the binary search tree implementation of 
 * the dictionary ADT. 
 * @author Daniel Gruszczynski
 * @date March 28, 2014
 * @version 1.0
 *
 */
class BST<Key extends Comparable<? super Key>, E> 
			  implements Dictionary<Key, E>
{
	private BSTNode<Key, E> root; // Root node of the tree
	private int count; // Node count 

	// Constructor
	BST(){
		root = null;
		count = 0;
	}

	// Dereference the tree
	public void clear(){
		root = null;
		count = 0;
	}

	// Insert record into the tree
	public void insert(Key k, E item){
		root = inserthelp(root, k, item);
		count++;
	}

	// Remove record from tree
	public E remove(Key k){
		E temp = findhelp(root, k);
		if(temp != null){
			root = removehelp(root, k);
			count--;
		}
		return temp;
	}

	// Remove the root from tree
	public E removeAny(){
		if(root == null)
			return null;
		
		E temp = root.element();
		root = removehelp(root, root.key());
		count--;
		return temp;
	}

	// Find a record in tree with key k
	public E find(Key k){
		return findhelp(root, k);
	}

	// Return number of records/nodes
	public int size(){
		return count;
	}

	// Return height of tree (depth + 1)
	public int height(){
		return heighthelp(root);
	}

	// Return number of leaf nodes
	public int numLeaves(){
		return numleaveshelp(root);
	}
	
	
	// --------------- Helper functions ---------------

	// Finds height of subtree 
	private int heighthelp(BSTNode<Key, E> root)
	{
		if(root == null)
			return 0;
		else
		{
			int leftHeight = heighthelp(root.left());
			int rightHeight = heighthelp(root.right());
		
			return max(leftHeight, rightHeight) + 1;
		}
	}

	// Determines which subtree has larger depth
	private int max(int l, int r)
	{
		if(l > r)
			return l;
		else
			return r;
	}

	// Returns number of leaf nodes in a subtree
	private int numleaveshelp(BSTNode<Key, E> root)
	{
		if(root.isLeaf())
			return 1;
		else
		{
			int leftLeaves = numleaveshelp(root.left());	
			int rightLeaves = numleaveshelp(root.right());
			return leftLeaves + rightLeaves;
		}
	}

	// Traverses tree to find spot of insertion
	private BSTNode<Key, E> inserthelp(BSTNode<Key, E> root, Key k, E item){
		if(root == null)
			return new BSTNode<Key, E>(k, item); // Return new node for insertion in empty subtree
	
		if(root.key().compareTo(k) > 0)
			root.setLeft(inserthelp(root.left(), k , item)); // k is less than subtree root key
		else
			root.setRight(inserthelp(root.right(), k, item)); // k is greater than subtree root key

		return root;
	}

	// Traverses tree to find element for given key k
	private E findhelp(BSTNode<Key, E> root, Key k){
		if(root == null)
			return null;
	
		if(root.key().compareTo(k) > 0)
			return findhelp(root.left(), k);
		else if (root.key().compareTo(k) == 0)
			return root.element();
		else
			return findhelp(root.right(), k);
	}

	// Traverses tree to remove element with key k
	private BSTNode<Key, E> removehelp(BSTNode<Key, E> root, Key k){
		if(root == null)
			return null;
		
		if (root.key().compareTo(k) > 0)
			root.setLeft(removehelp(root.left(), k));
		else if (root.key().compareTo(k) < 0)
			root.setRight(removehelp(root.right(), k));
		else
		{
			// Found node -- case for zero or one child
			if(root.left() == null)
				return root.right();
			if(root.right() == null)
				return root.left();
			// Case for two children 
			else
			{
				// Find min value of right subtree to make
				// as root of current subtree
				BSTNode<Key, E> temp = getmin(root.right());
				root.setElement(temp.element());
				root.setKey(temp.key());
				root.setRight(deletemin(root.right()));
			}
		}
		return root;
	}

	// Traverse left side of subtree until min is found (when root.left() == null)
	private BSTNode<Key, E> getmin(BSTNode<Key, E> root){
		if(root.left() == null) 
			return root;
		return getmin(root.left());		
	}

	// Delete min from subtree 
	private BSTNode<Key, E> deletemin(BSTNode<Key, E> root){
		if(root.left() == null)
			return root.right();
		root.setLeft(deletemin(root.left()));
		return root;
	}
}
