/**
 * This Class is Responsible to Represent a Maze, Maze is a char[][] matrix
 * with Objects such that Pacman that can be noticed by 'P' in the matrix, moreover
 * we can see Objects Such that Fruit == 'F' ,Ghosts and Players.
 * @version 4.0
 * @author Tzvi Mints and Or Abuhazira
 */
package Algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
	private char[][] MATRIX; // Original Matrix
	private boolean[][] visited; // Visted Matrix
	private Coordinate _START; // Start Coordinate Point
	private List<Coordinate> _END; // End Coordinate Point
	private static Maze maze; // Current Object

	/* * * * * * * * * * * * * * * * * * Singleton * * * * * * * * * * * * * * * */
	/**
	 * This Method is responsible to create only one type of Maze during the Program.
	 * Design Pattern - Singleton
	 * @param mat is the input Matrix
	 * @return Maze Object
	 */
	public static Maze getInstance(char[][] mat) {
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
	private Maze(char[][] mat)
	{
		this.Update(mat);
	}
	/* * * * * * * * * * * * * * * * * * Update * * * * * * * * * * * * * * * */
	/**
	 * This Method is responsible to get a char[][] and to set M,F,P in the needed Place
	 * M represent Player, F represent Fruit, P represent Pacman
	 * @param mat is the input matrix char[][]
	 */
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
				else if(mat[i][j] == 'P' || mat[i][j] == 'F') 
					_END.add(new Coordinate(i, j));
			}
	}
	/* * * * * * * * * * * * * * * * * * Getters and Setters * * * * * * * * * * * * * * * */
	/** This Function is responsible to return the HIGHT of the Matrix **/
	public int getHeight() { return MATRIX.length; }
	/** This Function is responsible to return the WIDTH of the Matrix **/
	public int getWidth() { return MATRIX[0].length; }
	/** This Function is responsible to return the Start Point of the Algorithm **/
	public Coordinate getStartPoint() { return this._START; }
	/** This Function is responsible to return the End Point of the Algorithm **/
	public boolean isFinalPoint(int x,int y)
	{
		for(Coordinate cur : _END)
			if(x == cur.getX() && y == cur.getY())
				return true;
		return false;
	}
	/** This Function is responsible to check if Point is the Start Point of the Algorithm **/
	public boolean isStartPoint(int x,int y) { return x == _START.getX() && y == _START.getY(); }
	/** This Function is responsible to check if Point is Explored During the the Algorithm **/
	public boolean isExplored(int r, int c) { return visited[r][c]; }
	/** This Function is responsible to check if Point is a Box **/
	public boolean isBox(int r, int c){ return MATRIX[r][c] == 'B'; }
	/** This Function is responsible to check if Point is a Ghost **/
	public boolean isGhost(int r, int c){ return MATRIX[r][c] == 'G'; }
	/** This Function is responsible to set Visited to a Point **/
	public void setVistied(int r, int c, boolean val) { visited[r][c] = val; }
	/** This Function is responsible to check if a input point is in [-5,5] pixels radius from a ghost **/
	public boolean NearGhost(int r, int c)
	{ for(int  i = r - 5; i <= r + 5 ; i++)
		for(int j = c - 5; j <= c + 5; j++)
			if(MATRIX[i][j] == 'G')
				return true;
	return false;
	}
	/** This Function is responsible to check if a input point is in border **/
	public boolean isValidLocation(int r, int c)
	{
		if(r < 5 ) return false;
		else if(r >= getHeight() - 5) return false;
		else if(c < 5) return false;
		else if (c >= getWidth() - 5) return false;
		else return true;
	}
	/* * * * * * * * * * * * * * * * * * Return String With Path * * * * * * * * * * * * * * * */
	/**
	 * This Method is Responsible to Return Matrix with the path from S Point to E point
	 * when the path will be represented by "*" for example: S ********* E
	 * @param path is the path of the Algorithm
	 * @return String that represent the Matrix
	 */
	public String ReturnMatWithPath(List<Coordinate> path)
	{
		char[][] temp = Arrays.stream(MATRIX).map(char[]::clone).toArray(char[][]::new);
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
				} else if (maze[row][col] == 'P') {
					result.append('P');
				} else if (maze[row][col] == 'F') {
					result.append('F');
				} else if (maze[row][col] == 'G') {
					result.append('G');
				} else if (maze[row][col] == 'M') {
					result.append('M');
				} else {
					result.append('*');
				}
			}
			result.append('\n');
		}
		return result.toString();
	}
	/* * * * * * * * * * * * * * * * * * reset * * * * * * * * * * * * * * * */
	/**
	 * This Method is used to Reset the Visited Boolean Array
	 * When the Thread Refresh everytime there no need for this, but we can use this
	 * if we want to go While(Path is not empty) for instance
	 */
	public void reset()
	{
		for(int i=0; i< visited.length; i++)
			Arrays.fill(visited[i], false);
	}
}
