package backend.academy.hangman.game.constants;

public interface GameSettings {
    int MAX_ATTEMPTS = HangmanStages.HANGMAN_STAGES.size();
    int HINT_DISPLAY_THRESHOLD = MAX_ATTEMPTS / 2 + 1;
    String DICTIONARY_FILE_PATH = "src/main/java/backend/academy/hangman/data/dictionary.json";
}
