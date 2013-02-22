package com.kemicare.game.level.tile;

import com.kemicare.game.gfx.Color;
import com.kemicare.game.gfx.Screen;
import com.kemicare.game.level.Level;

public class GrassTile extends Tile {
	public GrassTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int x, int y) {
		int col = Color.get(level.grassColor, level.grassColor, level.grassColor + 111, level.grassColor + 111);
		
		screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
	}
}
