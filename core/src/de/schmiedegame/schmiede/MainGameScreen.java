package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;


public class MainGameScreen extends ScreenAdapter {

    private Smithinggame game;
    private World world;


    private float pauseTimer;


    public MainGameScreen(Smithinggame game) {
        this.game = game;
        world = new World();

        pauseTimer = 0.5f;
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        world.draw(game.batch);

        if (pauseTimer > 0) {
            pauseTimer -= delta;
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                game.setScreen(new PauseScreen(game, this));
                hide();
            }
        }
    }

    @Override
    public void show() {
        pauseTimer = 1;
    }
}
