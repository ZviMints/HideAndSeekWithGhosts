/**
 * The Method is the main Panel of the game
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import Coords.MyCoords;
import Game.Game;
import Geom.Point3D;
import Map.Map;
import Objects.Box;
import Objects.Fruit;
import Objects.Ghost;
import Objects.Pacman;
import Player.Animate;
import Player.Player;
import Robot.Play;
import ShortestPathAlgo.Algo;

public class GamePanel extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private List<Fruit> FruitsList; // All the Fruits
	private List<Pacman> PacmansList; // All the Pacmans
	private List<Box> BoxsList; // All the Boxs
	private List<Ghost> GhostsList;	 // All the Ghosts
	private Player player; // Player
	private Game game; // This Game Database
	private Algo algo; // Algorithm of the current game
	private Map map; // Map of current game 

	public Play play;
	public volatile boolean started = false;
	/* * * * * * * * * * * * * * * * * *   Setters and Getters * * * * * * * * * * * * * * * */
	public List<Fruit> getFruitsList() { return FruitsList; }

	/* * * * * * * * * * * * * * * * * *   Constructor * * * * * * * * * * * * * * * */
	public GamePanel(Game game, Map map, Play play)
	{	
		this.game = game;
		this.map = map;
		this.play = play;
		FruitsList = this.game.getFruitList();
		PacmansList = this.game.getPacmanList();
		GhostsList = this.game.getGhostList();
		BoxsList = this.game.getBoxList();

		addMouseListener(this); // Mouse Clicks
		setFocusable(true);
	}
	/* * * * * * * * * * * * * * * Main Paint Method! * * * * * * * * * * * * * * * */
	public void paintComponent(Graphics g)
	{        
		super.paintComponent(g);
		g.drawImage(this.map.getBgImage() , 0, 0, map.getWidth(),map.getHeight(), this); // Regular Map

		// ************ Print all Boxs ************ //
		for(Box box : BoxsList)
		{


		}
		// ************ Print all Fruits ************ //
		g.setColor(Color.GREEN);
		for(int i=0 ; i <FruitsList.size() ; i++)
		{
			Fruit fruit = FruitsList.get(i);
			Point3D p = fruit.getP();
			Point3D p_pixels = map.getPixelFromCord(p);
			int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
			g.fillOval(x, y, 25, 25);
		}

		// ************ Print all Pacmans ************ //
		g.setColor(Color.YELLOW);
		for(Pacman pacman : PacmansList)
		{
			Point3D p = pacman.getP();
			Point3D p_pixels = map.getPixelFromCord(p);
			int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
			g.fillOval(x, y, 25, 25);
		}

		// ************ Print all Ghosts ************ //
		g.setColor(Color.RED);
		for(Ghost ghost : GhostsList)
		{
			Point3D p = ghost.getP();
			Point3D p_pixels = map.getPixelFromCord(p);
			int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
			g.fillOval(x, y, 40, 40);
		}

		// ************ Print Player ************ //
		g.setColor(Color.WHITE);
		if(this.player != null)
		{
			Point3D p = player.getP();
			Point3D p_pixels = map.getPixelFromCord(p);
			int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
			g.fillOval(x, y, 40, 40);
		}
	}

	/* * * * * * * * * * * * * * * * * * Mouse Listener * * * * * * * * * * * * * * * */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 && player == null) // Left Click
		{
			player = new Player(map.getCordFromPixel(new Point3D(e.getX() - 20 ,e.getY() - 20,0)),"Tzvi and Or Player",10);
			repaint();
		}
		if(started)
		{
			MyCoords coords = new MyCoords();
			Point3D clicked = map.getCordFromPixel(new Point3D(e.getX(),e.getY(),0));
			Point3D vec = coords.vector3D(player.getP(), clicked);
			vec.MakeNormal();
			player.setVec(vec);
		}
	}
	/* * * * * * * * * * * * * * * * * * Not Used * * * * * * * * * * * * * * * */
	@Override public void mouseClicked(MouseEvent e) { }
	@Override public void mouseEntered(MouseEvent arg0) { }
	@Override public void mouseExited(MouseEvent arg0) { }
	@Override public void mouseReleased(MouseEvent arg0) { }
	/* * * * * * * * * * * * * * * * * *  StartGame * * * * * * * * * * * * * * * */
	public void StartGame() {
		if(player != null)
		{
			play.setInitLocation(player.getP().x(),player.getP().y());
			started = true;
			play.start();
			Animate thread = new Animate(this,player);
			thread.start();
		}
	}
	/* * * * * * * * * * * * * * * * * *  update * * * * * * * * * * * * * * * */
	public void update() {
		FruitsList.clear();
		PacmansList.clear();
		GhostsList.clear();
		BoxsList.clear();
		ArrayList<String> board_data = play.getBoard();
		for(int a=0 ; a<board_data.size(); a++) {
			if(board_data.get(a).charAt(0) != 'M')
				UpdateGame(board_data.get(a));
		}
		repaint();
	}
	/* * * * * * * * * * * * * * * * * *  update Game * * * * * * * * * * * * * * * */
	private void UpdateGame(String input) {
		ArrayList<String> array = new ArrayList<>();
		String[] splitted = input.split(",");
		Collections.addAll(array, splitted); 
		switch(splitted[0])
		{
		case "B":
			Box box = game.MakeBox(array);
			BoxsList.add(box);
			break;
		case "F":
			Fruit fruit = game.MakeFruit(array);
			FruitsList.add(fruit);
			break;
		case "P":
			Pacman pacman = game.MakePacman(array);
			PacmansList.add(pacman);
			break;
		case "G":
			Ghost ghost = game.MakeGhost(array);
			GhostsList.add(ghost);
			break;
		}
	}
}