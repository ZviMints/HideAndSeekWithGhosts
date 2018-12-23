/**
 * This Class represent Data About Pacman that implements Meta_data
 * @author Tzvi Mints and Or Abuhazira
 */
package Pacman;

public class PacmanData {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private String id;
	private String speed;
	private String radius;

	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public PacmanData(String id, String speed, String radius) {
		this.id = id; 
		this.speed = speed; 
		this.radius = radius; 
		}

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public String toString() {
		String ans = "";
		ans += 	"ID" + ":" + getID() + "," ;
		ans += 	"Speed" + ":" + getSpeed() + "," ;
		ans += 	"Radius" + ":" + getRadius();
		return ans;
	}

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */ 
	public String getID() { return id; }
	public void setID(String id) { this.id = id; }
	public String getSpeed() { return speed; }
	public void setSpeed(String speed) { this.speed = speed; }
	public String getRadius() { return radius; }
	public void setRadius(String radius) { this.radius = radius; }
}

