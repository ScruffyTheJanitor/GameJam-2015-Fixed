package com.indiecharter.game.entity;

import com.indiecharter.utils.Constants;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Platform {
	
	
	
	public void create(World world, int x, int y , int width, int height) {

		PolygonShape rect = new PolygonShape();

		BodyDef bDef = new BodyDef();
		bDef.position.set(x / Constants.PPM, y / Constants.PPM);
		bDef.type = BodyType.StaticBody;
		Body body = world.createBody(bDef);

		rect.setAsBox(width / Constants.PPM, height / Constants.PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.filter.categoryBits = Constants.BIT_GROUND;
		fDef.filter.maskBits = (short) (Constants.BIT_ENEMIES | Constants.BIT_PLAYER);
		body.createFixture(fDef).setUserData("ground");
		
		rect.dispose();
	}
}