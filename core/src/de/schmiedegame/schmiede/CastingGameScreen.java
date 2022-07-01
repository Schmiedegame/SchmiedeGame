package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Grafik-Klasse des Casting-Minispiels.
 */
public class CastingGameScreen extends ScreenAdapter {

    //Konstanten
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;


    //Verwaltung
    private Gamestates.state gamestate;

    private Smithinggame game;
    //Objekt, von dem aus auf das Minispiel zugegriffen wurde
    private ScreenAdapter gameScreen;
    private CastingGame castinggame;


    //Grafikelemente
    private ShapeRenderer shapeRenderer;
    private Rectangle controlRect;

    private TextureRegion background;

    private Button play_button;
    private Button leave_button;

    private Label instruction;
    private Label progress;

    private Group group;
    private Stage stage;


    public CastingGameScreen(Smithinggame game, ScreenAdapter gamescreen) {

        //Musik wird gewechselt
        MusicPlayer.play_minigame_track();


        //Verwaltungselemente werden initialisiert
        gamestate = Gamestates.state.INACTIVE;

        this.game = game;
        this.gameScreen = gamescreen;
        castinggame = new CastingGame();

        //Grafikelemente werden initialisiert
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setColor(Color.GREEN);

        background = Assets.casting_background_animation.getKeyFrame(0);

        controlRect = new Rectangle(200, -100, 25, 300);

        group = new Group();
        group.setBounds(0, 0, WIDTH, HEIGHT);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Initialisierung restlicher Grafikelemente
        play_button = new ImageButton(Assets.play_button_states.get(0), Assets.play_button_states.get(1), Assets.play_button_states.get(2));
        play_button.setPosition(350, 25);

        //play_button und leave_button sind auf der selben Position. leave_button ist anfangs deaktiviert. play_button wird im Laufe des Spiels deaktiviert.
        leave_button = new ImageButton(Assets.leave_button_states.get(0), Assets.leave_button_states.get(1), Assets.leave_button_states.get(2));
        leave_button.setPosition(350, 25);
        leave_button.setVisible(false);
        leave_button.setDisabled(true);

        instruction = new Label("Sei praezise! (X)", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        instruction.setBounds(0,0, WIDTH, 200);
        instruction.setAlignment(Align.center);
        instruction.moveBy(0,525);

        progress = new Label("", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        progress.setBounds(0, 0, WIDTH, 150);
        progress.setAlignment(Align.center);
        progress.setTouchable(Touchable.disabled);

        group.addActor(play_button);
        group.addActor(leave_button);
        group.addActor(instruction);
        group.addActor(progress);

        stage.addActor(group);
    }

    /**
     * Methode, die jeden frame aufgerufen wird.
     * @param delta Vergangene Zeit seit letztem frame
     */
    @Override
    public void render(float delta) {

        //Führt eine Verwaltungs- oder Logikaufgabe aus, abhängig vom Status des Spiels:
        //ACTIVE:   Spiel ist im Gange
        //FINISHED: Spiel wurde abgeschlossen
        //INACTIVE: Spiel ist nicht im Gange
        //Ablauf:
        //1. INACTIVE -> Spiel wird gestartet
        //2. ACTIVE -> Spiel wird gespielt
        //3. FINISHED -> Spiel wird deaktiviert
        //4. INACTIVE -> Spiel kann verlassen werden
        switch (gamestate) {

            case ACTIVE:

                castinggame.Update_ControlRect(delta, controlRect);
                background = castinggame.Update_background(delta);
                castinggame.Update_Progress_Label(progress);
                gamestate = castinggame.Update_gamestate();

                break;

            case FINISHED:

                finish_game();

                break;

            case INACTIVE:

                if (play_button.isChecked()) {

                    gamestate = Gamestates.state.ACTIVE;

                    play_button.setVisible(false);
                    play_button.setDisabled(true);
                    play_button.toggle();

                    instruction.setVisible(false);

                }

                if (leave_button.isChecked()) {
                    game.setScreen(gameScreen);
                    dispose();
                }

                break;

        }


        //Stellt Grafikelemente dar
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(controlRect.getX(), controlRect.getY(), controlRect.getWidth(), controlRect.getHeight());
        shapeRenderer.end();

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        stage.draw();

    }


    /**
     * Wird aufgerufen, wenn Minispiel abgeschlossen wurde.
     */
    private void finish_game() {

        instruction.setText("Ende!");
        instruction.setVisible(true);

        progress.setVisible(false);

        leave_button.setDisabled(false);
        leave_button.setVisible(true);

        background = Assets.casting_background_finished;

        gamestate = Gamestates.state.INACTIVE;
    }
}
