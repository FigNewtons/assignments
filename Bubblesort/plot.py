'''
	Daniel Gruszczynski
	January 15, 2014
	ITCS 2214-001

	Bubble sort graph generator

'''
import numpy as np
import matplotlib.pyplot as plt

# x and y-axis window
x = [0, 10000]
y = [0, 20000000]


# Open and parse file into list
filename = 'data.txt'
f = open(filename)

data = [[int(num) for num in line.split()] for line in f]

# Plot data and label
plt.plot(data[0], data[1], 'ro')   # plots red dots
plt.plot(data[0], data[1], 'r--')  # plots red dashed lines
plt.text(5500, 15000000, 'Actual Result')

# Plot O(n^2) and label
domain = np.linspace(x[0], x[1], 1000)
plt.plot(domain, domain**2)
plt.text(2500, 15000000, 'O(n^2)\nExpected')

# Graph labels
plt.title('Bubble Sort Performance Graph')
plt.xlabel('Data Size')
plt.ylabel('Number of Swap Operations')

# Axis intervals  (xmin, xmax, ymin, yax)
plt.axis([x[0], x[1], y[0], y[1]])

# Shows graph
#plt.show()

# Saves graph in directory
plt.savefig('graph.png')
