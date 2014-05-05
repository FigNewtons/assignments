import java.util.*;

/**
 * 
 * <b>Graphl.java<b>
 * 
 * This is an adjacency graph implementation using a hashmap
 * for String vertex labels.
 * 
 * @author Daniel Gruszczynski
 * @date April 10, 2014
 * @version 1.0
 *
 */

public class Graphl implements Graph
{
	private HashMap<String, GraphList> vertex; // Stores strings as keys to linked lists
	private int numEdges;   // Number of edges 
	private HashMap<String, Integer> marked; // Stores strings as keys to integer value (1 - visited)
	
	// Used for Dijkstra's algorithm
	private HashMap<String, Integer> distance;
	private HashMap<String, ArrayList<String>> path;
	
	// Used for marking vertices during graph traversal
	private static final int UNVISITED = 0;
	private static final int VISITED = 1;
	
	// Constructor using varargs
	public Graphl(String... labels){
		Init(labels);
	}
	
	// Initializes the hashmaps with a set capacity and fill ratio of 0.5
	public void Init(String[] list) {
		
		vertex = new HashMap<String, GraphList>(list.length, (float)0.5);
		marked = new HashMap<String, Integer>(list.length, (float)0.5);
		distance = new HashMap<String, Integer>(list.length, (float)0.5);
		path = new HashMap<String, ArrayList<String>>(list.length, (float)0.5);
		
		numEdges = 0;
		
		for(String s: list){
			vertex.put(s, new GraphList());
			marked.put(s, UNVISITED);
			distance.put(s, Integer.MAX_VALUE);
			path.put(s, new ArrayList<String>());
		}
	}
	
	// Returns number of vertices
	public int numVertices(){
		return vertex.size();
	}
	
	// Returns number of edges
	public int numEdges(){
		return numEdges;
	}
	
	// Returns the first edge's dest vertex
	public String first(String v){
		if(!isVertex(v) || vertex.size() == 0)
			return null;
		
		GraphList glist = vertex.get(v);			
		glist.moveToStart();
		
		return glist.getValue().vertex();
	}
	
	// Returns the next edge's dest vertex
	public String next(String v, String current){
		if(isVertex(v) && isVertex(current)){
			Edge<String> edge = null;
			if(isEdge(v, current)){
				GraphList glist = (GraphList) vertex.get(v);
				glist.next();
				edge = glist.getValue();
			}
			
			if(edge != null)
				return edge.vertex();
		}
		
		return null;
	}
	
	// Add a new String vertex to graph
	public void addVertex(String v){
		vertex.put(v, new GraphList());
		marked.put(v, UNVISITED);
		distance.put(v, Integer.MAX_VALUE);		
		path.put(v, new ArrayList<String>());
	}
	
	// Deletes vertex and its associated edges
	public void delVertex(String v){
		if(isVertex(v)){
	    	vertex.remove(v); // Remove vertex
	    	marked.remove(v); // Remove marked key-pair
	    	distance.remove(v); // Same
	    	path.remove(v);
	    	
			Set set = vertex.entrySet();
			Iterator i = set.iterator();
		
			// Iterate through remaining vertices' edge list to remove
			// edges connected to v
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry)i.next();         
				GraphList temp = (GraphList) me.getValue();
				
				for(temp.moveToStart(); temp.currentPos() != temp.length(); temp.next())
				{
					if(temp.getValue().vertex() == v)
						temp.remove();
				}			
			}	
		}
	}
	
	// Sets a weighted edge between two vertices
	public void setEdge(String source, String dest, int weight){
		assert weight != 0 : "Cannot set weight to zero";
		
		if(!isVertex(source) || !isVertex(dest))
			return;
				
		GraphList glist =  vertex.get(source);
		Edge<String> currEdge = new Edge<String>(dest, weight);
				
		// If the two vertices already share an edge, remove and
		// reinsert the edge with new weight
		if(isEdge(source, dest)){
			glist.remove(); // currentPos stored when isEdge runs
			glist.insert(currEdge);
		}
		else{
			numEdges++;
		    glist.insert(currEdge);
		}
	}
	
	// Deletes edge between two vertices
	public void delEdge(String source, String dest){
		if(!isVertex(source) || !isVertex(dest))
			return;
		
		if(isEdge(source, dest)){
			GraphList glist = vertex.get(source);
			glist.remove();
			numEdges--;
		}
	}
	
	// Returns true if vertex in graph
	public boolean isVertex(String source){
		if(vertex.containsKey(source))
			return true;
		
		return false;
	}
	
	// Returns true is the two vertices share an edge
	public boolean isEdge(String source, String dest){		
		if(!isVertex(source) || !isVertex(dest))
			return false;
			
		GraphList glist = vertex.get(source);
		Edge<String> edge = glist.getValue();

		// Check if dest is the current neighbor in list
		if ((edge != null) && (edge.vertex() == dest))
				return true;
		
		// Traverse linked list until edge is found
		for(glist.moveToStart(); glist.currentPos() < glist.length(); glist.next()){
			if(glist.getValue().vertex() == dest)
				return true;
		}
		return false;
	}
	
	// Returns the weight of the edge between the vertices
	// If one of the vertices do not exist, the function returns -1
	public int weight(String source, String dest){	
		if(!isVertex(source) || !isVertex(dest))
			return -1;
		
		GraphList glist = (GraphList) vertex.get(source);
		
		if(isEdge(source, dest))
			return glist.getValue().weight();
		
		return 0;
	}
	
	// Changed marked state of vertex
	public void setMark(String v, int value){
		if(isVertex(v))
			marked.put(v, value);
	}
	
	// Returns 1 if vertex was visited, 0 if unvisited, and -1 if vertex
	// not in graph
	public int getMark(String v){
		if(isVertex(v))
			return marked.get(v);
		
		return -1;
	}
	
	// Depth-first search
	public void DFS(String start){	
		if(isVertex(start)){
			reset(marked, UNVISITED);
			traverse(start);
		}
		
		return;
	}
	
	// Breadth-first search
	public void BFS(String start){
		
		if(!isVertex(start))
			return;
		
		reset(marked, UNVISITED);
		Queue<String> queue = new AQueue<String>(numVertices());
		
		queue.enqueue(start);
		setMark(start, VISITED);
		
		while(!queue.isEmpty()){
			String v = queue.dequeue();
			GraphList glist = vertex.get(v);
			
			System.out.println("Visited: " + v);
			
			for(glist.moveToStart(); glist.currentPos() != glist.length(); glist.next())
			{
				String neighbor = glist.getValue().vertex();
				
				if(marked.get(neighbor) == UNVISITED){
					setMark(neighbor, VISITED);
					queue.enqueue(neighbor);
				}
			}
		}
	}

	// Computes the shortest path from a starting vertex 
	// using Dijkstra's algorithm (assumes a weighted graph)
	public void Dijkstra(String start){		
		
		// Initialize distance array to "infinity", and reset marks and paths
		reset(distance, Integer.MAX_VALUE);
		reset(marked, UNVISITED);
		reset(path);
		
		// Set start string to distance of zero 
		distance.put(start, 0);
	
		// Iterate over the vertices
		for(int i = 0; i < numVertices(); i++){
			String v = minVertex(); // Get vertex with min distance
			marked.put(v, VISITED); // Mark as visited
			
			// Add the vertex itself to end of the path list
			if (start != v)
				path.get(v).add(v);
			
			if(distance.get(v) == Integer.MAX_VALUE)
				return; // Vertex is unreachable
			
			// Update distances and paths
			GraphList glist = vertex.get(v);
			for(glist.moveToStart(); glist.currentPos() != glist.length(); glist.next())
			{
				String neighbor = glist.getValue().vertex();
				if(distance.get(neighbor) > (distance.get(v) + weight(v, neighbor)))
				{
					distance.put(neighbor, distance.get(v) + weight(v, neighbor));		
					path.get(neighbor).add(v);
				}
			}
		}
		
		printDijkstra(start);
	}

	// Graph traversal for DFS
	private void traverse(String start){
		setMark(start, VISITED);
		System.out.println("Visited: " + start);
		
		GraphList glist = vertex.get(start);
		
		for(glist.moveToStart(); glist.currentPos() != glist.length(); glist.next())
		{
			String s = glist.getValue().vertex();
			if((int)marked.get(s) == 0)
				traverse(s);			
		}
		return;
	}
	
	// Resets paths for Dijkstra's algorithm 
	private void reset(HashMap<String, ArrayList<String>> hash){
 		Set set = hash.entrySet();
		Iterator i = set.iterator();
	
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();         
			me.setValue(new ArrayList<String>());
		}
	}

	// Resets either marked or distance hash map 
	// Marked - UNVISITED
	// Distance - Integer.MAX_VALUE
	private void reset(HashMap<String, Integer> hash, Integer value){
		Set set = hash.entrySet();
		Iterator i = set.iterator();
	
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();         
			setMark((String) me.getKey(), value);
		}
	}
	
	
	// Returns the next unmarked min vertex
	private String minVertex(){
		String v = null;
		
		Set set = marked.entrySet();
		Iterator i = set.iterator();
		
		// Set v to any unmarked vertex
		while(i.hasNext()){
			Map.Entry<String, Integer> me = (Map.Entry) i.next();
			
			if(me.getValue() == UNVISITED)
				v = me.getKey();
		}
		
		i = set.iterator(); // reset iterator
		
		// Now find the smallest distance to an unmarked vertex 
		while(i.hasNext()){
			Map.Entry<String, Integer> me = (Map.Entry) i.next();
			if((me.getValue() == UNVISITED) && (distance.get(me.getKey()) < distance.get(v)))
				v = me.getKey();
		}
		
		return v;	
	}
	
	// Neatly outputs shortest path table 
	private void printDijkstra(String start){
		Set d = distance.entrySet();
		Iterator i = d.iterator();
		
		System.out.println("\nSource\tDest.\tDistance\tShortest Path");
		while(i.hasNext()){
			Map.Entry<String, Integer> me = (Map.Entry) i.next();
			ArrayList<String> temp  = path.get(me.getKey());
			
			String distance = me.getValue().toString();

			// Unreachable path		
			if((me.getValue() == 0) && (!me.getKey().equals(start)))
					distance = "Inf";	

			System.out.println(" " + start + "\t" + me.getKey() + "\t" + 
							   distance + "\t\t" + temp.toString());
		}
	}
	
}
