package com.mygdx.game.Main;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Screens.GameScreen;

public class SeriousGame extends Game {
	static public Skin skin;
	static public TextureAtlas textureAtlas;

	@Override
	public void create () {
		//skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		//textureAtlas = new TextureAtlas();
		//textureAtlas.addRegion("note",new TextureRegion(new Texture("note.png")));
		//this.setScreen(new TitleScreen(this));
		this.setScreen(new GameScreen(this));

	}

	@Override
	public void render () {
		super.render();

	}

	public void dispose () {
		skin.dispose();

	}
}

