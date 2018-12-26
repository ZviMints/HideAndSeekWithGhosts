package myFrame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Score extends JPanel {
	private static JLabel date;
	private static JLabel totaltime;
	private static JLabel score;
	private static JLabel Timeleft;
	private static JLabel killed;
	private static JLabel box;
	private Image Background = Toolkit.getDefaultToolkit().getImage("./img/ScoreBackground.png"); // Menu background image path
	public Score()
	{
		date = new JLabel("Date" + ": " + new Date().toString());
		totaltime = new JLabel("Time" + ": " + "0");
		score = new JLabel("Score" + ": " + "0");
		Timeleft = new JLabel("Time Left" + ": " + "inf");
		killed = new JLabel("Killed" + ": " + "0");
		box = new JLabel("Out of Box" + ": " + "0");

		
		date.setBounds(15,20, 200, 15);
		totaltime.setBounds(15,40, 200, 15);
		score.setBounds(15,60, 200, 15);
		Timeleft.setBounds(212,20,200, 15);
		killed.setBounds(212,40, 200, 15);
		box.setBounds(212,60, 200, 15);
		Timeleft.setFont(new Font("Ariel",Font.BOLD, 12));
		score.setFont(new Font("Ariel",Font.BOLD, 12));

		
				
		
		
		
		add(date);
		add(totaltime);
		add(score);
		add(Timeleft);
		add(killed);
		add(box);
	}
	/* * * * * * * * * * * * * * * * * * Paint Background  * * * * * * * * * * * * * * * */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Background,0,0,getWidth(),getHeight(), this);			    
	}
	/* * * * * * * * * * * * * * * * * * updateScore * * * * * * * * * * * * * * * */
	public static void updateScore(String statistics) {
		String[] s = statistics.split(",");
		String _date = s[0].substring(s[0].indexOf(':')+1);
		String _totaltime = s[1].substring(s[1].indexOf(':')+1);
		String _score = s[2].substring(s[2].indexOf(':')+1);
		String _Timeleft = s[3].substring(s[3].indexOf(':')+1);
		String _killed = s[4].substring(s[4].indexOf(':')+1);
		String _box = s[5].substring(s[5].indexOf(':')+1);
		date.setText("Date" + ": " + _date);
		totaltime.setText("Time" + ": " + _totaltime);
		score.setText("Score" + ": " + _score);
		Timeleft.setText("Time Left" + ": " + _Timeleft);
		killed.setText("Killed" + ": " + _killed);
		box.setText("Out of Box" + ": " + _box);
	}
	/* * * * * * * * * * * * * * * * * * updateScore * * * * * * * * * * * * * * * */
}
