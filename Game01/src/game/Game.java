package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 1024, HEIGHT = WIDTH /16 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Dead dead;
	private Oops oops;
	
	public static int gameLevel = 1;
	
	
	public enum STATE{
		Menu,
		Game,
		Dead,
		Oops
	};
	
	public static STATE gameState = STATE.Game;
	
	public Game()   {
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Game1", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		menu = new Menu();
		dead = new Dead();
		oops = new Oops();
		
		r = new Random();
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player1(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(256, 256, ID.BasicEnemy, handler));
			handler.addObject(new RangedEnemy(124, 124, ID.RangedEnemy, handler));
		}
		
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		}
		else if(gameState == STATE.Menu) {
			menu.tick();
		}
		else if(gameState == STATE.Dead) {
			dead.tick();
		}
		else {
			oops.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		}
		else if( gameState == STATE.Menu){
			menu.render(g);
		}
		else if( gameState == STATE.Dead) {
			dead.render(g);
		}
		else {
			oops.render(g);
		}


		
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			return var = max;
		}
		else if(var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	
	public void setLevel(int lvl) {
		gameLevel = lvl;
	}
	
	public static void main(String args[])  {
		new Game();
	}
}
