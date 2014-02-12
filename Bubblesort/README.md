~~~~~~~~~~~~~~~~~~~Running the scripts~~~~~~~~~~~~~~~~~~~~~

To run, enter the following in terminal:

>$ java bubbleSort; python plot.py

Everything should run automatically with the data
in data.txt and the resulting graph saved as graph.png

Note: The user must have python 2.7.x or above installed
	  along with numpy and matplotlib (these were used to
	  create the graph).


~~~~~~~~~~~~What is the bubble sort algorithm?~~~~~~~~~~~~~

The bubble sort algorithm is one of many algorithms used to 
place the items of a list in order. It works by iteratively
stepping through the list and determining if two items are
out of order. If so, then a swap is made. By the end of each
iteration, the correct item will have "bubbled" towards the
end of the list, and the next iteration is made (skipping over
that now filled position until the entire list is sorted).

Because the bubble sort algorithm uses a nested for loop, it is
expected to be O(n^2). More specific, we can calculate the 
complexity of the algorithm more precisely as follows:

n = array.length

for(int i = 0; i < n; i++)
	for (int j = n - 1; j > i; j--)

    i		 # of times the inner loop runs
----------------------------------------------
	0						n - 1
	1						n - 2
	2						n - 3
   ...						 ...
  n - 2					      2
  n - 1					      1



Thus, the total number of iterations is:

 1 + 2 + 3 + ... + n - 1

We can rewrite as a summation:

	n - 1
	 Σ(i)  =  (1/2)(n - 1)(n)
	i = 0

			or

		(n^2 - n) / 2

This follows that the plot generated should 
approximate (n^2 / 2 ) as n increases. 



		













 
