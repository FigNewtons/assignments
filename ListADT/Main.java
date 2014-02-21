import java.util.Random;
import java.io.*;

/**
 * 
 * <b>Main.java</b><br>
 * This program tests the performance of linear search and binary search
 * with randomly populated lists of integers over increasingly large list
 * sizes.
 * 
 * @author Daniel Gruszczynski
 * @version 1.0
 * @date February 20, 2014
 * 
 */

public class Main {

	/**
	 * Handles the list searching
	 * 
	 * @param list			  The list the function will search.
	 * @param isLinearSearch  A linear search will be performed if true. 
	 * 						  If false, a binary search will be performed.	
	 * 
	 * @return 				  The average count of search comparisons made
	 */
	public static float search(AList list, boolean isLinearSearch)
	{
		float average = 0;				// average count of search comparison per random int
		int numSearches = 1000;			// number of searches to make for each sort
		Random random = new Random();
		
		for(int i = 0; i < numSearches; i++)
		{
			list.moveToPos(random.nextInt(list.length()));
			int count;
					
			if(isLinearSearch)
				count = list.linearSearch(list.getValue());
			else
				count = list.binarySearch(list.getValue());
			
			average += count;
			System.out.print(count + " ");		
		}
		
		average = average / numSearches;
		System.out.println("\nAverage count: " + average + "\n");
		
		return average;
		
	}
	
	public static void main(String[] args) throws IOException
	{
		// Export data to text file for graphing 
		// WARNING: Data file is overwritten each time this program runs!
		String filename = "data.txt";
		File file = new File(filename);
		PrintWriter exportData = new PrintWriter(file);
		
		int[] sizes = {512, 1024, 2048, 4096, 8192}; //store list sizes
		
		for(int size: sizes)
		{
			AList<Integer> list = new AList<Integer>(size);
			Random random = new Random();
			
			// Populate the list with random integers from 0 - 10000
			for(int i = 0; i < size; i++)
				list.append(random.nextInt(10001));	

			// Perform unsorted linear search 
			System.out.print("Unsorted Linear Search -- Size:" + size + "\nCounts: ");
			exportData.print(search(list,true) + " ");
			
			// Sort list
			list.bubbleSort();
		
			// Perform sorted linear search
			System.out.print("Sorted Linear Search -- Size:" + size + "\nCounts: ");
			exportData.print(search(list,true) + " ");
			
			// Perform binary search
			System.out.print("Binary Search -- Size:" + size + "\nCounts: ");
			exportData.print(search(list,false) + "\n");
			
			System.out.println("------------------------------------");
		}
		
		exportData.close();
	}	
}
