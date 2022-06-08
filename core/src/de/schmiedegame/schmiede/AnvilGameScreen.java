package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Grafik-Klasse des Anvil Minispiels
 */
public class AnvilGameScreen extends ScreenAdapter {

    private final int WIDTH = 1000;
    private final int HEIGHT = 700;

    private final int BUTTON_NUM = 5;

    private Gamestates.state gamestate = Gamestates.state.INACTIVE;

    private AnvilGame anvilgame;

    private Smithinggame game;
    private MainGameScreen gameScreen;

    private TextureRegion background;

    private Group group;
    private Stage stage;

    private Array<ImageButton> anvil_buttons;

    private ImageButton play_button;
    private ImageButton leave_button;


    public AnvilGameScreen(Smithinggame game, MainGameScreen gameScreen) {
        anvilgame = new AnvilGame();

        this.game = game;
        this.gameScreen = gameScreen;

        background = Assets.anvil_background_region;

        group = new Group();
        group.setBounds(0, 0, WIDTH, HEIGHT);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

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


        play_button = new ImageButton(Assets.play_button_states.get(0), Assets.play_button_states.get(1), Assets.play_button_states.get(2));
        play_button.setPosition(350, 25);

        leave_button = new ImageButton(Assets.leave_button_states.get(0), Assets.leave_button_states.get(1), Assets.leave_button_states.get(2));
        leave_button.setPosition(350, 25);
        leave_button.setVisible(false);
        leave_button.setDisabled(true);

        group.addActor(play_button);
        group.addActor(leave_button);

        stage.addActor(group);
    }


    @Override
    public void render(float delta) {
        switch(gamestate) {
            case ACTIVE:
                anvilgame.update(delta, anvil_buttons);
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
     * Wird aufgerufen falls das Minispiel abgeschlossen ist.
     * Der "Verlassen" Knopf wird aktiviert und die Interaktionsknöpfe auf dem Amboss werden versteckt.
     */
    public void finish_game() {
        leave_button.setDisabled(false);
        leave_button.setVisible(true);
        for (int i = 0; i < BUTTON_NUM; i++) {
            anvil_buttons.get(i).setDisabled(true);
            anvil_buttons.get(i).setVisible(false);
        }

        gamestate = Gamestates.state.INACTIVE;
    }


}
