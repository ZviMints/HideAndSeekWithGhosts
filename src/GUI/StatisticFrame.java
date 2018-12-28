package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class StatisticFrame {
	private static String[] ColumnHeader;
	private static Object[][] data;
	private static JLabel Icon;
	private static JScrollPane sp1;
	private static JFrame load;
	private static JFrame frame;
	private static JTextArea ta;
	private static JTable table;
	private static JScrollPane sp;

	public void startFrame() {
		load = new JFrame("Loading");
		ImageIcon loading = new ImageIcon("./img/load.gif");
		load.add(new JLabel(loading, JLabel.CENTER));
		load.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		load.setBounds(300,150,600, 400);
		load.setVisible(true);
		load.setResizable(false);
		
		Thread getInfo_FROM_DB = new Thread(new Runnable() {
			@Override
			public void run() {
				Setframe();
			}
		});
		getInfo_FROM_DB.start();
		
		Thread Loading = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				load.setVisible(false);
				frame.setVisible(true);
			}
		});
		Loading.start();
	}
	/**
	 * This method produces items of statistics including a table and info from Database
	 */
	public void Setframe() {
		// **** Statistic frame ***** //

		frame = new JFrame("Statistics");
		frame.setLayout(null);
		frame.setVisible(false);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setBounds(200,20,1000,500);

		// **** Statistic text ***** //

		Statistics stat = new Statistics(); 
		ta = new JTextArea();
		ta.setBounds(0,0,100,200);
		ta.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		ta.setText(stat.toString()); 
		ta.setEditable(false);
		Color c = Color.decode("#CCE5FF"); 
		ta.setBackground(c);
		sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(0,173,515,294);
		frame.add(sp);

		// **** Statistic Icon ***** //

		ImageIcon statistic = new ImageIcon("./img/statistic.gif"); // Set Icon to Button
		Icon = new JLabel(statistic);
		Icon.setBounds(500, 173, 500, 300);
		frame.add(Icon);

		// **** Statistic table ***** //

		dataFromDB(stat);
		ColumnHeader = new String[] {"File name", "Player Best Result", "OOP COURSE Average - {OUR STATS}","Algo Best Result"};
		table = new JTable(data , ColumnHeader);
		table.setEnabled(false);
		Color d = Color.decode("#FFCC99"); 
		table.setBackground(d);
		table.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		sp1 = new JScrollPane(table);
		frame.add(new JScrollPane(sp1));
	}
	/**
	 * This method generates a table that includes statistics for each game
	 */
	public void dataFromDB(Statistics stat) {
		data = new Object[9][4]; 
		for (int i = 0; i < data.length; i++) {
			data[i][0]= "Ex4_OOP_example"+(i+1); // name of game
			data[i][1] = Statistics.getbestGame()[i][0]; 	// info for the best game in each game
			data[i][2] = Statistics.getAverage()[i][0]; // info for the average in each game
			data[i][3] = Statistics.getbestGameAlgo()[i][0]; //info for the best game algo in each game
		}

	}
}
