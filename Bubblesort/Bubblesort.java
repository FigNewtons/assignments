/*
	Daniel Gruszczynski
	January 15, 2014
	ITCS 2214-001
	
	Bubble Sort Algorithm 
	
	For educational purposes only!

*/
import java.util.Random;
import java.io.*;

public class Bubblesort {

	// Sorts an integer array using the bubble sort algorithm 
	// and returns the number of swaps made
	public static int bubbleSort(int[] array)
	{
		int numberOfSwaps = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = array.length -1; i < j; j--)
			{
				if(array[j] < array[j - 1])
				{
					int swap = array[j];
					array[j] = array[j - 1];
					array[j - 1] = swap;
					
					numberOfSwaps++;
				}
			}
		}
		
		return numberOfSwaps;
	}
	
	// Creates an array given a size and populates it with
	// random integers in the range of (0,10000) inclusive
	public static int[] generateArray(int size)
	{
		Random random = new Random();
		int[] array = new int[size];
		
		for(int i = 0; i < size; i++)
		{
			array[i] = random.nextInt(10001); 
		}
		
		return array;
	}
	
	// Tests the accuracy of the bubbleSort method for an array
	// of a given size -- outputs the array before and after the 
	// sorting as well as the number of swaps made
	public static void testSort(int size)
	{
		int[] array = generateArray(size);
		
		System.out.println("*****Test Case*****");
		System.out.println("Will not be the same array used for performance data.");
		System.out.print("Size: " + size + "\nInput: ");

		for (int num: array)
			System.out.print(num + " ");

		System.out.print("\n");
		int testCount = bubbleSort(array);

		System.out.print("Output: ");
		for (int num: array)
			System.out.print(num + " ");
		
		System.out.println("\nNumber of Swaps: " + testCount + "\n");

	}


	public static void main(String[] args) throws IOException
	{
		// Export data to text file for graphing 
		// WARNING: Data file is overwritten each time this program runs!
		String filename = "data.txt";
		File file = new File(filename);
		PrintWriter exportData = new PrintWriter(file);
		
		testSort(32); // Tests output for an array of size 32

		System.out.print("*****Performance Data Set*****\nData Size: "); // Header for performance data

		int[] swapCount = new int[9];	// Stores number of swaps for each array size
		
		// Loop used to generate array sizes from
		// 2^5 to 2^13 (32...8192) for performance data 
		for(int exp = 5; exp < 14; exp++)
		{
			int size = (int) Math.pow(2, exp);	// size to create array
			int index = exp - 5;				// index to store number of swaps
			
			int[] array = generateArray(size);			
			swapCount[index] = bubbleSort(array);
			
			System.out.printf("%-8d",size);		// display data size to screen
			exportData.print(size + " ");		// write data size to file
			
		}
		
		System.out.print("\nSwap Count:");
		exportData.println();
		
		for(int count: swapCount)
		{
			System.out.printf("%-8d",count);	// display swap count to screen
			exportData.print(count + " ");		// write swap count to file
		}
		
		System.out.println();
		exportData.close();
	}

}
