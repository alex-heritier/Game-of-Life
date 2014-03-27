
public class Tile {
	public static int classId = 0;
	private int id;
	private static int size = 5;
	private boolean alive;
	private boolean marked;
	
	public Tile(int rand) {
		id = classId;
		classId++;
		if (rand == 0)
			alive = true;
		else
			alive = false;
	}
	
	public void flip() {
		marked = false;
		alive = !alive;
	}
	
	public void mark() {
		marked = true;
	}
	
	public boolean marked() {
		return marked;
	}
	
	public boolean alive() {
		return alive;
	}
	
	public int id() {
		return id;
	}
	
	public static int size() {
		return size;
	}
}
