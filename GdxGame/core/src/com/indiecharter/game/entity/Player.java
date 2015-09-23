package com.indiecharter.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
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
import com.indiecharter.game.entity.handlers.PowerUpHandler;
import com.indiecharter.game.entity.powerup.PUID;
import com.indiecharter.game.entity.powerup.PowerUp;
import com.indiecharter.utils.Constants;
import com.indiecharter.utils.HealthHandler;

public class Player extends Entity {
	public World world;
	public Sprite player;
	public PowerUpHandler puHandler;

	public static int score = 0;

	public Player(PowerUpHandler puHand, EntityHandler handler) {
		this.attachedHandler = handler;
		this.id = ID.player;
		health = new HealthHandler(10, 10);
		this.puHandler = puHand;
		Texture playerTexture = new Texture("char_sprites/main/main_idle.png");
		playerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		player = new Sprite(playerTexture);
		player.setPosition(0, 0);
		player.setOrigin(0, 0);
		player.setSize(player.getWidth() / 100, player.getHeight() / 100);

	}

	public void create(World world) {

		this.world = world;

		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		bDef.position.set(100 / Constants.PPM, 300 / Constants.PPM);
		bDef.fixedRotation = true;
		bDef.bullet = true;
		bDef.gravityScale = 1.2f;
		body = world.createBody(bDef);
		body.setLinearDamping(3.6f);
		PolygonShape rect = new PolygonShape();
		rect.setAsBox(10 / Constants.PPM, 17.5f / Constants.PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 1f;
		fDef.friction = 1f;
		fDef.filter.categoryBits = Constants.BIT_PLAYER;
		fDef.filter.maskBits = Constants.BIT_GROUND;
		body.createFixture(fDef).setUserData("player");

		rect.setAsBox(7 / Constants.PPM, 4 / Constants.PPM, new Vector2(0, -16.5f / Constants.PPM), 0);
		fDef.shape = rect;
		fDef.density = 0f;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData("foot");

		rect.setAsBox(4 / Constants.PPM, 14 / Constants.PPM, new Vector2(-8 / Constants.PPM, 0), 0);
		fDef.shape = rect;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData("lefthand");

		rect.setAsBox(4 / Constants.PPM, 14 / Constants.PPM, new Vector2(8 / Constants.PPM, 0), 0);
		fDef.shape = rect;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData("righthand");

		rect.dispose();
	}
	
	long sT = System.currentTimeMillis();
	public void update() {
		player.setPosition(body.getPosition().x - player.getWidth() / 2, body.getPosition().y - player.getHeight() / 2);
		this.checkPowerUps();
		
		if(Gdx.input.isKeyJustPressed(Keys.S) && sT + 40< System.currentTimeMillis()){
			
			int direction;
			if(player.isFlipX()){
				direction = -1;
			}else{
				direction = 1;
			}
			Bullet bullet = new Bullet(this.body.getPosition().x * 100, this.body.getPosition().y * 100 , direction, attachedHandler);
			bullet.create(this.body.getWorld());
			this.attachedHandler.addEntity(bullet);
			sT = System.currentTimeMillis();
		}
	}

	public void draw(SpriteBatch batch) {
		player.draw(batch);
	}

	/**
	 * 
	 * @param Direction
	 *            -1 is left and 1 is right;
	 */
	public void jumpSide(int Direction) {
		body.applyForceToCenter(66f * Direction, 160f, true);
	}

	public void jump() {
		body.applyForceToCenter(0, 150, true);
	}

	public void jumpSmall() {
		body.applyForceToCenter(0, 160, true);
	}

	public void pushLeft() {
		body.applyForceToCenter(-100, 100, true);
	}

	public void pushRight() {
		body.applyForceToCenter(100, 100, true);

	}
	
	
	public void left() {
		body.applyForceToCenter(-6f, 0, true);
		player.setFlip(true, false);
	}

	public void right() {
		body.applyForceToCenter(6f, 0, true);
		player.setFlip(false, false);
	}

	public void shoot() {

	}

	public void checkPowerUps() {
		for (PowerUp p : this.puHandler.getPowerUpList()) {
			if (p.id == PUID.Hearts) {
				if (new Rectangle(p.body.getPosition().x, p.body.getPosition().y, p.sprite.getWidth(),
						p.sprite.getHeight())
								.overlaps(new Rectangle(this.body.getPosition().x, this.body.getPosition().y,
										this.player.getWidth(), this.player.getHeight()))) {
					p.activate(this);
					this.world.destroyBody(p.body);
					this.puHandler.removePowerUp(p);

				}
			}

			if (p.id == PUID.COIN) {
				if (new Rectangle(p.body.getPosition().x, p.body.getPosition().y, p.sprite.getWidth(),
						p.sprite.getHeight())
								.overlaps(new Rectangle(this.body.getPosition().x, this.body.getPosition().y,
										this.player.getWidth(), this.player.getHeight()))) {
					p.activate(this);
					this.world.destroyBody(p.body);
					this.puHandler.removePowerUp(p);
					System.out.println(Constants.Score);
				}

			}
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}