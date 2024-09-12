package backend.academy.hangman.game.constants;

/**
 * This interface defines result codes used throughout the Hangman game.
 * These codes represent different states or outcomes of user input and guessing attempts.
 *
 * The result codes are used to handle various game scenarios and ensure consistent communication
 * of game state throughout the application.
 *
 *
 * @author zavik001
 * @version 1.0
 */
public interface ResultCodes {
    /**
     * Indicates that the input is invalid (e.g., not a letter).
     */
    int INVALID_INPUT = -2;

    /**
     * Indicates that the letter entered by the user has already been guessed.
     */
    int ALREADY_ENTERED = 1;

    /**
     * Indicates that the user's guess was correct.
     */
    int CORRECT_GUESS = 0;

    /**
     * Indicates that the user's guess was incorrect.
     */
    int WRONG_GUESS = -1;
}
