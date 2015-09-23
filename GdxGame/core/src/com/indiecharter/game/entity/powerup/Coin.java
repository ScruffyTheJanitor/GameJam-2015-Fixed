package com.indiecharter.game.entity.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.indiecharter.game.entity.Entity;
import com.indiecharter.utils.Constants;

public class Coin extends PowerUp{
	
	public Coin(float x, float y){
		this.id = PUID.COIN;
		this.x = x;
		this.y = y;
		Texture heartTexture = new Texture("items/item_coin.png");
		this.sprite = new Sprite(heartTexture);
		sprite.setPosition(0, 0);
		sprite.setOrigin(0, 0);
		sprite.setSize(sprite.getWidth() / 100, sprite.getHeight() / 100);
	}
	
	@Override
	public void create(World world) {

		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		bDef.position.set(x / Constants.PPM, y / Constants.PPM);
		bDef.fixedRotation = true;
		bDef.bullet = true;
		bDef.gravityScale = 1.2f;
		body = world.createBody(bDef);
		body.setLinearDamping(3.6f);
		PolygonShape rect = new PolygonShape();
		rect.setAsBox(4 / Constants.PPM, 4 / Constants.PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 1f;	
		fDef.friction = 1f;
		fDef.filter.categoryBits = Constants.BIT_ENEMIES;
		fDef.filter.maskBits = Constants.BIT_GROUND;
		body.createFixture(fDef).setUserData("NOTHING");;

		rect.dispose();
	}

	@Override
	public void update(float delta) {
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
				body.getPosition().y - sprite.getHeight() / 2);
	}
	@Override
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}


	@Override
	public void activate(Entity aE) {
		Constants.Score++;
	}

}