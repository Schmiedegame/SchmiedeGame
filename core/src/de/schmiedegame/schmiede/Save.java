package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Statische Klasse, mit welcher auf gespeicherte Daten zugegriffen werden kann, bzw. diese überschrieben werden können.
 */
public class Save {

    private static String[] savedata;

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



    public static int getFurnaceAvg() {
        return Integer.valueOf(savedata[2]);
    }

    public static void saveFurnaceAvg(int score) {
        savedata[2] = String.valueOf(score);
    }

    public static int getCastingAvg() {
        return Integer.valueOf(savedata[3]);
    }

    public static void saveCastingAvg(int score) {
        savedata[3] = String.valueOf(score);
    }

    public static int getAnvilAvg() {
        return Integer.valueOf(savedata[4]);
    }

    public static void saveAnvilAvg(int score) {
        savedata[4] = String.valueOf(score);
    }

    public static int getTimesPlayed() {
        return Integer.valueOf(savedata[5]);
    }

    public static void saveTimesPlayed(int num) {
        savedata[5] = String.valueOf(num);
    }
}
