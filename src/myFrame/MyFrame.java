package myFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Game.Game;
import Geom.Point3D;
import Map.Map;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private JSplitPane mainSplittedPane; // Main Frame ( All Window )
	private GamePanel panel; // Represent the Game ( Left Size )
	private JPanel MenuPanel; // Menu Panel ( Right Size )
	private Game game = null;
	private Map map = null;

	// p00(32.105848,35.202429) **  p01(32.105848,35.212541) //
	//                          **                           //
	//                          **                           //
	// p10(32.101951,35.202429) **  p11(32.101951,35.212541) //
	private static Point3D p00 = new Point3D(32.105848,35.202429);
	private static Point3D p01 = new Point3D(32.105848,35.212541);
	private static Point3D p10 = new Point3D(32.101951,35.202429);
	private static Point3D p11 = new Point3D(32.101951,35.212541);

	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public MyFrame(String path) 
	{
		// ******** Map ******** ///
		map = new Map("./img/Background.png", p00, p01, p10, p11, 1433,642); 
		// NOTE: 2 Points p10,p01 is Enough! but we did for 4 points.
		game = new Game(path,map);
		StartPanel();
	}
	public MyFrame() 
	{
		this("./data/Ex4_OOP_example8.csv");
	}
	/* * * * * * * * * * * * * * * * * * Initialize Window * * * * * * * * * * * * * * * */ 
	public void StartPanel()

	{
		panel = new GamePanel(game, map);

		// ******** Menu ******** ///
		MenuPanel = new JPanel();
		MenuPanel.setLayout(new BoxLayout(MenuPanel, BoxLayout.X_AXIS));
		Menu menu = new Menu(this);
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