package de.schmiedegame.schmiede;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Klasse, die alle Bestandteile der Spielwelt enthält und verwaltet.
 */
public class World {

    //Spieler und Hintergrund (sowie weitere Objekte, die auf den Hintergrund projeziert werden)
    private Player player;
    TextureRegion background;

    public World() {
        player = new Player();
        background = Assets.game_background_region;
    }

    /**
     * Aktualisiert den Status, der dynamischen Objekte innerhalb der Spielwelt.
     * @param delta Vergangene Zeit, seit dem letzten Update.
     */
    public void update(float delta) {
        player.update(delta);
    }

    /**
     * Stellt die Spielwelt auf dem Bildschirm dar.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
        player.draw(batch);
    }

    public void Save() {
        player.Save();
    }

}
