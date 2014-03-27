import java.awt.*;
import java.util.Random;


public class GoLModel {
	public static final int FPS = 10;
	public static final int CHANCE_OF_BEING_ALIVE = 10;	//percent
	static int generation = 0;
	static Tile[][] grid;
	static int screenWidth;
	static int screenHeight;
	static int gridWidth;
	static int gridHeight;

	public static void start(String args[]) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight() - Tile.size();
		gridWidth = screenWidth/Tile.size();
		gridHeight = screenHeight/Tile.size();

		//initialize grid
		grid = new Tile[gridHeight][gridWidth];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Tile(new Random().nextInt(100/CHANCE_OF_BEING_ALIVE));
				/*
				if (i == 5 && j == 5
						|| i == 5 && j == 7
						|| i == 6 && j == 6
						|| i == 6 && j == 7
						|| i == 7 && j == 6)
					grid[i][j] = new Tile(0);
				else
					grid[i][j] = new Tile(1);
				 */
			}

		//System.out.printf("Width: %d\nHeight: %d\n", width, height);
		//System.out.printf("Grid height: %d\nGrid width: %d\nGrid count: %d\n", gridHeight, gridWidth, gridHeight*gridWidth);

		GoLView.launch();

		while (true) {
			GoLView.refresh(grid);
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {

					if ((i > 0 && i < gridHeight-1)
							&& (j > 0 && j < gridWidth-1)
							&& !grid[i][j].alive()
							&& !(grid[i][j-1].alive()
									|| grid[i][j-1].alive()
									|| grid[i-1][j-1].alive()
									|| grid[i-1][j].alive()
									|| grid[i-1][j+1].alive()
									|| grid[i][j+1].alive()
									|| grid[i+1][j+1].alive()
									|| grid[i+1][j].alive()
									|| grid[i+1][j-1].alive()))
						continue;

					if (!grid[i][j].alive()) {
						if (isSurroundedBy3(j, i))
							grid[i][j].mark();
					} else {
						if (!isHealthy(j, i))
							grid[i][j].mark();
					}
				}
			}

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j].marked())
						grid[i][j].flip();
				}
			}
			generation++;
			
			try {Thread.sleep(1000/FPS);}
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	private static boolean isSurroundedBy3(int x, int y) {
		int matches = 0;
		//System.out.println("entering isSurroundedBy3 with x == " + x + " and y == " + y);
		if (x == 0 && y == 0
				|| x == 0 && y == gridHeight-1
				|| x == gridWidth-1 && y == 0
				|| x == gridWidth-1 && y == gridHeight-1) {
			//System.out.println("exiting isSurroundedBy3");
			return false;
		} else if (x == 0) {
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			if (grid[y+1][x+1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches == 3)
				return true;
			else
				return false;
		} else if (y == 0) {
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y+1][x-1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			if (grid[y+1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches == 3)
				return true;
			else
				return false;
		} else if (x == gridWidth-1) {
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x-1].alive())
				matches++;
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y+1][x-1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches == 3)
				return true;
			else
				return false;
		} else if (y == gridHeight-1) {
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y-1][x-1].alive())
				matches++;
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches == 3)
				return true;
			else
				return false;
		} else {
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y-1][x-1].alive())
				matches++;
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			if (grid[y+1][x+1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			if (grid[y+1][x-1].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches == 3)
				return true;
			else
				return false;
		}
	}

	private static boolean isHealthy(int x, int y) {
		int matches = 0;
		//System.out.println("entering isSurroundedBy3 with x == " + x + " and y == " + y);
		if (x == 0 && y == 0
				|| x == 0 && y == gridHeight-1
				|| x == gridWidth-1 && y == 0
				|| x == gridWidth-1 && y == gridHeight-1) {
			//System.out.println("exiting isSurroundedBy3");
			return false;
		} else if (x == 0) {
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			if (grid[y+1][x+1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches >= 2 && matches < 4)
				return true;
			else
				return false;
		} else if (y == 0) {
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y+1][x-1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			if (grid[y+1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches >= 2 && matches < 4)
				return true;
			else
				return false;
		} else if (x == gridWidth-1) {
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x-1].alive())
				matches++;
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y+1][x-1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches >= 2 && matches < 4)
				return true;
			else
				return false;
		} else if (y == gridHeight-1) {
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y-1][x-1].alive())
				matches++;
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches >= 2 && matches < 4)
				return true;
			else
				return false;
		} else {
			if (grid[y][x-1].alive())
				matches++;
			if (grid[y-1][x-1].alive())
				matches++;
			if (grid[y-1][x].alive())
				matches++;
			if (grid[y-1][x+1].alive())
				matches++;
			if (grid[y][x+1].alive())
				matches++;
			if (grid[y+1][x+1].alive())
				matches++;
			if (grid[y+1][x].alive())
				matches++;
			if (grid[y+1][x-1].alive())
				matches++;
			//System.out.println("exiting isSurroundedBy3");

			if (matches >= 2 && matches < 4)
				return true;
			else
				return false;
		}
	}
}
