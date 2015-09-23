package com.indiecharter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.game.CoreGame;
import com.indiecharter.game.gui.LifeGui;
import com.indiecharter.game.stages.GameStage;

public class GameScreen implements Screen{
	private CoreGame game;
	private OrthographicCamera cameraD;
	private SpriteBatch batch;
	private GameStage stage;
	private ParticleEffect effect;
	
	private Sprite player;
	private LifeGui lifeGui;
	
	private Texture background;
	
	
	public GameScreen(CoreGame game) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.game = game;
		batch = new SpriteBatch();
		stage = new GameStage();
		background = new Texture("env/env_wall.png");
		Texture playerTe = new Texture("char_sprites/main/main_idle.png");
		playerTe.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		player = new Sprite(playerTe);
		player.setPosition(0, 0);
		player.setOrigin(0, 0);
		player.setSize(player.getWidth() / 100, player.getHeight() / 100);
		lifeGui = new LifeGui(stage.player.health);
		setupCamera();
	}

	private void setupCamera(){

		cameraD = new OrthographicCamera(800, 600);
		cameraD.position.set(cameraD.viewportWidth/2, cameraD.viewportHeight/2, 0f);
		cameraD.update();
	}
	
	@Override
	public void show() {
		/*effect = new ParticleEffect();
		effect.load(Gdx.files.internal("particles/DirtyDust"), Gdx.files.internal(""));
		effect.scaleEffect(0.5f);
		effect.start();*/
	}

	@Override
	public void render(float delta) {
		
		if(!Gdx.input.isKeyPressed(Keys.ESCAPE)){
			update();
		}
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 51f / 255f, 0);
		
		batch.setProjectionMatrix(cameraD.combined);
		batch.begin();
		batch.draw(background, -(stage.player.body.getPosition().x * 10) - background.getWidth()/8, 0);
		batch.draw(background, -(stage.player.body.getPosition().x * 10) - background.getWidth()/8 - 932, 0);
		batch.draw(background, -(stage.player.body.getPosition().x * 10) - background.getWidth()/8 + 932, 0);
		

		batch.draw(background, -(stage.player.body.getPosition().x * 10) - background.getWidth()/8 - 932*2, 0);
		batch.draw(background, -(stage.player.body.getPosition().x * 10) - background.getWidth()/8 + 932*2, 0);
		lifeGui.render(batch);
		batch.end();
		
		stage.camera.normalizeUp();
		
		stage.draw();
		batch.setProjectionMatrix(stage.camera.combined);
		batch.begin();
		stage.draw(batch);
		//player.draw(batch);
		batch.end();
		stage.camera.update();
			
		//Gdx.app.log("FPS", " " + 1/Gdx.graphics.getDeltaTime());
		
		if(stage.player.health.currentHealth <= 0){
			game.setScreen(new MenuScreen(game));
			dispose();
		}
		
		//lb.render(delta);
	}
	
	public void update(){
		lifeGui.update();
		stage.act();
		//effect.setPosition(stage.player.body.getPosition().x , stage.player.body.getPosition().y);
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
		background.dispose();
	}

}