package de.schmiedegame.schmiede;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import static com.badlogic.gdx.Gdx.gl;

public class MainMenuScreen extends ScreenAdapter {
    private Smithinggame game;

    private Button play_button;
    private Button help_button;

    public MainMenuScreen(Smithinggame game) {
        this.game = game;

        play_button = new Button();
        play_button.setSize(500, 150);
        play_button.setPosition(250, 300);

        help_button = new Button();
        help_button.setSize(500, 150);
        help_button.setPosition(250, 100);
    }

    @Override
    public void render(float delta) {
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        play_button.draw(game.batch, 1);
        help_button.draw(game.batch, 1);
        game.batch.end();
    }
}
