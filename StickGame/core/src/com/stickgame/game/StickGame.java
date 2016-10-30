package com.stickgame.game;

import com.badlogic.gdx.Game;
import com.stickgame.game.screens.EcranMenuPrincipal;

public class StickGame extends Game {

	@Override
	public void create() {
		
		this.setScreen(new EcranMenuPrincipal());
		
	}
	
}
