package com.kemicare.game.item;

import com.kemicare.game.gfx.Color;

public class Resource {
	public static Resource wood = new Resource("Wood", 1+4*32, Color.get(-1, 200, 531, 430));

	public final String name;
	public final int sprite;
	public final int color;
	
	public Resource(String name, int sprite, int color) {
		this.name = name;
		this.sprite = sprite;
		this.color = color;
		
		
	}

}