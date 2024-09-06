package backend.academy.hangman.game;

import java.util.List;

public final class Constants {
    public static final int MAX_ATTEMPTS = 10;
    public static final int TOTAL_CATEGORIES = 5;
    public static final int TOTAL_DIFFICULTY_LEVELS = 3;
    public static final int WORDS_PER_LEVEL = 5;

    public static final String MESSAGE_LOSS = "Game Over. You Lost!";
    public static final String MESSAGE_HINT = "Hint: ";
    public static final String MESSAGE_WIN = "Congratulations, You Won!";
    public static final String MESSAGE_COLON = ": ";
    public static final String PROMPT_GUESS_WORD = "Guess the word: ";
    public static final String MESSAGE_GREETING = "Hello, welcome to the Hangman game!";
    public static final String PROMPT_CATEGORY_SELECTION =
            "Please enter the category of words you want to guess:";
    public static final String PROMPT_DIFFICULTY_SELECTION = "Select difficulty level (1-3)";
    public static final String MESSAGE_REMAINING_ATTEMPTS = "Remaining attempts: ";
    public static final List<String> WORD_CATEGORIES = List.of(
            "animals", "countries", "fruits", "movies", "sport"
    );

    public static final String PATH_TO_DICTIONARY =
            "src/main/java/backend/academy/hangman/dictionary/";

    public static final String HANGMAN_BODY_PART = "|        |\n";

    public static final String HANGMAN_BASE = " \n____________________\n" + HANGMAN_BODY_PART;
    public static final String HANGMAN_EMPTY = "|\n";
    public static final String HANGMAN_HEAD = "|        O\n";
    public static final String HANGMAN_LEFT_ARM = "|       /|\n";
    public static final String HANGMAN_RIGHT_ARM = "|       /|\\\n";
    public static final String HANGMAN_LEFT_LEG = "|       / \n";
    public static final String HANGMAN_RIGHT_LEG = "|       / \\\n";
    public static final String HANGMAN_GROUND = "|___________________";

    static final String[] HANGMAN_STAGES = {
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
                + HANGMAN_GROUND,
    };

    public static final String INVALID_CATEGORY = "invalid";
    public static final String NO_HINT_AVAILABLE =
            "No hint available, please enter invalid and try again";
    public static final String INVALID_DIFFICULTY =
            "Invalid difficulty level, please enter invalid and try again";
    public static final String NO_WORDS_AVAILABLE =
            "No words available for this level, please enter invalid and try again";

    private Constants() {
        // Prevent instantiation
    }
}
