package com.kemicare.game.entity;

import com.kemicare.game.gfx.Color;
import com.kemicare.game.gfx.Screen;

public class TestMob extends Mob {
	private int xa, ya;
	private int shirtColor;

	public TestMob() {
		shirtColor = random.nextInt(6) * 100 + random.nextInt(6) * 10 + random.nextInt(6);
		x = random.nextInt(64 * 16);
		y = random.nextInt(64 * 16);
	}

	public void tick() {
		super.tick();
		if (!move(xa, ya) || random.nextInt(40) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
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

		int col = Color.get(-1, 100, shirtColor, 532);
		if (hurtTime > 0) {
			col = Color.get(-1, 555, 555, 555);
		}
		screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, col, flip1);
		screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, col, flip1);
		screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, col, flip2);
		screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, col, flip2);
	}
}