package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.HUD;

public class Player1 extends GameObject{

	Random r = new Random();
	Handler handler;
	
	private int timer = 0;
	
	public Player1(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 64);
		
		collision();
		
		timer--;
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if((tempObject.getId() == ID.BasicEnemy) || (tempObject.getId() == ID.RangedEnemy) || (tempObject.getId() == ID.Projectile)) {
				if(getBounds().intersects(tempObject.getBounds())) {
					if(timer < 0) {
						HUD.HEALTH -= 1 * tempObject.getDmg();
						HUD.HEALTHBAR -= 500 / (HUD.HEALTHMAX / (1 * tempObject.getDmg()));
						timer = 100;
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	
	
}
