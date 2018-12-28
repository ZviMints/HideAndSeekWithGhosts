package myFrame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class statisticFrame {
	private static String[] ColumnHeader;
	private static Object[][] data;
	private static JLabel Icon;
	private static JScrollPane sp1;
	private static JFrame load;
	private static JFrame frame;
	private static JTextArea ta;
	private static JTable table;
	private static JScrollPane sp;
	
	public statisticFrame() {
		
	}	
	
	public void startFrame() {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				load = new JFrame("loading");
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
		ta.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
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
	/**
	 * This method generates a table that includes statistics for each game
	 */
	public void dataFromDB(Statistics stat) {
		data = new Object[9][4]; 
		for (int i = 0; i < data.length; i++) {
			data[i][0]= "example"+(i+1); // name of game
			data[i][1] = stat.getbestGame()[i][0]; 	// info for the best game in each game
			data[i][2] = stat.getAverage()[i][0]; // info for the average in each game
			data[i][3] = stat.getbestGameAlgo()[i][0]; //info for the best game algo in each game
		}

	}
	
	

}
