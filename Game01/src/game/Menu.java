package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{

	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		Font fnt = new Font("arial", 1 , 50);
		Font fnt2 = new Font("arial", 1 , 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", Game.WIDTH/2 - 65, Game.HEIGHT/2 - 90);
		
		g.setFont(fnt2);
		
		g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 80, 200, 64);
		g.drawString("Play", Game.WIDTH/2 - 30, Game.HEIGHT/2 - 35);

		g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2, 200, 64);
		g.drawString("Options", Game.WIDTH/2 - 55, Game.HEIGHT/2 + 45);

		g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 + 80, 200, 64);
		g.drawString("Quit", Game.WIDTH/2 - 30, Game.HEIGHT/2 + 120);
	}
}
