/**
 * The class is responsible to find the Shorest Path From input matrix
 * @version 4.0
 * @author Tzvi Mints and Or Abuhazira
 */
package Algorithm;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class FindShortestPathFromMat {
	private static final int[][] DIRECTIONS = { 
			{0,-1}, // 0° 
			{1,0}, // 90°
			{0,1}, //180°
			{-1,0}, //270°
			{-1,-1}, //315°
			{1,-1}, // 45°
			{-1,1}, //225°
			{1,1}, // 135°
	};
	private Maze maze; // Hold Maze

	/* * * * * * * * * * * * * * * * * * SOLVE * * * * * * * * * * * * * * * */
	public List<Coordinate> SOLVE(Maze maze)
	{
		this.maze = maze; // Initialize Maze
		Coordinate START = maze.getStartPoint(); // Define Start Point
		List<Coordinate> ans =  FindShortestPath(START); // Find Shortest Path
		return ans; // Return the BackTracking Path
	}
	/* * * * * * * * * * * * * * * * * * FindShortestPath * * * * * * * * * * * * * * * */
	private synchronized List<Coordinate> FindShortestPath(Coordinate START) {
		Queue<Coordinate> Q = new LinkedList<>(); // Initalize new Queue
		Q.add(START); // Add Start Pixel to the Queue

		while(!Q.isEmpty()) // While Queue is no Empty
		{	
			Coordinate temp = Q.remove(); // Remove first one in the Queue and initialize temp in that value
			int x = temp.getX(); 
			int y = temp.getY();
			// Base case : Check if its in MAP
			if(!maze.isValidLocation(x, y) || maze.isExplored(x, y))
				continue; // To The Next Step
			// Check if its a valid point
			if(maze.isBox(x, y))
			{
				maze.setVistied(x, y, true);
				continue; // To The Next Step
			}
			if(maze.NearGhost(x, y)) // if its Ghost
			{
				maze.setVistied(x, y, true);
				continue; // To The Next Step
			}
			// Base case : check if we finished
			if(maze.isFinalPoint(x, y))
			{
				return BackTracking(temp); // BackTrack by pred->pred->...->pred->null for find the path
			}
			// Must be on a path
			maze.setVistied(x, y, true); // Set current Coordinate as visited	
			
			for(int i=0; i<DIRECTIONS.length; i++) // For all dir in DIRECTIONS
			{
				int[] dir = DIRECTIONS[i];
				int NextX = x + dir[0]; // Next Coordinate we can move in X Values
				int NextY = y + dir[1]; // Next Coordinate we can move in Y values
				Coordinate cur = new Coordinate(NextX, NextY,temp);
				Q.add(cur);
			}
		}
		return Collections.emptyList(); // Return empty list
	}
	/* * * * * * * * * * * * * * * * * * Tracking * * * * * * * * * * * * * * * */
	// Can use ArrayList for BackTracking
	private List<Coordinate> BackTracking(Coordinate temp) {
		LinkedList<Coordinate> path = new LinkedList<>();
		Coordinate cur = temp;
		while(cur != null) // While there still someone on the way from S ---> D
		{
			path.addFirst(cur); // Add to the List
			cur = cur.getPred(); // Get the the pred
		}
		return path;
	}
	/* * * * * * * * * * * * * * * * * * Getters and Setters * * * * * * * * * * * * * * * */
	/**
	 * This Method is Responsible to Return the Directions
	 * @return int[][] of directions
	 */
	public static int[][] getDir() {
		return DIRECTIONS;
	}
}
