package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	
	public static int HEALTHBAR = 500;
	public static int HEALTH = 100;
	public static int HEALTHMAX = HEALTH;
	
	private int greenValue1 = 255;
	
	
	public void tick() {
		HEALTHBAR = Game.clamp(HEALTHBAR, 0, Game.WIDTH - 64);
		
		greenValue1 = Game.clamp(greenValue1, 0, 255);
		
		greenValue1 = HEALTHBAR/2;
		
		if(HEALTH <= 0) {
			Game.gameState = Game.STATE.Dead;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH / 2 - 252, Game.HEIGHT - 80, 500 + 4, 36);
		
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH / 2 - 250, Game.HEIGHT - 78, 500, 32);
		
		g.setColor(new Color(75, greenValue1, 0));
		g.fillRect(Game.WIDTH / 2 - 250, Game.HEIGHT - 78, HEALTHBAR , 32);

		
	}
}
