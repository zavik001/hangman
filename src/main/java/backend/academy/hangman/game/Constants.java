
package backend.academy.hangman.game;

public final class Constants {
    public static final int MAX_ATTEMPTS = 10; // MAX_ATTEMPTS <= 10
    public static final int TOTAL_CATEGORIES = 5; 
    public static final int TOTAL_DIFFICULTY_LEVELS = 3;
    public static final int WORDS_PER_LEVEL = 5;

    public static final String MESSAGE_LOSS = "Game Over. You Lost!";
    public static final String MESSAGE_HINT = "Hint: ";
    public static final String MESSAGE_WIN = "Congratulations, You Won!";
    public static final String MESSAGE_COLON = ": ";
    public static final String PROMPT_GUESS_WORD = "Guess the word: ";
    public static final String MESSAGE_GREETING = "Hello, welcome to the Hangman game!";
    public static final String PROMPT_CATEGORY_SELECTION = "Please enter the category of words you want to guess:";
    public static final String PROMPT_DIFFICULTY_SELECTION = "Select difficulty level (1-3)";
    public static final String MESSAGE_REMAINING_ATTEMPTS = "Remaining attempts: ";    

    public static String PATH_TO_DICTIONARY = "src/main/java/backend/academy/hangman/dictionary/";
    public static final String[] WORD_CATEGORIES = { "animals", "countries", "fruits", "movies", "sport" };
    public static final String[] HANGMAN_STAGES = {
            " ___________________\n" +
                    "|        |\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|        \n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|        |\n" +
                    "|        \n" +
                    "|\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|        |\n" +
                    "|        |\n" +
                    "|        \n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|        |\n" +
                    "|        |\n" +
                    "|        |\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|       /|\n" +
                    "|        |\n" +
                    "|        |\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|       /|\\\n" +
                    "|        |\n" +
                    "|        |\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|       /|\\\n" +
                    "|        |\n" +
                    "|       /|\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|       /|\\\n" +
                    "|        |\n" +
                    "|       /|\\\n" +
                    "|\n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|       /|\\\n" +
                    "|        |\n" +
                    "|       /|\\\n" +
                    "|      /   \n" +
                    "|___________________",

            " ___________________\n" +
                    "|        |\n" +
                    "|        O\n" +
                    "|       /|\\\n" +
                    "|        |\n" +
                    "|       /|\\\n" +
                    "|      /   \\\n" +
                    "|___________________"
    };

    private Constants() {
        // Prevent instantiation
    }
}