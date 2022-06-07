package de.schmiedegame.schmiede;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Smithinggame extends Game {

    public SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        Assets.load();
        setScreen(new MainGameScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {

    }
}