package com.indiecharter.game.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.utils.Constants;

public class Button {
	private Sprite sprite;
	private float x, y;
	private boolean wasClicked;
	
	public Button(float x, float y, Sprite sprite, OrthographicCamera cam){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.sprite.setPosition(x, y);
	}
	
	public void update(float delta){
		if( Constants.LastClick != null && Constants.LastClick.x > this.x && Constants.LastClick.x < this.x + sprite.getTexture().getWidth() && Constants.LastClick.y > this.y && Constants.LastClick.y < this.y + sprite.getTexture().getHeight()){
			System.out.println("CLICKED");
			this.wasClicked = true;
			Constants.LastClick = null;
		}
	}
	
	public void render(SpriteBatch batch){
		batch.draw(sprite.getTexture(), x, y);
	}
	
	public boolean wasClicked(){
		return wasClicked;
	}
	
	public void dipose(){
		
	}
}