package com.indiecharter.game;

import com.badlogic.gdx.Game;
import com.indiecharter.game.screens.LoadingScreen;

public class CoreGame extends Game {
	@Override
	public void create () {
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
