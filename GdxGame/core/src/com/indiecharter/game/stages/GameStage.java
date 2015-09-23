package com.indiecharter.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.indiecharter.game.entity.Bullet;
import com.indiecharter.game.entity.Hog;
import com.indiecharter.game.entity.Justletithappen;
import com.indiecharter.game.entity.MrHands;
import com.indiecharter.game.entity.Platform;
import com.indiecharter.game.entity.Player;
import com.indiecharter.game.entity.handlers.EntityHandler;
import com.indiecharter.game.entity.handlers.PowerUpHandler;
import com.indiecharter.game.entity.powerup.Hearts;
import com.indiecharter.utils.B2DContact;
import com.indiecharter.utils.Constants;

public class GameStage extends Stage {

	private final float PPM = Constants.PPM;

	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;
	public Player player;
	private Platform platform;
	private B2DContact cl;
	
	
	
	private Justletithappen jlih;

	private ParticleEffect effect;

	public OrthographicCamera camera;

	private MrHands handy;
	private Hog hoggy;
	
	private PowerUpHandler puHandler;
	private EntityHandler handler;
	
	private Bullet bullet;
	
	private World world;
	Box2DDebugRenderer renderer;

	public GameStage() {
		handler = new EntityHandler();
		puHandler = new PowerUpHandler();
		world = new World(new Vector2(0, -10), true);
		cl = new B2DContact();
		world.setContactListener(cl);
		renderer = new Box2DDebugRenderer();
		setupCamera();
		player = new Player(puHandler, handler);
		platform = new Platform();
		handy = new MrHands(handler, puHandler);
		hoggy = new Hog(handler);
		jlih = new Justletithappen();
		
		
		jlih.create(world);
		handy.create(world);
		hoggy.create(world);
		
		platform.create(world, 100, 200, 50, 5);
		platform.create(world, 100, 100, 50, 5);
		platform.create(world, 100, 100, 50, 5);

		platform.create(world, 300, 100, 5, 50);

		platform.create(world, 0, 7, 1000, 5);
		
		platform.create(world, -1000, 250, 5, 500);
		platform.create(world, 1000, 250, 5, 500);
		
		platform.create(world, 0, 400, 1000, 5);
		player.create(world);

		//effect = new ParticleEffect();
		//effect.load(Gdx.files.internal("particles/DirtyDust"), Gdx.files.internal(""));
		//effect.scaleEffect(0.003f);
		//effect.start();
		handler.addEntity(player);
		handler.addEntity(handy);
		handler.addEntity(jlih);
		handler.addEntity(hoggy);
		Hearts heart = new Hearts(100,100);
		heart.create(world);
		puHandler.addPowerUp(heart);
		
	}
	private void setupCamera() {
		camera = new OrthographicCamera(800 / PPM / 2, 600 / PPM / 2);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
		camera.update();
	}

	public void act(float delta) {
		super.act(delta);
		handleInput();
		accumulator += delta;
		
		while (accumulator >= delta) {
			world.step(TIME_STEP, 6, 2);
			accumulator -= TIME_STEP;
			camera.position.x = player.body.getPosition().x;
			camera.update();
			handler.update();
			puHandler.update(delta);
		}
		
		//effect.update(delta);
		
		//effect.setPosition(this.player.body.getPosition().x,
			//	this.player.body.getPosition().y - (16 / Constants.PPM));
	}

	public void draw() {
		super.draw();
		renderer.render(world, camera.combined);
	}

	public void draw(SpriteBatch batch) {
		handler.draw(batch);
		puHandler.draw(batch);
		//effect.draw(batch);
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.D)) {
			//if (effect.isComplete()) {
			//	effect.reset();
			//}
		}
	}
	
	public void handleInput(){
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && cl.isPlayerOnGround()) {
			player.jump();
		} else if (Gdx.input.isKeyJustPressed(Keys.SPACE) && cl.isPlayerOnLeft()) {
			player.jumpSide(1);
		} else if (Gdx.input.isKeyJustPressed(Keys.SPACE) && cl.isPlayerOnRight()) {
			player.jumpSide(-1);
		} else if (Gdx.input.isKeyJustPressed(Keys.SPACE) && cl.canPlayerDoubleJump()) {
			player.jumpSmall();
			cl.setPlayerDoubleJump(false);
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			jlih.left();
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			jlih.right();
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			jlih.jump();
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			player.left();
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			player.right();
		}
	}

}