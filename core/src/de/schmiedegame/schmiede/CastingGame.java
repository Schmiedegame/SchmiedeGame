package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class CastingGame {

    private final int RECT_SPEED = 250;

    private final int LOWER_RECT_BOUNDS = -100;
    private final int UPPER_RECT_BOUNDS = 200;

    private final int LOWER_AIM_BOUNDS = 25;
    private final int UPPER_AIM_BOUNDS = 75;

    private final float PROGRESS_SPEED = 0.1f;


    private Vector2 position_vector;

    private float vertMovement;

    private float gameProgress;
    private float gameTimer;

    private Animation<TextureRegion> background_animation;



    public CastingGame() {

        position_vector = new Vector2(200, -100);

        vertMovement = -1;

        gameProgress = 0;

        background_animation = Assets.casting_background_animation;
    }



    public void Update_ControlRect(float delta, Rectangle controlRect) {

        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            vertMovement = 1 * delta * RECT_SPEED;
        }
        else {
            vertMovement = -1 * delta * RECT_SPEED;
        }

        if (controlRect.getY() + vertMovement < UPPER_RECT_BOUNDS && controlRect.getY() + vertMovement > LOWER_RECT_BOUNDS) {
            position_vector.y += vertMovement;
        }

        controlRect.setPosition(position_vector.x, position_vector.y);

        gameTimer += delta;
    }


    public TextureRegion Update_background(float delta) {

        if (position_vector.y > LOWER_AIM_BOUNDS && position_vector.y < UPPER_AIM_BOUNDS) {
            gameProgress += delta * PROGRESS_SPEED;
        }
        else if (gameProgress - delta * PROGRESS_SPEED > 0){
            gameProgress -= delta * PROGRESS_SPEED;
        }

        return background_animation.getKeyFrame(gameProgress);
    }


    public void Update_Progress_Label(Label label) {
        label.setText(String.valueOf((int)(gameProgress * 100)) + "%");
    }

    public Gamestates.state Update_gamestate() {
        if (gameProgress < 1) {
            return Gamestates.state.ACTIVE;
        }
        else {
            Score.Add_casting_score(gameTimer);
            return Gamestates.state.FINISHED;
        }
    }
}
