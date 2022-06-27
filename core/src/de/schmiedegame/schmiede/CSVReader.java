package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;

import java.io.*;

/**
 * Werkzeug um CSV Dateien zu lesen oder zu schreiben.
 */
public class CSVReader {

    private static final String delimiter = ";";

    /**
     * Liest die CSV Datei am gegebenen Ort aus und gibt sie in Form eines String-Arrays zurück.
     * @param path Dateipfad
     * @return Ausgelesene Datei
     */
    public static String[] read(String path) {
        try {

        File file = new File(path);
        FileReader fr = new FileReader(file);

        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] tempArr = new String[1];
        while ((line = br.readLine()) != null) {
            tempArr = line.split(delimiter);
        }

        return tempArr;

        } catch (IOException io){
            System.out.println("Error");
            return null;
        }
    }

    /**
     * Schreibt das gegebene String-Array in Form einer CSV Datei am gegebenen Ort. Inhalt vorhandener Datei wird dabei erst gelöscht.
     * @param path Dateipfad
     * @param data Zu schreibendes String-Array
     */
    public static void write(String path, String[] data) {
        try {
            File file = new File(path);
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < data.length; i++) {

                writer.write(data[i]);
                writer.write(";");

            }

            writer.close();
        }
        catch (IOException io) {
            System.out.println("Error");
        }
    }
}