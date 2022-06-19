package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;

/**
 * Bildschirm für das eigentliche Spiel.
 * In einem Objekt dieser Klasse wird Hauptsächlich die Verwaltung des Overlays und der Bildschirmwechsel übernommen.
 * Die Logik und Grafik des Spiels wird in der Klasse World verarbeitet.
 */
public class MainGameScreen extends ScreenAdapter {

    private final float PAUSE_TIME = 0.5f;

    //Main-Game(Smithinggame) Objekt, das bei jedem Screen zu übergeben ist (Siehe Smithinggame.java Kommentare)
    private Smithinggame game;

    //World, die alles was mit dem Spiel zu tun hat, enthält
    private World world;

    //Timer, um zu verhindern, dass der Bildschirm sofort wieder auf Pause wechselt, wenn man zurückkehrt, da der Benutzer die Escape-Taste nicht nach einem Frame wieder loslässt.
    private float pauseTimer;


    /**
     * Konstruktor für den Bildschirm des eigentlichen Spiels.
     * @param game Main-Game Objekt
     */
    public MainGameScreen(Smithinggame game) {
        this.game = game;
        world = new World();

        pauseTimer = PAUSE_TIME;

    }

    /**
     * Methode, die jeden Frame aufgerufen wird.
     * @param delta Vergangene Zeit seit letztem Frame
     */
    @Override
    public void render(float delta) {

        //Die Spielwelt wird aktualisiert und dargestellt.
        world.update(delta);
        world.draw(game.batch);

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

        //***TEMPORÄR***
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            game.setScreen(new AnvilGameScreen(game, this));
            hide();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            game.setScreen(new FurnaceGameScreen(game, this));
            hide();
        }
        //***TEMPORÄR***

    }

    /**
     * Methode, die aufgerufen wird, wenn das Objekt wieder der aktuelle screen des Main-Game Objekts wird.
     */
    @Override
    public void show() {
        pauseTimer = PAUSE_TIME;
    }
}
