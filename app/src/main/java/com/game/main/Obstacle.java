package com.game.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obstacle extends GameObject {
	/*
	 * @author ludwigpramer
	 */
	private static int speed = 1;
	private static final int X = 600; 
	private Image img;
	public Obstacle( int y, Handler handler) {
		super(X, y, ID.Obstacle, handler);
		this.width = 100;
		this.height = 400;
		try {
		this.img = ImageIO.read(new File("src/main/java/assets/imgs/obstacle.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.rect = new Rectangle(x, y, width, height);
		//System.out.println(this.rect.toString());
		//System.out.printf("Initialized new Obstacle at %d\n", y);
	}

	
	@Override
	public void update() {
		changeRect(x, y, width, height);
		this.x -= speed;
		if(this.x <= -1) {
			this.kill();

			
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	@Override
	public boolean collideWith(GameObject o) {
		return this.rect.intersects(o.rect);
		
	}


	@Override
	public void changeRect(int x, int y, int width, int height) {
		this.rect.x = x;
		this.rect.y = y;
		this.rect.width = width;
		this.rect.height = height;
		
	}
	public static void setSpeed(int speed) {
		Obstacle.speed = speed;
	}



}
