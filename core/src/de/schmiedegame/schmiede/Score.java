package de.schmiedegame.schmiede;

public class Score {

    private static float raw_anvil_score;
    private static float processed_anvil_score;

    private static int raw_furnace_score;
    private static float processed_furnace_score;


    /**
     * Initialisiert alle Attribute der Score Klasse
     */
    public static void load() {
        raw_anvil_score = 0f;
        processed_anvil_score = 0f;

        raw_furnace_score = 0;
        processed_furnace_score = 0f;
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
        Print_anvil_score();
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
        Print_furnace_score();
    }

    public static void Print_anvil_score() {
        System.out.println(processed_anvil_score);
    }

    public static void Print_furnace_score() {
        System.out.println(processed_furnace_score);
    }
}
