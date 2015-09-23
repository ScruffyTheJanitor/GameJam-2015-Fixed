package com.indiecharter.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class B2DContact implements ContactListener{

	private boolean playerOnGround = false;
	private boolean playerOnLeft = false;
	private boolean playerOnRight = false;
	private boolean canPlayerDoubleJump = false;
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() == null|| fb.getUserData() == null){
			return;
		}
		
		if(fa.getUserData() != null && fb.getUserData().equals("foot")){
			this.playerOnGround = true;
		}
		if(fb.getUserData() != null && fa.getUserData().equals("foot")){
			this.playerOnGround = true;
		}
		
		if(fa.getUserData() != null && fb.getUserData().equals("lefthand")){
			this.playerOnLeft = true;
		}
		if(fb.getUserData() != null && fa.getUserData().equals("lefthand")){
			this.playerOnLeft = true;
		}
		
		if(fa.getUserData() != null && fb.getUserData().equals("righthand")){
			this.playerOnRight = true;
		}
		if(fb.getUserData() != null && fa.getUserData().equals("righthand")){
			this.playerOnRight = true;
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fb.getUserData().equals("foot")){
			this.playerOnGround = false;
			canPlayerDoubleJump = true;
		}
		if(fb.getUserData() != null && fa.getUserData().equals("foot")){
			this.playerOnGround = false;
			canPlayerDoubleJump = true;
		}
		if(fa.getUserData() != null && fb.getUserData().equals("lefthand")){
			this.playerOnLeft = false;
			canPlayerDoubleJump = true;
		}
		if(fb.getUserData() != null && fa.getUserData().equals("lefthand")){
			this.playerOnLeft = false;
			canPlayerDoubleJump = true;
		}
		if(fa.getUserData() != null && fb.getUserData().equals("righthand")){
			this.playerOnRight = false;
			canPlayerDoubleJump = true;
		}
		if(fb.getUserData() != null && fa.getUserData().equals("righthand")){
			this.playerOnRight = false;
			canPlayerDoubleJump = true;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isPlayerOnGround(){
		return playerOnGround;
	}
	
	public boolean isPlayerOnLeft(){
		return playerOnLeft;
	}
	
	public boolean isPlayerOnRight(){
		return playerOnRight;
	}
	
	public boolean canPlayerDoubleJump(){
		return canPlayerDoubleJump;
	}
	
	public void setPlayerDoubleJump(boolean bool){
		canPlayerDoubleJump = bool;
	}
}