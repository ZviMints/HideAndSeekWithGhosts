package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Algo {
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
	private Maze maze;

	/* * * * * * * * * * * * * * * * * * SOLVE * * * * * * * * * * * * * * * */
	public List<Coordinate> SOLVE(Maze maze)
	{
		this.maze = maze;
		Coordinate START = maze.getStartPoint(); // Define Start Point
		return FindShortestPath(START);
	}
	/* * * * * * * * * * * * * * * * * * FindShortestPath * * * * * * * * * * * * * * * */
	private List<Coordinate> FindShortestPath(Coordinate START) {
        Queue<Coordinate> Q = new LinkedList<>();
		Q.add(START);

		while(!Q.isEmpty())
		{	
			Coordinate temp = Q.remove();
			int x = temp.getX();
			int y = temp.getY();
			// Base case : Check if its in MAP
			if(!maze.isValidLocation(x, y) || maze.isExplored(x, y))
				continue;
			// Check if its a valid point
			if(maze.isBox(x, y))
			{
				maze.setVistied(x, y, true);
				continue;
			}
			// Base case : check if we finished
			if(maze.isFinalPoint(x, y))
			{
				return BackTracking(temp);
			}
			// Must be on a path
			maze.setVistied(x, y, true);
			for(int i=0; i<DIRECTIONS.length; i++)
			{
				int[] dir = DIRECTIONS[i];
				Coordinate cur = new Coordinate(x + dir[0], y + dir[1],temp);
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
		while(cur != null)
		{
			path.addFirst(cur);
			cur = cur.getPred();
		}
		return path;
	}
}
