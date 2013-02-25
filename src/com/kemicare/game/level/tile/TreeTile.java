package com.kemicare.game.level.tile;

import com.kemicare.game.entity.Entity;
import com.kemicare.game.entity.ItemEntity;
import com.kemicare.game.entity.Mob;
import com.kemicare.game.entity.particle.TextParticle;
import com.kemicare.game.gfx.Color;
import com.kemicare.game.gfx.Screen;
import com.kemicare.game.item.Resource;
import com.kemicare.game.item.ResourceItem;
import com.kemicare.game.level.Level;

public class TreeTile extends Tile {
	public TreeTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int x, int y) {
		int col = Color.get(10, 30, 151, level.grassColor);
		int barkCol1 = Color.get(10, 30, 430, level.grassColor);
		int barkCol2 = Color.get(10, 30, 320, level.grassColor);

		boolean u = level.getTile(x, y - 1) == this;
		boolean l = level.getTile(x - 1, y) == this;
		boolean d = level.getTile(x, y + 1) == this;
		boolean r = level.getTile(x + 1, y) == this;
		boolean ul = level.getTile(x - 1, y - 1) == this;
		boolean ur = level.getTile(x + 1, y - 1) == this;
		boolean dl = level.getTile(x - 1, y + 1) == this;
		boolean dr = level.getTile(x + 1, y + 1) == this;
		if (u && ul && l) {
			screen.render(x * 16 + 0, y * 16 + 0, 10 + 1 * 32, col, 0);
		} else {
			screen.render(x * 16 + 0, y * 16 + 0, 9 + 0 * 32, col, 0);
		}
		if (u && ur && r) {
			screen.render(x * 16 + 8, y * 16 + 0, 10 + 2 * 32, barkCol2, 0);
		} else {
			screen.render(x * 16 + 8, y * 16 + 0, 10 + 0 * 32, col, 0);
		}
		if (d && dl && l) {
			screen.render(x * 16 + 0, y * 16 + 8, 10 + 2 * 32, barkCol2, 0);
		} else {
			screen.render(x * 16 + 0, y * 16 + 8, 9 + 1 * 32, barkCol1, 0);
		}
		if (d && dr && r) {
			screen.render(x * 16 + 8, y * 16 + 8, 10 + 1 * 32, col, 0);
		} else {
			screen.render(x * 16 + 8, y * 16 + 8, 10 + 3 * 32, barkCol2, 0);
		}
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		int damage = level.getData(x, y) + dmg;
		level.add(new TextParticle("" + dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500)));
		if (damage > 0) {
			for (int i = 0; i < 4; i++) {
				level.add(new ItemEntity(new ResourceItem(Resource.wood), x * 16 + random.nextInt(10) + 3, y * 16 + random.nextInt(10) + 3));
			}
			level.setTile(x, y, Tile.grass, 0);
		} else {
			level.setData(x, y, damage);
		}
	}
}
