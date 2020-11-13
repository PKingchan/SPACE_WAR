import java.awt.*;
import java.awt.image.*;
import java.net.URL;
import javax.imageio.*;

public class PowerUp {
	private Color color;
	private double x, y;
	private int r, type;
	private BufferedImage imgLife, imgPower1, imgPower2;

	//	type 1 = +1 life
	//	type 2 = +1 power
	//	type 3 = +2 power
	public PowerUp (int type, double x, double y) {
		this.type = type;
		this.x = x;
		this.y = y;

		if (type == 1) {
			if (imgLife == null) imgLife = new Generals().loadImg("/img/features/life.png");
			r = 3;
		}
		if (type == 2) {
			if (imgPower1 == null) imgPower1 = new Generals().loadImg("/img/features/item1.png");
			r = 4;
		}
		if (type == 3) {
			if (imgPower2 == null) imgPower2 = new Generals().loadImg("/img/features/item2.png");
			r = 6;
		}
	}

	//		Getters
	public double getX () {return x;}
	public double getY () {return y;}
	public double getR () {return r;}
	public int getType () {return type;}

	//		Render methods
	public boolean update () {
		y += 2;
		if (y > SpacePanel.height + r) return true;
		return false;
	}
	public void draw (Graphics2D g) {
		if (type == 1) {
			g.drawImage(imgLife, (int) (x - r), (int) (y - r), null);
		} else 
		if (type == 3) {
			g.drawImage(imgPower2, (int) (x - r), (int) (y - r), null);
		} else 
		if (type == 2) {
			g.drawImage(imgPower1, (int) (x - r), (int) (y - r), null);
		}
	}
}