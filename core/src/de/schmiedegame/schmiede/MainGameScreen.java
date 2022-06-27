package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Bildschirm für das eigentliche Spiel.
 * In einem Objekt dieser Klasse wird Hauptsächlich die Verwaltung des Overlays und der Bildschirmwechsel übernommen.
 * Die Logik und Grafik des Spiels wird in der Klasse World verarbeitet.
 */
public class MainGameScreen extends ScreenAdapter {

    private final float PAUSE_TIME = 0.5f;
    private final Color prompt_color = new Color(0.6f, 0f, 0f, 0.4f);


    private Gamestates.prompt_state prompt_state;
    //Main-Game(Smithinggame) Objekt, das bei jedem Screen zu übergeben ist (Siehe Smithinggame.java Kommentare)
    private Smithinggame game;
    //World, die alles was mit dem Spiel zu tun hat, enthält
    private World world;

    //Begrenzungen der Interaktionsbereiche
    private int[][][] interaction_bounds;
    //Parameter für die rect() Methode des ShapeDrawer um den Interaktionsbereich anzuzeigen
    private int[] prompt_rect_parameters;


    ShapeRenderer shapeRenderer;


    //Timer, um zu verhindern, dass der Bildschirm sofort wieder auf Pause wechselt, wenn man zurückkehrt, da der Benutzer die Escape-Taste nicht nach einem Frame wieder loslässt.
    private float pauseTimer;


    /**
     * Konstruktor für den Bildschirm des eigentlichen Spiels.
     * @param game Main-Game Objekt
     */
    public MainGameScreen(Smithinggame game) {

        prompt_rect_parameters = new int[4];

        //Mit der Matrix etwas kompliziert gelöst, wenn noch Zeit bleibt überarbeiten.
        Init_Interaction_Bounds();

        //Wird auf den Finish state gesetzt und dann weiter geschaltet, damit promptRectParameters geupdatet wird.
        prompt_state = Gamestates.prompt_state.FINISH;
        change_state();

        this.game = game;
        world = new World();

        pauseTimer = PAUSE_TIME;

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(prompt_color);
    }

    /**
     * Methode, die jeden Frame aufgerufen wird.
     * @param delta Vergangene Zeit seit letztem Frame
     */
    @Override
    public void render(float delta) {

        //Die Spielwelt wird aktualisiert und dargestellt.
        world.update(delta);
        world.draw_world(game.batch);

        gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(prompt_rect_parameters[0], prompt_rect_parameters[1], prompt_rect_parameters[2], prompt_rect_parameters[3]);
        shapeRenderer.end();

        world.draw_player(game.batch);

        //Überprüfung der Escape-Taste zum Aufrufen des Pause-Bildschirms mit Überprüfung des Timers.
        if (pauseTimer > 0) {
            pauseTimer -= delta;
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                game.setScreen(new PauseScreen(game, this));
                hide();
            }
        }

        //Überprüfung der Interaktion mit den Minispielen mithilfe des prompt_state
        update_interaction();

    }

    /**
     * Methode, die aufgerufen wird, wenn das Objekt der aktuelle screen des Main-Game Objekts wird.
     */
    @Override
    public void show() {
        pauseTimer = PAUSE_TIME;

        //Musik wird gewechselt
        MusicPlayer.play_main_track();
    }

    @Override
    public void hide() {
        world.Save();
    }




    /**
     * Methode, die überprüft, ob der Spieler sich in einem Interaktionsbereich befindet und ob er versucht, mit diesem zu interagieren.
     */
    private void update_interaction() {

        int player_x = (int)world.getPlayer().getPosition().x;
        int player_y = (int)world.getPlayer().getPosition().y;

        boolean xPressed = Gdx.input.isKeyPressed(Input.Keys.X);

        boolean player_in_x;
        boolean player_in_y;

        switch (prompt_state) {

            case BEGIN:
                player_in_x = player_x > interaction_bounds[0][0][0] && player_x < interaction_bounds[0][0][1];
                player_in_y = player_y > interaction_bounds[0][1][0] && player_y < interaction_bounds[0][1][1];

                if(player_in_x && player_in_y) {
                    if (xPressed) {
                        change_state();
                    }
                }

                break;

            case FURNACE:
                player_in_x = player_x > interaction_bounds[1][0][0] && player_x < interaction_bounds[1][0][1];
                player_in_y = player_y > interaction_bounds[1][1][0] && player_y < interaction_bounds[1][1][1];

                if(player_in_x && player_in_y) {
                    if (xPressed) {
                        change_state();
                        game.setScreen(new FurnaceGameScreen(game, this));
                    }
                }

                break;

            case CASTING:
                player_in_x = player_x > interaction_bounds[2][0][0] && player_x < interaction_bounds[2][0][1];
                player_in_y = player_y > interaction_bounds[2][1][0] && player_y < interaction_bounds[2][1][1];

                if(player_in_x && player_in_y) {
                    if (xPressed) {
                        change_state();
                        game.setScreen(new CastingGameScreen(game, this));
                    }
                }

                break;

            case ANVIL:
                player_in_x = player_x > interaction_bounds[3][0][0] && player_x < interaction_bounds[3][0][1];
                player_in_y = player_y > interaction_bounds[3][1][0] && player_y < interaction_bounds[3][1][1];

                if(player_in_x && player_in_y) {
                    if (xPressed) {
                        change_state();
                        game.setScreen(new AnvilGameScreen(game, this));
                    }
                }

                break;

            case FINISH:
                player_in_x = player_x > interaction_bounds[4][0][0] && player_x < interaction_bounds[4][0][1];
                player_in_y = player_y > interaction_bounds[4][1][0] && player_y < interaction_bounds[4][1][1];

                if(player_in_x && player_in_y) {
                    if (xPressed) {
                        change_state();
                        game.setScreen(new ScoreScreen(game, this));
                    }
                }

                break;
        }
    }

    /**
     * Methode, die den prompt_state weiter schaltet und die Anzeige der Interaktionsbereiche aktualisiert.
     */
    private void change_state() {

        switch (prompt_state) {
            case BEGIN:
                prompt_rect_parameters[0] = interaction_bounds[1][0][0];
                prompt_rect_parameters[1] = interaction_bounds[1][1][0];
                prompt_rect_parameters[2] = 150;
                prompt_rect_parameters[3] = 75;

                prompt_state = Gamestates.prompt_state.FURNACE;
                break;

            case FURNACE:
                prompt_rect_parameters[0] = interaction_bounds[2][0][0];
                prompt_rect_parameters[1] = interaction_bounds[2][1][0];
                prompt_rect_parameters[2] = 150;
                prompt_rect_parameters[3] = 75;

                prompt_state = Gamestates.prompt_state.CASTING;
                break;

            case CASTING:
                prompt_rect_parameters[0] = interaction_bounds[3][0][0];
                prompt_rect_parameters[1] = interaction_bounds[3][1][0];
                prompt_rect_parameters[2] = 150;
                prompt_rect_parameters[3] = 75;

                prompt_state = Gamestates.prompt_state.ANVIL;
                break;

            case ANVIL:
                prompt_rect_parameters[0] = interaction_bounds[4][0][0];
                prompt_rect_parameters[1] = interaction_bounds[4][1][0];
                prompt_rect_parameters[2] = 75;
                prompt_rect_parameters[3] = 150;

                prompt_state = Gamestates.prompt_state.FINISH;
                break;

            case FINISH:
                prompt_rect_parameters[0] = interaction_bounds[0][0][0];
                prompt_rect_parameters[1] = interaction_bounds[0][1][0];
                prompt_rect_parameters[2] = 75;
                prompt_rect_parameters[3] = 150;

                prompt_state = Gamestates.prompt_state.BEGIN;
                break;
        }
    }




    private void Init_Interaction_Bounds() {
        //Alle Interaktionsbereiche sind hier definiert
        //Index Legende:
        //Index 1: Station (0-4)
        //Index 2: X oder Y (0-1)
        //Index 3: Lower oder Upper (0-1)
        interaction_bounds = new int[5][2][2];

        //Tresen
        interaction_bounds[0][0][0] = 134;
        interaction_bounds[0][0][1] = 209;
        interaction_bounds[0][1][0] = 207;
        interaction_bounds[0][1][1] = 357;

        //Furnace
        interaction_bounds[1][0][0] = 225;
        interaction_bounds[1][0][1] = 375;
        interaction_bounds[1][1][0] = 425;
        interaction_bounds[1][1][1] = 500;

        //Casting
        interaction_bounds[2][0][0] = 428;
        interaction_bounds[2][0][1] = 578;
        interaction_bounds[2][1][0] = 425;
        interaction_bounds[2][1][1] = 500;

        //Anvil
        interaction_bounds[3][0][0] = 625;
        interaction_bounds[3][0][1] = 775;
        interaction_bounds[3][1][0] = 425;
        interaction_bounds[3][1][1] = 500;

        //Abgabe
        interaction_bounds[4][0][0] = 791;
        interaction_bounds[4][0][1] = 866;
        interaction_bounds[4][1][0] = 207;
        interaction_bounds[4][1][1] = 357;
    }
}
