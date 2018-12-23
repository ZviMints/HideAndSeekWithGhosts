/**
 * Will class represent the GUI Menu of the Project.
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package myFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Menu extends JPanel{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private  JLabel Start; // Start button
	private  JLabel Load; // Load button
	private  JTextField Score; // Score TextField
	private Image MenuBackground = Toolkit.getDefaultToolkit().getImage("./img/MenuBackground.jpg"); // Menu background image path

	private  MyFrame MainFrame;
	private  GamePanel Panel;

	/* * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */

	/* * * * * * * * * * * * * *  Constructor  * * * * * * * * * * * * * * * */
	public Menu(MyFrame myFrame)
	{
		initialize();
	}
	/* * * * * * * * * * * * * * * * * * Paint Background  * * * * * * * * * * * * * * * */
    @Override
    protected void paintComponent(Graphics g) {
		    g.drawImage(MenuBackground,0,0, getWidth(), getHeight(), this);
		    super.paintComponent(g);
		  }
	/* * * * * * * * * * * * * * * * * * Initialize Window * * * * * * * * * * * * * * * */
	private void initialize() {
		
		// **** Start JButton ***** //
		Start = new JLabel(new ImageIcon("./img/Start.png"));
		Start.setVisible(true);
		Start.setBounds(0, 20, 232, 69);
		this.add(Start);
		
		// **** Start Mouse Listener ***** //
		Start.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				// Do Something
			}
		});

		// **** Load JButton ***** //
		Load = new JLabel(new ImageIcon("./img/Load.png"));
		Load.setVisible(true);
		Load.setBounds(0, 20 + 20 + 69, 232, 69);
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

		// **** Score Text Field ***** //
		final ImageIcon Score_Image = new ImageIcon("./img/Score.png");
		Score = new JTextField() { // Making TextArea From Image
			private static final long serialVersionUID = 1L;
			Image image = Score_Image.getImage();
			{
				setOpaque(false);
			}
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, this);
				super.paint(g);
			}
		};	
		Score.setHorizontalAlignment(JTextField.CENTER);
		Score.setBorder(null);
		Score.setVisible(false);
		Score.setText("0");
		Score.setFont(new Font("Courier New", Font.PLAIN, 15));
		Score.setForeground(Color.WHITE);
		Score.setBounds(0 + 8, 697 - 120 , 232, 69);
		Score.setEditable(false);
		this.add(Score);
		
	}
}
