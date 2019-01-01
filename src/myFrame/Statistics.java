package myFrame;

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

	public static double[][] bestGame = new double[9][2]; // מערך עבור שמירת הניקוד הגבוה עבור כל משחק 
	public static double[][] average = new double[9][2]; // מערך עבור חישוב הממוצע עבור כל משחק

	public Statistics()
	{
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
			ans+= "FirstID\tSecondID\tThirdID\tLogTime\t\tPoint\tSomeDouble";
			ans+= "\n\n";

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

				case "-895765157": // example 8
					updateInfo(resultSet, 7);
					break;

				case "919248096": // example 9
					updateInfo(resultSet, 8);
					break;

				default:	
					break;
				}
			}
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
			if(bestGame[index][1] != 0) { // If we've already played the game
				if(resultSet.getDouble("Point") > bestGame[index][0]) // If the score is higher than the last high point
					bestGame[index][0]=resultSet.getInt("Point"); // new best game
			}
			else 
			{
				bestGame[index][0]=resultSet.getInt("Point"); // If this is the first game we played
				bestGame[index][1] = 1; // Mark we played
			}
		}
		else
		{
			average[index][0]+=resultSet.getDouble("Point"); // Add game scores to other players' games
			average[index][1]++; // Count games for the average calculation
		}
	}
	/**
	 * A method that records all the existing games in a database as a string
	 * @param resultSet
	 * @throws SQLException
	 */
	public void allInfo(ResultSet resultSet) throws SQLException {
		ans+= resultSet.getInt("FirstID")+"\t" +
				resultSet.getInt("SecondID")+"\t" +
				resultSet.getInt("ThirdID")+"\t" +
				resultSet.getTimestamp("LogTime") +"\t" +
				resultSet.getDouble("Point") +"\t" +
				resultSet.getString("SomeDouble");
		ans+= "\n";
	}

	/* * * * * * * * * * * * * * * * * * average calculation * * * * * * * * * * * * * * * */
	/**
	 * Method for calculating average for each play
	 */
	public void aveCalculation() 
	{
		for (int i = 0; i < average.length; i++)
		{
			average[i][0] /= average[i][1]; 
		}
	}
	/* * * * * * * * * * * * * * * * * * Getter and setter * * * * * * * * * * * * * * * */

	public static double[][] getBestGame() { return bestGame; }
	public static double[][] getAverage() { return average; }

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */

	public String toString()
	{
		return ans;
	}
}
