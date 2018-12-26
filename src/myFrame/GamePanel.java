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
	private Map map; // Map of current game 
	private double _ratio; // Ratio from scale the map (Paint Objects)
	public Play play;
	private boolean LoadedGame;
	private Image StartImage = Toolkit.getDefaultToolkit().getImage("./img/StartImage.png");
	private static boolean AlgoMode = false;
	
	public volatile boolean GameMode = false;
	public Animate _thread;
	public Thread _timer;
	

	/* * * * * * * * * * * * * * * * * *   Setters and Getters * * * * * * * * * * * * * * * */
	public List<Fruit> getFruitsList() { return FruitsList; }
	public void Refresh() { this._ratio = (map.getWidth() + map.getHeight())/(double)(700 + 637);}
	public boolean HasPlayer() { return player != null; }

	/* * * * * * * * * * * * * * * * * *   Constructor * * * * * * * * * * * * * * * */
	public GamePanel(Game game, Map map, Play play)
	{	
		this.game = game;
		this.map = map;
		this.play = play;
		this._ratio = (map.getWidth() + map.getHeight())/(double)(700 + 637);
		this.LoadedGame = true;
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
					g.drawImage(this.map.getMapImageHover() , 0, 0, map.getWidth(),map.getHeight(), this); // Regular Map
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
			g.drawImage(StartImage , 0, 0, 900,450, this); // Regular Map
		}
	}

	/* * * * * * * * * * * * * * * * * * Mouse Listener * * * * * * * * * * * * * * * */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 && player == null) // Left Click
		{
			player = new Player(map.getCordFromPixel(new Point3D(e.getX(),e.getY(),0)),"Tzvi and Or Player");
			if(player.intersectBox(BoxsList, map))
			{
				JOptionPane.showMessageDialog(null, "Cant Put Player Over Box!");
				player = null;
			}
			else
			{
				repaint();
			}
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
			GameMode = true;
			play.start();
			_thread = new Animate(this,player);
			_thread.start();
			Thread _timer = new Thread(){
				public void run(){
					while(true)
					{
						try {
							sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						int start_index = play.getStatistics().indexOf(" Time left") + 11;
						int end_index = play.getStatistics().indexOf(" kill") - 1;
						double time = Double.parseDouble(play.getStatistics().substring(start_index, end_index));
						if(time == 0.0 ) break;
					}
					if(GameMode == true)
					{
						update();
						_thread.stop();
						GameMode = false;
						JOptionPane.showMessageDialog(null, "Game Over \nReason: 0.0 Time left");
						Menu.SetVisableTrue();
					}
				}
			};
			_timer.start();
		}
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


		if(p.x() >= 17.5 && p.x() <= map.getWidth() - 18.5 && p.y() >= 17.5 && p.y() <= map.getHeight() - 17.5)  // Good Place
		{
			if(player.intersectGhost(GhostsList,map) || player.intersectBox(BoxsList,map))
				player.InDanger = true;

			else
				player.InDanger = false;

			play.rotate(player.ang);
		}
		else 
		{
			player.InDanger = true;
			play.rotate(0);
			play.rotate(180);
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
	public void StartAlgo() {
		AlgoMode = true;
		GameToMatrix mat = new GameToMatrix(player,FruitsList,BoxsList,GhostsList,PacmansList,map);
		Algo algo = new Algo(mat);
		int[] steps = algo.getNextStep();
		Point3D next_step_pixels = new Point3D(steps[1],steps[0]);
		Point3D next_step_coords = map.getCordFromPixel(next_step_pixels);
		player.setP(next_step_coords);		
	}
}