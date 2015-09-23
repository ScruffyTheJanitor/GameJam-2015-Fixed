package com.indiecharter.game.entity.handlers;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.game.entity.powerup.PowerUp;

public class PowerUpHandler {
	LinkedList<PowerUp> PowerUp = new LinkedList<PowerUp>();
	LinkedList<PowerUp> deBufferedPowerUp = new LinkedList<PowerUp>();
	LinkedList<PowerUp> bufferedPowerUp = new LinkedList<PowerUp>();
	
	public void update(float delta){
		for(PowerUp e: PowerUp){
			e.update(delta);
		}
		
		for(PowerUp e: bufferedPowerUp){
			PowerUp.add(e);
		}
		
		for(PowerUp e: deBufferedPowerUp){
			PowerUp.remove(e);
		}
		
		bufferedPowerUp.clear();
		deBufferedPowerUp.clear();
	}
	
	public void draw(SpriteBatch batch){
		for(PowerUp e: PowerUp){
			e.render(batch);
		}
	}
	
	public void addPowerUp(PowerUp e){
		bufferedPowerUp.add(e);
	}

	public void removePowerUp(PowerUp e){
		deBufferedPowerUp.add(e);
	}
	
	public LinkedList<PowerUp> getPowerUpList(){
		return PowerUp;
	}
}