package de.schmiedegame.schmiede;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.*;

public class Assets {

    public static final int BACKGROUND_WIDTH = 1000;
    public static final int BACKGROUND_HEIGHT = 700;

    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 32;

    public static Texture background;
    public static TextureRegion background_region;
    public static Texture player;
    public static TextureRegion player_region;
    public static Animation tom_walking;

    public static void load() {
        background = new Texture("background.png");
        //For some reason, y seems to be counted from the middle here, consider to put height/2 as y
        background_region = new TextureRegion(background, 0, BACKGROUND_HEIGHT/2, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        player = new Texture("player.png");
        player_region = new TextureRegion(player, 0, PLAYER_HEIGHT/2, PLAYER_WIDTH, PLAYER_HEIGHT);
    }
}
