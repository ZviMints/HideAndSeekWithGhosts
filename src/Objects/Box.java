package Objects;
import java.awt.Rectangle;
public class Box extends Rectangle {
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private String id;
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Box(String id, int x, int y, int width, int height) { 
		super(x,y,width,height);
		this.id = id;
	}
	public Box(Box b) {
		super(b);
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Box:" + "--> ";
		ans += "ID" + ":" + this.id;
		ans +=  "," + "x" + ":" + this.x ;
		ans +=  "," + "y" + ":" + this.y ;
		ans +=  "," + "width" + ":" + this.width ;
		ans +=  "," + "height" + ":" + this.height ;
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Getter and Setters * * * * * * * * * * * * * * * */
	public String getId() {
		return id;
	}	
}
