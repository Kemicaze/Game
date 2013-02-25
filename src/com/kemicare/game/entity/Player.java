package com.kemicare.game.entity;

import java.util.List;

import com.kemicare.game.InputHandler;
import com.kemicare.game.gfx.Color;
import com.kemicare.game.gfx.Screen;
import com.kemicare.game.level.Level;

public class Player extends Mob {
	private InputHandler input;
	private boolean wasAttacking;
	private int attackTime, attackDir;

	public Player(InputHandler input) {
		this.input = input;
		x = 16;
		y = 16;
	}

	public void tick() {
		super.tick();
		int xa = 0;
		int ya = 0;
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;
		move(xa, ya);

		if (input.attack) {
			if (!wasAttacking)
				attack();
			wasAttacking = true;
		} else {
			wasAttacking = false;
		}
		if (attackTime > 0)
			attackTime--;
	}

	private void attack() {
		walkDist += 8;
		attackDir = dir;
		attackTime = 5;
		int yo = -2;
		if (dir == 0) {
			hurt(x - 8, y + 4+yo, x + 8, y + 12+yo);
		}
		if (dir == 1) {
			hurt(x - 8, y - 12+yo, x + 8, y - 4+yo);
		}
		if (dir == 3) {
			hurt(x + 4, y - 8+yo, x + 12, y + 8+yo);
		}
		if (dir == 2) {
			hurt(x - 12, y - 8+yo, x - 4, y + 8+yo);
		}

		int xt = x >> 4;
		int yt = (y+yo) >> 4;
		int r = 12;
		if (attackDir == 0)
			yt = (y + r+yo) >> 4;
		if (attackDir == 1)
			yt = (y - r+yo) >> 4;
		if (attackDir == 2)
			xt = (x - r) >> 4;
		if (attackDir == 3)
			xt = (x + r) >> 4;

		if (xt >= 0 && yt >= 0 && xt < level.w && yt < level.h) {
			level.getTile(xt, yt).hurt(level, xt, yt, this, random.nextInt(4) + 1, attackDir);
		}
	}

	private void hurt(int x0, int y0, int x1, int y1) {

		List<Entity> entities = level.getEntities(x0, y0, x1, y1);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e != this)
				e.hurt(this, random.nextInt(4) + 1, attackDir);
		}
	}

	public void render(Screen screen) {
		int xt = 0;
		int yt = 14;

		int flip1 = (walkDist >> 3) & 1;
		int flip2 = (walkDist >> 3) & 1;

		if (dir == 1) {
			xt += 2;
		}
		if (dir > 1) {
			flip1 = 0;
			flip2 = ((walkDist >> 5) & 1);
			if (dir == 2) {
				flip1 = 1;
			}
			xt += 4 + ((walkDist >> 4) & 1) * 2;
		}

		int xo = x - 8;
		int yo = y - 11;

		if (attackTime > 0 && attackDir == 1) {
			screen.render(xo + 0, yo - 4, 6 + 13 * 32, Color.get(-1, 555, 555, 555), 0);
			screen.render(xo + 8, yo - 4, 6 + 13 * 32, Color.get(-1, 555, 555, 555), 1);
		}

		screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, Color.get(-1, 100, 220, 532), flip1);
		screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, Color.get(-1, 100, 220, 532), flip1);
		screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, Color.get(-1, 100, 220, 532), flip2);
		screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, Color.get(-1, 100, 220, 532), flip2);

		if (attackTime > 0 && attackDir == 2) {
			screen.render(xo - 4, yo, 7 + 13 * 32, Color.get(-1, 555, 555, 555), 1);
			screen.render(xo - 4, yo + 8, 7 + 13 * 32, Color.get(-1, 555, 555, 555), 3);
		}

		if (attackTime > 0 && attackDir == 3) {
			screen.render(xo + 8 + 4, yo, 7 + 13 * 32, Color.get(-1, 555, 555, 555), 0);
			screen.render(xo + 8 + 4, yo + 8, 7 + 13 * 32, Color.get(-1, 555, 555, 555), 2);
		}

		if (attackTime > 0 && attackDir == 0) {
			screen.render(xo + 0, yo + 8 + 4, 6 + 13 * 32, Color.get(-1, 555, 555, 555), 2);
			screen.render(xo + 8, yo + 8 + 4, 6 + 13 * 32, Color.get(-1, 555, 555, 555), 3);
		}
	}
}