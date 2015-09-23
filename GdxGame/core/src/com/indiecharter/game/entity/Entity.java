package com.indiecharter.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.indiecharter.game.ID;
import com.indiecharter.game.entity.handlers.EntityHandler;
import com.indiecharter.utils.HealthHandler;

public abstract class Entity {
	
	public Body body;
	public HealthHandler health;
	public Sprite sprite;
	
	public ID id;
	
	public EntityHandler attachedHandler;
	
	public abstract void create(World world);
	public abstract void update();
	public abstract void draw(SpriteBatch batch);
	public abstract void dispose();
}