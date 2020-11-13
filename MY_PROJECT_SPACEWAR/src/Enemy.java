import java.awt.*;
import java.awt.image.*;
import java.net.URL;
import javax.imageio.*;

public class Enemy {
	private boolean ready, dead, hit;
	private Color color;
	private BufferedImage mon1, mon2, mon3, mon4, image;
	private double x, y, dx, dy, rad, speed;
	private int r, health, type, rank;
	private long hitTimer;

	public Enemy (int type, int rank) {
		this.type = type;
		this.rank = rank;

		//		 types and ranks
		if (type == 1) {
			if (mon1 == null) mon1 = new Generals().loadImg("/img/etc/mon1.png");
			if (rank == 1) {
				speed = 3;
				r = 15;
				health = 1;
			}
			if (rank == 2) {
				speed = 3;
				r = 20;
				health = 2;
			}
			if (rank == 3) {
				speed = 1.5;
				r = 25;
				health = 3;
			}
			if (rank == 4) {
				speed = 1.5;
				r = 30;
				health = 5;
			}
			image = mon1;
		}
		if (type == 2) {
			if (mon2 == null) mon2 = new Generals().loadImg("/img/etc/mon2.png");
			if (rank == 1) {
				speed = 3;
				r = 15;
				health = 2;
			}
			if (rank == 2) {
				speed = 3;
				r = 25;
				health = 3;
			}
			if (rank == 3) {
				speed = 2.5;
				r = 30;
				health = 3;
			}
			if (rank == 4) {
				speed = 2.5;
				r = 40;
				health = 4;
			}
			image = mon2;
		}
		if (type == 3) {
			if (mon3 == null) mon3 = new Generals().loadImg("/img/etc/mon3.png");
			if (rank == 1) {
				speed = 1.5;
				r = 15;
				health = 5;
			}
			if (rank == 2) {
				speed = 1.5;
				r = 25;
				health = 6;
			}
			if (rank == 3) {
				speed = 1.5;
				r = 30;
				health = 7;
			}
			if (rank == 4) {
				speed = 1.5;
				r = 40;
				health = 8;
			}
			image = mon3;
		}

		

		x = Math.random() * SpacePanel.width / 2 + SpacePanel.width / 4;
		y = -r;

		double angle = Math.random() * 140 + 20;
		rad = Math.toRadians(angle);

		dx = Math.cos(rad) * speed;
		dy = Math.sin(rad) * speed;

		ready = false;
		dead = false;
		hit = false;
		hitTimer = 0;
	}

	//		Setters
	//		Getters
	public double getX () {return x;}
	public double getY () {return y;}
	public int getR () {return r;}
	public boolean isDead () {return dead;}
	public int getType () {return type;}
	public int getRank () {return rank;}

	//		General methods
	public void explode () {
		if (rank > 1) {
			int amount = 0;
			if (type == 1) amount = 2;
			if (type == 2) amount = 3;
			if (type == 3) amount = 4;
			

			for (int i = 0; i < amount; i++) {
				Enemy e = new Enemy(getType(), getRank() - 1);
				e.x = this.x;
				e.y = this.y;
				double angle = 0;

				if (!ready) angle = Math.random() * 140 + 20;
				else angle = Math.random() * 360;

				e.rad = Math.toRadians(angle);
				SpacePanel.enemies.add(e);
			}
		}
	}
	public void hit () {
		health--;
		if (health <= 0) dead = true;
		hit = true;
		hitTimer = System.nanoTime();
	}

	//		Render methods
	public void update () {
		x += dx;
		y += dy;

		if (!ready) 
			if (x > r && x < SpacePanel.width - r && y > r && y < SpacePanel.height - r) 
				ready = true;

		//		Bounce
		if (x < r && dx < 0) dx = -dx;
		if (y < r && dy < 0) dy = -dy;
		if (x > SpacePanel.width - r && dx > 0) dx = -dx;
		if (y > SpacePanel.height - r && dy > 0) dy = -dy;

		if (hit) {
			long elapsed = (System.nanoTime() - hitTimer) / 1000000;
			if (elapsed > 50) {
				hit = false;
				hitTimer = 0;
			}
		}
	}
	public void draw (Graphics2D g) {
		g.drawImage(image, (int) (x - r), (int) (y - r), (int) (r * 2), (int) (r * 1.7), null);
	}
}