package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Statische Klasse, mit welcher auf gespeicherte Daten zugegriffen werden kann, bzw. diese überschrieben werden können.
 */
public class Save {

    public static String[] savedata;

    /**
     * Lädt den Inhalt der Speicherdatei in das String-Array "savedata".
     */
    public static void load() {
        //savedata = CSVReader.read(Gdx.files.internal("save.csv").toString());
        savedata = CSVReader.read("save.csv");
    }

    /**
     * Überschreibt die Speicherdatei mit dem String-Array "savedata".
     */
    public static void save() {
        System.out.println(savedata[0]);
        //CSVReader.write(Gdx.files.internal("save.csv").toString(), savedata);
        CSVReader.write("save.csv", savedata);
    }

    /**
     * Erstellt einen Positionsvektor aufgrund der gespeicherten Positionsdaten des Spielers
     * @return
     */
    public static Vector2 getSavedPlayerPosition() {
        return new Vector2(Float.valueOf(savedata[0]), Float.valueOf(savedata[1]));
    }

    /**
     * Überschreibt die gespeicherte Position des Spielers mit dem gegebenen Vektor
     * @param position Positionsvektor
     */
    public static void savePlayerPosition(Vector2 position) {
        savedata[0] = String.valueOf(position.x);
        savedata[1] = String.valueOf(position.y);
    }
}
