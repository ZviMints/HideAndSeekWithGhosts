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
import Game.Game;
import Geom.Point3D;
import Map.Map;
import Robot.Play;

public class MyFrame extends JPanel
{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private static Game game = null;
	private static Map map = null;

	// p00 **  p01   //
	//     **        //
	//     **        //
	// p10() **  p11 //
	private static Point3D p00;
	private static Point3D p01;
	private static Point3D p10;
	private static Point3D p11;
	private static Play play; // VS computer 
	private static GamePanel panel;
	public static Box box;
	private static Menu menu;
	private static Frame frame;

	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public MyFrame(String path)
	{
		startPanel(path);
	}
	public void startPanel(String path) 
	{
		if(path != null) {
			// ******** Play ******** ///
			play = new Play(path);
			// ******** Map ******** ///
			String map_data = play.getBoundingBox();
			String[] s = map_data.split(",");
			p10 = new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4]));
			p01 = new Point3D(Double.parseDouble(s[5]),Double.parseDouble(s[6]),Double.parseDouble(s[7]));
			p00 = new Point3D(p01.x(),p10.y(),0);
			p11 = new Point3D(p10.x(),p01.y(),0);
			map = new Map("./img/Background.png", p00, p01, p10, p11, 900,450); 
			// NOTE: 2 Points p10,p01 is Enough! but we did for 4 points.
			// ******** Game ******** ///
			game = new Game(path);

			// ******** Game Panel ******** ///
			panel = new GamePanel(game,map, play);
			panel.setBorder(new LineBorder(Color.BLACK,3));
			panel.setPreferredSize( new Dimension(900, 450) );
			frame.setResizable(true);
		}
		else {
			panel = new GamePanel();
			panel.setBorder(new LineBorder(Color.BLACK,3));
			panel.setPreferredSize( new Dimension(900, 450) );
		}

		// ******** GUI ******** ///
		box = Box.createHorizontalBox();

		menu = new Menu(panel , this);
		menu.setPreferredSize( new Dimension(560, 100) );
		menu.setMaximumSize( menu.getPreferredSize() );
		menu.setLayout(null);
		box.add( menu );

		Score score = new Score();
		score.setPreferredSize( new Dimension(340, 100) );
		score.setMinimumSize( score.getPreferredSize() );
		score.setLayout(null);

		box.add( score );

		setLayout( new BorderLayout() );
		add(panel, BorderLayout.NORTH);
		add(box, BorderLayout.CENTER);
		
	}
	public MyFrame() 
	{
		startPanel(null);
	}
	/* * * * * * * * * * * * * * Create And Show GUI * * * * * * * * * * * * * * * */   
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
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(map!=null) {
					map.setHeight(panel.getSize().height);
					map.setWidth(panel.getSize().width);
					System.out.println("("+map.getHeight()+","+map.getWidth()+")");
					panel.Refresh();
				}
			}
		});
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