package de.schmiedegame.schmiede;

public class Score {

    private static float raw_anvil_score;
    private static float processed_anvil_score;

    private static float raw_casting_score;
    private static float processed_casting_score;

    private static int raw_furnace_score;
    private static float processed_furnace_score;


    private static int furnace_avg;
    private static int casting_avg;
    private static int anvil_avg;

    private static int times_played;


    /**
     * Initialisiert alle Attribute der Score Klasse
     */
    public static void load() {
        raw_anvil_score = 0f;
        processed_anvil_score = 0f;

        raw_casting_score = 0f;
        processed_casting_score = 0f;

        raw_furnace_score = 0;
        processed_furnace_score = 0f;


        furnace_avg = Save.getFurnaceAvg();
        casting_avg = Save.getCastingAvg();
        anvil_avg = Save.getAnvilAvg();

        times_played = Save.getTimesPlayed();
    }



    /**
     * Addiert die rohe Punktzahl des Furnace Minispiels und ruft Verarbeitungsmethode auf.
     * @param clicks Anzahl an Klicks
     */
    public static void Add_furnace_score(int clicks) {
        raw_furnace_score += clicks;
        Process_furnace_score();
    }

    /**
     * Verarbeitet die rohe Punktzahl des Furnace Minispiels zu einer Bewertung mittels Prozent.
     * Volle Punktzahl würde man hier durch 75 Klicks in 10 Sekunden erreichen.
     */
    private static void Process_furnace_score() {
        processed_furnace_score = ((float)raw_furnace_score)/75f;

        if (processed_furnace_score > 1) {
            processed_furnace_score = 1;
        }
    }


    /**
     * Addiert die rohe Punktzahl des Casting Minispiels und ruft Verarbeitungsmethode auf.
     * @param time Benötigte Zeit um das Minispiel zu beenden
     */
    public static void Add_casting_score(float time) {
        raw_casting_score += time;
        Process_casting_score();
    }

    /**
     * Verarbeitet die rohe Punktzahl des Casting Minispiels zu einer Bewertung mittels Prozent.
     * Volle Punktzahl würde man hier durch Beenden des Spiels in 12 Sekunden erreichen.
     */
    private static void Process_casting_score() {
        processed_casting_score = 1/raw_casting_score * 12;

        if (processed_casting_score > 1) {
            processed_casting_score = 1;
        }
    }


    /**
     * Addiert die rohe Punktzahl während des Anvil Minispiels und ruft Verarbeitungsmethode auf.
     * @param reactionTime Vergangene Zeit bis eine Reaktion stattfand
     */
    public static void Add_anvil_score(float reactionTime) {
        raw_anvil_score += 1/reactionTime;
        Process_anvil_score();
    }

    /**
     * Verarbeitet die rohe Punktzahl des Anvil Minispiels.
     * Formel ist angepasst für volle Punktzahl bei 19 Schlägen mit 0.4 Sekunden Reaktionszeit
     */
    private static void Process_anvil_score() {
        processed_anvil_score = raw_anvil_score/54;

        if (processed_anvil_score > 1) {
            processed_anvil_score = 1;
        }
    }

    /**
     * Gibt feedback bezüglich der Reaktionszeit.
     * @param reactionTime Vergangene Zeit bis eine Reaktion stattfand
     */
    protected static String Anvil_feedback(float reactionTime) {
        float a = (1/reactionTime) / 2.5f;
        if(a < 0.5f){
            return "SCHWACH!";
        }
        else if (a < 0.85f) {
            return "GUT!";
        }
        else{
            return "PERFEKT!";
        }
    }

    /**
     * Setzt alle temporären Werte zurück.
     */
    public static void reset() {
        raw_furnace_score = 0;
        raw_casting_score = 0;
        raw_anvil_score = 0;
        processed_furnace_score = 0;
        processed_casting_score = 0;
        processed_anvil_score = 0;
    }

    /**
     * Soll aufgerufen werden, wenn das Spiel einmal durchlaufen wurde. Durchschnittliche Punktestände werden berechnet und abgespeichert.
     */
    public static void iterate() {
        furnace_avg = ((furnace_avg * times_played) + getFurnaceScore()) / (times_played + 1);
        casting_avg = ((casting_avg * times_played) + getCastingScore()) / (times_played + 1);
        anvil_avg = ((anvil_avg * times_played) + getAnvilScore()) / (times_played + 1);

        times_played += 1;

        Save.saveFurnaceAvg(furnace_avg);
        Save.saveCastingAvg(anvil_avg);
        Save.saveAnvilAvg(anvil_avg);

        Save.saveTimesPlayed(times_played);
    }


    public static int getFurnaceScore() {
        return (int)(processed_furnace_score * 100);
    }

    public static int getCastingScore() {
        return (int)(processed_casting_score * 100);
    }

    public static int getAnvilScore() {
        return (int)(processed_anvil_score * 100);
    }


    public static void Print_anvil_score() {
        System.out.println(processed_anvil_score);
    }

    public static void Print_furnace_score() {
        System.out.println(processed_furnace_score);
    }

    public static void Print_casting_score() {
        System.out.println(processed_casting_score);
    }
}
