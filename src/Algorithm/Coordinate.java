/**
 * This is class that represent a Coordinate 
 * each Coordinate have x,y and pred
 * used for BFS
 * @version 4.0
 * @author Tzvi Mints and Or Abuhazira
 */
package Algorithm;
public class Coordinate {
	private int _x; // the current x in pixels
	private int _y; // the current y in pixels
	private Coordinate _pred; // the pred of the current Coordinate
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	/**
	 * The Constructor
	 * @param x is the input x in pixels ( width )
	 * @param y is the input y in pixels ( height )
	 */
	public Coordinate(int x, int y)
	{
		this._x = x;
		this._y = y;
		this._pred = null;
	}
	public Coordinate(int x, int y, Coordinate pred)
	{
		this._x = x;
		this._y = y;
		this._pred = pred;
	}
	/* * * * * * * * * * * * * * * * * * Getters and Setters * * * * * * * * * * * * * * * */
	public int getX()
	{
		return this._x;
	}
	public int getY()
	{
		return this._y;
	}
	public Coordinate getPred()
	{
		return this._pred;
	}
	/* * * * * * * * * * * * * * * * * * equals * * * * * * * * * * * * * * * */
	/**
	 * The Method is responsible for check if the coordinates are equals
	 * @param other is the input coordinate
	 * @return true iff this.x == other.x and this.y = other.y
	 */
	public boolean equals(Coordinate other)
	{
		return (this._x == other._x) && (this._y == other._y);
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		if(_pred != null)
		return "(" + _x + "," + _y +") "+ "PRED: " + "["+_pred.getX() +"," + _pred.getY() + "]" + ")";
		else
			return "(" + _x + "," + _y + ")";
	}
}
