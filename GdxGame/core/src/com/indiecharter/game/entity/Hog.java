package com.indiecharter.game.entity;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.indiecharter.game.ID;
import com.indiecharter.game.entity.handlers.EntityHandler;
import com.indiecharter.utils.Constants;
import com.indiecharter.utils.HealthHandler;

public class Hog extends Entity {



	public Hog(EntityHandler handler) {
		this.attachedHandler = handler;
		this.id = ID.enemy;
		this.health = new HealthHandler(5,5);
		Texture spriteTexture = new Texture("char_sprites/hog/1.png");
		spriteTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		sprite = new Sprite(spriteTexture);
		sprite.setPosition(0, 0);
		sprite.setOrigin(0, 0);
		sprite.setSize(sprite.getWidth() / 100, sprite.getHeight() / 100);
		
	}

	public void create(World world) {

		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		bDef.position.set(100 / Constants.PPM, 300 / Constants.PPM);
		bDef.fixedRotation = true;
		bDef.bullet = true;
		bDef.gravityScale = 1.2f;
		body = world.createBody(bDef);
		body.setLinearDamping(3.6f);
		PolygonShape rect = new PolygonShape();
		rect.setAsBox(16 / Constants.PPM, 12.5f / Constants.PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 1f;
		fDef.friction = 1f;
		fDef.filter.categoryBits = Constants.BIT_ENEMIES;
		fDef.filter.maskBits = Constants.BIT_GROUND;
		body.createFixture(fDef).setUserData("NOTHING");;

		rect.dispose();
		
	}

	public void update() {
		this.checkHitPlayer();
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	public void chargeLeft() {
		body.applyForceToCenter(-260f, 0, true);
		sprite.setFlip(false, false);
		facingLeft = true;
	}

	public void chargeRight() {
		body.applyForceToCenter(260f, 0, true);
		sprite.setFlip(true, false);
		facingLeft = false;
	}

	long lastHitTime;
	boolean facingLeft = true;
	boolean lastFacingLeft = facingLeft;
	
	public void checkHitPlayer() {
		for (Entity e : this.attachedHandler.getEntityList()) {
			if (e.id == ID.player) {
				autoMove(e);
				Player player = (Player) e;
				if (new Rectangle(player.body.getPosition().x, player.body.getPosition().y, player.player.getWidth(),
						player.player.getHeight())
								.overlaps(new Rectangle(this.body.getPosition().x, this.body.getPosition().y,
										this.sprite.getWidth(), this.sprite.getHeight()))) {
					if (lastHitTime + 500 < System.currentTimeMillis()) {
						
						if(this.body.getPosition().x > e.body.getPosition().x){
							player.pushLeft();
						}
						if(this.body.getPosition().x < e.body.getPosition().x){
							player.pushRight();
						}
						lastHitTime = System.currentTimeMillis();
						e.health.damage(1);
							
					}

					System.out.println("Attacked");

				}
			}
		}
		checkHealth();
	}
	public void checkHealth(){
		if(this.health.isDead()){
			this.body.getWorld().destroyBody(this.body);
			this.attachedHandler.removeEntity(this);
		}
	}

	long lastCharge;
	
	public void autoMove(Entity target){
		boolean nextFace = this.facingLeft;
		
		if(this.body.getPosition().x > target.body.getPosition().x){
			nextFace = true;
		}
		if(this.body.getPosition().x < target.body.getPosition().x){
			nextFace = false;
		}
		
		
		if(this.lastFacingLeft != nextFace){
			this.lastFacingLeft = nextFace;
			this.lastCharge += 400;
		}
		
		Random random = new Random();
		if(lastCharge + 400 < System.currentTimeMillis()){
			lastCharge = System.currentTimeMillis()  + random.nextInt(200);
			if(nextFace){
				this.chargeLeft();
			}
			if(!nextFace){
				this.chargeRight();
			}
		}
	}
	
	@Override
	public void dispose() {

	}
}