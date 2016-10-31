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
public class ControleurPersonnage {
	
	public enum Keys {
		LEFT, RIGHT, JUMP, ATTACK
	}
	
	private static final long LONG_JUMP_PRESS = 150l;
	private static final float ACCELERATION = 150f;
	private static final float GRAVITY = -50f;
	private static final float MAX_JUMP_SPEED = 70f;
	private static final float DAMP = 0.9f;
	private static final float MAX_VEL = 40f;
	
	private Niveau niveau;
	private Personnage personnage1;
	private long jumpPressedTime;
	private boolean jumpingPressed;
	private boolean grounded = false;
	
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		protected Rectangle newObject(){
			return new Rectangle();
		}
	};
	
	private static Map<Keys, Boolean> keys = new HashMap<ControleurPersonnage.Keys, Boolean>();
	
	static{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.ATTACK, false);
	}
	
	public ControleurPersonnage(Niveau niveau){
		this.niveau = niveau;
		this.personnage1 = niveau.getPersonnage1();
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
		
		if(grounded && personnage1.getEtat().equals(Etat.SAUTER)){
			personnage1.setEtat(Etat.INACTIF);
		}
		
		this.personnage1.getAcceleration().y = ControleurPersonnage.GRAVITY;
		this.personnage1.getAcceleration().scl(delta);
		
		this.personnage1.getVelocite().add(this.personnage1.getAcceleration().x, this.personnage1.getAcceleration().y);
		
		checkCollisionWithBlocks(delta);
		
		this.personnage1.getVelocite().x *= DAMP;
		
		if(this.personnage1.getVelocite().x > MAX_VEL){
			this.personnage1.getVelocite().x = MAX_VEL;
		}
		
		if(this.personnage1.getVelocite().x < -MAX_VEL){
			this.personnage1.getVelocite().x = -MAX_VEL;
		}
		
		this.personnage1.update(delta);
		
	}
	
	private boolean processInput(){
		
		/********  Touche sauter ********/
		
		if(keys.get(Keys.JUMP)){
			
			if(!this.personnage1.getEtat().equals(Etat.SAUTER)){
				
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				this.personnage1.setEtat(Etat.SAUTER);
				this.personnage1.getVelocite().y = MAX_JUMP_SPEED;
				grounded = false;
				
			} else{
				
				if(jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= ControleurPersonnage.LONG_JUMP_PRESS)){
					
					jumpingPressed = false;
					
				} else{
					
					if(jumpingPressed){
						
						this.personnage1.getVelocite().y = ControleurPersonnage.MAX_JUMP_SPEED;
						
					}
				}
				
			}
		}
		
		/********  Touche gauche ********/
		
		if(keys.get(Keys.LEFT)){
			
			this.personnage1.setFaceGauche(true);
			
			if(!this.personnage1.getEtat().equals(Etat.SAUTER)){
				
				this.personnage1.setEtat(Personnage.Etat.MARCHER);
				
			}
			this.personnage1.getAcceleration().x = -ControleurPersonnage.ACCELERATION;
		
		/********  Touche droite ********/
			
		} else if(keys.get(Keys.RIGHT)){
			
			this.personnage1.setFaceGauche(false);
			
			if(!this.personnage1.getEtat().equals(Etat.SAUTER)){
				
				this.personnage1.setEtat(Personnage.Etat.MARCHER);
				
			}
			
			this.personnage1.getAcceleration().x = ControleurPersonnage.ACCELERATION;
			
			/********  Sinon le personnage attaque ********/
		} else if(keys.get(Keys.ATTACK)){
			
			this.personnage1.setEtat(Personnage.Etat.ATTAQUER);
		
		/********  Sinon le personnage est inactif ********/
		} else{
			
			if(!this.personnage1.getEtat().equals(Etat.SAUTER)){
				
				this.personnage1.setEtat(Personnage.Etat.INACTIF);
				
			}
			
			this.personnage1.getAcceleration().x  = 0;
			
		}
		
		return false;
		
	}
	
	private void checkCollisionWithBlocks(float delta){
		
		this.personnage1.getVelocite().scl(delta);
		
		Rectangle persoRect = rectPool.obtain();
		persoRect.set(this.personnage1.getLimites().x, this.personnage1.getLimites().y, this.personnage1.getLimites().width, this.personnage1.getLimites().height);
		
		MapLayer collisionObjectLayer = this.niveau.getTiledMap().getLayers().get("collision");
		MapObjects objects = collisionObjectLayer.getObjects();
		
		this.niveau.getRectCollision().clear();
		
		for(RectangleMapObject rectangleMapObject : objects.getByType(RectangleMapObject.class)){
			if(rectangleMapObject.getProperties().containsKey("block")){
				if(persoRect.overlaps(rectangleMapObject.getRectangle())){
					this.personnage1.getVelocite().x = 0;
					this.niveau.getRectCollision().add(rectangleMapObject.getRectangle());
					break;
					
				}
			}
		}
		
		persoRect.x = this.personnage1.getPosition().x;
		
		persoRect.y += this.personnage1.getVelocite().y;
		
		for(RectangleMapObject rectangleMapObject : objects.getByType(RectangleMapObject.class)){
			
			if(rectangleMapObject.getProperties().containsKey("block")){
				if(persoRect.overlaps(rectangleMapObject.getRectangle())){
				
					if(this.personnage1.getVelocite().y < 0){
						
						grounded = true;
						
					}
					
					this.personnage1.getVelocite().y = 0;
					this.niveau.getRectCollision().add(rectangleMapObject.getRectangle());
					break;
				}
				
			}
			
		}
		
		persoRect.y = this.personnage1.getPosition().y;
		this.personnage1.getPosition().add(this.personnage1.getVelocite());
		this.personnage1.getLimites().x = this.personnage1.getPosition().x;
		this.personnage1.getLimites().y = this.personnage1.getPosition().y;
		this.personnage1.getVelocite().scl(1 / delta);
		
	}	

}
