package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject{

	private Handler handler;
	private GameObject player;
	
	public Projectile(int x, int y, int velX, int velY, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		this.velX = velX;
		this.velY = velY;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			handler.removeObject(this);
		}
		if(x <= 0 || x >= Game.WIDTH - 16) {
			handler.removeObject(this);
		}
		handler.addObject(new Trail(x, y, 16, 16, 0.1f, ID.Trail, Color.blue, handler));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 8, 8);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	}

	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return 5;
	}

}
