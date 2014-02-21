'''
	Daniel Gruszczynski
	February 20, 2014
	ITCS 2214-001

	Linear vs Binary Search Graph Generator

'''
import numpy as np
import matplotlib.pyplot as plt

# x and y-axis window
x = [0, 10000]
y = [0, 4500]


# Open and parse file into list
filename = 'data.txt'
f = open(filename)
data = [line.split() for line in f]

# x coordinates
size = [512, 1024, 2048, 4096, 8192]
# y coordinates
unsorted_linear = [row[0] for row in data]
sorted_linear = [row[1] for row in data]
binary = [row[2] for row in data]


# Unsorted Linear Search
plt.plot(size, unsorted_linear, 'co')   
p1, = plt.plot(size, unsorted_linear, 'c--')  

# Sorted Linear Search
plt.plot(size, sorted_linear, 'bo')
p2, = plt.plot(size, sorted_linear, 'b--')

# Binary Search 
plt.plot(size, binary, 'ro')
p3, = plt.plot(size, binary, 'r--')


# Plot O(n/2) 
domain = np.linspace(x[0], x[1], 1000)
p4, = plt.plot(domain, 0.5 * domain , 'b')

# Plot O(log n) and label
domain = np.linspace(0.01, x[1], 1000)
r = np.log(domain) 
p5, = plt.plot(domain, r , 'r')

# Legend
plt.legend([p1,p2,p3,p4,p5], ['Unsorted Linear', 'Sorted Linear', 'Binary', 'Linear O(n)', 'Binary O(log n)' ], loc=2)

# Graph labels
plt.title('Linear vs Binary Search Performance Graph')
plt.xlabel('Data Size')
plt.ylabel('Avg # of Comparisons')

#Axis intervals  (xmin, xmax, ymin, yax)
plt.axis([x[0], x[1], y[0], y[1]])

# Shows graph
#plt.show()

# Saves graph in directory
plt.savefig('graph.png')
