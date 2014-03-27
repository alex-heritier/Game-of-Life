import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;


public class GoLPanel extends JPanel {
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	Tile[][] grid;
	
	public void paintComponent(Graphics g) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].alive())
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.BLACK);
				g.fillRect(Tile.size()*j, Tile.size()*(i+1), Tile.size(), Tile.size());
				//System.out.printf("Tile #%d\nX: %d\nY: %d\n", grid[i][j].id(), Tile.size()*j, Tile.size()*i);
			}
		}
		
		/*
		g.setColor(Color.WHITE);					//draw tile id
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				g.drawString(grid[i][j].id()+"", Tile.size()*j + Tile.size()/4, Tile.size()*(i+2) - Tile.size()/2);
			}
		}
		*/
		
		/*
		g.setColor(Color.RED);
		for (int i = 0; i < screenWidth/Tile.size(); i++) {			//draw lines
			//g.drawLine(i*Tile.size()-1, 0, i*Tile.size()-1, screenHeight);
			g.drawLine(i*Tile.size(), 0, i*Tile.size(), screenHeight);
			//g.drawLine(i*Tile.size()+1, 0, i*Tile.size()+1, screenHeight);
		}
		g.setColor(Color.RED);
		for (int i = 0; i < screenHeight/Tile.size(); i++) {
			//g.drawLine(0, i*Tile.size()-1, screenWidth, i*Tile.size()-1);
			g.drawLine(0, i*Tile.size(), screenWidth, i*Tile.size());
			//g.drawLine(0, i*Tile.size()+1, screenWidth, i*Tile.size()+1);
		}
		*/
		
		g.setColor(Color.WHITE);
		g.drawString("Gen. " + GoLModel.generation, 0, GoLModel.gridHeight*Tile.size());
		
	}
	
	public void draw(Tile[][] grid) {
		this.grid = grid;
		repaint();
	}
}
