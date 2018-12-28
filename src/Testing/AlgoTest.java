package Testing;

import java.util.List;

import org.junit.jupiter.api.Test;

import Algorithm.Algo;
import Algorithm.Coordinate;
import Algorithm.GameToMatrix;
import Algorithm.Maze;
import Map.Map;
import Game.Game;
import Geom.Point3D;
import Player.Player;

class AlgoTest {

	@Test
	void test() throws InterruptedException {
		char[][] arr = {{'M',    ' ',    ' ',    ' ',    ' ', ' '},
				       {' ',     ' ',   'B',    ' ',    'B', 'F'},
				       {' ',     ' ',    ' ',   ' ',    'B', ' '}};


		Game game = new Game("./data/Ex4_OOP_example3.csv");
		Point3D p00 = new Point3D(32.105848,35.202429);
		Point3D p01 = new Point3D(32.105848,35.212541);
		Point3D p10 = new Point3D(32.101951,35.202429);
		Point3D p11 = new Point3D(32.101951,35.212541);

		Map map = new Map("./img/Background.png", p00, p01, p10, p11, 1433,642);   // NOTE: 2 Points p10,p01 is Enough! but we did for 4 points.
		GameToMatrix mat = new GameToMatrix(new Player(map.getCordFromPixel(new Point3D(0,0,0)),"TZVI AND OR"),game.getFruitList(),game.getBoxList(),game.getGhostList(),game.getPacmanList(),map);
//		mat.POPUP();
//		Thread.sleep(100000);
		Maze maze = new Maze(arr);
		Algo algo = new Algo();
		List<Coordinate> path = algo.SOLVE(maze);
		System.out.println(path.toString());
		if(path.isEmpty()) 
			System.out.println("null");
		else
		{
			System.out.println(maze.ReturnMatWithPath(path));
			maze.reset();
		}

	}

}
