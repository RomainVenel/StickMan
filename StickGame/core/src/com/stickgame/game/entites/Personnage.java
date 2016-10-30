package com.stickgame.game.entites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Romain Venel & Maxime Genevier
 *
 */
public class Personnage {
	
	public enum Etat {
		INACTIF, MARCHER, SAUTER, ATTAQUER, MOURIR
	}
	
	private static final float VITESSE = 4f; //Unité de déplacement par seconde
	private static final float JUMP_VELOCITY = 1f; //Unité de déplacement en sautant
	private static final float TAILLE = 32f; //Taille du personnage
	
	private float stateTime = 0;
	
	private Vector2 position = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocite = new Vector2();
	private Rectangle limites = new Rectangle();
	private Etat etat = Etat.INACTIF;
	private boolean faceGauche = true;
	
	public Personnage(Vector2 position){
		
		this.position = position;
		this.limites.height = Personnage.TAILLE;
		this.limites.width = Personnage.TAILLE;
		this.limites.x = this.position.x;
		this.limites.y = this.position.y;
		
	}
	
	public void update(float delta){
		
		stateTime += delta;
		
	}

	/**
	 * @return the stateTime
	 */
	public float getStateTime() {
		return stateTime;
	}

	/**
	 * @param stateTime the stateTime to set
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	/**
	 * @return the position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	/**
	 * @return the acceleration
	 */
	public Vector2 getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * @return the velocite
	 */
	public Vector2 getVelocite() {
		return velocite;
	}

	/**
	 * @param velocite the velocite to set
	 */
	public void setVelocite(Vector2 velocite) {
		this.velocite = velocite;
	}

	/**
	 * @return the limites
	 */
	public Rectangle getLimites() {
		return limites;
	}

	/**
	 * @param limites the limites to set
	 */
	public void setLimites(Rectangle limites) {
		this.limites = limites;
	}

	/**
	 * @return the etat
	 */
	public Etat getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	/**
	 * @return the faceGauche
	 */
	public boolean isFaceGauche() {
		return faceGauche;
	}

	/**
	 * @param faceGauche the faceGauche to set
	 */
	public void setFaceGauche(boolean faceGauche) {
		this.faceGauche = faceGauche;
	}

	/**
	 * @return the vitesse
	 */
	public static float getVitesse() {
		return VITESSE;
	}

	/**
	 * @return the jumpVelocity
	 */
	public static float getJumpVelocity() {
		return JUMP_VELOCITY;
	}

	/**
	 * @return the taille
	 */
	public static float getTaille() {
		return TAILLE;
	}

}
