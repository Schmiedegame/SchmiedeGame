package de.schmiedegame.schmiede;

import com.badlogic.gdx.ScreenAdapter;

public class MainGameScreen extends ScreenAdapter {

    private Smithinggame game;
    private World world;


    public MainGameScreen(Smithinggame game) {
        this.game = game;
        world = new World();
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        world.draw(game.batch);
    }
}
