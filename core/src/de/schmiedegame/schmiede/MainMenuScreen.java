package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.badlogic.gdx.Gdx.gl;

public class MainMenuScreen extends ScreenAdapter {

    private final int WIDTH = 1000;
    private final int HEIGHT = 700;

    private Smithinggame game;

    private ImageButton play_button;
    private ImageButton help_button;
    private Stage stage;
    private Group group;

    public MainMenuScreen(Smithinggame game) {
        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        group = new Group();
        group.setBounds(0,0, WIDTH, HEIGHT);

        play_button = new ImageButton(new TextureRegionDrawable(Assets.button_up), new TextureRegionDrawable(Assets.button_down), new TextureRegionDrawable(Assets.button_checked));
        play_button.setPosition(350, 350);
        help_button = new ImageButton(new TextureRegionDrawable(Assets.button_up), new TextureRegionDrawable(Assets.button_down), new TextureRegionDrawable(Assets.button_checked));
        help_button.setPosition(350, 200);


        group.addActor(play_button);
        group.addActor(help_button);

        stage.addActor(group);
    }

    @Override
    public void render(float delta) {
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        stage.draw();
        game.batch.end();
    }
}
