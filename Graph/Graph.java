/**
 * 
 * <b>Graph.java</b>
 * 
 * This is the graph interface. This interface does not use generics
 * and assumes that vertices are String values. 
 * 
 */

public interface Graph
{
	public void Init(String... labels); // Initializes graph
	
	public int numVertices(); // Returns number of vertices

	public int numEdges(); // Returns number of edges

	public String first(String v); // Returns v's first neighbor

	public String next(String v, String current); // Returns v's neighor after current vertex

	public void addVertex(String v); // Adds vertex to graph
	
	public void delVertex(String v); // Deletes vertex from graph and all of its edges
	
	public void setEdge(String source, String dest, int w); // Sets edge of weight w

	public void delEdge(String source, String dest); // Deletes edge between source and dest
	
	public boolean isVertex(String source); // Returns true if string is vertex in graph

	public boolean isEdge(String source, String dest); // Returns true if they share an edge

	public int weight(String source, String dest); // Returns edge weight, if it exists

	public void setMark(String v, int value); // Set v's mark to 1 (visited) or 0 (unvisited)

	public int getMark(String v); // Returns 1 if v was visited and O if not
	
	public void DFS(String start); // Does a depth-first search beginning at start String
	
	public void BFS(String start); // Does a breadth-first search beginning at start String
}
