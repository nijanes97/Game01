package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Oops {

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		Font fnt = new Font("arial", 1 , 50);
		Font fnt2 = new Font("arial", 1 , 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("UwU", Game.WIDTH/2 - 75, Game.HEIGHT/2 - 90);
		
		g.drawString("looks like the game made a fucky wucky", Game.WIDTH/2 - 475, Game.HEIGHT/2 - 40);
		
		g.setColor(Color.gray);
		g.setFont(fnt2);
		g.drawString("but seriously you shouldn't be here", Game.WIDTH/2 - 275, Game.HEIGHT/2 + 20);
	}
}
