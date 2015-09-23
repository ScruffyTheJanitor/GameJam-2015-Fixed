package com.indiecharter.utils;

public class HealthHandler {
	
	public int currentHealth;
	public int maxHealth;
	private boolean isDead;
	
	
	public HealthHandler(int MaxHealth, int Health){
		this.maxHealth = MaxHealth;
		this.currentHealth = Health;
		checkIsDead();
	}
	
	public void heal(int hearts){
		if(this.currentHealth + hearts > this.maxHealth){
			this.currentHealth = this.maxHealth;
		}else{
			this.currentHealth += hearts;
		}
	}
	
	public void damage(int hearts){
		this.currentHealth -= hearts;
		checkIsDead();
	}
	
	public void checkIsDead(){
		if(this.currentHealth < 0){
			isDead = true;
		}else{
			isDead = false;
		}
	}
	
	public boolean isDead(){
		return isDead;
	}
}