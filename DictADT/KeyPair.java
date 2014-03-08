
public class KeyPair <Key,E> 
{
	private Key k;
	private E item;
	
	KeyPair()
	{
		k = null;
		item = null;
	}
	
	KeyPair(Key k, E item)
	{
		this.k = k;
		this.item = item;
	}
	
	public Key key(){return k;}
	public E value(){return item;}
	
}
