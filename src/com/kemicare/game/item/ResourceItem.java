package com.kemicare.game.item;

public class ResourceItem extends Item{
	public Resource resource;

	public ResourceItem(Resource resource) {
		this.resource = resource;
	}

	public int getColor() {
		return resource.color;
	}

	public int getSprite() {
		return resource.sprite;
	}

}