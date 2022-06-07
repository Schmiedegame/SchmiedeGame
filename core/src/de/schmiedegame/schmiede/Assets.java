package de.schmiedegame.schmiede;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Assets {

    //Konstanten
    public static final int BACKGROUND_WIDTH = 1000;
    public static final int BACKGROUND_HEIGHT = 700;

    public static final int PLAYER_WIDTH = 64;
    public static final int PLAYER_HEIGHT = 64;
    public static final int PLAYER_KEYFRAMES = 6;
    public static final float PLAYER_ANIM_DURATION = 0.1f;

    public static final int BUTTON_WIDTH = 300;
    public static final int BUTTON_HEIGHT = 100;

    //--------------
    public static Texture background;
    public static TextureRegion background_region;

    //Button hat 3 Zustände, also 3 TextureRegions
    public static Texture button;
    public static TextureRegion button_up;
    public static TextureRegion button_down;
    public static TextureRegion button_checked;

    //2 verschiedene Animationen die beide ein Array von TextureRegions brauchen
    public static Texture player_right;
    public static Texture player_left;
    public static Array<TextureRegion> player_anim_frames_right;
    public static Array<TextureRegion> player_anim_frames_left;
    public static Animation<TextureRegion> player_animation_right;
    public static Animation<TextureRegion> player_animation_left;


    /**
     * Initiiert alle Assets. Da alle Assets static sind, müssen sie nur einmal initialisiert werden
     */
    public static void load() {
        background = new Texture("background.jpg");
        //Kooridnaten werden hier von oben links gezählt
        background_region = new TextureRegion(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //player_anim_frames_right ist das Array, das alle keyframes für die Animation enthält
        player_right = new Texture("player_right.png");
        player_anim_frames_right = new Array<TextureRegion>(PLAYER_KEYFRAMES);
        for (int i = 0; i < PLAYER_KEYFRAMES; i++) {
            player_anim_frames_right.add(new TextureRegion(player_right, PLAYER_WIDTH * i, 0, PLAYER_WIDTH, PLAYER_HEIGHT));
        }
        player_animation_right = new Animation<TextureRegion>(PLAYER_ANIM_DURATION, player_anim_frames_right, Animation.PlayMode.LOOP);

        player_left = new Texture("player_left.png");
        player_anim_frames_left = new Array<TextureRegion>(PLAYER_KEYFRAMES);
        for (int i = 0; i < PLAYER_KEYFRAMES; i++) {
            player_anim_frames_left.add(new TextureRegion(player_left, PLAYER_WIDTH * i, 0, PLAYER_WIDTH, PLAYER_HEIGHT));
        }
        player_animation_left = new Animation<TextureRegion>(PLAYER_ANIM_DURATION, player_anim_frames_left, Animation.PlayMode.LOOP_REVERSED);

        button = new Texture("buttons.png");
        button_up = new TextureRegion(button, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        button_down = new TextureRegion(button, 0, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        button_checked = new TextureRegion(button, 0, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
    }
}
