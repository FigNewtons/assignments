/**
 *  <b>GraphList</b><br>
 *  
 *  This is the subclass of a singly-linked list for our graph.
 * 
 *
 */
public class GraphList extends LinkedList<Edge<String>>
{
	private LinkedList<Edge<String>> list;
	
	public GraphList(){
		list = new LinkedList<Edge<String>>();	
	}
	
	public LinkedList<Edge<String>> list(){
		return list;
	}	
}
