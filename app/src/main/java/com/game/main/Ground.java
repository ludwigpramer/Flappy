package com.game.main;

//import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

public class Ground {
	private int x;
	private int y;
//	private int width;
//	private int height;
//	private Image img;
	public Ground(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void tempRender(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 600, 20);
	}
}
