package Algorithm;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Geom.Point3D;
import Map.Map;
import Objects.Box;
import Objects.Fruit;
import Objects.Ghost;
import Objects.Pacman;
import Player.Player;

public class GameToMatrix {
	char[][] mat;
	static JFrame frame;
	static JTextArea ta;
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public GameToMatrix(Player player, List<Fruit> FruitsList, List<Box> BoxsList, List<Ghost> GhostsList, List<Pacman> PacmansList,Map map)
	{
		int w = map.getWidth();
		int h = map.getHeight();
		mat = new char[h][w];
		for(int i=0; i<mat.length;i++)
			for(int j=0;j<mat[i].length;j++)
				mat[i][j]=' ';

		if(player != null)
		{
			Point3D p = map.getPixelFromCord(player.getP());
			mat[(int)p.y()][(int)p.x()] = 'M';
		}

		for(Ghost ghost : GhostsList)
		{
			Point3D p = map.getPixelFromCord(ghost.getP());
			mat[(int)p.y()][(int)p.x()] = 'G';
		}
		for(Box b : BoxsList)
		{
			Point3D b_p0 = map.getPixelFromCord(b.getP0());
			Point3D b_p1 = map.getPixelFromCord(b.getP1());
			for(int i = (int)b_p0.x() ; i< (int)b_p1.x(); i++)
			{
				int j1 = (int)b_p1.y();
				int j2 = (int)b_p0.y();
				for(int j=0; j<5; j++)
				{
				mat[j1++][i] = 'B';
				mat[j2++][i] = 'B';
				}
			}

			for(int j = (int)b_p1.y() ; j < (int)b_p0.y(); j++)
			{
				int i1 = (int)b_p0.x();
				int i2 = (int)b_p1.x();
				for(int i=0; i<5; i++)
				{
					mat[j][i1++] = 'B';
					mat[j][i2++] = 'B';
				}
			}
		}
		for(Pacman pacman : PacmansList)
		{
			Point3D p = map.getPixelFromCord(pacman.getP());
			mat[(int)p.y()][(int)p.x()] = 'P';
		}
		for(Fruit fruit : FruitsList)
		{
			Point3D p = map.getPixelFromCord(fruit.getP());
			mat[(int)p.y()][(int)p.x()] = 'F';
		}
	}
	/* * * * * * * * * * * * * * * * * * POP UP * * * * * * * * * * * * * * * */
	public void POPUP() {
		if(frame == null)
		{
			frame = new JFrame("State Matrix");
			frame.setLayout(null);
			frame.setVisible(true);
			frame.setResizable(true);
			frame.setBounds(200,20,mat[0].length + 15,mat.length + 35);
			ta = new JTextArea();
			ta.setBounds(0,0,mat[0].length,mat.length);
			ta.setEditable(false);
			frame.setResizable(false);
			JScrollPane sp = new JScrollPane(ta);
			sp.setBounds(0,0,mat[0].length,mat.length);
			frame.add(sp);


			Maze maze = new Maze(mat);
			Algo algo = new Algo();
			List<Coordinate> path = algo.SOLVE(maze);
			if(path.isEmpty()) 
				ta.setText(toString());
			else
			{
				ta.setText(maze.ReturnMatWithPath(path));
			}
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					frame = null;
					e.getWindow().dispose();
				}
			});
		}
		else
		{
			ta.setText(toString());
		}
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString() {
		String ans = "";
		for (char[] row : mat){
			ans += Arrays.toString(row) + "\n";
		}
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Get Matrix * * * * * * * * * * * * * * * */
	public char[][] getMat() {
		return mat;
	}
}
