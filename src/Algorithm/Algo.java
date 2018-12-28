package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Algo {
	private static final int[][] DIRECTIONS = { {-1,-1} , {1,1}, {0,-1} ,{1,-1} , {-1,0} , {-1,1} , {0,1}  , {1,0} };
	/* * * * * * * * * * * * * * * * * * SOLVE * * * * * * * * * * * * * * * */
	public List<Coordinate> SOLVE(Maze maze)
	{
		LinkedList<Coordinate> Next2Visit = new LinkedList<Coordinate>();
		Coordinate START = maze.getStartPoint();
		Next2Visit.add(START);

		while(!Next2Visit.isEmpty())
		{
			Coordinate temp = Next2Visit.remove();
			if(!maze.isValidLocation(temp.getX(), temp.getY())
					|| maze.isExplored(temp.getX(), temp.getY()))
				continue;

			if(maze.isBox(temp.getX(), temp.getY()))
			{
				maze.setVistied(temp.getX(), temp.getY(), true);
				continue;
			}

			if(maze.isFinalPoint(temp.getX(), temp.getY()))
			{
				return BackTracking(temp);
			}

			for(int[] dir : DIRECTIONS)
			{
				Coordinate cur = new Coordinate(temp.getX() + dir[0], temp.getY() + dir[1],temp);
				Next2Visit.add(cur);
				maze.setVistied(temp.getX(), temp.getY(), true);
			}
		}
		return Collections.emptyList();

	}
	/* * * * * * * * * * * * * * * * * * Back Trancking * * * * * * * * * * * * * * * */
	private List<Coordinate> BackTracking(Coordinate temp) {
		List<Coordinate> path = new ArrayList<>();
		Coordinate cur = temp;
		while(cur != null)
		{
			path.add(cur);
			cur = cur.getPred();
		}
		return path;
	}
}
