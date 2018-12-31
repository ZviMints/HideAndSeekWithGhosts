/**
 * This Class represent Fruit 
 * @author Tzvi Mints and Or Abuhazira
 */
package Player;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import Coords.MyCoords;
import Geom.Point3D;
import Map.Map;
import Objects.Box;
import Objects.Fruit;
import Objects.Ghost;
import Objects.Pacman;

public class Player {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D _p; // In [Lat,Lon,Alt]
	private String _id;
	private Image PlayerImage = Toolkit.getDefaultToolkit().getImage("./img/Player.png"); // Player image
	public double ang = 361;
	public boolean InDanger = false;
	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public Player(Point3D p, String id)
	{
		this._id = id;
		this._p = p;
	}
	/* * * * * * * * * * * * * *  toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "";
		ans += "Player id: " + _id + " Point: " + _p;
		return ans;
	}
	/* * * * * * * * * * * * * *  Transfer * * * * * * * * * * * * * * * */
	public void transfer(Point3D vec)
	{
		MyCoords coords = new MyCoords();
		this._p = coords.add(this._p, vec);
	}
	/* * * * * * * * * * * * * *  Setters and Getters * * * * * * * * * * * * * * * */
	public Point3D getP() {
		return _p;
	}
	public void setP(double x, double y, double z) {
		this._p.setX(x);
		this._p.setY(y);
		this._p.setZ(z);
	}
	public Image getPlayerImage() {
		return PlayerImage;
	}
	public void setPlayerImage(Image playerImage) {
		PlayerImage = playerImage;
	}
	/* * * * * * * * * * * * * *  intersect Ghosts * * * * * * * * * * * * * * * */
	public boolean intersectGhost(List<Ghost> GhostsList, Map map) {
		for(int i=0 ; i<GhostsList.size(); i++)
		{
			Ghost g = GhostsList.get(i);
			Point3D g_p = map.getPixelFromCord((g.getP()));
			Point3D p_p = map.getPixelFromCord(this.getP());
			double eps = 2;
			if(p_p.equals(g_p,eps))
			{
				return true;	
			}
		}
		return false;
	}
	/* * * * * * * * * * * * * *  intersect Box * * * * * * * * * * * * * * * */
	public boolean intersectBox(List<Box> BoxsList, Map map) {
		for(int i=0 ; i<BoxsList.size(); i++)
		{
			Box b = BoxsList.get(i);
			Point3D b_p0 = map.getPixelFromCord(b.getP0());
			Point3D b_p1 = map.getPixelFromCord(b.getP1());
			Point3D p = map.getPixelFromCord(this.getP());
			double eps = 13;
			if( p.x() >= b_p0.x() - eps && p.x() <= b_p1.x() + eps  && p.y() >= b_p1.y() - eps && p.y() < b_p0.y() + eps )
				return true;
		}
		return false;
	}
}
