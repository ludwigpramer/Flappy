package com.game.main;

import java.awt.Rectangle;

public abstract class GameObject implements Updatable {
	/*
	 * @author ludwigpramer
	 */
	protected int x;
	protected int y;
	protected int velx;
	protected int vely;
	protected int width;
	protected int height;
	protected ID id;
	protected Rectangle rect;
	protected boolean dead = false;
	protected Handler handler;
	public GameObject(int x, int y, ID id, Handler handler)  {
		this.x = x;
		this.y = y; 
		this.id = id;
		this.velx = 0;
		this.vely = 0;
		this.handler = handler;
	}
	public void kill() {
		
		dead = true;
		
		
	}
	public boolean isDead() {
		return dead;
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVelx() {
		return velx;
	}
	public void setVelx(int velx) {
		this.velx = velx;
	}
	public int getVely() {
		return vely;
	}
	public void setVely(int vely) {
		this.vely = vely;
	}
	public ID getId() {
		return id;
	}
	public void addXVel(int velx) {
		this.velx += velx;
	}
	public void addYVel(int vely) {
		this.vely += vely;
	}
	public void addVel(int velx, int vely) {
		this.velx += velx;
		this.vely += vely;
	}
	public abstract void changeRect(int x, int y, int width, int height);
		
	
	
}
