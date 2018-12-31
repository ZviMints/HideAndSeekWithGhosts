/**
 * The Method is the main Panel of the game
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Algorithm.GameToMatrix;
import Coords.MyCoords;
import GUI.Menu;
import GUI.Score;
import Game.Game;
import Geom.Point3D;
import Map.Map;
import Objects.Box;
import Objects.Fruit;
import Objects.Ghost;
import Objects.Pacman;
import Player.Player;
import Robot.Play;

public class GamePanel extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private List<Fruit> FruitsList; // All the Fruits
	private List<Pacman> PacmansList; // All the Pacmans
	private List<Box> BoxsList; // All the Boxs
	private List<Ghost> GhostsList;	 // All the Ghosts
	private Player player; // Player
	private Game game; // This Game Database
	private Map map; // Map of current game 
	private double _ratio; // Ratio from scale the map (Paint Objects)
	private boolean LoadedGame = false;
	private boolean AlgoIsPlaying = false;
	private Play play; // The Server side Player
	private  boolean GameMode = false; // If We are In Game Mode 
	private static final long _SleepTime = 50; // For How much time the thread will Sleep
	private static final Image StartImage = Toolkit.getDefaultToolkit().getImage("./img/StartImage.png");

	/* * * * * * * * * * * * * * * * * *   Setters and Getters * * * * * * * * * * * * * * * */
	public List<Fruit> getFruitsList() { return FruitsList; }
	public List<Ghost> getGhostsList() { return GhostsList; }
	public List<Pacman> getPacmansList() { return PacmansList; }
	public List<Box> getBoxsList() { return BoxsList; }
	public void Refresh() { this._ratio = (map.getWidth() + map.getHeight())/(double)(700 + 637);}
	public boolean HasPlayer() { return player != null; }
	public Player getPlayer() { return player; }
	public void setPlayer(Player p) { this.player = p; }
	public Play getPlay() { return this.play; }
	public boolean getGameMode() { return this.GameMode; }
	public void setGameMode(boolean status) { this.GameMode = status; }
	public void setAlgoMode(boolean status) { this.AlgoIsPlaying = status; }
	public Map getMap() { return this.map; }

	/* * * * * * * * * * * * * * * * * *   Constructor * * * * * * * * * * * * * * * */
	/**
	 * This is the main Constructor of GamePanel
	 * @param game is the current game that loaded by the user
	 * @param map is the current map that loaded by the user
	 * @param play is the server side
	 */
	public GamePanel(Game game, Map map, Play play)
	{	
		this.game = game;
		this.map = map;
		this.play = play;
		this._ratio = (map.getWidth() + map.getHeight())/(double)(700 + 637);
		this.LoadedGame  = true;
		FruitsList = this.game.getFruitList();
		PacmansList = this.game.getPacmanList();
		GhostsList = this.game.getGhostList();
		BoxsList = this.game.getBoxList();

		addMouseListener(this); // Mouse Clicks
		setFocusable(true);
	}
	public GamePanel() {
		this.LoadedGame = false;
	}
	/* * * * * * * * * * * * * * * Main Paint Method! * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for paint Componenets such that
	 * pacman, fruit, ghost, box and player on the game map
	 * each time we make an upgrade in the game, we will repaint() the map
	 * and use this function in order to show current and fix map
	 */
	public void paintComponent(Graphics g)
	{    
		if(LoadedGame) {
			super.paintComponent(g); // Call the super constrcutor
			g.drawImage(this.map.getMapImage() , 0, 0, map.getWidth(),map.getHeight(), this); // Print the map

			// ************ Print Player ************ //
			g.setColor(Color.WHITE); // Set the Color of the Player
			if(this.player != null) // If Player is not null we will show him on the map
			{
				Point3D p = player.getP(); // get the Geom element of the player
				Point3D p_pixels = map.getPixelFromCord(p); // convert player point from geom to pixel point
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y(); // get the x and y values for the player in pixels
				g.drawImage(player.getPlayerImage(), x - (int)(_ratio*12.5), y - (int)(_ratio*12.5),(int)(25*_ratio), (int)(25*_ratio), this);
				if(player.InDanger) // if player is in Danger == Near Box || Near border || Hïit Ghost
				{
					g.drawImage(this.map.GetDangerHover() , 0, 0, map.getWidth(),map.getHeight(), this); // DANGER 
				}
			}
			// ************ Print all Boxs ************ //
			g.setColor(Color.BLACK); // Set the Color of the Box
			for(int i = 0; i<BoxsList.size() ; i++) // For all Box in Boxes
			{
				Box box = BoxsList.get(i); // Get the current Box
				Point3D p0 = box.getP0(); // Get First Point of the Box
				Point3D p1 = box.getP1(); // Get the Second Point of the Box

				Point3D p0_pixels = map.getPixelFromCord(p0); // Convert first point to Pixels
				Point3D p1_pixels = map.getPixelFromCord(p1); // Convert second point to Pixels

				int x0 = (int) p0_pixels.x(); int y0 = (int) p0_pixels.y(); // Make x0,y0 in Pixe;s
				int x1 = (int) p1_pixels.x(); int y1 = (int) p1_pixels.y(); // Make x1,y1 in Pixels

				int width = (int) Math.abs(x0 - x1);  // Set the Width of the Rectangle
				int height = (int) Math.abs(y0 - y1); // Set the Height of the Rectangle

				g.drawImage(box.getBoxImage() ,x0-2, y1, 2, height, this); // Draw border at RIGHT
				g.drawImage(box.getBoxImage() ,x0 + width, y1, 2, height, this);  // Draw border at LEFT
				g.drawImage(box.getBoxImage() ,x0, y1 - 2, width,2,this); // Draw border at TOP
				g.drawImage(box.getBoxImage() ,x0, y1 +  height, width, 2, this); // Draw border at BOTTOM

				g.fillRect(x0 , y1, width , height); // Draw Rectangle 

			}

			// ************ Print all Fruits ************ //
			g.setColor(Color.GREEN);  // Set the Color of the Fruit
			for(int i=0 ; i <FruitsList.size() ; i++)  // For all Fruit at FruitsList
			{
				Fruit fruit = FruitsList.get(i); // Get The i's Fruit of the FruitsList
				if(!fruit.isAlive()) continue;
				Point3D p = fruit.getP(); // Get the current Fruit Point in Geom Element
				Point3D p_pixels = map.getPixelFromCord(p); // Convert the current Point to Pixels
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y(); // Set x,y in pixels
				g.drawImage(fruit.getFruitImage(), x - (int)(_ratio*10), y - (int)(_ratio*10),(int)(20*_ratio), (int)(20*_ratio), this);
			}

			// ************ Print all Pacmans ************ //
			g.setColor(Color.YELLOW);  // Set the Color of the Pacman
			for(int i=0; i<PacmansList.size() ;i++) // For all Pacman at PacmansList 
			{
				Pacman pacman = PacmansList.get(i); // Get The i's Pacman of the PacmansList
				if(!pacman.isAlive()) continue;
				Point3D p = pacman.getP(); // Get the current Pacman Point in Geom Element
				Point3D p_pixels = map.getPixelFromCord(p); // Convert the current Point to Pixels
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y(); // Set x,y in pixels
				g.drawImage(pacman.getPacmanImage(), x - (int)(_ratio*12.5), y - (int)(_ratio*12.5),(int)(25*_ratio), (int)(25*_ratio), this);
			}

			// ************ Print all Ghosts ************ //
			g.setColor(Color.RED); // Set the Color of the Ghost
			for(int i=0; i<GhostsList.size() ;i++) // For all Ghost at Ghost List 
			{
				Ghost ghost = GhostsList.get(i);  // Get The i's Ghost of the GhostList
				Point3D p = ghost.getP(); // Get the current Ghost Point in Geom Element
				Point3D p_pixels = map.getPixelFromCord(p); // Convert the current Point to Pixels
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y(); // Set x,y in pixels
				g.drawImage(ghost.getGhostImage(), x - (int)(_ratio*12.5), y - (int)(_ratio*12.5),(int)(25*_ratio), (int)(25*_ratio), this);
			}
		}
		else
		{
			g.drawImage(StartImage , 0, 0, 900,450, this); // START IMAGE
		}
	}
	/* * * * * * * * * * * * * * * * * *  StartGame * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for Start Game by Player (NO ALGO!)
	 */
	public void StartGame() {
		if(player != null) // if player is  initialized
		{
			play.setInitLocation(player.getP().x(),player.getP().y()); // set the location at Server Side
			GameMode = true; // set Game Mode to True
			play.start(); // Turn on the Server
			Thread animate = new Thread(){ // Inner Class for GAME THREAD
				public void run(){ // Main run Method
					while(play.isRuning()) // While the game is running
					{
						try { Thread.sleep(_SleepTime );} // The animation wont run too fast				 
						catch (InterruptedException e) {} 
						if(player.ang <= 360) // (Start of the game the angle is 361, we didnt choose direction yet)
							update(); // update the Game
					}
					update();	// Update the Game for the Last fruit
					JOptionPane.showMessageDialog(null, "Game Finished"); // JOptionPane Message
					Menu.SetVisableTrue(); // Set Menu Buttons On
					GameMode = false; // Turn the game mode off
				}
			};
			animate.start(); // Run the thread
		}
	}
	/* * * * * * * * * * * * * * * * * * Start Algo * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for Start the Game By ALGORITHM
	 */
	public void StartAlgo() {
		AlgoThread AlgoThread = new AlgoThread(this); // Make new Algorithm thread
		AlgoThread.start(); // Start the thread
	}
	/* * * * * * * * * * * * * * * * * *  update * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for update the game.
	 * we will get the String from the Server and init new Objects and show them in the GUI
	 */
	public void update() {
		Point3D p = map.getPixelFromCord(player.getP()); // Get the player point in pixels

		for(Fruit f : FruitsList)
			f.setAlive(false); // Set False All FruitsList

		for(Pacman pacman : PacmansList)
			pacman.setAlive(false); // Set False All Pacmans

		ArrayList<String> BoardData = play.getBoard(); // run on all game board
		for(int a=0 ; a<BoardData.size(); a++) { // run on all game board
			UpdateObjects(BoardData.get(a)); // update the game 
		}
		// If Algorithm is playing or the player is in border 
		if(p.x() >= 17.5 &&
				p.x() <= map.getWidth() - 18.5 && 
				p.y() >= 17.5 &&
				p.y() <= map.getHeight() - 17.5 
				|| AlgoIsPlaying)  // Good Place
		{
			if(player.intersectGhost(GhostsList,map) || player.intersectBox(BoxsList,map)) // If Player Intersects one of the enemeys
				player.InDanger = true; // the player is in danger 
			else
				player.InDanger = false; // the player is not in danger

			play.rotate(player.ang); // rotate the player
		}
		else  // The player intersects one of the Game Corners
		{
			player.InDanger = true; // the player is in danger
			if(p.x() < 17.5) // Left Corner
			{
				if(player.ang >= 0 && player.ang <=180) 
					play.rotate(player.ang); // rotate the player ( make a move )
			}
			else if(p.x() > map.getWidth() - 18.5) // Right Corner
			{
				if(player.ang >= 180 && player.ang <= 360) 
					play.rotate(player.ang); // rotate the player ( make a move )
			}
			else if(p.y() < 17.5 ) // TOP Corner
			{
				if(player.ang >= 90 && player.ang <= 270)
					play.rotate(player.ang); // rotate the player ( make a move )
			}
			else if( p.y() > map.getHeight() - 17.5) // Bottom Corner
			{
				if((player.ang >= 270 && player.ang <= 360) || player.ang >= 0 && player.ang <=90)
					play.rotate(player.ang); // rotate the player ( make a move )
			}
		}
		repaint(); // Repaint the game
		Score.updateScore(play.getStatistics()); // update the Statistics in the Score Class
	}
	/* * * * * * * * * * * * * * * * * *  Update Game * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for getting string as an input and make Objects from it
	 * @param input is the input ID
	 */
	private void UpdateObjects(String input) {
		ArrayList<String> array = new ArrayList<>(); // make new array list 
		String[] splitted = input.split(","); // Split by ','
		Collections.addAll(array, splitted); // add all to array
		double x;
		double y;
		double z;
		switch(splitted[0])
		{
		case "B": // Its BOX!
			break; // No Changes.
		case "F": // Its FRUIT!
			Fruit f = (Fruit) GetByID("Fruit",array.get(1)); // Get the current FRUIT
			x = Double.parseDouble(array.get(2)); // get Lat location
			y = Double.parseDouble(array.get(3)); // get Lon location
			z = Double.parseDouble(array.get(4)); // get Alt location
			f.setP(x,y,z); // Set new Point3D
			f.setAlive(true); // Set Fruit to be Alive
			break; // Get out of the switch
		case "P": // Its PACMAN!
			Pacman pacman = (Pacman) GetByID("Pacman",array.get(1)); // Get the current Pacman
			x = Double.parseDouble(array.get(2)); // get Lat location
			y = Double.parseDouble(array.get(3)); // get Lon location
			z = Double.parseDouble(array.get(4)); // get Alt location
			pacman.setP(x,y,z); // Set new Point3D
			pacman.setAlive(true); // Set Pacman to be Alive
			break; // Get out of the switch
		case "G": // Its Ghost!
			Ghost g = (Ghost) GetByID("Ghost",array.get(1)); // Get the current Pacman
			x = Double.parseDouble(array.get(2)); // get Lat location
			y = Double.parseDouble(array.get(3)); // get Lon location
			z = Double.parseDouble(array.get(4)); // get Alt location
			g.setP(x,y,z); // Set new Point3D
			break; // Get out of the switch
		case "M": // Its Player!
			x = Double.parseDouble(array.get(2)); // get Lat location
			y = Double.parseDouble(array.get(3)); // get Lon location
			z = Double.parseDouble(array.get(4)); // get Alt location
			player.setP(x,y,z); // Set the player to the new Point
			break;
		}
	}
	/**
	 * This Method is responsible to get Object such that Fruit,Ghost or Pacman by ID
	 * @param Type is the Type of the Object
	 * @param ID is the id of the object
	 * @return Object such that Ghost,Pacman or Fruit
	 */
	private Object GetByID(String Type, String ID) {

		if(Type.equals("Fruit")) 
		{
			for(Fruit f : FruitsList)
				if(f.get_id().equals(ID)) return f;
		}
		else if(Type.equals("Pacman")) 
		{
			for(Pacman p : PacmansList)
				if(p.get_id().equals(ID)) return p;
		}
		else if(Type.equals("Ghost")) 
		{
			for(Ghost g : GhostsList)
				if(g.get_id().equals(ID)) return g;		 
		}
		System.exit(1); // Fatal Error!
		return null;
	}
	/* * * * * * * * * * * * * * * * * * Show Matrix * * * * * * * * * * * * * * * */
	/**
	 * Show the Matrix (GRAPH)
	 */
	public void ShowMatrix() {
		GameToMatrix mat = new GameToMatrix(player,FruitsList,BoxsList,GhostsList,PacmansList,map);
		mat.POPUP(); // Pop-up the Window with current Matrix that represent the Game
	}
	/* * * * * * * * * * * * * * * * * * Mouse Listener * * * * * * * * * * * * * * * */
	@Override // Inner Class
	public void mousePressed(MouseEvent e) { 
		if(e.getButton() == MouseEvent.BUTTON1) // Left Click
		{
			if(player == null && !AlgoIsPlaying)  // Initialize new Player
			{
				player = new Player(map.getCordFromPixel(new Point3D(e.getX(),e.getY(),0)),"Tzvi and Or Player");
				if(player.intersectBox(BoxsList, map)) 
				{
					JOptionPane.showMessageDialog(null, "Cant Put Player Over Box!");
					player = null; // If we hit a box
				}
				else
					repaint(); // Repaint the Game
			}
			if(GameMode) // If we are Playing and the Player is playing and not the Algorithm
			{
				MyCoords coords = new MyCoords(); // Make new coords Class
				Point3D clicked = map.getCordFromPixel(new Point3D(e.getX(),e.getY(),0)); // convert click from pixel to Geom Coords
				Point3D source = player.getP(); // get the current player point in [alt,lon,alt]
				double ang = coords.azimuth(source.x(), source.y(), clicked.x(), clicked.y()); // Caulculate the azimuth
				player.ang = ang; // set the player Angle to the azimuth
			}
		}
	}
	/* * * * * * * * * * * * * * * * * * Not Used * * * * * * * * * * * * * * * */
	@Override public void mouseClicked(MouseEvent e) { }
	@Override public void mouseEntered(MouseEvent arg0) { }
	@Override public void mouseExited(MouseEvent arg0) { }
	@Override public void mouseReleased(MouseEvent arg0) { }
}