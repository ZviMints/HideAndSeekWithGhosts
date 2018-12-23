/**
 * This Class represent Data About Fruit that implements Meta_data
 * @author Tzvi Mints and Or Abuhazira
 */
package Player;
public class PlayerData {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private String id;
	private boolean Damaged; // If Player get Damage

	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public PlayerData(String id) { this.id = id; this.Damaged = false;}

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public String toString() {
		String ans = "";
		ans += 	"ID" + ":" + getID();
		return ans;
	}

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */ 
	public String getID() { return id; } // Get ID
	public void setID(String id) { this.id = id; } // Set ID
	public void Hurt() {this.Damaged = true; } // Make Player Damaged
	public boolean Damaged() { return this.Damaged; } // Check if Player is Damaged
	public void Unhurt() {this.Damaged = false;} // Make Player Undamaged
}
