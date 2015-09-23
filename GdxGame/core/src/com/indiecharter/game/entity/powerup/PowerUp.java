package com.indiecharter.game.entity.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.indiecharter.game.entity.Entity;

public abstract class PowerUp {
	public float x, y;
	public Sprite sprite;
	public PUID id;
	public Body body;
	
	public abstract void create(World world);
	
	public abstract void activate(Entity aE);
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void update(float delta);
}