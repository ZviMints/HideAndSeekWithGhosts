/**
 * Will class represent the GUI Menu of the Project.
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private  JButton Start; // Start button
	private  JButton Load; // Load button
	private Image MenuBackground = Toolkit.getDefaultToolkit().getImage("./img/MenuBackground.jpg"); // Menu background image path
	private GamePanel panel;
	private static final long Tzvi_ID = 314977489;
	private static final long Or_ID = 311226617;

	/* * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */

	/* * * * * * * * * * * * * *  Constructor  * * * * * * * * * * * * * * * */
	public Menu(GamePanel panel)
	{
		this.panel = panel;
		initialize();
	}
	/* * * * * * * * * * * * * * * * * * Initialize Window * * * * * * * * * * * * * * * */
	private void initialize() {
		
		// **** Start JButton ***** //
		Start = new JButton("Start Game");
		Start.setVisible(true);
		Start.setBounds(120, 25, 100, 50);
		this.add(Start);
		
		// **** Start Mouse Listener ***** //
		Start.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				panel.play.setIDs(Tzvi_ID,Or_ID);
				panel.StartGame();
				Start.setVisible(false);
			}
		});

		// **** Load JButton ***** //
		Load = new JButton("Load Game");
		Load.setVisible(true);
		Load.setBounds(240,25, 100, 50);
		this.add(Load);

		// **** Load Mouse Listener ***** //
		Load.addMouseListener(new MouseAdapter() { 		// ************** On Click Load
			public void mouseClicked(MouseEvent e)  {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					String _Filename = chooser.getSelectedFile().getAbsolutePath();
					if(_Filename.contains(".csv")) 
					{
//						MyFrame.game = new Game(_Filename);
//						MainFrame.mainSplittedPane.invalidate();
//						MainFrame.mainSplittedPane.setVisible(false);
//						MainFrame.mainSplittedPane.removeAll();
//						MainFrame.panel = new DotsAndLines(MyFrame.game,MainFrame.map);
//						MainFrame.StartPanel();
					}
					else {
						JOptionPane.showMessageDialog(null, "This File is not .CSV file");
					}
				}
			}
		});
	}
}
