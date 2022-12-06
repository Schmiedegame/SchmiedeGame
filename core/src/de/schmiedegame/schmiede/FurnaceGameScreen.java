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

import static com.badlogic.gdx.Gdx.gl;
import static de.schmiedegame.schmiede.Assets.furnace_button_states;

public class FurnaceGameScreen extends ScreenAdapter {

    private final int WIDTH = 1000;
    private final int HEIGHT = 700;


    //gamestate gibt den momentanen Status des Spiels an.
    private Gamestates.state gamestate = Gamestates.state.INACTIVE;

    private Smithinggame game;
    private MainGameScreen gameScreen;

    private FurnaceGame furnacegame;



    private TextureRegion background;

    private Group group;
    private Stage stage;

    private ImageButton furnace_button;

    private ImageButton play_button;
    private ImageButton leave_button;

    private Label instruction;
    private Label timer;


    public FurnaceGameScreen(Smithinggame game, MainGameScreen gameScreen) {

        //Musik wird gewechselt
        MusicPlayer.play_minigame_track();

        this.game = game;
        this.gameScreen = gameScreen;

        furnacegame = new FurnaceGame();


        //Grafikelemente
        background = Assets.furnace_background_region;

        group = new Group();
        group.setBounds(0, 0, WIDTH, HEIGHT);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Initialisierung des Interaktionsknopfes sowie Positionierung
        furnace_button = new ImageButton(furnace_button_states.get(0), furnace_button_states.get(1), furnace_button_states.get(2));
        furnace_button.setPosition(254, 132);
        furnace_button.setDisabled(true);

        //Initialisierung restlicher Grafikelemente
        play_button = new ImageButton(Assets.play_button_states.get(0), Assets.play_button_states.get(1), Assets.play_button_states.get(2));
        play_button.setPosition(350, 25);

        //play_button und leave_button sind auf der selben Position. leave_button ist anfangs deaktiviert. play_button wird im Laufe des Spiels deaktiviert.
        leave_button = new ImageButton(Assets.leave_button_states.get(0), Assets.leave_button_states.get(1), Assets.leave_button_states.get(2));
        leave_button.setPosition(350, 25);
        leave_button.setVisible(false);
        leave_button.setDisabled(true);

        instruction = new Label("Klicke schnell!", new Label.LabelStyle(Assets.font_100, Color.WHITE));
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
        group.addActor(furnace_button);

        stage.addActor(group);
    }


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

                gamestate = furnacegame.update_gamestate();
                furnacegame.update(delta, furnace_button, instruction, timer);

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

                    furnace_button.setDisabled(false);
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
        furnace_button.setDisabled(true);
        furnace_button.setVisible(false);

        gamestate = Gamestates.state.INACTIVE;
    }
}
