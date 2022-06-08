package de.schmiedegame.schmiede;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Logik-Klasse des Anvil Minispiels
 */
public class AnvilGame {

    //Konstanten
    private final float TIMER_DURATION = 0.5f;
    private final int BUTTON_NUM = 5;
    private final int ITERATIONS = 20;

    //Timer und Counter
    private float markTimer;
    private int iterationsLeft;

    //Logikvariablen
    private boolean[] activeButtons;
    private float[] reactionTime;

    Random random;


    public AnvilGame() {
        markTimer = TIMER_DURATION;
        random = new Random();

        iterationsLeft = ITERATIONS;

        activeButtons = new boolean[BUTTON_NUM];
        reactionTime = new float[BUTTON_NUM];
    }

    /**
     * Aktualisiert Markierung von Buttons und überprüft den Input.
     * Ein Button wird nur markiert, falls nicht schon alle Iterationen erledigt sind.
     * @param delta Seit letztem update vergangene Zeit
     * @param buttons Das Array, das die Buttons enthält
     */
    public void update(float delta, Array<ImageButton> buttons) {

        if (iterationsLeft > 0) {
            markTimer -= delta;
            if (markTimer <= 0) {
                mark_button(buttons);
                iterationsLeft -= 1;
            }
        }

        check_button_input(delta, buttons);
    }

    /**
     * Ein zufälliger Button aus dem Array wird markiert und der Timer für das Markieren zurückgesetzt.
     * @param buttons Das Array, das die Buttons enthält
     */
    private void mark_button(Array<ImageButton> buttons) {
        //Generieren einer random Zahl zwischen -4 und 4 um einen random index des button arrays zu modifizieren
        int rand = random.nextInt() % 5;
        //Sicherstellen, dass kein negativer Index ausgewählt wird
        if (rand < 0) {
            rand = rand * -1;
        }

        //System ist nicht perfekt, da wenn der random ausgewählte Button bereits markiert ist, einfach nichts passiert, aber kann der Einfachheit halber so bleiben
        if (!buttons.get(rand).isChecked())
        {
            buttons.get(rand).setChecked(true);
            buttons.get(rand).setDisabled(false);
            activeButtons[rand] = true;
        }

        markTimer = TIMER_DURATION;

    }

    /**
     * Überprüft den Input aller buttons.
     * Nur buttons werden überprüft die im array "activeButtons" als aktiv markiert sind.
     * Falls ja, wird überprüft, ob der Benutzer den Knopf schon wieder deaktiviert hat, also reagiert hat. In diesem Fall wird er wieder deaktiviert um manuelles markieren zu unterbinden und der Button wird inaktiv gesetzt und die gezählte Reaktionszeit an Score übermittelt und auf 0 zurückggesetzt.
     * Andernfalls wird die Reaktionszeit weiter gezählt
     * @param delta Seit letztem update vergangene Zeit
     * @param buttons Das Array, das die Buttons enthält
     */
    private void check_button_input(float delta, Array<ImageButton> buttons) {

        for (int i = 0; i < BUTTON_NUM; i++) {
            //Überprüfung ob der Button an der Stelle i zu prüfen ist
            if (activeButtons[i]) {
                //Überprüfung, ob der Button vom Spieler schon gedrückt wurde
                if (!buttons.get(i).isChecked()) {

                    buttons.get(i).setDisabled(true);
                    activeButtons[i] = false;
                    Score.Add_anvil_score(reactionTime[i]);
                    //Score.Print_anvil_score();
                    reactionTime[i] = 0f;
                }
                else {
                    reactionTime[i] += delta;
                }
            }
        }

    }


    /**
     * Aktualisiert den gamestate auf FINISHED falls das Minispiel abgeschlossen sein sollte
     * @return Neuer gamesetate
     */
    public Gamestates.state update_gamestate() {

        if (iterationsLeft <= 0) {
            return Gamestates.state.FINISHED;
        }
        else {
            return Gamestates.state.ACTIVE;
        }
    }

}
