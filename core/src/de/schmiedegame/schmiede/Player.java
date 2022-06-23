package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Klasse, die die Spielfigur verwaltet und steuert.
 */
public class Player {

    //Konstanten
    private final int SPEED = 400;

    private final int Y_BOUNDS_DOWN = 55;
    private final int Y_BOUNDS_UP = Gdx.graphics.getHeight() - 55;
    private final int X_BOUNDS_LEFT = 35;
    private final int X_BOUNDS_RIGHT = Gdx.graphics.getWidth() - 35;

    private final float ACCELERATION_TIME = 0.1f;

    //---------
    //Animation wird in Abhängigkeit der Bewegungsrichtung geändert
    //stateTime ist die vergangene Zeit während einer laufenden Animation
    private Animation<TextureRegion> animation;
    private float stateTime;

    private Vector2 movement_vector;

    private Vector2 position_vector;

    //Beschleunigung
    private float accelerationTimer;



    public Player() {
        //position_vector = Save.getSavedPlayerPosition();
        position_vector = new Vector2(Float.valueOf(Save.savedata[0]), Float.valueOf(Save.savedata[1]));
        movement_vector = new Vector2();
        animation = Assets.player_animation_right;
        stateTime = 0f;

        accelerationTimer = 0f;
    }

    /**
     * Aktualisiert die position des players.
     * Verwaltet außerdem die Animation und die stateTime.
     * @param delta Vergangene Zeit seit letztem Update
     */
    public void update(float delta) {

        //***BEWEGUNG UND ANIMATION***
        movement_vector.setZero();

        //Input muss doppelt überprüft werden, damit die Animation richtig ablaufen kann.
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            //Falls Bewegung, wird stateTime um delta erhöht.
            stateTime += delta;
            //Beschleunigung für weichere Bewegung.
            if (accelerationTimer <= ACCELERATION_TIME)
            {
                accelerationTimer += delta;
                delta = delta * accelerationTimer * 5;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                movement_vector.y = 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                movement_vector.y = -1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                movement_vector.x = 1;
                //Animation wird auf rechts geändert falls Bewegung nach rechts.
                animation = Assets.player_animation_right;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                movement_vector.x = -1;
                //Animation wird auf rechts geändert falls Bewegung nach links.
                animation = Assets.player_animation_left;
            }
        }
        else {
            //stateTime wird zurückgesetzt bei Stillstand.
            stateTime = 0;
            accelerationTimer = 0f;
        }

        //Movement-Vektor wird normiert für richtige diagonale Bewegung und wird dann mit der Geschwindigkeit sowie delta verrechnet.
        movement_vector.nor();
        movement_vector.x = movement_vector.x * SPEED * delta;
        movement_vector.y = movement_vector.y * SPEED * delta;

        //Überprüfung der Kollisionen vor Ausführung der Bewegung.
        if(position_vector.x + movement_vector.x < X_BOUNDS_RIGHT - Assets.PLAYER_HEIGHT && position_vector.x + movement_vector.x > X_BOUNDS_LEFT) {
            position_vector.x += movement_vector.x;
        }
        if(position_vector.y + movement_vector.y < Y_BOUNDS_UP - Assets.PLAYER_WIDTH && position_vector.y + movement_vector.y > Y_BOUNDS_DOWN) {
            position_vector.y += movement_vector.y;
        }

        //***BEWEGUNG UND ANIMATION ENDE***

    }

    /**
     * Rendert den Spieler auf dem Bildschirm.
     * @param batch Spritebatch, die das Rendern durchführt
     */
    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(animation.getKeyFrame(stateTime), position_vector.x, position_vector.y);
        batch.end();
    }


    /**
     * Gibt die Position des Spielers in Form eines Vektors.
     * @return Position
     */
    public Vector2 getPosition() {
        return position_vector;
    }

    public void Save() {
        Save.savePlayerPosition(position_vector);
    }
}
