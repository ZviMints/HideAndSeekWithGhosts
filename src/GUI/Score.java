package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Score extends JPanel {
	private static JLabel date; // The current time
	private static JLabel totaltime; // Total time of Game
	private static JLabel score; // Score of Game
	private static JLabel Timeleft; // The time left from the game 
	private static JLabel killed; // How many times the ghosts hit
	private static JLabel box; // How many time the Box hit
	private Image Background = Toolkit.getDefaultToolkit().getImage("./img/ScoreBackground.png"); // Menu background image path
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	/**
	 * This method builds a score Frame that includes
	 * data ,total time ,score ,time left ,killed ,box
	 */
	public Score()
	{
		date = new JLabel("Date" + ": " + new Date().toString()); // Initialize the current time
		totaltime = new JLabel("Time" + ": " + "0"); // Initialize the total time of game
		score = new JLabel("Score" + ": " + "0"); // Initialize the score of game
		Timeleft = new JLabel("Time Left" + ": " + "INF"); // Initialize the time left of game
		killed = new JLabel("Killed" + ": " + "0"); // Initialize how many times the ghosts hit
		box = new JLabel("Touched Box" + ": " + "0"); // Initialize how many times the box hit
		// ************ Size Font and Location ************ //
		date.setBounds(15,20, 200, 15);
		totaltime.setBounds(15,40, 200, 15);
		score.setBounds(15,60, 200, 15);
		Timeleft.setBounds(212,20,200, 15);
		killed.setBounds(212,40, 200, 15);
		box.setBounds(212,60, 200, 15);
		Timeleft.setFont(new Font("Ariel",Font.BOLD, 12));
		score.setFont(new Font("Ariel",Font.BOLD, 12));
		// ************ Add to Panel ************ //
		add(date);
		add(totaltime);
		add(score);
		add(Timeleft);
		add(killed);
		add(box);
	}
	/* * * * * * * * * * * * * * * * * * Paint Background  * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for drawing the image Background
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Background,0,0,getWidth(),getHeight(), this); // Size and Location for image			    
	}
	/* * * * * * * * * * * * * * * * * * updateScore * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for updating at 
	 * data ,total time ,score ,time left ,killed ,box any given time
	 * This method receives information from the statistics
	 * @param statistics
	 */
	public static void updateScore(String statistics) {
		String[] s = statistics.split(","); // Enters all the information into a string array
		String _date = s[0].substring(s[0].indexOf(':')+1); // The current time
		String _totaltime = s[1].substring(s[1].indexOf(':')+1); // The time passed from the game
		String _score = s[2].substring(s[2].indexOf(':')+1); // Current score obtained
		String _Timeleft = s[3].substring(s[3].indexOf(':')+1); // The time left for the game
		String _killed = s[4].substring(s[4].indexOf(':')+1); // The vulnerability of the current ghosts
		String _box = s[5].substring(s[5].indexOf(':')+1); // The vulnerability of the current boxs
		// **** Set Score in Text ***** //
		date.setText("Date" + ": " + _date);
		totaltime.setText("Time" + ": " + _totaltime);
		score.setText("Score" + ": " + _score);
		Timeleft.setText("Time Left" + ": " + _Timeleft);
		killed.setText("Ghost Damage" + ": " + _killed);
		box.setText("Box Hit" + ": " + _box);
	}
}
