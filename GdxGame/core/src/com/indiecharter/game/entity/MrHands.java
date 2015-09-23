package com.indiecharter.game.entity;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
import com.indiecharter.game.entity.handlers.PowerUpHandler;
import com.indiecharter.game.entity.powerup.Coin;
import com.indiecharter.utils.Constants;
import com.indiecharter.utils.HealthHandler;

public class MrHands extends Entity{


	public PowerUpHandler puh;
	
	public MrHands(EntityHandler handler, PowerUpHandler puh) {
		this.puh = puh;
		this.attachedHandler = handler;
		this.id = ID.enemy;
		this.health = new HealthHandler(1,1);
		Texture spriteTexture = new Texture("char_sprites/mrhands/1.png");
		spriteTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		sprite = new Sprite(spriteTexture);
		sprite.setPosition(0, 0);
		sprite.setOrigin(0, 0);
		sprite.setSize(sprite.getWidth() / 100, sprite.getHeight() / 100);
	}

	public void create(World world) {

		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		bDef.position.set(1000 / Constants.PPM, 300 / Constants.PPM);
		bDef.fixedRotation = true;
		bDef.bullet = true;
		bDef.gravityScale = 0f;
		body = world.createBody(bDef);
		body.setLinearDamping(0.5f);
		PolygonShape rect = new PolygonShape();
		rect.setAsBox(10.5f / Constants.PPM, 11.5f / Constants.PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 0f;
		fDef.friction = 0f;
		fDef.filter.categoryBits = Constants.BIT_ENEMIES;
		fDef.filter.maskBits = 0;
		body.createFixture(fDef).setUserData("NOTHING") ;
		
		rect.dispose();
	}

	public void update() {
		this.checkHitPlayer();
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	float speed = 60;
	public void up(){
		body.applyForceToCenter(0, speed, true);
	}
	
	public void down(){
		body.applyForceToCenter(0, -speed, true);
	}
	
	public void left() {
		body.applyForceToCenter(-speed, 0, true);
		sprite.setFlip(false, false);
	}

	public void right() {
		body.applyForceToCenter(speed, 0, true);
		sprite.setFlip(true, false);

	}
	
	long lastHitTime;
	public void checkHitPlayer() {
		for (Entity e : this.attachedHandler.getEntityList()) {
			if (e.id == ID.player) {
				autoMove(e);
				Player player = (Player) e;
				if (new Rectangle(player.body.getPosition().x, player.body.getPosition().y, player.player.getWidth(),
						player.player.getHeight())
								.overlaps(new Rectangle(this.body.getPosition().x, this.body.getPosition().y,
										this.sprite.getWidth(), this.sprite.getHeight()))) {
					if (lastHitTime + 100 < System.currentTimeMillis()) {
						
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
		}checkHealth();
	}
	
	public void checkHealth(){
		if(this.health.isDead()){
			this.body.getWorld().destroyBody(this.body);
			this.attachedHandler.removeEntity(this);
		}
	}

	long lastCharge;
	
	public void autoMove(Entity target){
		Random random = new Random();
		
		if(lastCharge + 1 < System.currentTimeMillis()){
			lastCharge = System.currentTimeMillis()  + random.nextInt(200);
			if(this.body.getPosition().y > 20 / 100){
			Coin heart = new Coin(this.body.getPosition().x * 100, this.body.getPosition().y * 100);
			heart.create(body.getWorld());
			puh.addPowerUp(heart);
			}
			
			if(this.body.getPosition().x > target.body.getPosition().x){
				this.left();
			}
			if(this.body.getPosition().x < target.body.getPosition().x){
				this.right();
			}
			
			if(this.body.getPosition().y > target.body.getPosition().y){
				this.down();
			}
			
			if(this.body.getPosition().y < target.body.getPosition().y){
				this.up();
			}
		}
	}
	
	@Override
	public void dispose() {		

	}

}