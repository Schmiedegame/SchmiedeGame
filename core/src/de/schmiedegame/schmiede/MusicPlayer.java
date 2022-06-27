package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Statische Klasse, die für das Abspielen der Musik verantwortlich ist.
 */
public class MusicPlayer {

    private static Music main_track;
    private static Music minigame_track;

    private static float main_track_position;


    /**
     * Initialisiert die verfügbaren Soundtracks. Als aktiver Soundtrack wird standardmäßig "main_track" ausgewählt.
     */
    public static void load() {
        main_track = Gdx.audio.newMusic(Gdx.files.internal("No Destination.mp3"));
        main_track.setVolume(0.5f);
        main_track.setLooping(true);

        minigame_track = Gdx.audio.newMusic(Gdx.files.internal("Run As Fast As You Can.mp3"));
        minigame_track.setVolume(0.5f);
        minigame_track.setLooping(true);


    }

    /**
     * Spielt den Standard Soundtrack ab.
     */
    public static void play_main_track() {
        main_track.play();
        main_track.setPosition(main_track_position);

        minigame_track.stop();
    }

    /**
     * Spielt den Soundtrack für die Minispiele ab.
     */
    public static void play_minigame_track() {
        minigame_track.play();

        save_main_track_position();
        main_track.stop();
    }

    /**
     * Speichert die momentane Position des Standard Soundtracks.
     */
    public static void save_main_track_position() {
        main_track_position = main_track.getPosition();
    }



}
