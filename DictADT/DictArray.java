import java.util.ArrayList;

public class DictArray<KeyPair> implements List<KeyPair>
{
	private static final int DEFAULT_SIZE = 10;
	
	private int maxSize;	
	private int listSize;	
	private int currentPos; 		

	private ArrayList<KeyPair> listArray;	
	
	
	DictArray() {this(DEFAULT_SIZE);}

	DictArray(int size)
	{
		maxSize = size;
		listSize = 0;
		currentPos = 0;	
		listArray = new ArrayList<KeyPair>(size);
	}
	
	public void clear()
	{
		listSize = 0;
		currentPos = 0;
	}

	public void insert(KeyPair item)
	{
		assert listSize < maxSize: "List capacity exceeded";
		for(int index = listSize; index > currentPos; index--)
			listArray.set(index, listArray.get(index - 1));
		listArray.set(currentPos, item);
		listSize++;
	}
	
	public void append(KeyPair item)
	{
		assert listSize < maxSize : "List capacity exceeded";
		listArray.add(listSize++, item);
	}

	public KeyPair remove()
	{
		if(currentPos < 0 || currentPos >= listSize)
			return null;
		
		KeyPair item = listArray.get(currentPos);
		
		for(int index = currentPos; index < listSize - 1; index++)
			listArray.set(index, listArray.get(index + 1));
		
		return item;
	}
	
	public void moveToStart(){currentPos = 0;}

	public void moveToEnd(){currentPos = listSize;}
	
	public void prev()
	{
		if(currentPos != 0)
			currentPos--;
	}
	
	public void next()
	{
		if(currentPos < listSize)
			currentPos++;
	}

	public int length(){return listSize;}
	
	public int currentPos(){return currentPos;}
	
	public void moveToPos(int pos)
	{
		assert (pos >= 0) && (pos <= listSize): "Position out of range";
		currentPos = pos;
	}
	
	public KeyPair getValue()
	{
		assert (currentPos >= 0) && (currentPos < listSize): "No current key-pair";
		return listArray.get(currentPos);
	}
}
