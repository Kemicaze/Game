package com.kemicare.game.level.tile;

import java.util.Random;

import com.kemicare.game.entity.Entity;
import com.kemicare.game.entity.Mob;
import com.kemicare.game.gfx.Screen;
import com.kemicare.game.level.Level;

public class Tile {
	protected Random random = new Random();
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grass = new GrassTile(0);
	public static Tile rock = new RockTile(1);
	public static Tile water = new WaterTile(2);
	public static Tile flower = new FlowerTile(3);
	public static Tile tree = new TreeTile(4);
	
	public final byte id;
	
	public Tile(int id) {
		this.id = (byte) id;
		tiles[id] = this;
	}

	public void render(Screen screen, Level level, int x, int y) {
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e){
		return true;
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
	}
} 