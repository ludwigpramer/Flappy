package com.game.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;

public class Handler implements Iterable<GameObject> {
	/*
	 * @author ludwigpramer
	 */
	ArrayList<GameObject> list = new ArrayList<>();
	public void render(Graphics g) {
		for(GameObject o : this) {
			if(o.id != ID.Player && !o.dead) o.render(g);
	}
	}
	public void update() {
		for(GameObject o : this) {
			o.update();
		}
	}
	@Override
	public Iterator<GameObject> iterator() {
		return list.iterator();
	}
	public void add(GameObject o) {
		list.add(o);
	}
	public void add(GameObject ...gameObjects ) {
		for(GameObject o : gameObjects) {
			list.add(o);
		}
	}
	public void remove(int index) {
		list.remove(index);
	}
	public void remove(GameObject o) {
		list.remove(o);
	}
	public GameObject get(int index) {
		return list.get(index);
	}
}
