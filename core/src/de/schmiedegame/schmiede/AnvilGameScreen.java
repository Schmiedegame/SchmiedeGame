package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Grafik-Klasse des Anvil Minispiels.
 */
public class AnvilGameScreen extends ScreenAdapter {

    //Konstanten
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;

    private final int BUTTON_NUM = 5;


    //gamestate gibt den momentanen Status des Spiels an.
    private Gamestates.state gamestate = Gamestates.state.INACTIVE;

    //Main-Game Objekt und MainGameScreen Objekt, von dem aus auf das Minispiel zugegriffen wurde.
    private Smithinggame game;
    private MainGameScreen gameScreen;

    //Logik Objekt
    private AnvilGame anvilgame;


    //Grafikelemente
    private TextureRegion background;

    private Group group;
    private Stage stage;

    private Array<ImageButton> anvil_buttons;

    private ImageButton play_button;
    private ImageButton leave_button;

    private Label instruction;
    private Label timer;


    /**
     * Konstruktor der Grafik Klasse des Anvil Minispiels.
     * @param game Main-Game Objekt
     * @param gameScreen MainGameScreen Objekt, von dem aus der Zugriff stattfand
     */
    public AnvilGameScreen(Smithinggame game, MainGameScreen gameScreen) {

        //Musik wird gewechselt
        MusicPlayer.play_minigame_track();

        this.game = game;
        this.gameScreen = gameScreen;

        anvilgame = new AnvilGame();


        //Grafikelemente
        background = Assets.anvil_background_region;

        group = new Group();
        group.setBounds(0, 0, WIDTH, HEIGHT);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Initialisierung der Interaktionsknöpfe für das Minispiel, sowie Positionierung.
        anvil_buttons = new Array<ImageButton>(BUTTON_NUM);
        for (int i = 0; i < BUTTON_NUM; i++) {
            anvil_buttons.add(new ImageButton(Assets.anvil_button_states.get(0), Assets.anvil_button_states.get(1), Assets.anvil_button_states.get(2)));
            anvil_buttons.get(i).setDisabled(true);
            group.addActor(anvil_buttons.get(i));
        }
        anvil_buttons.get(0).setPosition(350, 370);
        anvil_buttons.get(1).setPosition(350, 240);
        anvil_buttons.get(2).setPosition(450, 305);
        anvil_buttons.get(3).setPosition(550, 370);
        anvil_buttons.get(4).setPosition(550, 240);


        //Initialisierung restlicher Grafikelemente
        play_button = new ImageButton(Assets.play_button_states.get(0), Assets.play_button_states.get(1), Assets.play_button_states.get(2));
        play_button.setPosition(350, 25);

        //play_button und leave_button sind auf der selben Position. leave_button ist anfangs deaktiviert. play_button wird im Laufe des Spiels deaktiviert.
        leave_button = new ImageButton(Assets.leave_button_states.get(0), Assets.leave_button_states.get(1), Assets.leave_button_states.get(2));
        leave_button.setPosition(350, 25);
        leave_button.setVisible(false);
        leave_button.setDisabled(true);

        instruction = new Label("Sei schnell!", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        instruction.setBounds(0,0, WIDTH, 200);
        instruction.setAlignment(Align.center);
        instruction.moveBy(0,525);

        timer = new Label("", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        timer.setBounds(0, 0, WIDTH, 150);
        timer.setAlignment(Align.center);
        timer.setTouchable(Touchable.disabled);

        group.addActor(play_button);
        group.addActor(leave_button);
        group.addActor(instruction);
        group.addActor(timer);

        stage.addActor(group);
    }


    /**
     * Methode, die jeden Frame aufgerufen wird.
     * @param delta Vergangene Zeit seit letztem Frame
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
        switch(gamestate) {
            case ACTIVE:

                anvilgame.update(delta, anvil_buttons, instruction, timer);
                gamestate = anvilgame.update_gamestate();

                break;

            case FINISHED:

                finish_game();

                break;

            case INACTIVE:

                if (play_button.isChecked()) {
                    gamestate = Gamestates.state.ACTIVE;
                    play_button.setVisible(false);
                    play_button.setDisabled(true);
                }

                if (leave_button.isChecked()) {
                    game.setScreen(gameScreen);
                    dispose();
                }

                break;
        }


        //Stellt die Grafikelemente dar.
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        game.batch.begin();
        stage.draw();
        game.batch.end();
    }


    /**
     * Wird aufgerufen, wenn das Minispiel abgeschlossen ist.
     * Der "Verlassen" Knopf wird aktiviert und die Interaktionsknöpfe auf dem Amboss werden versteckt.
     */
    public void finish_game() {

        instruction.setText("Ende!");

        timer.setVisible(false);

        leave_button.setDisabled(false);
        leave_button.setVisible(true);
        for (int i = 0; i < BUTTON_NUM; i++) {
            anvil_buttons.get(i).setDisabled(true);
            anvil_buttons.get(i).setVisible(false);
        }

        gamestate = Gamestates.state.INACTIVE;
    }


}
