/**
 * 
 */
package com.stickgame.game.entites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Romain Venel & Maxime Genevier
 *
 */
public class Block {
	
	private static final float TAILLE = 1f;
	private Vector2 position = new Vector2();
	private Rectangle limites = new Rectangle();
	
	public Block(Vector2 position){
		
		this.position = position;
		this.limites.height = Block.TAILLE;
		this.limites.width = Block.TAILLE;
		this.limites.x = this.position.x;
		this.limites.y = this.position.y;
		
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
	 * @return the taille
	 */
	public static float getTaille() {
		return TAILLE;
	}

}
