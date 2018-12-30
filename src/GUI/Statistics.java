package GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Statistics {
	private static final String MAP1 = "2128259830"; 
	private static final String MAP2 = "1149748017"; 
	private static final String MAP3 = "-683317070"; 
	private static final String MAP4 = "1193961129"; 
	private static final String MAP5 = "1577914705"; 
	private static final String MAP6 = "-1315066918"; 
	private static final String MAP7 = "-1377331871"; 
	private static final String MAP8 = "306711633"; 
	private static final String MAP9 = "919248096"; 
	private static final double TZVI_ID = 314977489;
	private static final double OR_ID = 311226617;


	private static String ans = "";
	public static double[][] BestGameAlgo; // אלגוריתם - מערך עבור שמירת הניקוד הגבוה עבור כל משחק 
	public static double[][] BestGame; // מערך עבור שמירת הניקוד הגבוה עבור כל משחק - שחקן
	public static double[][] Average; // מערך עבור חישוב הממוצע עבור כל משחק

	public Statistics()
	{
		BestGameAlgo = new double[9][2];
		BestGame = new double[9][2];
		Average = new double[9][2];
		
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);


			Statement statement = connection.createStatement();	
			//Select data
			String allCustomersQuery = "SELECT * FROM logs;";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);

			while(resultSet.next())
			{
				GetAllScoresFromDB(resultSet); // Contains all information that comes from the database
				String MapID = resultSet.getString("SomeDouble");
				switch (MapID) // Select by MapID 
				{
				case MAP1: UpdateToBestScores(resultSet, 0); break;
				case MAP2: UpdateToBestScores(resultSet, 1); break;
				case MAP3: UpdateToBestScores(resultSet, 2); break;
				case MAP4: UpdateToBestScores(resultSet, 3); break;
				case MAP5: UpdateToBestScores(resultSet, 4); break;
				case MAP6: UpdateToBestScores(resultSet, 5); break;
				case MAP7: UpdateToBestScores(resultSet, 6); break;
				case MAP8: UpdateToBestScores(resultSet, 7); break;
				case MAP9: UpdateToBestScores(resultSet, 8); break;
				}
			}
			ans = "FirstID\tSecondID\t"+"      "+"LogTime\t\tPoint\tMapID" + "\n" + ans;
			aveCalculation(); // Average calculation for all games
			resultSet.close();		
			statement.close();		
			connection.close();		
		}

		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* * * * * * * * * * * * * * * * * * UpdateToBestScores * * * * * * * * * * * * * * * */
	public void UpdateToBestScores(ResultSet resultSet , int index) throws SQLException {
		int current_name = resultSet.getInt("FirstID");
		double current_score = resultSet.getDouble("Point");
		if(current_name == TZVI_ID ) { // Check if we played the game
			if(BestGame[index][1] != 0) { // If we've already played the game
				if(current_score > BestGame[index][0]) // If the score is higher than the last high point
					BestGame[index][0] = current_score; // new best game
			}
			else  {
				BestGame[index][0] = current_score; // If this is the first game we played
				BestGame[index][1] = 1; // Mark we played
			}
		}
		else if(current_name == -TZVI_ID ) { // Check if we played the game
			if(BestGameAlgo[index][1] != 0) { // If we've already played the game
				if(current_score > BestGameAlgo[index][0]) // If the score is higher than the last high point
					BestGameAlgo[index][0] = current_score; // new best game
			}
			else  {
				BestGameAlgo[index][0] = current_score; // If this is the first game we played
				BestGameAlgo[index][1] = 1; // Mark we played
			}
		}
		
		else {
			Average[index][0]+=resultSet.getDouble("Point"); // Add game scores to other players' games
			Average[index][1]++; // Count games for the average calculation
		}
	}
	public static double[][] getBestGameAlgo() {
		return BestGameAlgo;
	}

	/* * * * * * * * * * * * * * * * * * GetAllScoresFromDB * * * * * * * * * * * * * * * */
	/**
	 * A method that records all the existing games in a database as a string
	 * @param resultSet
	 * @throws SQLException
	 */
	public void GetAllScoresFromDB(ResultSet resultSet) throws SQLException {
		ans = resultSet.getInt("FirstID")+"\t" +
				resultSet.getInt("SecondID")+"\t" +
				resultSet.getTimestamp("LogTime") +"\t" +
				resultSet.getDouble("Point") +"\t" +
				getMapID(resultSet.getString("SomeDouble")) + " : " + resultSet.getString("SomeDouble") + "\n" + ans;
	}
	/* * * * * * * * * * * * * * * * * * getMapID * * * * * * * * * * * * * * * */
	private String getMapID(String MapID) {
		switch (MapID) // Select by file name
		{
		case MAP1: return "Example 1";
		case MAP2: return "Example 2";
		case MAP3: return "Example 3";
		case MAP4: return "Example 4";
		case MAP5: return "Example 5";
		case MAP6: return "Example 6";
		case MAP7: return "Example 7";
		case MAP8: return "Example 8";
		case MAP9: return "Example 9";
		default: return "Unknown MapID";
		}
	}
	/* * * * * * * * * * * * * * * * * * Average Calculation * * * * * * * * * * * * * * * */
	/**
	 * Method for calculating average for each play
	 */
	public void aveCalculation() 
	{
		for (int i = 0; i < Average.length; i++)
			Average[i][0] /= Average[i][1]; 
	}
	/* * * * * * * * * * * * * * * * * * Getter and setter * * * * * * * * * * * * * * * */
	public static double[][] getbestGame() { return BestGame; }
	public static double[][] getAverage() { return Average; }
	public static double[][] getbestGameAlgo() { return BestGameAlgo; }
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		return ans;
	}
}
