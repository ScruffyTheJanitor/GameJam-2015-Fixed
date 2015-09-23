package com.indiecharter.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.indiecharter.utils.Constants;
import com.indiecharter.utils.HealthHandler;

public class Justletithappen extends Entity{
	public Body JustletithappenBody;
	public HealthHandler health;
	
	public Sprite Justletithappen;
	
	public Justletithappen(){
		Texture JustletithappenTexture = new Texture("char_sprites/creepy/1.png");
		JustletithappenTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		Justletithappen = new Sprite(JustletithappenTexture);
		Justletithappen.setPosition(0, 0);
		Justletithappen.setOrigin(0, 0);
		Justletithappen.setSize(Justletithappen.getWidth() / 100, Justletithappen.getHeight() / 100);
		
	}
	
	public void create(World world) {
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		bDef.position.set(100 / Constants.PPM, 300 / Constants.PPM);
		bDef.fixedRotation = true;
		bDef.bullet = true;
		bDef.gravityScale = 1.2f;
		JustletithappenBody = world.createBody(bDef);
		JustletithappenBody.setLinearDamping(3.6f);
		PolygonShape rect = new PolygonShape();
		rect.setAsBox(15 / Constants.PPM, 15 / Constants.PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 1f;
		fDef.friction = 1f;
		fDef.filter.categoryBits = Constants.BIT_ENEMIES;
		fDef.filter.maskBits = Constants.BIT_GROUND;
		JustletithappenBody.createFixture(fDef).setUserData("Justletithappen");
		
		rect.setAsBox(7 / Constants.PPM, 4 / Constants.PPM, new Vector2( 0, -16.5f / Constants.PPM), 0);
		fDef.shape = rect;
		fDef.density = 0f;
		fDef.isSensor = true;
		JustletithappenBody.createFixture(fDef).setUserData("foot");
		
		rect.setAsBox(4 / Constants.PPM, 4 / Constants.PPM, new Vector2( -8 / Constants.PPM, 0), 0);
		fDef.shape = rect;
		fDef.isSensor = true;
		JustletithappenBody.createFixture(fDef).setUserData("lefthand");
		
		rect.setAsBox(4 / Constants.PPM, 4 / Constants.PPM, new Vector2( 8 / Constants.PPM, 0 ), 0);
		fDef.shape = rect;
		fDef.isSensor = true;
		JustletithappenBody.createFixture(fDef).setUserData("righthand");
		
		rect.dispose();
	}
	
	public void update(){
		Justletithappen.setPosition(JustletithappenBody.getPosition().x - Justletithappen.getWidth()/2, JustletithappenBody.getPosition().y - Justletithappen.getHeight()/2);
	}
	
	public void draw(SpriteBatch batch){
		Justletithappen.draw(batch);
	}
	
	/**
	 * 
	 * @param Direction -1 is left and 1 is right;
	 */
	public void jumpSide(int Direction){
		JustletithappenBody.applyForceToCenter(66f * Direction, 160f, true);
	}
	
	public void jump(){
		JustletithappenBody.applyForceToCenter(0, 150, true);
	}
	public void jumpSmall(){
		JustletithappenBody.applyForceToCenter(0, 160, true);
	}
	
	public void left(){
		JustletithappenBody.applyForceToCenter(-6f, 0, true);
		Justletithappen.setFlip(false, false);
	}
	
	public void right(){
		JustletithappenBody.applyForceToCenter(6f, 0, true);
		Justletithappen.setFlip(true, false);
		
	}
	
	
	public void shoot(){
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}