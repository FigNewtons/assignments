import java.util.ArrayList;

/**
* MaxHeap.java
*
* This is an array-based implementation of a binary max heap,
* which itself represents a complete binary tree. The parent
* node must be greater than or equal to the value of its children.
*
* Position zero represents the root, one the root's left child,
* two the root's right child and so on through each level. Thus,
* the total number of nodes before another level is needed is 2^h - 1
* (where h is the current height)
*
**/

public class MaxHeap<E extends Comparable<? super E>>
{
	private static final int DEFAULT_SIZE = 10;

	private ArrayList<E> heap;
	private int maxSize; // Array size capacity
	private int size; // Current number of items in heap


	// -------------------- Constructors --------------------
	// Use the first two constructors if you have preloaded the heap contents
	public MaxHeap(ArrayList<E> heap, int maxSize){
		this.heap = heap;
		this.maxSize = maxSize;
		size = heap.size();
		
		buildheap();
	}

	public MaxHeap(E[] array, int maxSize){
		heap = new ArrayList<E>(maxSize);
		
		for(E item: array)
			heap.add(item);
		
		this.maxSize = maxSize;
		size = array.length;
		
		buildheap();
	}
	
	public MaxHeap(int maxSize){
		heap = new ArrayList<E>(maxSize);
		this.maxSize = maxSize;
		size = 0;
	}

	public MaxHeap(){
		heap = new ArrayList<E>(DEFAULT_SIZE);
		maxSize = DEFAULT_SIZE;
		size = 0;
	}

	// -------------------- Public methods --------------------

	// Returns current size of heap
	public int heapsize(){
		return size;
	}

	// Returns true if node is a leaf
	public boolean isLeaf(int pos){
		return (pos >= size/2) && (pos < size);
	}

	// Returns position of left child for a node with position pos
	public int leftchild(int pos){
		assert pos < size/2 : "Position has no left child";
		return 2 * pos + 1;
	}

	// Returns position of right child
	public int rightchild(int pos){
		assert pos < (size - 1) / 2 : "Position has no right child";
		return 2 * pos + 2;
	}

	// Returns position of parent
	public int parent(int pos){
		assert pos > 0 : "Position has no parent"; // Root = position 0
		return (pos - 1) / 2;
	}

	// Insert value at correct position in tree
	// One-at-a-time insertions take 0(n log n) in the worst case
	public void insert(E item){
		assert size < maxSize: "Heap is full";
		int current = size++;
		heap.set(current, item); // Temporarily insert at bottom of heap

		// Make swaps up the tree so values are in right order
		while((current != 0) &&
			  (heap.get(current).compareTo(heap.get(parent(current))) > 0))
		{
			swap(current, parent(current));
			current = parent(current);
		}	
	}

	// Generally used in conjunction with preloaded constructor to
	// put values in order. (aka heapify contents)
	public void buildheap(){
		for(int i = (size/2)-1; i >= 0; i--) //Start one level above bottom
			siftdown(i);
	}

	// Remove and return the max value (aka root)
	public E removemax(){
		assert size > 0 : "Removing from empty heap";
		swap(0, --size); // Swap root with last item in heap
		if(size != 0)
			siftdown(0); // Find the new correct root and fix heap

		return heap.remove(size); // Legit remove...not the lazy dereferencing kind
	}

	// Remove item at any position
	public E remove(int pos){
		assert (pos >= 0) && (pos < size): "Illegal heap position";
		if(pos == (size - 1))
			size--; // Last element in heap, no swapping required
		else
		{
			swap(pos, --size); // Swap with last value

			// If item is larger than its new parent
			while((pos > 0) &&
				  (heap.get(pos).compareTo(heap.get(parent(pos))) > 0))
			{
				swap(pos, parent(pos));
				pos = parent(pos);
			}

			// If new children now larger than item
			if(size != 0)
				siftdown(pos);
		}

		return heap.get(size);
	}

	// -------------------- Private methods --------------------

	// Place element in correct position
	private void siftdown(int pos){
		assert (pos >= 0) && (pos < size): "Illegal heap position";
		while(!isLeaf(pos)){
			int j = leftchild(pos);
			
			if((j < size-1) && heap.get(j).compareTo(heap.get(j+1)) < 0)
				j++; // j is now the index of the child with larger value

			if(heap.get(pos).compareTo(heap.get(j)) >= 0)
				return; // No swapping necessary

			swap(pos, j); // Swap parent with child
			pos = j; // Move down a level
		}
	}

	// Swaps the values between two positions in heap
	private void swap(int a, int b){
		E temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
}