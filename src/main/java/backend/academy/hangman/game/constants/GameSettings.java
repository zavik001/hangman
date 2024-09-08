package backend.academy.hangman.game.constants;

public final class GameSettings {
    public static final int MAX_ATTEMPTS = HangmanStages.HANGMAN_STAGES.size();
    public static final int HINT_DISPLAY_THRESHOLD = MAX_ATTEMPTS / 2 + 1;
    public static final String DICTIONARY_FILE_PATH =
            "src/main/java/backend/academy/hangman/data/dictionary.json";

    private GameSettings() {
        // Prevent instantiation
    }
}
