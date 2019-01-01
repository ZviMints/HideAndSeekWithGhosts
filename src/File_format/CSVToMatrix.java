/**
 * This class is responsible to make Dynamic matrix[][]
 * which is row contain Arraylist that represent line in csv
 * the number of rows is the number of csv file lines.
 * we can take Parameter from the matix in O(1)
 * @author Tzvi Mints and Or Abuhazira
 */
package File_format;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class CSVToMatrix {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private static String path; // Name of path
	private static ArrayList<ArrayList<String>> matrix; // Matrix for input values
	private static BufferedReader br; // Buffer to read a file
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	/**
	 * This method accepts a CSV file name and inserts the values into the matrix
	 * @param path
	 */
	public CSVToMatrix(String path) {
		this.setPath(path); // Name of the file we are putting into the matrix
		matrix = new ArrayList<ArrayList<String>>(); // Matrix for input values
		try {
			br = new BufferedReader(new FileReader(new File(path))); 
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			Reader();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public String getPath() { return path; }
	public int getRowsSize() { return matrix.size(); }
	public int getColumnsSize() { return matrix.get(0).size(); }
	public ArrayList<String> getHeader() { return matrix.get(0); }
	public void setPath(String path) { CSVToMatrix.path = path; }
	public ArrayList<String> getRowAtIndexI(int i) { return matrix.get(i); }
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "";
		for(int i=0; i<this.getRowsSize(); i++)
			ans += matrix.get(i) +"\n";
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Reader * * * * * * * * * * * * * * * */
	/**
	 * This method receives information from the constructor 
	 * and inserts the values into the matrix
	 * @param path
	 */
	public static void Reader() throws IOException {
		String data = br.readLine(); // String that accepts all information from the row in CSV
		int Columns = 0; // integer for column number
		while(data != null) // There is information from the file
		{
			matrix.add(new ArrayList<String>()); // Adds a list of information to each row in the matrix
			String[] rows = data.split(","); // Split to ","
			for (int i = 0; i < rows.length; i++) {
				matrix.get(Columns).add(rows[i]);
			}
			Columns++;
			data = br.readLine(); // Next line in the file
		}
	}
}