import static org.junit.Assert.*;

import org.junit.Test;


public class SortTest 
{

	Integer[] array = {11,6,3,15,12,1,4,0,7,2,9,8,14,5,10,13};
	Integer[] correct = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	
	Sort<Integer> sort = new Sort<Integer>(array);	

	@SuppressWarnings("deprecation")
	@Test
	public void bubbleTest() 
	{
		sort.bubbleSort();
		assertEquals(correct, sort.getArray());
	}
	
	@Test
	public void selectionTest()
	{
		sort.selectionSort();
		assertEquals(correct, sort.getArray());
	}

	@Test
	public void insertionTest()
	{
		sort.insertionSort();
		assertEquals(correct, sort.getArray());
	}
	
	@Test
	public void shellTest()
	{
		sort.shellSort();
		assertEquals(correct, sort.getArray());
	}
	
	@Test
	public void mergeTest()
	{
		Integer[] temp = new Integer[array.length];
		sort.mergeSort(temp, 0, array.length - 1);
		assertEquals(correct, sort.array);
	}
	
	@Test
	public void quickTest()
	{
		Integer[] temp = new Integer[array.length];
		sort.quickSort(0, temp.length-1);
		assertEquals(correct, sort.array);
	}
}
