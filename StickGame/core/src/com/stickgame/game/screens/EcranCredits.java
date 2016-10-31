/**
 * 
 */
package com.stickgame.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * @author Romain Venel & Maxime Genevier
 * 
 * 		Cette classe affiche les crédits du jeu
 * 
 * 		Elle implémente l'inferface Screen de LibGDX
 *
 */
public class EcranCredits implements Screen{

	private 	SpriteBatch 		spriteBatch;
	private 	Viewport 			viewport;
	private 	TextureAtlas 		atlas;
	private 	OrthographicCamera 	camera;
	protected 	Stage 				stage;
	protected 	Skin 				skin;
	private Music musique;
	
	public EcranCredits(){
		
		this.atlas = new TextureAtlas("C:/StickGame/android/assets/UI/uiskin.atlas");
		this.skin = new Skin(Gdx.files.internal("C:/StickGame/android/assets/UI/uiskin.json"), atlas);
		this.spriteBatch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(450, 450, camera);
		this.viewport.apply();
		this.camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		this.camera.update();
		this.stage = new Stage(viewport, spriteBatch);
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void show() {
		
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.center();
		
		musique=Gdx.audio.newMusic(Gdx.files.internal("C:/StickGame/android/assets/Musique/ROB (Maniac soundtrack) - Haunted.mp3"));
		musique.setVolume(0.2f);
		musique.setLooping(true);
		musique.play();
		
		TextButton btnRetour = new TextButton("Retour", skin);
		
		TextArea credits = new TextArea("Développeurs:\n\n" + "Romain Venel\nMaxime Genevier"
		+ "Graphistes:\n\n" + "Romain Venel\nTristan Lemire\nMaxime Genevier", skin);
		
		((TextField)credits).setDisabled(true);
		
		btnRetour.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				( (Game)Gdx.app.getApplicationListener() ).setScreen(new EcranMenuPrincipal());
				musique.stop();
			}
		});
		
		mainTable.add(credits).width(350).height(325);
		mainTable.row();
		mainTable.add(btnRetour).width(100).height(25).padTop(15);
		
		this.stage.addActor(mainTable);
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.stage.act();
		this.stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
		this.viewport.update(width, height);
		this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2, 0);
		this.camera.update();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
