/**
 * This Class represent Data About Pacman that implements Meta_data
 * @author Tzvi Mints and Or Abuhazira
 */
package Objects;

import java.awt.Color;

public class PacmanData implements Meta_data {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private String id;
	private String speed;
	private String radius;
	public Color color;
	public double time = 0 ;

	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public PacmanData(String id, String speed, String radius, Color color) {
		this.id = id; 
		this.speed = speed; 
		this.radius = radius; 
		this.color = color;
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
	public double getTime() { return time; }
	public void setTime(double time) { this.time = time; }

}

