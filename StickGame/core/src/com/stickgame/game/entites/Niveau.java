/**
 * 
 */
package com.stickgame.game.entites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
	
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	
	private TextureAtlas textureAtlas;
	private Texture fond2;
	private TextureRegion textureBlocks;
	
	public Niveau(){
		
		Random rand = new Random();
		int nombre = rand.nextInt(4) +1;
		
		System.out.println(nombre);
		
		switch (nombre) {
		case 1:
			this.niveau1();
			break;
		case 2:
			this.niveau2();
			break;
		case 3:
			this.niveau3();
			break;
		case 4:
			this.niveau4();
			break;
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
		
		this.tiledMap = new TmxMapLoader().load("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/plaine.tmx");
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		musique=Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Naruto Shippuden OST III - Waltz of Wind and Fire.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
	}
	
	private void niveau2(){
		
		this.tiledMap = new TmxMapLoader().load("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/espace.tmx");
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		musique = Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Bleach - Fade To Black B13a.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
	}
	
	private void niveau3(){

		this.tiledMap = new TmxMapLoader().load("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/volcan.tmx");
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		musique=Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Star Wars - Anakin Vs. Obi-Wan - Battle of the Heroes.mp3"));
		musique.setVolume(0.2f);
		musique.setLooping(true);
		musique.play();
		
	}
	
	private void niveau4(){
		
		this.tiledMap = new TmxMapLoader().load("C:/Users/VENEL/Documents/StickGame/android/assets/Monde/glace.tmx");
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		musique = Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/WotLK - Arthas, My Son.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
	}
	
	private void niveau5(){
		
		musique = Gdx.audio.newMusic(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Musique/Burning Bright - Star Guardians Login Screen.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
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

	/**
	 * @return the tiledMap
	 */
	public TiledMap getTiledMap() {
		return tiledMap;
	}

	/**
	 * @param tiledMap the tiledMap to set
	 */
	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	/**
	 * @return the tiledMapRenderer
	 */
	public TiledMapRenderer getTiledMapRenderer() {
		return tiledMapRenderer;
	}

	/**
	 * @param tiledMapRenderer the tiledMapRenderer to set
	 */
	public void setTiledMapRenderer(TiledMapRenderer tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}

}
