package com.indiecharter.game.item;

import com.badlogic.gdx.graphics.Texture;

public class Potion extends Item {

	public Potion() {
		super();
		value = 85.00;
		itemTexture = new Texture("Items/item_potion.png");
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(itemTexture, 100, 100);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

}