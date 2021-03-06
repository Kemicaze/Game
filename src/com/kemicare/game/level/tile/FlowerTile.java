package com.kemicare.game.level.tile;

import com.kemicare.game.entity.Mob;
import com.kemicare.game.gfx.Color;
import com.kemicare.game.gfx.Screen;
import com.kemicare.game.level.Level;

public class FlowerTile extends GrassTile {
	public FlowerTile(int id) {
		super(id);
		tiles[id] = this;
	}

	public void render(Screen screen, Level level, int x, int y) {
		super.render(screen, level, x, y);

		int data = level.getData(x, y);
		int color = data % 16;
		int shape = data / 16;

		int flowerCol = 0;
		if (color == 0)
			flowerCol = Color.get(10, level.grassColor, 555, 440);
		if (color == 1)
			flowerCol = Color.get(10, level.grassColor, 533, 400);
		if (color == 2)
			flowerCol = Color.get(10, level.grassColor, 115, 445);
		if (color == 3)
			flowerCol = Color.get(10, level.grassColor, 111, 000);

		if (shape == 0)
			screen.render(x * 16 + 0, y * 16 + 0, 1 + 1 * 32, flowerCol, 0);
		if (shape == 1)
			screen.render(x * 16 + 8, y * 16 + 0, 1 + 1 * 32, flowerCol, 0);
		if (shape == 2)
			screen.render(x * 16 + 0, y * 16 + 8, 1 + 1 * 32, flowerCol, 0);
		if (shape == 3)
			screen.render(x * 16 + 8, y * 16 + 8, 1 + 1 * 32, flowerCol, 0);
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		level.setTile(x, y, Tile.grass, 0);
	}
}