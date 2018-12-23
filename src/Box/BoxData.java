/**
 * This Class represent Data About Box
 * @author Tzvi Mints and Or Abuhazira
 */
package Box;

public class BoxData {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private String id;

	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public BoxData(String id) {
		this.id = id; 
		}

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public String toString() {
		String ans = "";
		ans += 	"ID" + ":" + getID() + "," ;
		return ans;
	}

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */ 
	public String getID() { return id; }
	public void setID(String id) { this.id = id; }
}

