package com.indiecharter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiecharter.game.CoreGame;
import com.indiecharter.utils.GameInputProcesser;

public class LoadingScreen implements Screen {

	private CoreGame game;
	private SpriteBatch batch;
	private long startTime;
	
	public LoadingScreen(CoreGame game) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.game = game;
		batch = new SpriteBatch();
		startTime = System.currentTimeMillis();
		Gdx.input.setInputProcessor(new GameInputProcesser());
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		batch.begin();
		
		
		
		batch.end();
	}
	
	public void update(){
		if(startTime < System.currentTimeMillis() + 4000){
			game.setScreen(new MenuScreen(game));
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