package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class RangedEnemy extends GameObject{

	private Handler handler;
	private Random rand;
	private int timer = 50;
	private int timer2 = 25;
	
	public RangedEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		rand = new Random();
		velX = 2;
		velY = 2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		if(timer == 0) {
			velX = 0;
			velY = 0;
			if(timer2 == 17) {
				handler.addObject(new Projectile(x, y, 10, 0, ID.Projectile, handler));
				handler.addObject(new Projectile(x, y, 7, 7, ID.Projectile, handler));
				handler.addObject(new Projectile(x, y, 0, 10, ID.Projectile, handler));
				handler.addObject(new Projectile(x, y, -7, 7, ID.Projectile, handler));
				handler.addObject(new Projectile(x, y, -10, 0, ID.Projectile, handler));
				handler.addObject(new Projectile(x, y, 7, -7, ID.Projectile, handler));
				handler.addObject(new Projectile(x, y, -7, -7, ID.Projectile, handler));
				handler.addObject(new Projectile(x, y, 0, -10, ID.Projectile, handler));
			}
			if(timer2 == 0) {
				velX = Game.clamp(rand.nextInt(), -2, 2);
				velY = Game.clamp(rand.nextInt(), -2, 2);
				timer = 100;
				timer2 = 25;
			}
			timer2--;
		}
		if(timer > 0) {
			timer--;
		}
		handler.addObject(new Trail(x, y, 16, 16, 0.1f, ID.Trail, Color.blue, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	}

	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return 3;
	}

}
