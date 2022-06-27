package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Hauptmenü-Bildschirm.
 * Wird zuerst gezeigt, wenn das Spiel gestartet wird
 */
public class MainMenuScreen extends ScreenAdapter {

    //Kosntanten
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;

    //Main-Game(Smithinggame) Objekt, das bei jedem Screen zu übergeben ist (Siehe Smithinggame.java Kommentare)
    private Smithinggame game;

    //Hintergrundanimation und stateTime zum Verwalten von dieser
    private Animation<TextureRegion> background;
    private float stateTime;

    //Buttons, sowie group und stage um Buttons zu verarbeiten
    private ImageButton play_button;
    private ImageButton help_button;
    private Stage stage;
    private Group group;

    /**
     * Konstruktor der MainMenuScreen Klasse
     * @param game Main-Game Objekt
     */
    public MainMenuScreen(Smithinggame game) {
        this.game = game;

        //Initialisierung Hintergrundanimation
        background = Assets.main_background_animation;
        stateTime = 0f;

        //Initialisierung Stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Initialisierung Group
        group = new Group();
        group.setBounds(0,0, WIDTH, HEIGHT);

        //Initialisierung der Buttons
        play_button = new ImageButton(Assets.play_button_states.get(0), Assets.play_button_states.get(1), Assets.play_button_states.get(2));
        play_button.setPosition(150, 300);
        help_button = new ImageButton(Assets.help_button_states.get(0), Assets.help_button_states.get(1), Assets.help_button_states.get(2));
        help_button.setPosition(550, 300);

        //Hinzufügen der Buttons zur group
        group.addActor(play_button);
        group.addActor(help_button);

        //Hinzufügen der Group zur stage
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

        stateTime += delta;

        game.batch.begin();
        game.batch.draw(background.getKeyFrame(stateTime), 0, 0);
        game.batch.end();

        game.batch.begin();
        stage.draw();
        game.batch.end();


        //Logik
        if (play_button.isChecked()) {
            MusicPlayer.save_main_track_position();

            game.setScreen(new MainGameScreen(game));
            dispose();
        }
        if (help_button.isChecked()) {
            game.setScreen(new HelpScreen(game, this));
            hide();
        }

    }

    /**
     * Methode, die aufgerufen wird, wenn das Objekt zum momentan angezeigten Screen des Main-Game(Smithinggame) Objekts wird
     */
    @Override
    public void show() {
        help_button.setChecked(false);
        Gdx.input.setInputProcessor(stage);
    }



}
