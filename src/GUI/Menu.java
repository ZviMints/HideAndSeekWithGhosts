/**
 * Will class represent the GUI Menu of the Project.
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
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
import javax.swing.UIManager;

import myFrame.GamePanel;
import myFrame.MyFrame;


public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private static  JButton Start; // Start button
	private static JButton Load; // Load button
	private static JButton Statistics; // Statistics button
	private GamePanel panel;
	private MyFrame myFrame;
	private static JButton Algo;
	private static boolean LOADED = false; 
	private static final long Tzvi_ID = 314977489; // id of Developers
	private static final long Or_ID = 311226617; // id of Developers
	private static JButton Mat;
	/* * * * * * * * * * * * * *  Constructor  * * * * * * * * * * * * * * * */
	/**
	 * This method accepts Game Panel and MyFrame and builds this class 
	 * @param panel
	 * @param myFrame
	 */
	public Menu(GamePanel panel , MyFrame myFrame)
	{
		this.myFrame = myFrame; // MyFrame
		this.panel = panel; // GamePanel
		initialize(); // initialize GUI
	}
	/* * * * * * * * * * * * * * * * * * Initialize Window * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for the construction of the GUI menu
	 */
	private void initialize() {

		// **** Start JButton ***** //
		ImageIcon start = new ImageIcon("./img/Start.png"); // Set Icon to Button
		Start = new JButton("Start Game",start); // initialize Start button
		Start.setVisible(true); 
		Start.setBounds(5, 25, 100, 50); // Size and Location
		Start.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		this.add(Start); // Add to panel

		// **** Statistics JButton ***** //
		ImageIcon statstics = new ImageIcon("./img/Stat.png"); // Set Icon to Button
		Statistics = new JButton("Statistics",statstics); // initialize Statstics button
		Statistics.setVisible(true);
		Statistics.setBounds(125, 25, 100, 50);  // Size and Location
		Statistics.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		this.add(Statistics); // Add to panel

		// **** Statistics Mouse Listener ***** //
		Statistics.addMouseListener(new MouseAdapter() { // Press the button
			public void mouseClicked(MouseEvent e)  {
				StatisticFrame stat = new StatisticFrame(); // Frame of Statistic
				stat.startFrame(); // Start Frame
			}});

		// **** Start Mouse Listener ***** //
		Start.addMouseListener(new MouseAdapter() { // Press the button
			public void mouseClicked(MouseEvent e) {
				if(LOADED) // If the CSV file is loaded
				{
					if(panel.HasPlayer()) // If we chose a place for the player
					{
						panel.getPlay().setIDs(Tzvi_ID,Or_ID); // We play
						panel.StartGame(); // Start Game
						Mat.setVisible(true);
						Start.setVisible(false);
						Algo.setVisible(false);
						LOADED = false;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Tap on the Map to Put Player First"); // If we did not choose a place for the player
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Load new Game Please!"); // If no CSV file is loaded
			}
		});

		// **** Load JButton ***** //
		ImageIcon load = new ImageIcon("./img/Load.png"); // Set Icon to Button
		Load = new JButton("Load",load); // initialize Load button
		Load.setVisible(true);
		Load.setBounds(235,25, 100, 50); // Size and Location
		Load.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		this.add(Load);// Add to panel


		// **** Load Mouse Listener ***** //
		Load.addMouseListener(new MouseAdapter() { 	// Press the button	
			public void mouseClicked(MouseEvent e)  {
				JFileChooser chooser = new JFileChooser(); // creator JFileChooser 
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					String _Filename = chooser.getSelectedFile().getAbsolutePath(); // The name of the chooser file 
					if(_Filename.contains(".csv")) // If the CSV file
					{
						if(panel.getGameMode() == false) //If still not started a game 
						{
							LOADED = true; // For the game to start
							MyFrame.box.invalidate(); 
							MyFrame.box.setVisible(false); 
							MyFrame.box.removeAll();   // remove box
							MyFrame.setTitle(_Filename);
							panel.invalidate();
							panel.setVisible(false);
							panel.removeAll(); // remove panel
							myFrame.startPanel(_Filename); // Updating game through MyFrame
						}
						else
							JOptionPane.showMessageDialog(null, "Finish Game First"); // The game is still running
					}
					else {
						JOptionPane.showMessageDialog(null, "This File is not .CSV file"); //File don't match
					}
				}
			}
		});
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } // Format to Load
		catch ( Exception e) { e.printStackTrace(); } 
		
		// **** Algo JButton ***** //
		ImageIcon algo = new ImageIcon("./img/Algo.png"); // Set Icon to Button
		Algo = new JButton("Algo",algo); // initialize Algo button
		Algo.setVisible(true);
		Algo.setBounds(345,25, 100, 50); // Size and Location
		Algo.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		this.add(Algo); // Add to panel

		// **** Algo Mouse Listener ***** //
		Algo.addMouseListener(new MouseAdapter() {  // Press the button	
			public void mouseClicked(MouseEvent e) {
				if(LOADED) // If the CSV file is loaded
				{
						panel.getPlay().setIDs(-Tzvi_ID,Or_ID); // Algo 
						panel.StartAlgo(); // Start Algo
						Mat.setVisible(true);
						Start.setVisible(false);
						Algo.setVisible(false);
						LOADED = false; 
				}
				else
					JOptionPane.showMessageDialog(null, "Load new Game Please!"); // If no CSV file is loaded
			}
		});	
		// **** Matrix JButton ***** //
				ImageIcon mat = new ImageIcon("./img/Mat.png"); // Set Icon to Button
				Mat = new JButton("Mat",mat); // initialize Mat button
				Mat.setVisible(false);
				Mat.setBounds(455,25, 100, 50); // Size and Location
				Mat.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
				this.add(Mat); // Add to panel

				// **** Mat Mouse Listener ***** //
				Mat.addMouseListener(new MouseAdapter() {  // Press the button
					public void mouseClicked(MouseEvent e) {
						if(panel.HasPlayer()) // If we chose a place for the player
						panel.ShowMatrix(); 
					}
				});	

	}
	/* * * * * * * * * * * * * * * * * * Update GamePanel * * * * * * * * * * * * * * * */
	/**
	 * This method updates GamePanel for loading a new game
	 * @param panel
	 */
	public void UpdatePanel(GamePanel panel) {
		this.panel = panel;
	}
	/* * * * * * * * * * * * * * * * * * Set Visable True * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for which buttons display at the beginning of a game
	 */
	public static void SetVisableTrue() {
		Start.setVisible(true);
		Algo.setVisible(true);
		Mat.setVisible(false);
	}	
}
