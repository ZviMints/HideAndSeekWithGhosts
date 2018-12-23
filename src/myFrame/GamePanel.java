/**
 * The Method is the main Panel of the game
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import Fruit.Fruit;
import Game.Game;
import Geom.Point3D;
import Map.Map;
import Pacman.Pacman;
import Pacman.PacmanThread;
import Path.Path;
import ShortestPathAlgo.Algo;

public class GamePanel extends JPanel implements MouseListener{

	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private List<Fruit> FruitsList; // All the Fruits
	private List<Pacman> PacmansList; // All the Pacmans
	private List<Ghost> GhostsList;	 // All the Ghosts
	private Player player; // Player
	private Game game; // This Game Database
	private Algo algo; // Algorithm of the current game
	private Map map; // Map of current game 
	private Image PlayerImage = Toolkit.getDefaultToolkit().getImage("./img/Player.png"); // Player image
	private Image PacmanImage = Toolkit.getDefaultToolkit().getImage("./img/Pacman.png"); // Pacman image
	private Image FruitImage = Toolkit.getDefaultToolkit().getImage("./img/Fruit.png"); // Fruit image
	private Image GhostImage = Toolkit.getDefaultToolkit().getImage("./img/Ghost.png"); //  Ghost image
	private boolean GotDamage = false;


	/* * * * * * * * * * * * * * * * * *   Constructor * * * * * * * * * * * * * * * */
	public GamePanel(Game game, Map map)
	{	
		this.game = game;
		this.map = map;
		FruitsList = this.game.getFruitList();
		PacmansList = this.game.getPacmanList();
		GhostsList = this.game.getGhostsList();
		addMouseListener(this); // Mouse Clicks
		setFocusable(true);
	}
	/* * * * * * * * * * * * * * * Main Paint Method! * * * * * * * * * * * * * * * */
	public void paintComponent(Graphics g)
	{        
		super.paintComponent(g); // Reprint
		g.drawImage(this.map.getBgImage() , 0, 0, map.getWidth(),map.getHeight(), this); // Regular Map
		
		if(GotDamage);
		g.drawImage(this.map.getBgImageHover() , 0, 0,map.getWidth(),map.getHeight(), this); // Got Damage

		// ************ Print all Fruits ************ //
		for(int i=0 ; i <FruitsList.size() ; i++)
		{
			Fruit fruit = FruitsList.get(i);
			if(!fruit.getInfo().Dead()) // If Fruit is Alive we need to Print it
			{
				Point3D p = (Point3D) fruit.getGeom(); 
				Point3D p_pixels = map.getPixelFromCord(p);
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
				g.drawImage(FruitImage, x-25, y-25, this);
			}
		}
		// ************ Print all Pacmans ************ //
		for(Pacman pacman : PacmansList)
		{
			Point3D p = (Point3D) pacman.getGeom();
			Point3D p_pixels = map.getPixelFromCord(p);
			int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
			g.drawImage(PacmanImage, x-25, y-25, this);
		}
		// ************ Print all Ghosts ************ //
		for(Ghost ghost : GhostsList)
		{
			Point3D p = (Point3D) ghost.getGeom();
			Point3D p_pixels = map.getPixelFromCord(p);
			int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
			g.drawImage(GhostImage, x-25, y-25, this);
		}
		// ************ Print Player ************ //
		if(this.player != null)
		{
			Point3D p = (Point3D) this.player.getGeom();
			Point3D p_pixels = map.getPixelFromCord(p);
			int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
			g.drawImage(PlayerImage, x-25, y-25, this);
		}
	}
	
	/* * * * * * * * * * * * * * * * * * Mouse Listener * * * * * * * * * * * * * * * */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 && player = null)) // Left Click
		{
			Point3D p = map.getCordFromPixel(new Point3D(e.getX(),e.getY(),0));	
			this.player = new Player(p);
		}
		repaint();
	}
	/* * * * * * * * * * * * * * * * * * Not Used * * * * * * * * * * * * * * * */
	@Override public void mouseClicked(MouseEvent e) { }
	@Override public void mouseEntered(MouseEvent arg0) { }
	@Override public void mouseExited(MouseEvent arg0) { }
	@Override public void mouseReleased(MouseEvent arg0) { }
}