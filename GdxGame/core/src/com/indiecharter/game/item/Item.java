package com.indiecharter.game.item;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Item extends ApplicationAdapter {
	protected Texture itemTexture;
	protected double value = 0.0;
	
	protected SpriteBatch batch;

	public Item() {
		batch = new SpriteBatch();
	}

	public abstract void render();

	public abstract void resize (int width, int height);

	public void dispose () {
		
	}
}