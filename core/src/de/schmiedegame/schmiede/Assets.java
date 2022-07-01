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




    public static TextureRegion game_background_region;

    public static Array<TextureRegionDrawable> play_button_states;
    public static Array<TextureRegionDrawable> help_button_states;
    public static Array<TextureRegionDrawable> leave_button_states;
    public static Array<TextureRegionDrawable> leave_button_small_states;

    public static Array<TextureRegion> player_anim_frames_right;
    public static Array<TextureRegion> player_anim_frames_left;
    public static Animation<TextureRegion> player_animation_right;
    public static Animation<TextureRegion> player_animation_left;

    public static Array<TextureRegion> main_background_keyframes;
    public static Animation<TextureRegion> main_background_animation;

    public static TextureRegion help_screen_region;

    public static TextureRegion pause_screen_region;

    public static TextureRegion anvil_background_region;

    public static Array<TextureRegionDrawable> anvil_button_states;

    public static TextureRegion furnace_background_region;

    public static Array<TextureRegionDrawable> furnace_button_states;


    public static Array<TextureRegion> casting_background_keyframes;
    public static Animation<TextureRegion> casting_background_animation;

    public static TextureRegion casting_background_finished;

    public static TextureRegion score_background_region;

    public static TextureRegion promptX;


    public static BitmapFont font_32;
    public static BitmapFont font_100;


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
        load_casting_background();
        load_score_background();
        load_promptx();
        load_fonts();
    }


    /**
     * Lädt die Assets für den Hintergrund des main games
     */
    private static void load_background() {
        //--------------
        Texture game_background = new Texture("background.jpg");
        //Koordinaten werden hier von oben links gezählt
        game_background_region = new TextureRegion(game_background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt alle Button assets
     */
    private static void load_buttons() {
        //Button Assets Initialisierung
        //Button hat 3 Zustände, also 3 TextureRegions
        Texture play_button = new Texture("Spielen.png");
        play_button_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(play_button, 0, BUTTON_HEIGHT * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            play_button_states.add(new TextureRegionDrawable(region));
        }

        Texture help_button = new Texture("Hilfe.png");
        help_button_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(help_button, 0, BUTTON_HEIGHT * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            help_button_states.add(new TextureRegionDrawable(region));
        }

        Texture leave_button = new Texture("Verlassen.png");
        leave_button_states = new Array<TextureRegionDrawable>(BUTTON_STATES);
        for (int i = 0; i < BUTTON_STATES; i++) {
            TextureRegion region = new TextureRegion(leave_button, 0, BUTTON_HEIGHT * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            leave_button_states.add(new TextureRegionDrawable(region));
        }

        Texture leave_button_small = new Texture("Verlassen_small.png");
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
        //2 verschiedene Animationen die beide ein Array von TextureRegions brauchen
        Texture player_right = new Texture("player_right.png");
        player_anim_frames_right = new Array<TextureRegion>(PLAYER_KEYFRAMES);
        for (int i = 0; i < PLAYER_KEYFRAMES; i++) {
            player_anim_frames_right.add(new TextureRegion(player_right, PLAYER_WIDTH * i, 0, PLAYER_WIDTH, PLAYER_HEIGHT));
        }
        player_animation_right = new Animation<TextureRegion>(PLAYER_ANIM_DURATION, player_anim_frames_right, Animation.PlayMode.LOOP);

        //Linke Animation Initialisierung
        Texture player_left = new Texture("player_left.png");
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
        //Animierter Hintergrund für das Hauptmenü
        Array<Texture> main_background_textures = new Array<Texture>(MAIN_BACKGROUND_KEYFRAMES);
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
        Texture help_screen = new Texture("Helpscreen.jpg");
        help_screen_region = new TextureRegion(help_screen, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt den Hintergrund für den "Pause"-Bildschirm
     */
    private static void load_pause_background() {
        //Hintergrund für den "Pause"-Bildschirm
        Texture pause_screen = new Texture("pause_screen.png");
        pause_screen_region = new TextureRegion(pause_screen, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt den Hintergrund für das Anvil Minispiel
     */
    private static void load_anvil_background() {
        //Hintergrund für das Anvil Minispiel
        Texture anvil_background = new Texture("Anvil_background.png");
        anvil_background_region = new TextureRegion(anvil_background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    /**
     * Lädt die Buttons für das Anvil Minispiel
     */
    private static void load_anvil_button() {
        //Buttons für das Anvil Minispiel
        Texture anvil_button = new Texture("Anvil_buttons.png");
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
        //Hintergrund für das Furnace Minispiel
        Texture furnace_background = new Texture("Furnace_background.png");
        furnace_background_region = new TextureRegion(furnace_background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }


    private static void load_furnace_button() {
        Texture furnace_button = new Texture("Furnace_Button.png");
        furnace_button_states = new Array<TextureRegionDrawable>(2);

        TextureRegion region = new TextureRegion(furnace_button, 0, 0, 474, 438);
        furnace_button_states.add(new TextureRegionDrawable(region));

        region = new TextureRegion(furnace_button, 0, 438, 474, 438);
        furnace_button_states.add(new TextureRegionDrawable(region));

        region = new TextureRegion(furnace_button, 474, 0, 474, 438);
        furnace_button_states.add(new TextureRegionDrawable(region));
    }

    /**
     * Lädt die Animation, sowie den Endbildschirm für das Casting Spiel.
     */
    private static void load_casting_background() {
        Array<Texture> casting_background_textures = new Array<Texture>(5);
        for (int i = 1; i < 5+1; i++) {
            String path = "Casting_background_" + String.valueOf(i) + ".png";
            casting_background_textures.add(new Texture(path));
        }
        casting_background_keyframes = new Array<TextureRegion>(MAIN_BACKGROUND_KEYFRAMES);
        for (int i = 0; i < 5; i++) {
            casting_background_keyframes.add(new TextureRegion(casting_background_textures.get(i), 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
        }
        casting_background_animation = new Animation<TextureRegion>(0.2f, casting_background_keyframes, Animation.PlayMode.NORMAL);


        Texture casting_background_finished_texture = new Texture("Casting_background_finished.png");
        casting_background_finished = new TextureRegion(casting_background_finished_texture, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }


    private static void load_score_background() {
        Texture score_background_texture = new Texture("score_background.png");
        score_background_region = new TextureRegion(score_background_texture, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    private static void load_promptx() {
        Texture promptX_texture = new Texture("PromptX.png");
        promptX = new TextureRegion(promptX_texture, 0, 0, 100, 100);
    }


    /**
     * Lädt die Bitmap fonts für labels
     */
    public static void load_fonts() {
        font_32 = new BitmapFont(Gdx.files.internal("VCR_32.fnt"));
        font_100 = new BitmapFont(Gdx.files.internal("VCR_100.fnt"));
    }


}
