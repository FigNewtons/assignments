/**
 * <b>Edge</b><br>
 * 
 * This is the edge for our adjacency graph list. It stores
 * the destination vertex and the edge weight. This is fed into
 * our link class as a single element. 
 * 
 *
 */

class Edge<E>
{
	private E vertex;
	private int weight;

	public Edge(E v, int w){
		vertex = v;
		weight = w;
	}

	public E vertex(){
		return vertex;
	}
	
	public int weight(){
		return weight;
	}
}
