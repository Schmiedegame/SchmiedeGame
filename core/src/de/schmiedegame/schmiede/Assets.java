package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class Assets {

    //Konstanten
    public static final int BACKGROUND_WIDTH = 1000;
    public static final int BACKGROUND_HEIGHT = 700;

    public static final int PLAYER_WIDTH = 64;
    public static final int PLAYER_HEIGHT = 64;
    public static final int PLAYER_KEYFRAMES = 6;
    public static final float PLAYER_ANIM_DURATION = 0.1f;

    public static final int ANVIL_BUTTON_WIDTH = 80;
    public static final int ANVIL_BUTTON_HEIGHT = 80;

    public static final int BUTTON_WIDTH = 300;
    public static final int BUTTON_HEIGHT = 100;
    public static final int SMALL_BUTTON_WIDTH = 150;
    public static final int SMALL_BUTTON_HEIGHT = 50;

    public static final int MAIN_BACKGROUND_KEYFRAMES = 10;
    public static final float MAIN_BACKGROUND_ANIM_DURATION = 0.5f;

    public static final int BUTTON_STATES = 3;

    //--------------
    private static Texture game_background;
    public static TextureRegion game_background_region;

    //Button hat 3 Zustände, also 3 TextureRegions
    private static Texture play_button;
    public static Array<TextureRegionDrawable> play_button_states;
    private static Texture help_button;
    public static Array<TextureRegionDrawable> help_button_states;
    private static Texture leave_button;
    public static Array<TextureRegionDrawable> leave_button_states;
    private static Texture leave_button_small;
    public static Array<TextureRegionDrawable> leave_button_small_states;

    //2 verschiedene Animationen die beide ein Array von TextureRegions brauchen
    private static Texture player_right;
    private static Texture player_left;
    public static Array<TextureRegion> player_anim_frames_right;
    public static Array<TextureRegion> player_anim_frames_left;
    public static Animation<TextureRegion> player_animation_right;
    public static Animation<TextureRegion> player_animation_left;

    //Animierter Hintergrund für das Hauptmenü
    private static Array<Texture> main_background_textures;
    public static Array<TextureRegion> main_background_keyframes;
    public static Animation<TextureRegion> main_background_animation;

    //Hintergrund für den "Hilfe"-Bildschirm
    private static Texture help_screen;
    public static TextureRegion help_screen_region;

    //Hintergrund für den "Pause"-Bildschirm
    private static Texture pause_screen;
    public static TextureRegion pause_screen_region;

    //Hintergrund für das Anvil Minispiel
    private static Texture anvil_background;
    public static TextureRegion anvil_background_region;

    //Buttons für das Anvil Minispiel
    private static Texture anvil_button;
    public static Array<TextureRegionDrawable> anvil_button_states;

    //Hintergrund für das Furnace Minispiel
    private static Texture furnace_background;
    public static TextureRegion furnace_background_region;

    private static Texture furnace_button;
    public static Array<TextureRegionDrawable> furnace_button_states;


    public static BitmapFont font_32;
    public static BitmapFont font_100;

    //Hintergrund für den ScoreScreen
    private static Texture score_background;
    public static TextureRegion score_background_region;


    /**
     * Initiiert alle Assets. Da alle Assets static sind, müssen sie nur einmal initialisiert werden
     */
    public static void load() {
        load_background();
        load_buttons();
        load_player_animations();
        load_main_background();
        load_help_background();
        load_pause_background();
        load_anvil_background();
        load_anvil_button();
        load_furnace_background();
        load_furnace_button();
        load_fonts();
        load_score_background();
    }


    /**
     * Lädt die Assets für den Hintergrund des main games
     */
    private static void load_background() {
        game_background = new Texture("background.jpg");
        //Koordinaten werden hier von oben links gezählt
        game_background_region = new TextureRegion(game_background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt alle Button assets
     */
    private static void load_buttons() {
        //Button Assets Initialisierung
        play_button = new Texture("Spielen.png");
        play_button_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(play_button, 0, BUTTON_HEIGHT * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            play_button_states.add(new TextureRegionDrawable(region));
        }

        help_button = new Texture("Hilfe.png");
        help_button_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(help_button, 0, BUTTON_HEIGHT * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            help_button_states.add(new TextureRegionDrawable(region));
        }

        leave_button = new Texture("Verlassen.png");
        leave_button_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(leave_button, 0, BUTTON_HEIGHT * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            leave_button_states.add(new TextureRegionDrawable(region));
        }

        leave_button_small = new Texture("Verlassen_small.png");
        leave_button_small_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(leave_button_small, 0, SMALL_BUTTON_HEIGHT * i, SMALL_BUTTON_WIDTH, SMALL_BUTTON_HEIGHT);
            leave_button_small_states.add(new TextureRegionDrawable(region));
        }
    }


    /**
     * Lädt die Animationen des Spielers für linke und rechte Bewegung
     */
    private static void load_player_animations() {
        //player_anim_frames_right ist das Array, das alle keyframes für die Animation enthält
        player_right = new Texture("player_right.png");
        player_anim_frames_right = new Array<TextureRegion>(PLAYER_KEYFRAMES);
        for (int i = 0; i < PLAYER_KEYFRAMES; i++) {
            player_anim_frames_right.add(new TextureRegion(player_right, PLAYER_WIDTH * i, 0, PLAYER_WIDTH, PLAYER_HEIGHT));
        }
        player_animation_right = new Animation<TextureRegion>(PLAYER_ANIM_DURATION, player_anim_frames_right, Animation.PlayMode.LOOP);

        //Linke Animation Initialisierung
        player_left = new Texture("player_left.png");
        player_anim_frames_left = new Array<TextureRegion>(PLAYER_KEYFRAMES);
        for (int i = 0; i < PLAYER_KEYFRAMES; i++) {
            player_anim_frames_left.add(new TextureRegion(player_left, PLAYER_WIDTH * i, 0, PLAYER_WIDTH, PLAYER_HEIGHT));
        }
        player_animation_left = new Animation<TextureRegion>(PLAYER_ANIM_DURATION, player_anim_frames_left, Animation.PlayMode.LOOP_REVERSED);
    }

    /**
     * Lädt den Hintergrund für das Hauptmenü
     */
    private static void load_main_background() {
        //Hauptmenü background Animation mit automatischer String-Bildung falls weitere Keyframes dazu kommen (Der Name muss nur dem selben Schema folgen)
        main_background_textures = new Array<Texture>(MAIN_BACKGROUND_KEYFRAMES);
        for (int i = 1; i < MAIN_BACKGROUND_KEYFRAMES + 1; i++) {
            String path = "Schmiede_main_background_" + String.valueOf(i) + ".png";
            main_background_textures.add(new Texture(path));
        }
        main_background_keyframes = new Array<TextureRegion>(MAIN_BACKGROUND_KEYFRAMES);
        for (int i = 0; i < MAIN_BACKGROUND_KEYFRAMES; i++) {
            main_background_keyframes.add(new TextureRegion(main_background_textures.get(i), 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
        }
        main_background_animation = new Animation<TextureRegion>(MAIN_BACKGROUND_ANIM_DURATION, main_background_keyframes, Animation.PlayMode.LOOP_PINGPONG);
    }

    /**
     * Lädt den Hintergrund für den "Hilfe"-Bildschirm
     */
    private static void load_help_background() {
        //Hintergrund für den "Hilfe" Bildschirm
        help_screen = new Texture("Helpscreen.jpg");
        help_screen_region = new TextureRegion(help_screen, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt den Hintergrund für den "Pause"-Bildschirm
     */
    private static void load_pause_background() {
        //Hintergrund für den "Pause"-Bildschirm
        pause_screen = new Texture("pause_screen.png");
        pause_screen_region = new TextureRegion(pause_screen, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt den Hintergrund für das Anvil Minispiel
     */
    private static void load_anvil_background() {
        //Hintergrund für das Anvil Minispiel
        anvil_background = new Texture("Anvil_background.png");
        anvil_background_region = new TextureRegion(anvil_background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt die Buttons für das Anvil Minispiel
     */
    private static void load_anvil_button() {
        anvil_button = new Texture("Anvil_buttons.png");
        anvil_button_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(anvil_button, 0, ANVIL_BUTTON_HEIGHT * i, ANVIL_BUTTON_WIDTH, ANVIL_BUTTON_HEIGHT);
            anvil_button_states.add(new TextureRegionDrawable(region));
        }
    }

    /**
     * Lädt den Hintergrund für das Furnace Minispiel
     */
    private static void load_furnace_background() {
        furnace_background = new Texture("Furnace_background.png");
        furnace_background_region = new TextureRegion(furnace_background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }


    private static void load_furnace_button() {
        furnace_button = new Texture("Furnace_Button.png");
        furnace_button_states = new Array<TextureRegionDrawable>(2);

        TextureRegion region = new TextureRegion(furnace_button, 0, 0, 474, 438);
        furnace_button_states.add(new TextureRegionDrawable(region));

        region = new TextureRegion(furnace_button, 0, 438, 474, 438);
        furnace_button_states.add(new TextureRegionDrawable(region));

        region = new TextureRegion(furnace_button, 474, 0, 474, 438);
        furnace_button_states.add(new TextureRegionDrawable(region));
    }

    /**
     * Lädt die Bitmap fonts für labels
     */
    public static void load_fonts() {
        font_32 = new BitmapFont(Gdx.files.internal("VCR_32.fnt"));
        font_100 = new BitmapFont(Gdx.files.internal("VCR_100.fnt"));
    }

    //TEMPORÄR: Black background für ScoreScreen
    public static void load_score_background() {
        score_background = new Texture("pause_screen.png");
        score_background_region = new TextureRegion(score_background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }
}
