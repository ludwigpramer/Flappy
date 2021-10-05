
package com.game.main;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
@SuppressWarnings("unused")
public class KeyInput implements KeyListener {
	/*
	 * @author ludwigpramer
	 */
	private Handler handler; 
	private Player player;
	public KeyInput(Handler handler, Player p) {
		this.handler = handler;
		this.player = p;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_SPACE:
			player.setJumping(true);
			player.setVely(19*2);
		
			break;
		case KeyEvent.VK_P:
			Game.pause();
			break;
		case KeyEvent.VK_N:
			player.setJumping(true);
			player.setVely(19*2);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
