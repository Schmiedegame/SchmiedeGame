package de.schmiedegame.schmiede;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class FurnaceGame {

    private final float GAME_DURATION = 10f;

    private int clickCounter;
    private float gameTimer;

    public FurnaceGame() {

        clickCounter = 0;
        gameTimer = GAME_DURATION;

    }


    public void update(float delta, ImageButton button, Label label, Label timer) {

        if (gameTimer >= 0) {


            if (button.isChecked()) {
                button.toggle();
                clickCounter += 1;
                label.setText(String.valueOf(clickCounter));
            }
        }
        else {
            Score.Add_furnace_score(clickCounter);
        }

        timer.setText(String.valueOf((int)gameTimer + 1));

        gameTimer -= delta;

    }


    public Gamestates.state update_gamestate() {

        if (gameTimer >= 0) {
            return Gamestates.state.ACTIVE;
        }
        else {
            return  Gamestates.state.FINISHED;
        }
    }
}
