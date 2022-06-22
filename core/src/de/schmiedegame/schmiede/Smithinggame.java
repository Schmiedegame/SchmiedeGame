package de.schmiedegame.schmiede;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Hauptklasse des Spiels.
 * Hier werden die nötigen static Variablen initialisiert und das Hauptmenü aufgerufen.
 */
public class Smithinggame extends Game {

    //SpriteBatch die das gesamte Spiel über benutzt wird
    public SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        Assets.load();
        Score.load();
        //Beachten, dass bei setScreen immer das Smithinggame Objekt übergeben werden muss um Zugriff auf den batch zu erhalten und um Screens zu wechseln
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

}