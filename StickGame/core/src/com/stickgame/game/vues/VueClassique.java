/**
 * 
 */
package com.stickgame.game.vues;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.stickgame.game.entites.Block;
import com.stickgame.game.entites.Niveau;
import com.stickgame.game.entites.Personnage;
import com.stickgame.game.entites.Personnage.Etat;

/**
 * @author Romain Venel & Maxime Genevier
 *
 */
public class VueClassique {
	
	private static final float CAMERA_WIDTH = 15f;
	private static final float CAMERA_HEIGHT = 9f;
	private static final float RUNNING_FRAME_DURATION = 0.09f;
	
	private Niveau niveau;
	private OrthographicCamera camera;
	private ShapeRenderer renduDebug = new ShapeRenderer();

	private TextureRegion personnageInactifLeft;
	private TextureRegion personnageInactifRight;
	private TextureRegion personnageJumpLeft;
	private TextureRegion personnageFallLeft;
	private TextureRegion personnageJumpRight;
	private TextureRegion personnageFallRight;
	private TextureRegion personnageFrame;
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	private Animation attackLeftAnimation;
	private Animation attackRightAnimation;
	
	private int walkLeftNbFrames = 5;
	
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private float ppuX; //Pixels par unité pour X et Y
	private float ppuY;
	
	public VueClassique(Niveau niveau, boolean debug){
		
		this.niveau = niveau; 
		this.camera = new OrthographicCamera(VueClassique.CAMERA_WIDTH, VueClassique.CAMERA_HEIGHT);
		this.camera.position.set(VueClassique.CAMERA_WIDTH / 2f, VueClassique.CAMERA_HEIGHT / 2f, 0);
		this.camera.update();
		this.debug = debug;
		this.spriteBatch = new SpriteBatch();
		this.chargerTextures();
		
	}
	
	public void render(){
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.spriteBatch.begin();
			this.spriteBatch.draw(this.niveau.getFond2(),0,0,645,480);
			this.dessinerBlocks();
			this.dessinerPersonnage();
		this.spriteBatch.end();
		if(debug){
			this.dessinerDebug();
		}
		
	}
	
	private void chargerTextures(){
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("C:/Users/VENEL/Documents/StickGame/android/assets/Personnage/texturesPersonnage.atlas"));
		
		personnageInactifLeft = atlas.findRegion("StickMan0");
		personnageInactifRight = new TextureRegion(personnageInactifLeft);
		personnageInactifRight.flip(true, false);
		personnageJumpLeft = atlas.findRegion("StickMan4");
		personnageJumpRight = new TextureRegion(personnageJumpLeft);
		personnageJumpRight.flip(true, false);
		personnageFallLeft = atlas.findRegion("StickMan5");
		personnageFallRight = new TextureRegion(personnageFallLeft);
		personnageFallRight.flip(true, false);
		
		TextureRegion[] walkLeftFrames = new TextureRegion[walkLeftNbFrames];
		for(int i = 0; i < walkLeftNbFrames; i++){
			walkLeftFrames[i] = atlas.findRegion("StickMan" + i);
		}
		
		walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);
		
		TextureRegion[] walkRightFrames = new TextureRegion[walkLeftNbFrames];
		for(int i = 0; i < walkLeftNbFrames; i++){
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		
		walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
		
		TextureRegion[] attackLeftFrame = new TextureRegion[12];
		for(int i = 0; i < 12; i++){
			attackLeftFrame[i] = atlas.findRegion("StickMan - Attack" + i);
		}
		
		attackLeftAnimation = new Animation(RUNNING_FRAME_DURATION, attackLeftFrame);
		
		TextureRegion[] attackRightFrame = new TextureRegion[12];
		for(int i = 0; i < 12; i++){
			attackRightFrame[i] = new TextureRegion(attackLeftFrame[i]);
			attackRightFrame[i].flip(true, false);
		}
		
		attackRightAnimation = new Animation(RUNNING_FRAME_DURATION, attackRightFrame);	
		
	}
	
	private void dessinerPersonnage() {
		
		Personnage personnage1 = niveau.getPersonnage1();
		personnageFrame = personnage1.isFaceGauche() ? personnageInactifLeft : personnageInactifRight;
		if(personnage1.getEtat().equals(Etat.MARCHER)){
			personnageFrame = personnage1.isFaceGauche() ? walkLeftAnimation.getKeyFrame(personnage1.getStateTime(), true) : walkRightAnimation.getKeyFrame(personnage1.getStateTime(), true);	
		} else if(personnage1.getEtat().equals(Etat.SAUTER)){
			if(personnage1.getVelocite().y > 0){
				personnageFrame = personnage1.isFaceGauche() ? personnageJumpLeft : personnageJumpRight;
			} else{
				personnageFrame = personnage1.isFaceGauche() ? personnageFallLeft : personnageFallRight;
			}
		}
		else if(personnage1.getEtat().equals(Etat.ATTAQUER)){
			personnageFrame = personnage1.isFaceGauche() ? attackLeftAnimation.getKeyFrame(personnage1.getStateTime(), true): attackRightAnimation.getKeyFrame(personnage1.getStateTime(), true);
		}
		
		spriteBatch.draw(personnageFrame, personnage1.getPosition().x * ppuX, personnage1.getPosition().y * ppuY, Personnage.getTaille() * ppuX, Personnage.getTaille() * ppuY);
		
		Personnage personnage2 = niveau.getPersonnage2();
		personnageFrame = personnage2.isFaceGauche() ? personnageInactifLeft : personnageInactifRight;
		if(personnage2.getEtat().equals(Etat.MARCHER)){
			personnageFrame = personnage2.isFaceGauche() ? walkLeftAnimation.getKeyFrame(personnage2.getStateTime(), true) : walkRightAnimation.getKeyFrame(personnage2.getStateTime(), true);	
		} else if(personnage2.getEtat().equals(Etat.SAUTER)){
			if(personnage2.getVelocite().y > 0){
				personnageFrame = personnage2.isFaceGauche() ? personnageJumpLeft : personnageJumpRight;
			} else{
				personnageFrame = personnage2.isFaceGauche() ? personnageFallLeft : personnageFallRight;
			}
		} else if(personnage2.getEtat().equals(Etat.ATTAQUER)){
			personnageFrame = personnage2.isFaceGauche() ? attackLeftAnimation.getKeyFrame(personnage2.getStateTime(), true): attackRightAnimation.getKeyFrame(personnage2.getStateTime(), true);
		}
		
		spriteBatch.draw(personnageFrame, personnage2.getPosition().x * ppuX, personnage2.getPosition().y * ppuY, Personnage.getTaille() * ppuX, Personnage.getTaille() * ppuY);
	}
	
	private void dessinerBlocks(){
		
		for(Block block : this.niveau.getBlocksDessinables((int)CAMERA_WIDTH, (int)CAMERA_HEIGHT)){
			spriteBatch.draw(this.niveau.getTextureBlocks(), block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.getTaille() * ppuX, Block.getTaille() * ppuY);
		}
	}

	private void dessinerDebug() {

		this.renduDebug.setProjectionMatrix(camera.combined);
		this.renduDebug.begin(ShapeType.Line);
		
		for(Block block : niveau.getBlocksDessinables( (int)VueClassique.CAMERA_WIDTH, (int)VueClassique.CAMERA_HEIGHT )){
			
			Rectangle rectangle = block.getLimites();
			this.renduDebug.setColor(new Color(0, 1, 0, 1));
			this.renduDebug.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			
		}
		
		Personnage personnage1 = this.niveau.getPersonnage1();
		Rectangle rectangle1 = personnage1.getLimites();
		
		this.renduDebug.setColor(new Color(1, 0, 0, 1));
		this.renduDebug.rect(rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height);
		
		Personnage personnage2 = this.niveau.getPersonnage2();
		Rectangle rectangle2 = personnage2.getLimites();
		
		this.renduDebug.setColor(new Color(1, 1, 1, 1));
		this.renduDebug.rect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
		
		this.renduDebug.end();
		
	}
	
	private void drawCollisionBlocks(){
		
		this.renduDebug.setProjectionMatrix(camera.combined);
		this.renduDebug.begin(ShapeType.Filled);
		this.renduDebug.setColor(new Color(1,1,1, 1));
		for(Rectangle rect : this.niveau.getRectCollision()){
			
			renduDebug.rect(rect.x, rect.y, rect.width, rect.height);
			
		}
		
		renduDebug.end();
		
	}
	
	public void setSize(int width, int height){
		
		this.ppuX = (float)width / VueClassique.CAMERA_WIDTH;
		this.ppuY = (float)height / VueClassique.CAMERA_HEIGHT;
		
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
}
