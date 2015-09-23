package com.indiecharter.game.entity.handlers;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.game.entity.Entity;

public class EntityHandler {
	LinkedList<Entity> entities = new LinkedList<Entity>();
	LinkedList<Entity> deBufferedEntities = new LinkedList<Entity>();
	LinkedList<Entity> bufferedEntities = new LinkedList<Entity>();
	
	public void update(){
		for(Entity e: entities){
			e.update();
		}
		
		for(Entity e: bufferedEntities){
			entities.add(e);
		}
		
		for(Entity e: deBufferedEntities){
			entities.remove(e);
		}
		
		bufferedEntities.clear();
		deBufferedEntities.clear();
	}
	
	public void draw(SpriteBatch batch){
		for(Entity e: entities){
			e.draw(batch);
		}
	}
	
	public void addEntity(Entity e){
		bufferedEntities.add(e);
	}

	public void removeEntity(Entity e){
		deBufferedEntities.add(e);
	}
	
	public LinkedList<Entity> getEntityList(){
		return entities;
	}
}