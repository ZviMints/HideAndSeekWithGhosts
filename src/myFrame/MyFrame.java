/**
 * This Class is responsible for the Main connection between all classes
 * we can run this game and get a Frame.
 * connect GUI,Algorithm,Game,Play and more.. together
 * @version 4.0
 * @author Tzvi Mints and Or Abuhazira
 */
package myFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import GUI.Menu;
import GUI.Score;
import Game.Game;
import Geom.Point3D;
import Map.Map;
import Robot.Play;
public class MyFrame extends JPanel
{
	private static final long serialVersionUID = 1L; // Serial UID for MyFRAME (NOT IMPORTENT!)
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private static Game game = null; //Initialize Game
	private static Map map = null; //Initialize Map
	// p00 **  p01   //
	//     **        //
	//     **        //
	// p10() **  p11 //
	// NOTE: 2 Points is enough
	private static Point3D p00; // THE TOP LEFT Point
	private static Point3D p01; // THE TOP RIGHT Point
	private static Point3D p10; // THE BOTTOM LEFT Point
	private static Point3D p11; // THE BOTTOM RIGHT Point
	private static Play play; // the server side
	private static GamePanel panel; // The panel of the game
	public static Box box; // Initialize Box
	/* From Box Decleration:
	 * A lightweight container
	 * that uses a BoxLayout object as its layout manager.
	 * Box provides several class methods
	 * that are useful for containers using BoxLayout --
	 * even non-Box containers.
	 *
	 */
	private static Menu menu; // Initialize Menu class
	private static Frame frame; // Initialize Frame class

	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	/**
	 * This is the main constructor of the class, we get path as an input and make a GUI PANEL
	 * @param path is the path of the csv file
	 */
	public MyFrame(String path)
	{
		startPanel(path);
	}
	public MyFrame() 
	{
		startPanel(null);
	}
	/* * * * * * * * * * * * * * Create And Show GUI * * * * * * * * * * * * * * * */   
	/**
	 * This method is reponsible to start the panel with GUI
	 * @param path is the input path of the .csv file that starts the game
	 */
	public void startPanel(String path) 
	{
		if(path != null) {
			// ******** Play ******** ///
			play = new Play(path); // Initialize new Server Player
			// ******** Map ******** ///
			String map_data = play.getBoundingBox(); // Get Bounding Box from the Server
			String[] s = map_data.split(","); // Split the String by ','
			p10 = new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])); // Get the points from the String
			p01 = new Point3D(Double.parseDouble(s[5]),Double.parseDouble(s[6]),Double.parseDouble(s[7])); // Get the points from the String
			p00 = new Point3D(p01.x(),p10.y(),0); // Make new Point
			p11 = new Point3D(p10.x(),p01.y(),0); // Make new Point
			// NOTE: 2 Points is enough! but 4 is more easy for us. not important
			map = new Map("./img/Background.png", p00, p01, p10, p11, 900,450);  // initialize new Map
			// NOTE: 2 Points p10,p01 is Enough! but we did for 4 points.
			// ******** Game ******** ///
			game = new Game(path); // Make new Game

			// ******** Game Panel ******** ///
			panel = new GamePanel(game,map, play); // initialize new Game Panel that represent the Drawing of the game
			panel.setBorder(new LineBorder(Color.BLACK,3)); // Set Border to the panel in BlackColor with thick of 3
			panel.setPreferredSize( new Dimension(900, 450) ); // Set Size of the Game Panel
			frame.setResizable(true); // Set Resize to true
		}
		else { // If Path is null
			panel = new GamePanel(); // Make new Game Panel
			panel.setBorder(new LineBorder(Color.BLACK,3)); // Set Border to the panel in BlackColor with thick of 3
			panel.setPreferredSize( new Dimension(900, 450) ); // Set Size of the Game Panel
		}

		// ******** GUI ******** ///
		// MAKE HORIZONTAL BOX
		box = Box.createHorizontalBox();
		// MAKE MENU MENU
		menu = new Menu(panel , this);
		menu.setPreferredSize( new Dimension(560, 100) );
		menu.setMaximumSize( menu.getPreferredSize() );
		menu.setLayout(null);
		box.add( menu );
		
		//MAKE SCORE CLASS 
		Score score = new Score();
		score.setPreferredSize( new Dimension(340, 100) );
		score.setMinimumSize( score.getPreferredSize() );
		score.setLayout(null);
		box.add( score );

		setLayout( new BorderLayout() );
		add(panel, BorderLayout.NORTH); // Set Layout
		add(box, BorderLayout.CENTER); // Set Layout
	}
	/* * * * * * * * * * * * * * Create And Show GUI * * * * * * * * * * * * * * * */   
	/**
	 * This is the method that responsible for creating the GUI and Show it on the screen
	 * make a new frame
	 */
	private static void createAndShowGUI()
	{
		frame = new JFrame("T&O Exercise 4");
		ImageIcon icon = new ImageIcon("./img/icon.png"); // Set Icon to Frame
		frame.setIconImage(icon.getImage()); // Set Icon to Frame
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MyFrame());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible( true );
		frame.setResizable(false);


		/* * * * * * * * * * * * * * Make Resize able - Panel * * * * * * * * * * * * * * * */   
	frame.addComponentListener(new ComponentAdapter() { // Inner class for Resiable Listener
			@Override
			public void componentResized(ComponentEvent e) {
				if(map!=null) {
					map.setWidth(panel.getSize().width);
					if(frame.getHeight() <= 487)
						map.setHeight(frame.getHeight() - 37);
					else
						map.setHeight(450);
					panel.Refresh();
				}
			}
		});
	}
	/* * * * * * * * * * * * * * setTitle * * * * * * * * * * * * * * * */  
	/**
	 * This method is responsible for setting up title for the frame
	 * used for set title when we load a game and want to see in which file we are using now
	 * @param path the title we want to add
	 */
	public static void setTitle(String path)
	{
		frame.setTitle("T&O Exercise 4 : " + path); // Set new title for the frame
	}
	/* * * * * * * * * * * * * * Main * * * * * * * * * * * * * * * */   
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}		
}