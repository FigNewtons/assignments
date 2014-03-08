
public class UnsortedDict<Key, E> implements Dictionary<Key, E>
{
	private static final int DEFAULT_SIZE = 10;
	private DictArray<KeyPair<Key,E>> array;
	
	UnsortedDict()
	{
		this(DEFAULT_SIZE);
	}
	
	UnsortedDict(int size)
	{
		array = new DictArray<KeyPair<Key, E>>(size);
	}
	
	public void clear()
	{ 
		array.clear();
	}
	
	public void insert(Key k, E item) // insert a KeyPair
	{
		KeyPair<Key, E> temp = new KeyPair<Key, E>(k, item);
		array.append(temp);
	}
	
	public E remove(Key k) // remove item with Key k
	{
		E temp = find(k);
		
		if(temp != null)
			array.remove();
		
		return temp;
	}
	
	public E removeAny() // remove item at end of array
	{
		if(size() != 0)
		{
			array.moveToEnd();
			array.prev();
			KeyPair<Key, E> item = array.remove();
			return item.value();			
		}
		
		else return null;
	}	
	
	public E find(Key k) //sequential search O(n)
	{
		for(array.moveToStart(); array.currentPos() < array.length(); array.next())
		{
			KeyPair<Key, E> temp = array.getValue();
			if(k == temp.key())
				return temp.value();
		}
		
		return null;
	}
	
	public int size() // Dictionary size
	{
		return array.length();
	}
}
