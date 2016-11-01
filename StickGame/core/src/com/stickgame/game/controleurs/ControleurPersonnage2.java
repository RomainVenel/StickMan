/**
 * 
 */
package com.stickgame.game.controleurs;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.stickgame.game.entites.Niveau;
import com.stickgame.game.entites.Personnage;
import com.stickgame.game.entites.Personnage.Etat;

/**
 * @author Romain Venel & Maxime Genevier
 *
 */
public class ControleurPersonnage2 {
	
	public enum Keys {
		LEFT, RIGHT, JUMP, ATTACK
	}
	
	private static final long LONG_JUMP_PRESS = 150l;
	private static final float ACCELERATION = 3000f;
	private static final float GRAVITY = -80f;
	private static final float MAX_JUMP_SPEED = 120f;
	private static final float DAMP = 0.9f;
	private static final float MAX_VEL = 40f;
	
	private Niveau niveau;
	private Personnage personnage2;
	private long jumpPressedTime;
	private boolean jumpingPressed;
	private boolean grounded = false;
	
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		protected Rectangle newObject(){
			return new Rectangle();
		}
	};
	
	private static Map<Keys, Boolean> keys = new HashMap<ControleurPersonnage2.Keys, Boolean>();
	
	static{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.ATTACK, false);
	}
	
	public ControleurPersonnage2(Niveau niveau){
		this.niveau = niveau;
		this.personnage2 = niveau.getPersonnage2();
	}
	
	public void leftPressed(){
		keys.get(keys.put(Keys.LEFT, true));
	}
	
	public void rightPressed(){
		keys.get(keys.put(Keys.RIGHT, true));
	}
	
	public void attackPressed(){
		keys.get(keys.put(Keys.ATTACK, true));
	}
	
	public void jumpPressed(){
		keys.get(keys.put(Keys.JUMP, true));
	}
	
	public void leftReleased(){
		keys.get(keys.put(Keys.LEFT, false));
	}
	
	public void rightReleased(){
		keys.get(keys.put(Keys.RIGHT, false));
	}
	
	public void attackReleased(){
		keys.get(keys.put(Keys.ATTACK, false));
	}
	
	public void jumpReleased(){
		keys.get(keys.put(Keys.JUMP, false));
	}
	
	public void update(float delta){
		
		processInput();
		
		if(grounded && personnage2.getEtat().equals(Etat.SAUTER)){
			personnage2.setEtat(Etat.INACTIF);
		}
		
		this.personnage2.getAcceleration().y = ControleurPersonnage2.GRAVITY;
		this.personnage2.getAcceleration().scl(delta);
		
		this.personnage2.getVelocite().add(this.personnage2.getAcceleration().x, this.personnage2.getAcceleration().y);
		
		checkCollisionWithBlocks(delta);
		
		this.personnage2.getVelocite().x *= DAMP;
		
		if(this.personnage2.getVelocite().x > MAX_VEL){
			this.personnage2.getVelocite().x = MAX_VEL;
		}
		
		if(this.personnage2.getVelocite().x < -MAX_VEL){
			this.personnage2.getVelocite().x = -MAX_VEL;
		}
		
		this.personnage2.update(delta);
		
	}
	
	private boolean processInput(){
		
		/********  Touche sauter ********/
		
		if(keys.get(Keys.JUMP)){
			
			if(!this.personnage2.getEtat().equals(Etat.SAUTER)){
				
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				this.personnage2.setEtat(Etat.SAUTER);
				this.personnage2.getVelocite().y = MAX_JUMP_SPEED;
				grounded = false;
				
			} else{
				
				if(jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= ControleurPersonnage2.LONG_JUMP_PRESS)){
					
					jumpingPressed = false;
					
				} else{
					
					if(jumpingPressed){
						
						this.personnage2.getVelocite().y = ControleurPersonnage2.MAX_JUMP_SPEED;
						
					}
				}
				
			}
		}
		
		/********  Touche gauche ********/
		
		if(keys.get(Keys.LEFT)){
			
			this.personnage2.setFaceGauche(true);
			
			if(!this.personnage2.getEtat().equals(Etat.SAUTER)){
				
				this.personnage2.setEtat(Personnage.Etat.MARCHER);
				
			}
			this.personnage2.getAcceleration().x = -ControleurPersonnage2.ACCELERATION;
		
		/********  Touche droite ********/
			
		} else if(keys.get(Keys.RIGHT)){
			
			this.personnage2.setFaceGauche(false);
			
			if(!this.personnage2.getEtat().equals(Etat.SAUTER)){
				
				this.personnage2.setEtat(Personnage.Etat.MARCHER);
				
			}
			
			this.personnage2.getAcceleration().x = ControleurPersonnage2.ACCELERATION;
			
		} else if(keys.get(Keys.ATTACK)){
			
			this.personnage2.setEtat(Personnage.Etat.ATTAQUER);
		
		/********  Sinon le personnage est inactif ********/
		} else{
			
			if(!this.personnage2.getEtat().equals(Etat.SAUTER)){
				
				this.personnage2.setEtat(Personnage.Etat.INACTIF);
				
			}
			
			this.personnage2.getAcceleration().x  = 0;
			
		}
		
		return false;
		
	}
	
	private void checkCollisionWithBlocks(float delta){
		
		this.personnage2.getVelocite().scl(delta);
		
		Rectangle persoRect = rectPool.obtain();
		
		persoRect.set(this.personnage2.getLimites().x, this.personnage2.getLimites().y, this.personnage2.getLimites().width, this.personnage2.getLimites().height);
		
		MapLayer collisionObjectLayer = this.niveau.getTiledMap().getLayers().get("collision");
		MapObjects objects = collisionObjectLayer.getObjects();
		
		for(RectangleMapObject rectangleMapObject : objects.getByType(RectangleMapObject.class)){
			if(rectangleMapObject.getProperties().containsKey("block")){
				if(persoRect.overlaps(rectangleMapObject.getRectangle())){
					this.personnage2.getVelocite().x = 0;
					this.niveau.getRectCollision().add(rectangleMapObject.getRectangle());
					break;
					
				}
			}
		}
		
		persoRect.x = this.personnage2.getPosition().x;
		
		persoRect.y += this.personnage2.getVelocite().y;
		
		for(RectangleMapObject rectangleMapObject : objects.getByType(RectangleMapObject.class)){
			
			if(rectangleMapObject.getProperties().containsKey("block")){
				if(persoRect.overlaps(rectangleMapObject.getRectangle())){
				
					if(this.personnage2.getVelocite().y < 0){
						
						grounded = true;
						
					}
					
					this.personnage2.getVelocite().y = 0;
					this.niveau.getRectCollision().add(rectangleMapObject.getRectangle());
					break;
				}
				
			}
			
		}
		
		persoRect.y = this.personnage2.getPosition().y;
		this.personnage2.getPosition().add(this.personnage2.getVelocite());
		this.personnage2.getLimites().x = this.personnage2.getPosition().x;
		this.personnage2.getLimites().y = this.personnage2.getPosition().y;
		this.personnage2.getVelocite().scl(1 / delta);
		
	}

}
