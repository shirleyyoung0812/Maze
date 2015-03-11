package mazeDFS;
import java.util.*;
import java.awt.Graphics2D;


public class Maze {
	protected int[] grid;
	protected int rows;
	protected int columns;
	
	public Maze(int rows, int columns) {
		//using 1D array to represent cells
		//index / columns = row in the maze
		//index % columns = col in the maze
		this.grid = new int[rows * columns];
		//one cell is surrounded by walls in four directions
		//initially create cells with all walls up
		Arrays.fill(grid, UP | RIGHT | DOWN | LEFT);
		this.rows = rows;
		this.columns = columns;
		createRandomMaze();
	}
	
	protected static final int UP = 1;
	protected static final int RIGHT = 2;
	protected static final int  DOWN = 4;
	protected static final int LEFT = 8;
	
	
	
	private void createRandomMaze() {
		//create all walls that potentially can be broken
		List<Wall> walls = new ArrayList<Wall>();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				if (row > 0) 
					//cell = row * columns + col
					//cell / columns = row
					//cell % columns = col
					// represent the grid in the maze
					//the upper wall of the lower cell is the lower wall of the upper cell
					// the left wall of the right cell is the right wall of the left cell
					//so we only need to consider two directions
					walls.add(new Wall(row * columns + col, UP));
				if (col > 0)
					walls.add(new Wall(row * columns + col, LEFT));
			}
		}
		
		DisjointSet diset = new ArrayDisjointSet(rows * columns);
		//Object for generating random numbers
		Random rand = new Random();
		while (diset.size() > 1) {
			//get an index randomly
			int wallIndex = rand.nextInt(walls.size());
			int cell1 = walls.get(wallIndex).cell;
			int cell2 = (walls.get(wallIndex).direction == UP) ?
					cell1 - columns ://choose the cell and the one above it, break the upper wall
						cell1 - 1;//choose the one left to it, break the left wall
			//if there is no path between two cells
			//i.e., they are not in the same set
			if (diset.find(cell1) != diset.find(cell2)) {
				if (walls.get(wallIndex).direction == UP) {
					//break the upper wall of cell1 
					//which is also the lower wall of cells2
					grid[cell1] ^= UP;
					grid[cell2] ^= DOWN;
				}
				else {
					grid[cell1] ^= LEFT;
					grid[cell2] ^= RIGHT;
				}
				diset.union(cell1, cell2);
			}
			//the wall is knocked down, dead, disappeared, over...
			walls.remove(wallIndex);
		}
	}
	public static class Wall {
		private final int cell;
		private final int direction;
		public Wall(int cell, int direction) {
			this.cell = cell;
			this.direction = direction;
		}
	}
	
	
	
	/**
	 * The following methods are used to display the maze
	 * @param g
	 */
	protected int startX = 8;
	protected int startY = 30;
	protected int size = 10;
	
	public void display(Graphics2D g) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				int cell = grid[col + row*columns];
				if ((cell & LEFT) != 0) 
					drawLeftWall(g, row, col);
				if ((cell & RIGHT) != 0)
					drawRightWall(g, row, col);
				if ((cell & UP) != 0)
					drawUpperWall(g, row, col);
				if ((cell & DOWN) != 0)
					drawLowerWall(g, row, col);
			}
		}
	}
	
	private void drawLeftWall(Graphics2D g, int row, int col) {
		g.drawLine(startX + size * col, startY + size * row, 
				startX + size * col, startY + size * (row + 1));
	}
	private void drawRightWall(Graphics2D g, int row, int col) {
		g.drawLine(startX + size * (col + 1), startY + size * row, 
				startX + size * (col + 1), startY + size * (row + 1));
	}
	private void drawUpperWall(Graphics2D g, int row, int col) {
		g.drawLine(startX + size * col, startY + size * row, 
				startX + size * (col + 1), startY + size * row);
	}
	private void drawLowerWall(Graphics2D g, int row, int col) {
		g.drawLine(startX + size * col, startY + size * (row + 1), 
				startX + size * (col + 1), startY + size * (row + 1));
	}
}
