/**
 * 
 */
package com.stickgame.game.entites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @author Romain Venel & Maxime Genevier
 *
 */
public class Niveau {
	
	private int width;
	private int height;
	private Block[][] blocks;
	private Music musique;
	private TextureAtlas textureAtlas;
	private Texture fond2;
	private TextureRegion textureBlocks;
	
	public Niveau(){
		
		this.textureAtlas = new TextureAtlas(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/textures.atlas"));
		
		Random rand = new Random();
		int nombre = rand.nextInt(3) + 1;
		
		switch (nombre) {
		case 1:
			this.niveau1();
			break;
		case 2:
			this.niveau2();
			break;
		case 3:
			this.niveau3();
		default:
			break;
		}

		this.creerNiveau();
		
	}
	
	private Personnage personnage1;
	private Personnage personnage2;
	private Array<Rectangle> rectCollision = new Array<Rectangle>();

	private void creerNiveau(){
		
		this.personnage1 = new Personnage(new Vector2(1, 2));
		this.personnage1.setFaceGauche(false);
		this.personnage2 = new Personnage(new Vector2(8, 2));
		
	}
	
private void niveau1(){
		
		this.fond2 = new Texture(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/sun.png"));
		this.textureBlocks = textureAtlas.findRegion("sol1");
		
		musique=Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Burning Bright - Star Guardians Login Screen.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
		this.width = 15;
		this.height = 9;
		this.blocks = new Block[width][height];
		
		for(int colonne = 0; colonne < this.width; colonne++){
			for(int ligne = 0; ligne < this.height; ligne++){
				this.blocks[colonne][ligne] = null;
			}
		}
		
		for(int colonne = 0; colonne < this.width; colonne++){
			this.blocks[colonne][0] = new Block(new Vector2(colonne, 0));
		}
			
		this.blocks[3][1] = new Block(new Vector2(3, 1));
		
	}
	
	private void niveau2(){
		
		musique = Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Burning Bright - Star Guardians Login Screen.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
		this.textureBlocks = textureAtlas.findRegion("sol2");
		this.fond2 = new Texture(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/monde1.png"));
		
		this.width = 15;
		this.height = 9;
		this.blocks = new Block[width][height];
		
		for(int colonne = 0; colonne < this.width; colonne++){
			for(int ligne = 0; ligne < this.height; ligne++){
				this.blocks[colonne][ligne] = null;
			}
		}
		
		for(int colonne = 0; colonne < this.width; colonne++){
			this.blocks[colonne][0] = new Block(new Vector2(colonne, 0));
		}
		
		this.blocks[2][1] = new Block(new Vector2(2, 1));
		this.blocks[13][1] = new Block(new Vector2(13, 1));
		this.blocks[1][1] = new Block(new Vector2(1, 1));
		
		for(int colonne = 3; colonne < 9; colonne++){
			this.blocks[colonne][4] = new Block(new Vector2(colonne, 4));
		}
		
		for(int colonne = 7; colonne < 11; colonne++){
			this.blocks[colonne][3] = new Block(new Vector2(colonne, 3));
		}
		
		
		
	}
	
	private void niveau3(){

		this.textureBlocks = textureAtlas.findRegion("sol3");
		this.fond2 = new Texture(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/sol1.png"));
		
		musique=Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Burning Bright - Star Guardians Login Screen.mp3"));
		musique.setVolume(0.2f);
		musique.setLooping(true);
		musique.play();
		
		this.width = 15;
		this.height = 9;
		this.blocks = new Block[width][height];
		
		for(int colonne = 0; colonne < this.width; colonne++){
			for(int ligne = 0; ligne < this.height; ligne++){
				this.blocks[colonne][ligne] = null;
			}
		}
		
		for(int colonne = 0; colonne < this.width; colonne++){
			this.blocks[colonne][0] = new Block(new Vector2(colonne, 0));
		}
		
		for(int colonne = 3; colonne < 9; colonne++){
			this.blocks[colonne][4] = new Block(new Vector2(colonne, 4));
		}
		
		for(int colonne = 7; colonne < 11; colonne++){
			this.blocks[colonne][3] = new Block(new Vector2(colonne, 3));
		}
		
	}
	
	private void niveau4(){
		
		musique = Gdx.audio.newMusic(Gdx.files.internal("C:/Users/maxime/Documents/B.T.S/StickGame/android/assets/Musique/Burning Bright - Star Guardians Login Screen.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
		this.width = 10;
		this.height = 7;
		this.blocks = new Block[width][height];
		
		for(int colonne = 0; colonne < this.width; colonne++){
			for(int ligne = 0; ligne < this.height; ligne++){
				this.blocks[colonne][ligne] = null;
			}
		}
		
		for(int colonne = 5; colonne < this.width; colonne++){
			this.blocks[colonne][0] = new Block(new Vector2(colonne, 0));
		}
		
	}
	
	private void niveau5(){
		
		musique = Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Burning Bright - Star Guardians Login Screen.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
		this.width = 10;
		this.height = 7;
		this.blocks = new Block[width][height];
		
		for(int colonne = 0; colonne < this.width; colonne++){
			for(int ligne = 0; ligne < this.height; ligne++){
				this.blocks[colonne][ligne] = null;
			}
		}
		
		this.blocks[0][0] = new Block(new Vector2(0, 0));
		this.blocks[0][1] = new Block(new Vector2(0, 1));
		this.blocks[1][1] = new Block(new Vector2(1, 1));
		
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
		
		if(x2 > getWidth()){
			x2 = getWidth() - 1;
		}
		
		if(y2 > getHeight()){
			y2 = getHeight() - 1;
		}
		
		List<Block> blocks = new ArrayList<Block>();
		Block block;
		
		for(int colonne = x; colonne <= x2; colonne++){
			for(int ligne = y; ligne <= y2; ligne++){
				
				block = getBlocks()[colonne][ligne];
				
				if(block != null){
					
					blocks.add(block);
					
				}
				
			}
		}
		
		return blocks;

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

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the blocks
	 */
	public Block[][] getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks the blocks to set
	 */
	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}
	
	public Block get(int x, int y){
		return blocks[x][y];
	}

	public Texture getFond2() {
		return fond2;
	}

	public void setFond2(Texture fond2) {
		this.fond2 = fond2;
	}

	public TextureRegion getTextureBlocks() {
		return textureBlocks;
	}

	public void setTextureBlocks(TextureRegion textureBlocks) {
		this.textureBlocks = textureBlocks;
	}

}
