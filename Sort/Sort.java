import java.util.ArrayList;

public class Sort<E extends Comparable<E>>
{
	public E[] array;
	private int len;
	
	
	public Sort(E[] array)
	{
		this.array = array;
		len = array.length;
	}
	
	
	public E[] getArray()
	{
		return array;
	}
	
	
	// -------------------- The Three Exchange Sorts 0(n^2) --------------------
	//
	// Iterates through the array, sorting each element
	// before moving on to the next element 
	public void insertionSort()
	{
		for(int i = 1; i < len; i++)
		{
			for(int j = i; (j > 0) && (array[j].compareTo(array[j-1]) < 0); j--)
				swap(j, j-1);		
		}
	}
	
	// Iterates through the array, making swaps until the next lowest
	// element bubbles towards the front of the array
	public void bubbleSort()
	{
		for(int i = 0; i < len; i++)
		{
			for(int j = len - 1; i < j; j--)
			{
				if(array[j].compareTo(array[j-1]) < 0)
					swap(j, j-1);
			}	
		}		
	}
	
	// Iterates through the array, keeping track of the next lowest
	// value and then places it towards the front of the array
	public void selectionSort()
	{
		for(int i = 0; i < len - 1; i++)
		{
			int lowIndex = i;	// stores index of next lowest value
			for(int j = len-1; j > i; j--)
			{
				if(array[j].compareTo(array[lowIndex]) < 0)
					lowIndex = j;
			}
			swap(i, lowIndex);
		}
	}
	
	// Swaps the values of two positions in an array
	private void swap(int a , int b)
	{
		E temp = array[a];
		array[a] = array[b];
		array[b]= temp;
	}
	
	// ------------------------------  Shell Sort ------------------------------
	//
	// Sorts sublists offset by an interval(step) of i that increase
	// by a factor of two each iteration. Then, a final insertion sort
	// is performed on the nearly sorted list 
	public void shellSort()
	{
		for(int i = len/ 2; i > 2; i /=2 )
		{
			for(int j = 0; j < i; j++)
				insert(j, i);
		}
		insert(0, 1);	// final insertion sort
	}
	
	// Special insertion sort for shell sort algorithm
	private void insert(int start, int step)
	{
		for(int i = start + step; i < len; i+= step)
		{
			for(int j = i; (j >= step) && (array[j].compareTo(array[j-step]) < 0); j-= step)
				swap(j, j-step);
		}
	}
	
	// ------------------------------  Merge Sort ------------------------------
	//							  (Divide and Conquer)
	// Breaks array into two sublists, those sublists then cut in half, etc. 
	// until there are n/2 pairs. These pairs are sorted and merged back into
    // their parent sublist (sorted), and so on, until the sorted sublists 
	// are merged back into the original array in O(n log n) time
	public void mergeSort(E[] temp, int left, int right)
	{
		int mid = (left + right) / 2;
		
		if(left == right)
			return;
		
		mergeSort(temp, left, mid);			// mergesort front half
		mergeSort(temp, mid + 1, right);	// mergesort back half 

		for(int i = left; i <= right; i++)
			temp[i] = array[i];
			
		int index1 = left;
		int index2 = mid + 1;
		
		for(int current = left; current <= right; current++)
		{
			if(index1 == mid + 1)
				array[current] = temp[index2++]; //left sublist depleted
			else if(index2 > right) 
				array[current] = temp[index1++]; //right sublist depleted
			else if (temp[index1].compareTo(temp[index2]) < 0)
				array[current] = temp[index1++]; // left < right
			else
				array[current] = temp[index2++]; // right > left 
		}		
	}
	
	// ------------------------------  Quick Sort ------------------------------
	//							  (Divide and conquer)
	// Uses pivots to divide the list into two sublists, the left sublist
	// having values less than the pivot and the right having values greater.
	// Then, we further divide the sublists by choosing pivots from the remaining
	// values until we have a sorted list. The average case is O(n log n) time and 
	// worst case is O(n^2) <-- this occurs rarely given our method of pivot selection
	// 
	// The initial method call should be list.quickSort(0, array.length - 1) 
	// like mergesort.
	
	public void quickSort(int left, int right)
	{
		int pivotIndex = findpivot(left, right); // gets the index of the middle value
		swap(pivotIndex, right); // places the pivot at the end of the list/sublist
		
		// partitions the list based on the pivot value and
		// returns the index of the value that separates the new 
		// left and right sublist
		int partIndex = partition(left-1, right, array[right]); 
		swap(partIndex, right); // Put pivot back in right spot
		
		if((partIndex - left) > 1)
			quickSort(left, partIndex - 1); // Sort left sublist
		if((right - partIndex) > 1)
			quickSort(partIndex + 1, right); // Sort right sublist
	}
	
	// Selects the midpoint of the array/subarray for the pivot
	private int findpivot(int left, int right)
	{
		return (left + right)/ 2;
	}
	
	// Uses the left and right index to traverse the subarray,
	// making comparisons to the pivot. Swaps occur only when 
	// array[left] > pivot and array[right] < pivot (thus, being in the
	// wrong sublist). Otherwise, the left index moves to the right
	// and the right to the left until they meet or pass each other.
	private int partition(int left, int right, E pivot)
	{
		do{
			while(array[++left].compareTo(pivot) < 0); 
			while((right != 0) && (array[--right].compareTo(pivot) > 0));
			swap(left, right); 	
		}while(left < right);
		
		swap(left, right);
		return left;
	}
	
	// -------------------------------  Heap Sort ------------------------------
	// Instead of using a BST-like structure like in quicksort, heapsort
	// uses the properties of a max heap to efficiently store and remove
	// the max value (root) after each iteration. We use a temp array to make
	// things cleaner, but it isn't necessary if you choose to implement
	// it a different way. Heapsort is O(n log n) in the best, average, and
	// worst case scenarios, but is not as fast as quicksort in the average
	// case (by a constant factor).
	public void heapSort(E[] temp)
	{
		MaxHeap<E> heap = new MaxHeap<E>(array, len);
		
		E[] arr = temp;	

		for(int i=0; i < len; i++){
			E max = heap.removemax();
			arr[(len-1) - i] = max;	
		}
		
		System.arraycopy(arr, 0, array, 0, len);		
	}
	
	// --------------------------------  Bin Sort ------------------------------
	// This sort works optimally in O(n) on key values within a range of 0 
	// and MaxKey by indexing the values into bins (which are linked lists) 
	// and then iteratively retrieving them back to make a sorted array. 
	// In the worst case, this sort runs in 0(n^2) due to the processing 
	// of each bin's linked list (see the nested for loop) -- this happens when
	// there's a large disparity between the number of items to sort and the
	// range the items are within.
	public void binSort(int MaxKey)
	{
		// Make an array of linked lists -- These are the bins 
		List<Integer>[] bin = new LinkedList[MaxKey + 1];
		for(int i = 0; i < MaxKey + 1; i++)
			bin[i] = new LinkedList<Integer>();
		
		// Place numbers into their corresponding bins 
		for(int i = 0; i < len; i++)
			bin[(Integer)array[i]].append((Integer)array[i]);
		
		int index = 0; // used to reorder the original array 
		
		// Iterate through the bins and place values back into the array
		E item; 
		for(int i = 0; i < MaxKey + 1; i++)
			for(bin[i].moveToStart(); (item = (E) bin[i].getValue()) != null; bin[i].next())
				array[index++] = item;
	}
	
	// -------------------------------  Radix Sort -----------------------------
	// This sort works in O(n) time for key values within a certain base (range)
	// and whose max value has maxLength digits. We iterate maxLength times,
	// at each pass sorting the keys based on digit found at the current place-
	// holder. This is done using the mod operator. We use a count array to be 
	// able to copy values from the bins back to the original array at the end
	// of each pass. 
	public void radixSort(int maxLength, int range)
	{ 
		// The bin array is a temp array used to move values
		// back to the original array after each pass -- the
		// count array stores the number of values we need to
		// reserve in the original array to copy the values 
		// back over
		
		// Initialize array list
		ArrayList<E> bin = new ArrayList<E>(len);
		for(int i = 0; i < len; i++)
			bin.add(null);
		
		int[] count = new int[range];
		
		int i, j, radix;
		
		// Iterate from least significant digit towards the right
		for(i = 0, radix = 1; i < maxLength; i++, radix *= range)
		{
			// Reset count values
			for (j = 0; j < range; j++)
				count[j] = 0;
			
			// Record counts for the values 
			for (j = 0; j < len; j++)
				count[((Integer) array[j] / radix) % range]++;
			
			// This will be the indices we use to space out the values
			// correctly
			for (j = 1; j < range; j++)
				count[j] = count[j-1] + count[j];
			
			// Copy values from array to bins, splitting based on current digit
			for (j = len - 1; j >= 0; j--)
				bin.set(--count[((Integer) array[j] / radix) % range], array[j]);
			
			// Copy values back into original array
			for (j = 0; j < len; j++)
				array[j] = bin.get(j);		
		}
	}
}
