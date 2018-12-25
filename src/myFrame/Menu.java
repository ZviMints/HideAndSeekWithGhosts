/**
 * Will class represent the GUI Menu of the Project.
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private  JButton Start; // Start button
	private  JButton Load; // Load button
	private  JButton Statistics; // Statistics button
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
		Start.setBounds(10, 25, 100, 50);
		this.add(Start);

		// **** Statistics JButton ***** //
		Statistics = new JButton("Statistics");
		Statistics.setVisible(true);
		Statistics.setBounds(130, 25, 100, 50);
		this.add(Statistics);

		Statistics.addMouseListener(new MouseAdapter() { 		
			public void mouseClicked(MouseEvent e)  {
				Statistics stat = new Statistics();
				JTextArea ta = new JTextArea();
				ta.setText(stat.toString());
				ta.setEditable(false);
				JScrollPane sp=new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				JOptionPane.showMessageDialog(null, sp);
			}});
		
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
		Load.setBounds(260,25, 100, 50);
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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch ( Exception e) {
			e.printStackTrace();
		} 
	}
}
