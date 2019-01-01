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
	private static String[] ColumnHeader; // Column Header of table
	private static Object[][] data; // The information in the table
	private static JLabel Icon; // Icon of statistic
	private static JFrame load; // Icon of Loading statistic
	private static JFrame frame; // frame of statistic
	private static JTextArea ta; // The statistics that come from the DB
	private static JTable table; // Table for statistics
	private static JScrollPane sp; 
	private static JScrollPane sp1; 
	/* * * * * * * * * * * * * * * * * * Start Panel * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible for starting the statistic frame
	 * And a loading window until the statistic frame appears
	 */
	public void startFrame() {
		load = new JFrame("Loading");  // initialize frame loading
		ImageIcon loading = new ImageIcon("./img/load.gif"); // image loading
		load.add(new JLabel(loading, JLabel.CENTER));
		load.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		load.setBounds(300,150,600, 400);
		load.setVisible(true);
		load.setResizable(false);
		// **** Thread Loading ***** //
		Thread getInfo_FROM_DB = new Thread(new Runnable() {
			@Override
			public void run() {
				Setframe(); // set statistic frame
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
				load.setVisible(false); //Set visible false to loading frame
				frame.setVisible(true); //Set visible true to statistic frame
			}
		});
		Loading.start();
	}
	/* * * * * * * * * * * * * * * * * * Set Frame * * * * * * * * * * * * * * * */
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
		frame.setBounds(200,20,1000,500);// Size and Location
		
		// **** Statistic text ***** //
		Statistics stat = new Statistics(); // Initialize statistic
		ta = new JTextArea(); // Initialize text area
		ta.setFont(new Font("Helvetica Neue", Font.PLAIN, 12)); // Font and size of text
		ta.setText(stat.toString()); // Writing statistics in text
		ta.setEditable(false); // Can not edit statistic
		Color c = Color.decode("#CCE5FF"); // Color Code
		ta.setBackground(c); // Set color Background for text
		sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(0,173,515,294); // Size and Location
		frame.add(sp); // Add to frame
		
		// **** Statistic Icon ***** //
		ImageIcon statistic = new ImageIcon("./img/statistic.gif"); // Set Icon to image
		Icon = new JLabel(statistic); // Set label to Icon
		Icon.setBounds(500, 173, 500, 300);// Size and Location
		frame.add(Icon); // Add to frame

		// **** Statistic table ***** //
		dataFromDB(stat); // Enters information to the array
		ColumnHeader = new String[] {"File name", "Player Best Result", "OOP COURSE Average - {OUR STATS}","Algo Best Result"};// Column Header of table
		table = new JTable(data , ColumnHeader); // Initialize table
		table.setEnabled(false);// Can not edit table
		Color d = Color.decode("#FFCC99"); // Color Code
		table.setBackground(d);// Set color Background for table
		table.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));// Font and size of text
		sp1 = new JScrollPane(table);
		frame.add(new JScrollPane(sp1)); // Add to frame
	}
	/* * * * * * * * * * * * * * * * * * Data From DB * * * * * * * * * * * * * * * */
	/**
	 * This method generates a table that includes statistics for each game
	 */
	public void dataFromDB(Statistics stat) {
		data = new Object[9][4]; 
		for (int i = 0; i < data.length; i++) {
			data[i][0]= "Ex4_OOP_example"+(i+1); // name of game
			data[i][1] = Statistics.getbestGame()[i][0]; // info for the best game in each game
			data[i][2] = Statistics.getAverage()[i][0]; // info for the average in each game
			data[i][3] = Statistics.getbestGameAlgo()[i][0]; //info for the best game algo in each game
		}

	}
}
