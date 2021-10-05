package com.game.main;

import java.awt.Graphics;

public interface Updatable {
	/*
	 * @author ludwigpramer
	 */
	public void update();
	public void render(Graphics g);
	public boolean collideWith(GameObject o);
	
}
