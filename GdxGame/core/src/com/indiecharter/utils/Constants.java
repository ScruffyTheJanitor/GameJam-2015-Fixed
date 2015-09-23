package com.indiecharter.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	
	// This class defines some of the constants that will be used throughout the various classes in the game
	
	// gravity
	public static final float gravity = 0.75f;
	// pixels-per-meter
	public static final float PPM = 100f;
	public static final short BIT_GROUND = 2;
	public static final short BIT_PLAYER = 4;
	public static final short BIT_ENEMIES = 8;
	
	// the number of high scores to draw on the scoreboard
	public static final int numScoresToShow = 10;
	
	// Used with kb/m input
	public static Vector2 LastClick = null;
	
	// player's score
	public static int Score;
	
}