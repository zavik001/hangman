package backend.academy.hangman.game.constants;

public final class Messages {
    public static final String MESSAGE_LOSS = "Game Over. You Lost!";
    public static final String MESSAGE_HINT = "Hint: ";
    public static final String MESSAGE_WIN = "Congratulations, You Won!";
    public static final String MESSAGE_COLON = ": ";
    public static final String PROMPT_GUESS_WORD = "Guess the word: ";
    public static final String MESSAGE_GREETING = "Hello, welcome to the Hangman game!";
    public static final String PROMPT_CATEGORY_SELECTION =
            "Please enter the category of words you want to guess: ";
    public static final String PROMPT_DIFFICULTY_SELECTION = "Select difficulty level: ";
    public static final String MESSAGE_REMAINING_ATTEMPTS = "Remaining attempts: ";
    public static final String MESSA_WAIT = "Waiting for user input: ";

    private Messages() {
        // Prevent instantiation
    }
}
