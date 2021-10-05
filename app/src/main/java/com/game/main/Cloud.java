package com.game.main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cloud implements Updatable{
	/*
	 * @author ludwigpramer
	 */
	private Image img;
	private int x;
	private int y;
	public Cloud(int y){
		this.x = 600;
		this.y = y;
		try {
			this.img = ImageIO.read(new File("src/main/java/assets/imgs/cloud.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update() {
		this.x -= 2;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	@Override public boolean collideWith(GameObject o) {return false;}
	public static void genCloud() {
		
	}
}
