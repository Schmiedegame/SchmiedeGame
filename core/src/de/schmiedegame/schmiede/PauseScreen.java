package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import static com.badlogic.gdx.Gdx.gl;

public class PauseScreen extends ScreenAdapter {

    private final int WIDTH = 1000;
    private final int HEIGHT = 700;



    private Smithinggame game;
    private MainGameScreen gamescreen;

    private TextureRegion background;

    private Stage stage;
    private Group group;

    private ImageButton help_button;
    private ImageButton leave_button;

    //Timer um ständiges switchen zwischen Pause und MainGameScreen zu verhindern, da Escape länger als einen frame gedrückt wird
    private float pauseTimer;


    public PauseScreen(Smithinggame game, MainGameScreen gamescreen) {
        this.game = game;
        this.gamescreen = gamescreen;

        background = Assets.pause_screen_region;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        group = new Group();
        group.setBounds(0,0, WIDTH, HEIGHT);

        leave_button = new ImageButton(Assets.leave_button_states.get(0), Assets.leave_button_states.get(1), Assets.leave_button_states.get(2));
        leave_button.setPosition(350, 350);
        help_button = new ImageButton(Assets.help_button_states.get(0), Assets.help_button_states.get(1), Assets.help_button_states.get(2));
        help_button.setPosition(350, 200);

        group.addActor(leave_button);
        group.addActor(help_button);

        stage.addActor(group);


        pauseTimer = 0.5f;
    }

    /**
     * Methode, die jeden Frame aufgerufen wird
     * @param delta Vergangene Zeit seit letztem Frame
     */
    @Override
    public void render(float delta) {

        //Grafik
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        game.batch.begin();
        stage.draw();
        game.batch.end();


        //Logik
        if (leave_button.isChecked()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        if (help_button.isChecked()) {
            game.setScreen(new HelpScreen(game, this));
            hide();
        }

        if (pauseTimer > 0) {
            pauseTimer -= delta;
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                game.setScreen(gamescreen);
                dispose();
            }
        }
    }

    @Override
    public void show() {
        help_button.setChecked(false);
        Gdx.input.setInputProcessor(stage);
    }
}
