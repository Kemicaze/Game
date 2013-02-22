package com.kemicare.game.entity;

import java.util.List;

import com.kemicare.game.entity.particle.TextParticle;
import com.kemicare.game.gfx.Color;
import com.kemicare.game.level.Level;

public class Mob extends Entity {
	protected int walkDist = 0;
	protected int dir = 0;
	public int hurtTime = 0;
	protected int xKnockback, yKnockback;
	public int health = 10;

	public Mob() {
		x = y = 8;
		xr = 4;
		yr = 3;
	}

	public void tick() {
		if (health <= 0) {
			remove();
		}
		if (hurtTime > 0)
			hurtTime--;
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
			boolean stopped = true;
			if (xa != 0 && move2(xa, 0))
				stopped = false;
			if (ya != 0 && move2(0, ya))
				stopped = false;
			return !stopped;
		}
		return true;
	}

	private boolean move2(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			throw new IllegalArgumentException("Move2 can only move along one axis at a time!");
		}
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + (c % 2 * 2 - 1) * xr) >> 4;
			int yt = ((y + ya) + (c / 2 * 2 - 1) * yr) >> 4;
			if (!level.getTile(xt, yt).mayPass(level, xt, yt, this)) {
				return false;
			}
		}

		List<Entity> wasInside = level.getEntities(x - xr, y - yr, x + xr, y + yr);
		List<Entity> isInside = level.getEntities(x + xa - xr, y + ya - yr, x + xa + xr, y + ya + yr);
		isInside.removeAll(wasInside);
		for (int i = 0; i < isInside.size(); i++) {
			Entity e = isInside.get(i);
			if (e == this)
				continue;
			if (e.blocks(this)) {
				return false;
			}
		}

		x += xa;
		y += ya;
		return true;
	}

	public boolean blocks(Mob mob) {
		return true;
	}

	public void hurt(Mob mob, int damage, int attackDir) {
		level.add(new TextParticle(""+damage, x, y, Color.get(-1, 500, 500, 500)));
		health -= damage;
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

	public void findStartPos(Level level) {
		while (true){
			int x = random.nextInt(level.w);
			int y = random.nextInt(level.h);
			if (level.getTile(x, y).mayPass(level, x, y, this)){
				this.x = x*16+8;
				this.y = y*16+8;
				return;
			}
		}
	}
}
