package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{
	
	/**
	 *@author ludwigpramer
	 */
	private static final long serialVersionUID = 5420209024354289119L;
	private static final int X = 600;
	private static final int Y = 600;
	private static int obCount = 0;
	private static int clCount = 0;
	private static int score;
	Handler handler;
	GenHandler genHandler;
	Thread t1;
	Window window;
	Player p;
	static boolean running;
	static boolean paused;
	private static long START_TIME = 0;
	private ArrayList<Obstacle> obstacles = new ArrayList<>();
	public Game() {
		START_TIME = System.nanoTime();
		handler = new Handler();
		genHandler = new GenHandler();
		p = new Player( 150, Y-(25+40), ID.Player, Y, START_TIME, handler);
		handler.add(p);
		window = new Window(X, Y, "Hello", this, handler, p);
	}
	@Override public void run() {
		long now;
	    long updateTime;
	    long wait;

	    final int TARGET_FPS = 120;
	    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	    @SuppressWarnings("unused")
		long frameCounter = 0L;
	    while (running) {
	    	if(paused) break;
	        now = System.nanoTime();
	        
	        frameCounter++;
	        update();
	        render();
	        addObstacle();
	        addCloud();
	        //System.out.println("frame rendered");
	        updateTime = System.nanoTime() - now;
	        wait = (OPTIMAL_TIME - updateTime) / 1000000;
	        if(wait >= 0) {
	        try {
	            Thread.sleep(wait);
	        } catch (Exception e) {
	            e.printStackTrace();
	        	}
	        }
	    }
	    if(!paused) {
	    	try {
	    		stop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}
	public synchronized void start() {
		t1 = new Thread(this);
		System.out.println("Starting up ...");
		running = true;
		t1.start();
	}
	public synchronized void stop() throws InterruptedException {
		running = false;
		System.out.println("Shutting down ...");
		t1.join();
		System.exit(0);
		
		
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(27, 126, 225));
		g.fillRect(0, 0, X, Y);
		genHandler.render(g);
		new Ground(0, 600-33).tempRender(g);
		handler.render(g);
		p.render(g);
		drawString(30, 50, String.format("Score: %d", score), g);
		g.dispose();
		bs.show();
	}
	public void update() {
		
		handler.update();
		genHandler.update();
		for(Obstacle o : obstacles ) {
			if(p.collideWith(o) && !p.dead) {
				p.kill();
				System.out.println("DEAD");
			}
		}
		updateScore();
		switch(score) {
		case 20: {
			Obstacle.setSpeed(2);
			break;
		}
		case 50:{
			Obstacle.setSpeed(3);
			break;
			}	
		}
	}
	public void addObstacle() {
		obCount++;
		if(((int)(Math.random()*1000)) < 10 && obCount >= 240 && !p.isDead()) {
			int gap =((int) (Math.random()*600));
			Obstacle tempObstacle = new Obstacle(Y-(400 - gap/2)+20, handler);
			Obstacle tempObstacle2 = new Obstacle(-(400 - gap/2), handler);
			handler.add(tempObstacle, tempObstacle2);
			obstacles.add(tempObstacle); obstacles.add(tempObstacle2);
			obCount = 0;
		}
	}
	public void addCloud() {
		clCount++;
		if(((int)( Math.random()*1000)) < 12 && clCount >240 && !p.isDead()) {
				int tempY = (int) (Math.random()* 210+60);
				Cloud tempCloud = new Cloud( tempY );
				genHandler.add(tempCloud);
				
			
			clCount = 0;
		}
	}
	public void updateScore() {
		for(Obstacle o : obstacles) {
			if(p.getX() == o.getX()) {
				score++;
				break;
			}
		}
	}
	public void drawString(int x, int y, String text, Graphics g) {
		if(g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", Font.BOLD, 40));
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
			g2.drawString(text, x, y);
			g2.dispose();
		}
	}
	public static void pause() {
		paused = !paused;
	}
}
