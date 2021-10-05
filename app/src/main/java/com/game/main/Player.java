package com.game.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class Player extends GameObject {
	/*
	 * @author ludwigpramer
	 */
	boolean jumping = false;
	boolean killable = false;
	private long START_TIME = 0;
	private int Y;
	private Image upImg;
	private Image downImg;
	
	public Player(int x, int y, ID id, int Y, long startTime, Handler handler) {
		super(x, y, id, handler);
		
		
//		System.out.println(this.rect.toString());
		this.Y = Y;
		
		try {
			this.upImg = ImageIO.read(new File("src/main/java/assets/imgs/bird.png"));
		} catch (IOException e) { e.printStackTrace(); }
		try {
			this.downImg = ImageIO.read(new File("src/main/java/assets/imgs/bird_down.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		this.START_TIME = startTime;
		this.width = upImg.getWidth(null);
		this.height = upImg.getHeight(null);;
		this.rect = new Rectangle(x, y, width, height);
	}

	

	@Override
	public void update() {
		changeRect(x, y, width, height);
		try {
			if(!this.killable && START_TIME + TimeUnit.NANOSECONDS.convert(6L, TimeUnit.SECONDS) <= System.nanoTime() ) {
				this.killable = true;
				System.out.println("The Player is now killable!");
			} 
		} catch (Exception e) { e.printStackTrace(); }
			
			
		
		if(!this.dead) {
			//jumping
			if(jumping) {
				this.y -= (int) this.vely/6;
				this.vely -= 1;
			}
			if(this.y >= Y && this.jumping) {
				this.vely = 0;
				this.jumping = false;
			}
			
			/*  Killing the Player  */
			if(!this.dead && this.killable) {
				if(this.y <= 0 || this.y >= Y-height+25) {
					this.kill();
					System.out.println("DEAD");
					System.out.printf("X: %d Y: %d", this.getX(), this.getY());
				}	
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		if(!this.dead) {
			if(this.vely < 0) {
				g.drawImage(downImg, x, y, null);
			}else {
				g.drawImage(upImg, x, y, null);
			}
		//temporary Game-Stop
		}
		else {
			Game.pause();
		}
		
	}
	@Override
	public boolean collideWith(GameObject o) {
		return this.rect.intersects(o.rect);
	}


	public boolean isJumping() {
		return jumping;
	}



	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}



	@Override
	public void changeRect(int x, int y, int width, int height) {
		this.rect.x = x;
		this.rect.y = y;
		this.rect.width = width;
		this.rect.height = height;
	}
}
