package backend.academy.hangman.game.constants;

import java.util.Collections;
import java.util.List;

public interface HangmanStages {
    String HANGMAN_BODY_PART = "|        |\n";
    String HANGMAN_BASE = " \n____________________\n" + HANGMAN_BODY_PART;
    String HANGMAN_EMPTY = "|\n";
    String HANGMAN_HEAD = "|        O\n";
    String HANGMAN_LEFT_ARM = "|       /|\n";
    String HANGMAN_RIGHT_ARM = "|       /|\\\n";
    String HANGMAN_LEFT_LEG = "|       / \n";
    String HANGMAN_RIGHT_LEG = "|       / \\\n";
    String HANGMAN_GROUND = "|___________________";

    List<String> HANGMAN_STAGES = Collections.unmodifiableList(List.of(
        HANGMAN_BASE + HANGMAN_EMPTY + HANGMAN_EMPTY + HANGMAN_EMPTY + HANGMAN_GROUND,
        HANGMAN_BASE + HANGMAN_HEAD + HANGMAN_EMPTY + HANGMAN_EMPTY + HANGMAN_GROUND,
        HANGMAN_BASE + HANGMAN_HEAD + HANGMAN_BODY_PART + HANGMAN_EMPTY + HANGMAN_GROUND,
        HANGMAN_BASE + HANGMAN_HEAD + HANGMAN_BODY_PART + HANGMAN_BODY_PART + HANGMAN_GROUND,
        HANGMAN_BASE
                + HANGMAN_HEAD
                + HANGMAN_BODY_PART
                + HANGMAN_BODY_PART
                + HANGMAN_BODY_PART
                + HANGMAN_GROUND,
        HANGMAN_BASE
                + HANGMAN_HEAD
                + HANGMAN_LEFT_ARM
                + HANGMAN_BODY_PART
                + HANGMAN_BODY_PART
                + HANGMAN_GROUND,
        HANGMAN_BASE
                + HANGMAN_HEAD
                + HANGMAN_RIGHT_ARM
                + HANGMAN_BODY_PART
                + HANGMAN_BODY_PART
                + HANGMAN_GROUND,
        HANGMAN_BASE
                + HANGMAN_HEAD
                + HANGMAN_RIGHT_ARM
                + HANGMAN_BODY_PART
                + HANGMAN_LEFT_LEG
                + HANGMAN_GROUND,
        HANGMAN_BASE
                + HANGMAN_HEAD
                + HANGMAN_RIGHT_ARM
                + HANGMAN_BODY_PART
                + HANGMAN_RIGHT_LEG
                + HANGMAN_GROUND,
        HANGMAN_BASE
                + HANGMAN_HEAD
                + HANGMAN_RIGHT_ARM
                + HANGMAN_BODY_PART
                + HANGMAN_RIGHT_LEG
                + HANGMAN_LEFT_LEG
                + HANGMAN_GROUND
    ));
}
