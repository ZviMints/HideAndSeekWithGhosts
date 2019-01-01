/**
 * This Class is responsible to find the best greedy way to find
 * the shortest road by time
 * @author Tzvi Mints and Or Abuhazira
 */
package ShortestPathAlgo;

import myFrame.GameToMatrix;

public class Algo {
	char[][] mat;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	

	/* * * * * * * * * * * * * *  Getters and Setters * * * * * * * * * * * * * * * */


	/* * * * * * * * * * * * * *  Constructors * * * * * * * * * * * * * * * */
	public Algo(GameToMatrix matrix) // For the First time
	{
		this.mat = matrix.getMat();
	}
	public int[] getNextStep()
	{
		int[] ans = new int[2];
		return ans;
	}
}
