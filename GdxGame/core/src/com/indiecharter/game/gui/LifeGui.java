package com.indiecharter.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.utils.HealthHandler;

public class LifeGui {
	private HealthHandler healthHandler;
	private float x, y;
	private int heartCanisters;
	private int hearts;
	private Texture fullHeartCanister;
	private Texture halfHeartCanister;
	private Texture fullHeart;
	private Texture halfHeart;
	
	private Sprite sfullHeartCanister;
	private Sprite shalfHeartCanister;
	private Sprite sfullHeart;
	private Sprite shalfHeart;
	
	public LifeGui(HealthHandler health){
		this.healthHandler = health;
		
		fullHeartCanister = new Texture("gui/gui_heart_container.png");
		halfHeartCanister = new Texture("gui/gui_heart_container_half.png");
		fullHeart = new Texture("gui/gui_heart_full.png");
		halfHeart = new Texture("gui/gui_heart_half.png");
		sfullHeartCanister = new Sprite(fullHeartCanister);
		shalfHeartCanister = new Sprite(this.halfHeartCanister);
		sfullHeart = new Sprite(this.fullHeart);
		shalfHeart = new Sprite(this.halfHeart);
		update();
	}
	
	public void update(){
		
	}
	
	public void render(SpriteBatch batch){
		for(int i = 0; i < healthHandler.maxHealth; i++){
			this.sfullHeartCanister.setSize(20, 20);
			this.sfullHeartCanister.setPosition(x + (i * (sfullHeartCanister.getWidth() + 2)), y);
			this.sfullHeartCanister.draw(batch);
		}
		for(int i = 0; i < healthHandler.currentHealth; i++){
			this.sfullHeart.setSize(20, 20);
			this.sfullHeart.setPosition(x + (i * (sfullHeart.getWidth() + 2)), y);
			this.sfullHeart.draw(batch);
		}
	}
	
}