package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private final TextureRegion player_model;
    private final int SPEED = 300;
    private final int Y_BOUNDS = Gdx.graphics.getHeight();
    private final int X_BOUNDS = Gdx.graphics.getWidth();
    private Vector2 movement_vector;

    private Vector2 position_vector;



    public Player(int x, int y) {
        position_vector = new Vector2(x, y);
        movement_vector = new Vector2();
        player_model = Assets.player_region;
    }

    public void update(float delta) {
        movement_vector.setZero();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            movement_vector.y = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            movement_vector.y = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movement_vector.x = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movement_vector.x = -1;
        }

        movement_vector.nor();
        movement_vector.x = movement_vector.x * SPEED * delta;
        movement_vector.y = movement_vector.y * SPEED * delta;

        if(position_vector.x + movement_vector.x < X_BOUNDS - Assets.PLAYER_HEIGHT && position_vector.x + movement_vector.x > 0) {
            position_vector.x += movement_vector.x;
        }
        if(position_vector.y + movement_vector.y < Y_BOUNDS - Assets.PLAYER_WIDTH && position_vector.y + movement_vector.y > 0) {
            position_vector.y += movement_vector.y;
        }

    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(player_model, position_vector.x, position_vector.y);
        batch.end();
    }
}
