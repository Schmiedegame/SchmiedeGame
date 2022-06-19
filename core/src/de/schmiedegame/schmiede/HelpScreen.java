package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Klasse des "Hilfe" Bildschirms.
 */
public class HelpScreen extends ScreenAdapter {

    //Konstanten
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;

    //Verwaltung
    private Smithinggame game;
    private ScreenAdapter screen;

    //Grafikelemente
    private TextureRegion background;

    private ImageButton leave_button;
    private Group group;
    private Stage stage;


    /**
     * Konstruktor des "Hilfe" Bildschirms.
     * @param game Main-Game Objekt
     * @param screen Screen, von dem aus auf den Hilfe Bildschirm zugegriffen wurde
     */
    public HelpScreen(Smithinggame game, ScreenAdapter screen) {
        this.game = game;
        this.screen = screen;

        //Grafik
        background = Assets.help_screen_region;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        group = new Group();
        group.setBounds(0,0, WIDTH, HEIGHT);

        leave_button = new ImageButton(Assets.leave_button_small_states.get(0), Assets.leave_button_small_states.get(1), Assets.leave_button_small_states.get(2));
        leave_button.setPosition(425, 15);

        group.addActor(leave_button);

        stage.addActor(group);
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
            game.setScreen(screen);
            dispose();
        }
    }
}
