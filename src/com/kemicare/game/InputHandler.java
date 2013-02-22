package com.kemicare.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean attack;

	public void releaseAll() {
		up = down = left = right = false;
	}

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	private void toggle(KeyEvent ke, boolean pressed) {
		if (ke.getKeyCode() == KeyEvent.VK_UP)
			up = pressed;
		if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			down = pressed;
		if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			left = pressed;
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
			right = pressed;
		if (ke.getKeyCode() == KeyEvent.VK_C)
			attack = pressed;
	}

	public void keyTyped(KeyEvent ke) {
	}
}
