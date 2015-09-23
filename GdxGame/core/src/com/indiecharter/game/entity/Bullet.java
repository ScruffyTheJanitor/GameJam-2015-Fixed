package com.indiecharter.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.indiecharter.game.ID;
import com.indiecharter.game.entity.handlers.EntityHandler;
import com.indiecharter.utils.Constants;

public class Bullet extends Entity{
	
	public Sprite sprite;
	
	private float x , y;
	
	private float angle;
	private float direction;
	
	public Bullet(float x, float y, int direction, EntityHandler handler){
		this.id = null;
		this.attachedHandler = handler;
		this.x = x;
		this.y = y;
		this.direction = direction;
		Texture bulletTexture = new Texture("weapons/weapon_fireball.png");
		this.sprite = new Sprite(bulletTexture);
		sprite.setPosition(0, 0);
		sprite.setOrigin(0, 0);
		sprite.setSize(sprite.getWidth() / 100, sprite.getHeight() / 100);
		if (direction == 1) {
			sprite.flip(true, false);
		}
	}
	
	@Override
	public void create(World world) {

		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		bDef.position.set(x / Constants.PPM, y / Constants.PPM);
		bDef.fixedRotation = true;
		bDef.bullet = true;
		bDef.gravityScale = 0f;
		body = world.createBody(bDef);
		body.setLinearDamping(0);
		PolygonShape rect = new PolygonShape();
		rect.setAsBox(30.5f / Constants.PPM, 11.5f / Constants.PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 0;	
		fDef.friction = 0;
		fDef.filter.categoryBits = Constants.BIT_ENEMIES;
		fDef.filter.maskBits = 0;
		body.createFixture(fDef).setUserData("NOTHING");;
		
		
		rect.dispose();
	}


	@Override
	public void update() {
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
		body.getPosition().y - sprite.getHeight() / 2);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		
		body.applyForceToCenter(50 * direction, 0, true);
		
		if(this.body.getPosition().x > 20 || this.body.getPosition().x < -20){
			this.attachedHandler.removeEntity(this);
			body.getWorld().destroyBody(body);
		}
		checkCollideEnemy();
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		
	}
	
	public void move(float angle){
		
	}
	
	public void checkCollideEnemy(){
		for(Entity e: this.attachedHandler.getEntityList()){
			if(e.id == ID.enemy){
				if(new Rectangle(e.body.getPosition().x, e.body.getPosition().y, e.sprite.getWidth(), e.sprite.getHeight()).overlaps(new Rectangle(this.body.getPosition().x, this.body.getPosition().y, this.sprite.getWidth(), this.sprite.getHeight()))){
					e.health.damage(1);
					this.body.getWorld().destroyBody(this.body);
					this.attachedHandler.removeEntity(this);
				}
			}
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}