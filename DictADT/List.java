
public interface List<KeyPair> {
	
	public void clear(); 
	public void insert(KeyPair item); 
	public void append(KeyPair item); 
	public KeyPair remove(); 
	public void moveToStart(); 
	public void moveToEnd(); 
	public void prev(); 
	public void next(); 
	public int length(); 
	public int currentPos(); 
	public void moveToPos(int pos); 
	public KeyPair getValue(); 
}
