/**
 * Will class represent the GUI Menu of the Project.
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.Image;
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
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import Geom.Point3D;


public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private static  JButton Start; // Start button
	private static JButton Load; // Load button
	private static JButton Statistics; // Statistics button
	private static GamePanel panel;
	private static JFrame frame;
	private static JTextArea ta;
	private static JScrollPane sp;
	private static MyFrame myFrame;
	private static JButton Algo;
	private static boolean LOADED = false;
	private static final long Tzvi_ID = 314977489;
	private static final long Or_ID = 311226617;
	private static JButton Mat;

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
				frame = new JFrame();
				frame.setLayout(null);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setBounds(200,20,700,500);
				Statistics stat = new Statistics();
				ta = new JTextArea();
				ta.setBounds(5,5,100,200);
				ta.setText(stat.toString());
				sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				sp.setBounds(0,0,700,400);
				JLabel info = new JLabel("Best:");
				info.setBounds(200, 400, 500, 100);
				frame.add(info);
				frame.add(sp);

			}});

		// **** Start Mouse Listener ***** //
		Start.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				if(LOADED)
				{
					if(panel.HasPlayer())
					{
						panel.play.setIDs(Tzvi_ID,Or_ID);
						panel.StartGame();
						Mat.setVisible(true);
						Start.setVisible(false);
						Algo.setVisible(false);
						LOADED = false;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Tap on the Map to Put Player First");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Load new Game Please!");
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
							LOADED = true;
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
				if(LOADED)
				{
					if(panel.HasPlayer())
					{
						panel.play.setIDs(Tzvi_ID,Or_ID);
						panel.StartAlgo();
						Mat.setVisible(true);
						Start.setVisible(false);
						Algo.setVisible(false);
						LOADED = false;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Tap on the Map to Put Player First");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Load new Game Please!");
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
	}
}
