package de.schmiedegame.schmiede;

public class Score {

    private static float raw_anvil_score;
    private static float processed_anvil_score;


    public static void load() {
        raw_anvil_score = 0f;
    }

    /**
     * Diese Methode addiert roh die Punktzahl während des Anvil Minispiels und die Verarbeitungsmethode für die rohe Punktzahl wird aufgerufen.
     * @param reactionTime
     */
    public static void Add_anvil_score(float reactionTime) {
        raw_anvil_score += 1/reactionTime;
        Process_anvil_score();
    }

    /**
     * Verarbeitet die rohe Punktzahl des Anvil Minispiels.
     * Formel ist angepasst für volle Punktzahl bei 19 Schlägen mit 0.04 Sekunden Reaktionszeit
     */
    private static void Process_anvil_score() {
        processed_anvil_score = raw_anvil_score/54;
    }

    public static void Print_anvil_score() {
        System.out.println(processed_anvil_score);
    }
}
