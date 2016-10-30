/**
 * 
 */
package com.stickgame.game.entites;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @author Romain Venel & Maxime Genevier
 *
 */
public class Monde {
	
	private Niveau niveau;
	private Personnage personnage1;
	private Personnage personnage2;
	private Array<Rectangle> rectCollision = new Array<Rectangle>();
	
	public Monde(){
		
		this.creerMonde();
		
	}
	
	private void creerMonde(){
		
		this.personnage1 = new Personnage(new Vector2(1, 1));
		this.personnage2 = new Personnage(new Vector2(14, 1));
		this.niveau = new Niveau();
		
	}
	
	public List<Block> getBlocksDessinables(int width, int height){
		
		int x = (int)personnage1.getPosition().x - width;
		int y = (int)personnage1.getPosition().y - height;
		
		if(x < 0){
			x = 0;
		}
		
		if(y < 0){
			y = 0;
		}
		
		int x2 = x + 2 * width;
		int y2 = y + 2 * height;
		
		if(x2 > niveau.getWidth()){
			x2 = niveau.getWidth() - 1;
		}
		
		if(y2 > niveau.getHeight()){
			y2 = niveau.getHeight() - 1;
		}
		
		List<Block> blocks = new ArrayList<Block>();
		Block block;
		
		for(int colonne = x; colonne <= x2; colonne++){
			for(int ligne = y; ligne <= y2; ligne++){
				
				block = niveau.getBlocks()[colonne][ligne];
				
				if(block != null){
					
					blocks.add(block);
					
				}
				
			}
		}
		
		return blocks;

	}

	/**
	 * @return the niveau
	 */
	public Niveau getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the personnage1
	 */
	public Personnage getPersonnage1() {
		return personnage1;
	}

	/**
	 * @param personnage1 the personnage1 to set
	 */
	public void setPersonnage1(Personnage personnage1) {
		this.personnage1 = personnage1;
	}

	/**
	 * @return the personnage2
	 */
	public Personnage getPersonnage2() {
		return personnage2;
	}

	/**
	 * @param personnage2 the personnage2 to set
	 */
	public void setPersonnage2(Personnage personnage2) {
		this.personnage2 = personnage2;
	}

	/**
	 * @return the rectCollision
	 */
	public Array<Rectangle> getRectCollision() {
		return rectCollision;
	}

	/**
	 * @param rectCollision the rectCollision to set
	 */
	public void setRectCollision(Array<Rectangle> rectCollision) {
		this.rectCollision = rectCollision;
	}
	
	

}
