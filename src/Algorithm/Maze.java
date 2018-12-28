package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
	private char[][] maze; // Original Matrix
	private boolean[][] visited; // visted Matrix
	private Coordinate _START;
	private List<Coordinate> _END;
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public Maze(char[][] maze)
	{
		_END = new ArrayList<Coordinate>();
		this.maze = maze;
        visited = new boolean[maze.length][maze[0].length];

		for(int i=0; i<maze.length; i++)
			for(int j=0; j<maze[0].length; j++)
			{
				if(maze[i][j] == 'M') 
					_START = new Coordinate(i, j);
//				else if(maze[i][j] == 'P' || maze[i][j] == 'P') _END.add(new Coordinate(i, j));
				else if(maze[i][j] == 'F') _END.add(new Coordinate(i, j));

			}
	}
	/* * * * * * * * * * * * * * * * * * Getters and Setters * * * * * * * * * * * * * * * */
	public int getHeight()
	{
		return maze.length;
	}
	public int getWidth()
	{
		return maze[0].length;
	}
	public Coordinate getStartPoint()
	{
		return this._START;
	}
	public boolean isFinalPoint(int x,int y)
	{
		for(Coordinate cur : _END)
			if(x == cur.getX() && y == cur.getY())
				return true;
		return false;
	}
	public boolean isStartPoint(int x,int y)
	{
		return x == _START.getX() && y == _START.getY();
	}
	public boolean isExplored(int r, int c)
	{
		return visited[r][c];
	}
	public boolean isBox(int r, int c)
	{
		return maze[r][c] == 'B';
	}
	public void setVistied(int r, int c, boolean val)
	{
		visited[r][c] = val;
	}
	public boolean isValidLocation(int r, int c)
	{
		if(r < 5 ) return false;
		else if(r >= getHeight() - 5) return false;
		else if(c < 5) return false;
		else if (c >= getWidth() - 5) return false;
		else return true;
	}
	public String ReturnMatWithPath(List<Coordinate> path)
	{
		char[][] temp = Arrays.stream(maze)
				.map(char[]::clone)
				.toArray(char[][]::new);
		for (Coordinate coordinate : path) {
			if (isStartPoint(coordinate.getX(), coordinate.getY()) || isFinalPoint(coordinate.getX(), coordinate.getY())) {
				continue;
			}
			temp[coordinate.getX()][coordinate.getY()] = '*';
		}
		return toString(temp);
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString(char[][] maze) {
		  StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
	        for (int row = 0; row < getHeight(); row++) {
	            for (int col = 0; col < getWidth(); col++) {
	                if (maze[row][col] == ' ') {
	                    result.append(' ');
	                } else if (maze[row][col] == 'B') {
	                    result.append('B');
	                } else if (maze[row][col] == 'M') {
	                    result.append('M');
	                } else if (maze[row][col] == 'P') {
	                    result.append('P');
	                } else if (maze[row][col] == 'F') {
	                    result.append('F');
	                } else {
	                    result.append('*');
	                }
	            }
	            result.append('\n');
	        }
	        return result.toString();
	}
	/* * * * * * * * * * * * * * * * * * reset * * * * * * * * * * * * * * * */
	public void reset()
	{
		for(int i=0; i< visited.length; i++)
			Arrays.fill(visited[i], false);
	}
}
