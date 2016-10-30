/**
 * 
 */
package com.stickgame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.stickgame.game.controleurs.ControleurPersonnage;
import com.stickgame.game.controleurs.ControleurPersonnage2;
import com.stickgame.game.entites.Niveau;
import com.stickgame.game.vues.VueClassique;

/**
 * @author Romain Venel & Maxime Genevier
 * 
 * 		Cette classe affiche l'écran du jeu en mode Classique
 * 		
 * 		Mode classique: 2 joueurs s'affrontent en 1 contre 1
 * 		Ils ne choisissent pas le personnage
 *
 */
public class EcranClassique implements Screen, InputProcessor{

	private Niveau niveau;
	private VueClassique vueClassique;
	private ControleurPersonnage controleur;
	private ControleurPersonnage2 controleur2;
	private Music musique;
	
	
	@Override
	public void show() {
		
		this.niveau = new Niveau();
		this.vueClassique = new VueClassique(niveau, true);
		this.controleur = new ControleurPersonnage(niveau);
		this.controleur2 = new ControleurPersonnage2(niveau);
				
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void render(float delta) {
		
		this.controleur.update(delta);
		this.controleur2.update(delta);
		
		this.vueClassique.render();
		
	}

	@Override
	public void resize(int width, int height) {

		this.vueClassique.setSize(width, height);
		
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

		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		
					/********** Touuches du personnage 1 **********/
		
		if(keycode == Keys.LEFT){
			this.controleur.leftPressed();
		}
		if(keycode == Keys.RIGHT){
			this.controleur.rightPressed();
		}
		if(keycode == Keys.UP){
			this.controleur.jumpPressed();
		}
		if(keycode == Keys.NUMPAD_0){
			this.controleur.attackPressed();
		}
		
					/********** Touuches du personnage 2 **********/
		
		if(keycode == Keys.Q){
			this.controleur2.leftPressed();
		}
		if(keycode == Keys.D){
			this.controleur2.rightPressed();
		}
		if(keycode == Keys.Z){
			this.controleur2.jumpPressed();
		}
		if(keycode == Keys.F){
			this.controleur2.attackPressed();
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		
					/********** Touuches du personnage 1 **********/
		
		if(keycode == Keys.LEFT){
			this.controleur.leftReleased();
		}
		if(keycode == Keys.RIGHT){
			this.controleur.rightReleased();
		}
		if(keycode == Keys.UP){
			this.controleur.jumpReleased();
		}
		if(keycode == Keys.NUMPAD_0){
			this.controleur.attackReleased();
		}
		
					/********** Touches du personnage 2 **********/
		
		if(keycode == Keys.Q){
			this.controleur2.leftReleased();
		}
		if(keycode == Keys.D){
			this.controleur2.rightReleased();
		}
		if(keycode == Keys.Z){
			this.controleur2.jumpReleased();
		}
		if(keycode == Keys.F){
			this.controleur2.attackReleased();
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
