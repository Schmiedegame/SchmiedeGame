package de.schmiedegame.schmiede;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.gl;

public class World {

    private Player player;
    TextureRegion background;

    public World() {
        player = new Player(500, 300);
        background = Assets.background_region;
    }

    public void update(float delta) {
        player.update(delta);
    }

    public void draw(SpriteBatch batch) {
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
        player.draw(batch);
    }

}
