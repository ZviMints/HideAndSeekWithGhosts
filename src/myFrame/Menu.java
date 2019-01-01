/**
 * Will class represent the GUI Menu of the Project.
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import Geom.Point3D;
import ShortestPathAlgo.Algo;


public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private static  JButton Start; // Start button
	private static JButton Load; // Load button
	private static JButton Statistics; // Statistics button
	private static GamePanel panel;
	private static JFrame frame;
	private static JTextArea ta;
	private static JTable table;
	private static JScrollPane sp;
	private static MyFrame myFrame;
	private static JButton Algo;
	private static boolean PleaseLoadNewGame = false;
	private static final long Tzvi_ID = 314977489;
	private static final long Or_ID = 311226617;
	private static JButton Mat;
	private static String[] ColumnHeader;
	private static Object[][] data;
	private static JLabel Icon;
	private static JScrollPane sp1;
	private static JFrame load;
	/* * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */

	/* * * * * * * * * * * * * *  Constructor  * * * * * * * * * * * * * * * */
	public Menu(GamePanel panel , MyFrame myFrame)
	{
		this.myFrame = myFrame;
		this.panel = panel;
		initialize();
	}

	/* * * * * * * * * * * * * * * * * * Initialize Window * * * * * * * * * * * * * * * */
	private void initialize() {

		// **** Start JButton ***** //
		ImageIcon start = new ImageIcon("./img/Start.png"); // Set Icon to Button
		Start = new JButton("Start Game",start);
		Start.setVisible(true);
		Start.setBounds(5, 25, 100, 50);
		Start.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important

		this.add(Start);

		// **** Statistics JButton ***** //
		ImageIcon statstics = new ImageIcon("./img/Stat.png"); // Set Icon to Button
		Statistics = new JButton("Statistics",statstics);
		Statistics.setVisible(true);
		Statistics.setBounds(125, 25, 100, 50);
		Statistics.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important

		this.add(Statistics);

		Statistics.addMouseListener(new MouseAdapter() { 		
			public void mouseClicked(MouseEvent e)  {

				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						load = new JFrame("Test");
						ImageIcon loading = new ImageIcon("./img/load.gif");
						load.add(new JLabel(loading, JLabel.CENTER));
						load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						load.setBounds(400,200,600, 400);
						load.setVisible(true);
						load.setResizable(false);
						Setframe();
						for (int i = 0; i < 3; i++) {
							try {
								Thread.sleep(430);
							}
							catch (Exception e) {
							}
						}
						frame.setVisible(true);
						load.setVisible(false);
					}
				});
				t1.start();
			}});

		// **** Start Mouse Listener ***** //
		Start.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				if(panel.HasPlayer())
				{
					if(!PleaseLoadNewGame)
					{
						panel.play.setIDs(Tzvi_ID,Or_ID);
						panel.StartGame();
						Start.setVisible(false);
						Algo.setVisible(false);
						Mat.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Load new Game Please!");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Tap on the Map to Put Player First");
			}
		});

		// **** Load JButton ***** //
		ImageIcon load = new ImageIcon("./img/Load.png"); // Set Icon to Button
		Load = new JButton("Load",load);
		Load.setVisible(true);
		Load.setBounds(235,25, 100, 50);
		Load.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		this.add(Load);


		// **** Load Mouse Listener ***** //
		Load.addMouseListener(new MouseAdapter() { 		
			public void mouseClicked(MouseEvent e)  {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					String _Filename = chooser.getSelectedFile().getAbsolutePath();
					if(_Filename.contains(".csv")) 
					{
						if(panel.GameMode == false)
						{
							PleaseLoadNewGame = false;
							MyFrame.box.invalidate(); 
							MyFrame.box.setVisible(false); 
							MyFrame.box.removeAll();   // remove box
							panel.invalidate();
							panel.setVisible(false);
							panel.removeAll(); // remove panel
							myFrame.startPanel(_Filename); 	
						}
						else
							JOptionPane.showMessageDialog(null, "Finish Game First");
					}
					else {
						JOptionPane.showMessageDialog(null, "This File is not .CSV file");
					}
				}
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch ( Exception e) {
			e.printStackTrace();
		} 
		// **** Algo JButton ***** //
		ImageIcon algo = new ImageIcon("./img/Algo.png"); // Set Icon to Button
		Algo = new JButton("Algo",algo);
		Algo.setVisible(true);
		Algo.setBounds(345,25, 100, 50);
		Algo.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important

		this.add(Algo);

		// **** Algo Mouse Listener ***** //
		Algo.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				if(panel.HasPlayer())
				{
					if(!PleaseLoadNewGame)
					{
						panel.play.setIDs(Tzvi_ID,Or_ID);
						panel.StartAlgo();
						Mat.setVisible(true);
						Start.setVisible(false);
						Algo.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Load new Game Please!");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Tap on the Map to Put Player First");
			}
		});	
		// **** Matrix JButton ***** //
		ImageIcon mat = new ImageIcon("./img/Mat.png"); // Set Icon to Button
		Mat = new JButton("Mat",mat);
		Mat.setVisible(false);
		Mat.setBounds(455,25, 100, 50);
		Mat.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important

		this.add(Mat);

		// **** Mat Mouse Listener ***** //
		Mat.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				if(panel.HasPlayer())
					panel.ShowMatrix();
			}
		});	

	}

	public void UpdatePanel(GamePanel panel) {
		this.panel = panel;
	}



	public static void SetVisableTrue() {
		Start.setVisible(true);
		Algo.setVisible(true);
		Mat.setVisible(false);
		PleaseLoadNewGame = true;
	}

	/**
	 * This method generates a table that includes statistics for each game
	 */
	public void dataFromDB(Statistics stat) {
		data = new Object[9][4]; 
		for (int i = 0; i < data.length; i++) {
			data[i][0]= "example"+(i+1); // name of game
			data[i][1] = stat.getBestGame()[i][0]; 	// info for the bastgame in each game
			data[i][2] = stat.getAverage()[i][0]; // info for the average in each game
			data[i][3] = null; //info for the bastgame algo in each game
		}

	}
	
	/**
	 * This method produces items of statistics including a table ane info from Database
	 */
	public void Setframe() {
		// **** statistic frame ***** //
		
		frame = new JFrame();
		frame.setLayout(null);
		frame.setVisible(false);
		frame.setResizable(false);
		//frame.setLayout(new BorderLayout());
		frame.setBounds(200,20,1000,500);

		// **** statistic text ***** //
		
		Statistics stat = new Statistics(); 
		ta = new JTextArea();
		ta.setBounds(0,0,100,200);
		ta.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		ta.setText(stat.toString()); 
        Color c = Color.decode("#CCE5FF"); 
		ta.setBackground(c);
		sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(0,0,500,465);
		frame.add(sp);

		// **** statistic Icon ***** //
		
		ImageIcon statistic = new ImageIcon("./img/statistic.png"); // Set Icon to Button
		Icon = new JLabel(statistic);
		Icon.setBounds(500, 0, 500, 200);
		frame.add(Icon);

		// **** statistic table ***** //
		
		dataFromDB(stat);
		ColumnHeader = new String[] {"File name", "Bast result", "Average","Algo"};
		table = new JTable(data , ColumnHeader);
		table.setBounds(500, 320, 500, 465);
		sp1 = new JScrollPane(table);
		frame.add(new JScrollPane(sp1));
		frame.add(table);
		
		//frame.add(sp1, BorderLayout.CENTER);
		//frame.add(sp,BorderLayout.NORTH);
		//frame.add(Icon, BorderLayout.SOUTH);
		//frame.validate();

	}
}


