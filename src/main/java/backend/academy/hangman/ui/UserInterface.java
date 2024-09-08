package backend.academy.hangman.ui;

import java.util.List;

/**
 * This interface defines the contract for interacting with the user in the Hangman game.
 * It provides methods for input, output, and clearing the user interface.
 *
 * Implementations of this interface will handle how data is presented to and received from the user.
 *
 * @author zavik001
 * @since 1.0
 */
public interface UserInterface {

    /**
     * Clears the user interface or console window.
     * This method should remove any previous output from the display.
     */
    void clearWindow();

    /**
     * Reads a word input from the user and processes it (e.g., trimming whitespace, converting to lower case).
     *
     * @return a cleaned and processed word input from the user.
     */
    String readWordInput();

    /**
     * Reads a numeric input from the user.
     * This method expects a valid number to be entered.
     *
     * @return the number input by the user.
     */
    int readNumberInput();

    /**
     * Reads a single letter input from the user and processes it.
     * This method typically converts the input to lower case for consistency.
     *
     * @return the letter input by the user, in lower case.
     */
    char readLetterInput();

    /**
     * Displays a message to the user.
     *
     * @param message the message to display, can be any character sequence.
     */
    void showMessage(CharSequence message);

    /**
     * Displays a list of word categories to the user.
     *
     * @param wordCategories the list of word categories to display.
     */
    void showListMessage(List<String> wordCategories);
}
