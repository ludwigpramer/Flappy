package com.game.main;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;

public class GenHandler implements Iterable<Cloud>{
	private ArrayList<Cloud> list = new ArrayList<>();
	public GenHandler() { 
		
	}
	public void add(Cloud cloud) {
		list.add(cloud);
	}
	public void add(Cloud ...clouds) {
		for(Cloud c : clouds) {
			list.add(c);
		}
	}
	public Cloud get(int index) {
		return list.get(index);
	}
	public void remove(int index) {
		list.remove(index);
	}
	public void update() {
		for(Cloud c : this) {
			c.update();
		}
	}
	public void render(Graphics g) {
		for(Cloud c : this) {
			c.render(g);
		}
	}
	@Override
	public Iterator<Cloud> iterator() {
		return list.iterator();
	}
}
