package de.schmiedegame.schmiede;

public class Gamestates {

    enum state {
        ACTIVE,
        INACTIVE,
        FINISHED,
    }

    enum prompt_state {
        BEGIN,
        FURNACE,
        CASTING,
        ANVIL,
        FINISH
    }
}
