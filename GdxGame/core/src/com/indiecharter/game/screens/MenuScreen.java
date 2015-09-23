package com.indiecharter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.game.CoreGame;
import com.indiecharter.game.gui.Button;

public class MenuScreen implements Screen{
	
	private SpriteBatch batch;
	private CoreGame game;
	private OrthographicCamera cam;
	private Button button;
	private Sprite sprite;
	
	
	public MenuScreen(CoreGame game) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch = new SpriteBatch();
		this.game = game;
		cam = new OrthographicCamera();
		sprite = new Sprite();
		sprite.setTexture(new Texture("gui/button_exit.png"));
		setupCamera();
		button = new Button(400 - sprite.getTexture().getWidth()/2, 300 - sprite.getTexture().getHeight()/2, sprite, cam);
	}
	
	private void setupCamera(){

		cam = new OrthographicCamera(800, 600);
		cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2, 0f);
		cam.update();
	}

	@Override
	public void show() {
		
		
	}

	@Override
	public void render(float delta) {
		button.update(delta);
		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.1f, 0, 0, 0);
		batch.begin();
		button.render(batch);
		batch.end();		
		if(button.wasClicked()){
			game.setScreen(new GameScreen(game));
			dispose();
		}
		

	}

	@Override
	public void resize(int width, int height) {
 
		
	}

	@Override
	public void pause() {
 
		
	}

	@Override
	public void resume() {
 
		
	}

	@Override
	public void hide() {
 
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}