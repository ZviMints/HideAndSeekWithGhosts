package myFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Game.Game;
import Geom.Point3D;
import Map.Map;
import Robot.Play;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private JSplitPane mainSplittedPane; // Main Frame ( All Window )
	private GamePanel panel; // Represent the Game ( Left Size )
	private JPanel MenuPanel; // Menu Panel ( Right Size )
	private Game game = null;
	private Map map = null;

	// p00 **  p01   //
	//     **        //
	//     **        //
	// p10() **  p11 //
	private static Point3D p00;
	private static Point3D p01;
	private static Point3D p10;
	private static Point3D p11;
	private Play play; // VS computer 


	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public MyFrame(String path) 
	{	
		play = new Play(path);
		// ******** Map ******** ///
		String map_data = play.getBoundingBox();
		String[] s = map_data.split(",");
		p10 = new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4]));
		p01 = new Point3D(Double.parseDouble(s[5]),Double.parseDouble(s[6]),Double.parseDouble(s[7]));
		p00 = new Point3D(p01.x(),p10.y(),0);
		p11 = new Point3D(p10.x(),p01.y(),0);
		map = new Map("./img/Background.png", p00, p01, p10, p11, 1433,642); 
		// NOTE: 2 Points p10,p01 is Enough! but we did for 4 points.
		game = new Game(path);
		StartPanel();
	}
	public MyFrame() 
	{
		this("./data/Ex4_OOP_example8.csv");
	}
	/* * * * * * * * * * * * * * * * * * Initialize Window * * * * * * * * * * * * * * * */ 
	public void StartPanel()
	{
		// ******** Panel ******** ///
		panel = new GamePanel(game, map,play);
		// ******** Menu ******** ///
		MenuPanel = new JPanel();
		MenuPanel.setLayout(new BoxLayout(MenuPanel, BoxLayout.X_AXIS));
		Menu menu = new Menu(panel);
		menu.setAutoscrolls(true);
		menu.setFocusable(false);
		menu.setLayout(null);
		menu.setAlignmentX(Component.RIGHT_ALIGNMENT);
		MenuPanel.add(menu);


		// ******** Make JSplitPlane ******** ///
		ImageIcon icon = new ImageIcon("./img/icon.png"); // Set Icon to Frame
		this.setIconImage(icon.getImage()); // Set Icon to Frame
		this.setTitle("T&O OP_4 Exercise"); // Set Title to Frame 
		mainSplittedPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel,MenuPanel); // Make Main Splitted Pane
		mainSplittedPane.setOneTouchExpandable(true);
		mainSplittedPane.setPreferredSize(new Dimension(1433, 642));
		getContentPane().add(mainSplittedPane, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainSplittedPane.setDividerLocation(1433 - 400);
		setVisible(true);
		pack();

		/* * * * * * * * * * * * * * Make Resize able - Divider * * * * * * * * * * * * * * * */   
		mainSplittedPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent changeEvent) {
				if (changeEvent.getPropertyName().equals(JSplitPane.DIVIDER_LOCATION_PROPERTY)) {
					map.setWidth(mainSplittedPane.getDividerLocation());
				}
			} 
		});

		/* * * * * * * * * * * * * * Make Resize able - Panel * * * * * * * * * * * * * * * */   
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				map.setWidth(mainSplittedPane.getDividerLocation());
				map.setHeight(mainSplittedPane.getSize().height);
			}
		});
	}
	/* * * * * * * * * * * * * * Main * * * * * * * * * * * * * * * */   
	public static void main(String[] args) {
		MyFrame Game = new MyFrame();
	}
}