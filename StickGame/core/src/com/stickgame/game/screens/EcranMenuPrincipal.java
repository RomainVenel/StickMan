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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stickgame.game.screens.EcranCredits;;

/**
 * @author Romain Venel & Maxime Genevier
 *
 *		Cette classe créée le menu principal du jeu
 *		Plusieurs choix sont proposés: Classique, SuperFight, Ultimate(V4 --- A voir avec Romain)
 *		Crédits et Quitter
 *
 *		Elle implémente l'interface Screen de LibGDX
 *
 */
public class EcranMenuPrincipal implements Screen{

	private 	SpriteBatch 		spriteBatch;
	private 	Viewport 			viewport;
	private 	TextureAtlas 		atlas;
	private 	OrthographicCamera 	camera;
	protected 	Stage 				stage;
	protected 	Skin 				skin;
	private Music musique;
	
	public EcranMenuPrincipal(){
		
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
		
		musique=Gdx.audio.newMusic(Gdx.files.internal("C:/StickGame/android/assets/Musique/Burning Bright - Star Guardians Login Screen.mp3"));
		musique.setVolume(0.4f);
		musique.setLooping(true);
		musique.play();
		
		TextButton btnClassique = new TextButton("Classique", skin);
		TextButton btnSuperFight = new TextButton("Super Fight", skin);
		TextButton btnUltimate = new TextButton("Ultimate", skin);
		TextButton btnCredits = new TextButton("Credits", skin);
		TextButton btnQuitter = new TextButton("Quitter", skin);
		
		btnClassique.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				((Game)Gdx.app.getApplicationListener()).setScreen(new EcranClassique());
				musique.stop();
				musique.dispose();
				
			}
		});
		
		btnSuperFight.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				//((Game)Gdx.app.getApplicationListener()).setScreen(new EcranSuperFight());
				
			}
		});
		
		btnUltimate.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				//((Game)Gdx.app.getApplicationListener()).setScreen(new EcranUltimate());
				
			}
		});
		
		btnCredits.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				( (Game)Gdx.app.getApplicationListener() ).setScreen(new EcranCredits());
				musique.pause();
				
			}
		});
		
		btnQuitter.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
		
		
		mainTable.add(btnClassique).width(200).height(50);
		mainTable.row();
		
		mainTable.add(btnSuperFight).width(200).height(50).padTop(15);
		mainTable.row();
		
		mainTable.add(btnUltimate).width(200).height(50).padTop(15);
		mainTable.row();
		
		mainTable.add(btnCredits).width(200).height(50).padTop(15);
		mainTable.row();
		
		mainTable.add(btnQuitter).width(200).height(50).padTop(15);
		mainTable.row();
		
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
		
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
		this.dispose();
		
	}

	public Music getMusique() {
		return musique;
	}

	public void setMusique(Music musique) {
		this.musique = musique;
	}

}
