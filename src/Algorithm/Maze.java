package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
	private char[][] MATRIX; // Original Matrix
	private boolean[][] visited; // visted Matrix
	private Coordinate _START;
	private List<Coordinate> _END;
	private static Maze maze;

	/* * * * * * * * * * * * * * * * * * Singleton * * * * * * * * * * * * * * * */
	public static Maze Singleton(char[][] mat) {
		// To Ensure Only One Instance Is Created 
		if (maze == null) 
		{ 
			maze = new Maze(mat); 
			return maze;
		}
		else
			maze.Update(mat); 
		return maze;
	}
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public Maze(char[][] mat)
	{
		this.Update(mat);
	}
	/* * * * * * * * * * * * * * * * * * Update * * * * * * * * * * * * * * * */
	private void Update(char[][] mat)
	{
		_END = new ArrayList<Coordinate>();
		MATRIX = mat;
		visited = new boolean[mat.length][mat[0].length];
		for(int i=0; i<mat.length; i++)
			for(int j=0; j<mat[0].length; j++)
			{
				if(mat[i][j] == 'M') 
					_START = new Coordinate(i, j);
				else if(mat[i][j] == 'P' || mat[i][j] == 'F') _END.add(new Coordinate(i, j));
			}
	}

	/* * * * * * * * * * * * * * * * * * Getters and Setters * * * * * * * * * * * * * * * */
	public int getHeight()
	{
		return MATRIX.length;
	}
	public int getWidth()
	{
		return MATRIX[0].length;
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
	public boolean isGhost(int r, int c)
	{
		if(MATRIX[r][c] == 'G') return true;
		return false;
	}
	public boolean isBox(int r, int c)
	{
		return MATRIX[r][c] == 'B';
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
		char[][] temp = Arrays.stream(MATRIX)
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
