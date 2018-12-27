package Algorithm;
public class Coordinate {
	private int _x;
	private int _y;
	private Coordinate _pred;
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
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
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		if(_pred != null)
		return "(" + _x + "," + _y +") "+ "PRED: " + "["+_pred.getX() +"," + _pred.getY() + "]" + ")";
		else
			return "(" + _x + "," + _y + ")";
	}
}
