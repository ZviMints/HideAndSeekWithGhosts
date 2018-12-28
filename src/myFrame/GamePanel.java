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
	private Play play;
	private  boolean GameMode = false;
	private static final long _SleepTime = 50;
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
	public void paintComponent(Graphics g)
	{    
		if(LoadedGame) {
			super.paintComponent(g);
			g.drawImage(this.map.getMapImage() , 0, 0, map.getWidth(),map.getHeight(), this); // Regular Map

			// ************ Print Player ************ //
			g.setColor(Color.WHITE);
			if(this.player != null)
			{
				Point3D p = player.getP();
				Point3D p_pixels = map.getPixelFromCord(p);
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
				g.drawImage(player.getPlayerImage(), x - (int)(_ratio*12.5), y - (int)(_ratio*12.5),(int)(25*_ratio), (int)(25*_ratio), this);
				if(player.InDanger)
				{
					if(!AlgoIsPlaying)
					g.drawImage(this.map.GetDangerHover() , 0, 0, map.getWidth(),map.getHeight(), this); // DANGER 
				}
			}
			// ************ Print all Boxs ************ //
			g.setColor(Color.BLACK);
			for(int i = 0; i<BoxsList.size() ; i++)
			{
				Box box = BoxsList.get(i);
				Point3D p0 = box.getP0();
				Point3D p1 = box.getP1();

				Point3D p0_pixels = map.getPixelFromCord(p0);
				Point3D p1_pixels = map.getPixelFromCord(p1);

				int x0 = (int) p0_pixels.x(); int y0 = (int) p0_pixels.y();
				int x1 = (int) p1_pixels.x(); int y1 = (int) p1_pixels.y();

				int width = (int) Math.abs(x0 - x1); 
				int height = (int) Math.abs(y0 - y1);

				g.drawImage(box.getBoxImage() ,x0-2, y1, 2, height, this);
				g.drawImage(box.getBoxImage() ,x0+width, y1, 2, height, this);
				g.drawImage(box.getBoxImage() ,x0, y1 - 2, width,2,this);
				g.drawImage(box.getBoxImage() ,x0, y1 +  height, width, 2, this);

				g.fillRect(x0 , y1, width , height);

			}
			
			// ************ Print all Fruits ************ //
			g.setColor(Color.GREEN);
			for(int i=0 ; i <FruitsList.size() ; i++)
			{
				Fruit fruit = FruitsList.get(i);
				Point3D p = fruit.getP();
				Point3D p_pixels = map.getPixelFromCord(p);
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
				g.drawImage(fruit.getFruitImage(), x - (int)(_ratio*10), y - (int)(_ratio*10),(int)(20*_ratio), (int)(20*_ratio), this);
			}

			// ************ Print all Pacmans ************ //
			g.setColor(Color.YELLOW);
			for(int i=0; i<PacmansList.size() ;i++)
			{
				Pacman pacman = PacmansList.get(i);
				Point3D p = pacman.getP();
				Point3D p_pixels = map.getPixelFromCord(p);
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
				g.drawImage(pacman.getPacmanImage(), x - (int)(_ratio*12.5), y - (int)(_ratio*12.5),(int)(25*_ratio), (int)(25*_ratio), this);
			}

			// ************ Print all Ghosts ************ //
			g.setColor(Color.RED);
			for(int i=0; i<GhostsList.size() ;i++)
			{
				Ghost ghost = GhostsList.get(i);
				Point3D p = ghost.getP();
				Point3D p_pixels = map.getPixelFromCord(p);
				int x = (int) p_pixels.x(); int y = (int) p_pixels.y();
				g.drawImage(ghost.getGhostImage(), x - (int)(_ratio*12.5), y - (int)(_ratio*12.5),(int)(25*_ratio), (int)(25*_ratio), this);
			}
		}
		else
		{
			g.drawImage(StartImage , 0, 0, 900,450, this); // START IMAGE
		}
	}
	/* * * * * * * * * * * * * * * * * *  StartGame * * * * * * * * * * * * * * * */
	public void StartGame() {
		if(player != null)
		{
			play.setInitLocation(player.getP().x(),player.getP().y());
			GameMode = true;
			play.start();
			Thread animate = new Thread(){
				public void run(){
					while(play.isRuning())
					{
						try { Thread.sleep(_SleepTime );} // The animation wont run too fast				 
						catch (InterruptedException e) {} 
						if(player.ang <= 360)
							update();	
					}
					update();	
					JOptionPane.showMessageDialog(null, "Game Finished");
					Menu.SetVisableTrue();
					GameMode = false;
				}
			};
			animate.start();
		}
	}
	/* * * * * * * * * * * * * * * * * * Start Algo * * * * * * * * * * * * * * * */
	public void StartAlgo() {
		AlgoThread AlgoThread = new AlgoThread(this);
		AlgoThread.start();
	}
	/* * * * * * * * * * * * * * * * * *  update * * * * * * * * * * * * * * * */
	public void update() {
		Point3D p = map.getPixelFromCord(player.getP());
		PacmansList.clear();
		GhostsList.clear();
		FruitsList.clear();
		BoxsList.clear();
		ArrayList<String> board_data = play.getBoard();
		for(int a=0 ; a<board_data.size(); a++) {
			UpdateGame(board_data.get(a));
		}
		if(p.x() >= 17.5 && p.x() <= map.getWidth() - 18.5 && p.y() >= 17.5 && p.y() <= map.getHeight() - 17.5 || AlgoIsPlaying)  // Good Place
		{
			if(player.intersectGhost(GhostsList,map) || player.intersectBox(BoxsList,map)) // If Player Intersects one of the enemeys
				player.InDanger = true;
			else
				player.InDanger = false;
			play.rotate(player.ang);
		}
		else  // The player intersects one of the Game Corners
		{
			player.InDanger = true;
			if(p.x() < 17.5) // Left Corner
			{
				if(player.ang >= 0 && player.ang <=180) 
					play.rotate(player.ang);
			}
			else if(p.x() > map.getWidth() - 18.5) // Right Corner
			{
				if(player.ang >= 180 && player.ang <= 360) 
					play.rotate(player.ang);
			}
			else if(p.y() < 17.5 ) // TOP Corner
			{
				if(player.ang >= 90 && player.ang <= 270)
					play.rotate(player.ang);
			}
			else if( p.y() > map.getHeight() - 17.5) // Bottom Corner
			{
				if((player.ang >= 270 && player.ang <= 360) || player.ang >= 0 && player.ang <=90)
					play.rotate(player.ang);
			}
		}
		repaint();
		Score.updateScore(play.getStatistics());
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
		case "M":
			double x = Double.parseDouble(array.get(2));
			double y = Double.parseDouble(array.get(3));
			double z = Double.parseDouble(array.get(4));
			player.setP(new Point3D(x,y,z));
		}
	}
	/* * * * * * * * * * * * * * * * * * Show Matrix * * * * * * * * * * * * * * * */
	public void ShowMatrix() {
		GameToMatrix mat = new GameToMatrix(player,FruitsList,BoxsList,GhostsList,PacmansList,map);
		mat.POPUP();
	}
	/* * * * * * * * * * * * * * * * * * Mouse Listener * * * * * * * * * * * * * * * */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) // Left Click
		{
			if(player == null && !AlgoIsPlaying)
			{
				player = new Player(map.getCordFromPixel(new Point3D(e.getX(),e.getY(),0)),"Tzvi and Or Player");
				if(player.intersectBox(BoxsList, map))
				{
					JOptionPane.showMessageDialog(null, "Cant Put Player Over Box!");
					player = null;
				}
				else
					repaint();
			}
			if(GameMode)
			{
				MyCoords coords = new MyCoords();
				Point3D clicked = map.getCordFromPixel(new Point3D(e.getX(),e.getY(),0));
				Point3D source = player.getP();
				double ang = coords.azimuth(source.x(), source.y(), clicked.x(), clicked.y());
				player.ang = ang;
			}
		}
	}
	/* * * * * * * * * * * * * * * * * * Not Used * * * * * * * * * * * * * * * */
	@Override public void mouseClicked(MouseEvent e) { }
	@Override public void mouseEntered(MouseEvent arg0) { }
	@Override public void mouseExited(MouseEvent arg0) { }
	@Override public void mouseReleased(MouseEvent arg0) { }
}