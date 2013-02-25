package com.kemicare.game.entity;

import java.util.List;

import com.kemicare.game.entity.particle.TextParticle;
import com.kemicare.game.gfx.Color;
import com.kemicare.game.gfx.Screen;
import com.kemicare.game.item.Item;
import com.kemicare.game.level.Level;

public class ItemEntity extends Entity {
	protected int walkDist = 0;
	protected int dir = 0;
	public int hurtTime = 0;
	protected int xKnockback, yKnockback;
	public double xa, ya, za;
	public double xx, yy, zz;
	private Item item;

	public ItemEntity(Item item, int x, int y) {
		this.item = item;
		xx = this.x = x;
		yy = this.y = y;
		xr = 4;
		yr = 3;

		zz = 2;
		xa = random.nextGaussian() * 0.3;
		ya = random.nextGaussian() * 0.2;
		za = random.nextFloat() * 0.7 + 1;
	}

	public boolean move(int xa, int ya) {
		if (xKnockback < 0) {
			move2(-1, 0);
			xKnockback++;
		}
		if (xKnockback > 0) {
			move2(1, 0);
			xKnockback--;
		}
		if (yKnockback < 0) {
			move2(0, -1);
			yKnockback++;
		}
		if (yKnockback > 0) {
			move2(0, 1);
			yKnockback--;
		}
		if (hurtTime > 0)
			return true;
		if (xa != 0 || ya != 0) {
			walkDist++;
			if (ya > 0)
				dir = 0;
			if (ya < 0)
				dir = 1;
			if (xa < 0)
				dir = 2;
			if (xa > 0)
				dir = 3;
		}
		return super.move(xa, ya);
	}

	public void tick() {
		xx += xa;
		yy += ya;
		zz += za;
		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.6;
			ya *= 0.6;
		}
		za -= 0.15;
		int nx = (int) xx;
		int ny = (int) yy;

		move(nx - x, ny - y);

		if (hurtTime > 0)
			hurtTime--;
	}

	public void render(Screen screen) {
		int col = item.getColor();
		if (hurtTime > 0) {
			col = Color.get(-1, 555, 555, 555);
		}

		screen.render(x - 4, y - 4, item.getSprite(), Color.get(-1, 0, 0, 0), 0);
		screen.render(x - 4, y - 4 - (int) (zz), item.getSprite(), col, 0);
	}

	public void hurt(Mob mob, int damage, int attackDir) {
		if (attackDir == 0)
			yKnockback = +6;
		if (attackDir == 1)
			yKnockback = -6;
		if (attackDir == 2)
			xKnockback = -6;
		if (attackDir == 3)
			xKnockback = +6;
		hurtTime = 10;
	}
}
