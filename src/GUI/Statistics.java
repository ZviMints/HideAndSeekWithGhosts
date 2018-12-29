package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;
import com.mysql.fabric.xmlrpc.base.Array;

public class Statistics {
	private static String ans = "";
	// for each of the next Arrays : [ ] [ ]
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
				allInfo(resultSet); // Contains all information that comes from the database

				switch (resultSet.getString("SomeDouble")) // Select by file name
				{

				case "2128259830": // example 1
					updateInfo(resultSet, 0);
					break;

				case "1149748017": // example 2
					updateInfo(resultSet, 1);
					break;

				case "-683317070": // example 3
					updateInfo(resultSet, 2);
					break;

				case "1193961129": // example 4
					updateInfo(resultSet, 3);
					break;

				case "1577914705": // example 5
					updateInfo(resultSet, 4);
					break;

				case "-1315066918": // example 6
					updateInfo(resultSet, 5);
					break;

				case "-1377331871": // example 7
					updateInfo(resultSet, 6);
					break;

				case "306711633": // example 8
					updateInfo(resultSet, 7);
					break;

				case "919248096": // example 9
					updateInfo(resultSet, 8);
					break;

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


	/* * * * * * * * * * * * * * * * * * Update info statistic * * * * * * * * * * * * * * * */

	public void updateInfo(ResultSet resultSet , int index) throws SQLException {

		if(resultSet.getInt("FirstID") == 314977489 ) { // Check if we played the game
			if(BestGame[index][1] != 0) { // If we've already played the game
				if(resultSet.getDouble("Point") > BestGame[index][0]) // If the score is higher than the last high point
					BestGame[index][0]=resultSet.getInt("Point"); // new best game
			}
			else  {
				BestGame[index][0]=resultSet.getInt("Point"); // If this is the first game we played
				BestGame[index][1] = 1; // Mark we played
			}
		}
		else if(resultSet.getInt("FirstID") == -314977489 ) { // Check if we played the game
			if(BestGameAlgo[index][1] != 0) { // If we've already played the game
				if(resultSet.getDouble("Point") > BestGameAlgo[index][0]) // If the score is higher than the last high point
					BestGameAlgo[index][0]=resultSet.getInt("Point"); // new best game
			}
			else  {
				BestGameAlgo[index][0]=resultSet.getInt("Point"); // If this is the first game we played
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


	/**
	 * A method that records all the existing games in a database as a string
	 * @param resultSet
	 * @throws SQLException
	 */
	public void allInfo(ResultSet resultSet) throws SQLException {
		ans = resultSet.getInt("FirstID")+"\t" +
				resultSet.getInt("SecondID")+"\t" +
				resultSet.getTimestamp("LogTime") +"\t" +
				resultSet.getDouble("Point") +"\t" +
				getMapID(resultSet.getString("SomeDouble")) + " : " + resultSet.getString("SomeDouble") + "\n" + ans;
	}
	/* * * * * * * * * * * * * * * * * * getMapID * * * * * * * * * * * * * * * */

	private String getMapID(String s) {
		switch (s) // Select by file name
		{

		case "2128259830": // example 1
			return "Example 1";

		case "1149748017": // example 2
			return "Example 2";


		case "-683317070": // example 3
			return "Example  3";


		case "1193961129": // example 4
			return "Example  4";


		case "1577914705": // example 5
			return "Example  5";


		case "-1315066918": // example 6
			return "Example  6";


		case "-1377331871": // example 7
			return "Example  7";


		case "306711633": // example 8
			return "Example  8";


		case "919248096": // example 9
			return "Example  9";

		}
		return "Unknown map ID";
	}


	/* * * * * * * * * * * * * * * * * * average calculation * * * * * * * * * * * * * * * */
	/**
	 * Method for calculating average for each play
	 */
	public void aveCalculation() 
	{
		for (int i = 0; i < Average.length; i++)
		{
			Average[i][0] /= Average[i][1]; 
		}
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
