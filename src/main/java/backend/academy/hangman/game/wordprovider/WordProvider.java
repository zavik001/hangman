package backend.academy.hangman.game.wordprovider;

import java.util.List;

/**
 * This interface defines the contract for providing words and related information in the Hangman game.
 * Implementations of this interface will handle retrieving categories, difficulties, and random words.
 *
 * @author zavik001
 * @since 1.0
 */
public interface WordProvider {

    /**
     * Retrieves a list of available categories for words.
     *
     * @return a list of category names.
     */
    List<String> getCategories();

    /**
     * Retrieves a random category from the available categories.
     *
     * @return a randomly selected category name.
     */
    String getRandomCategory();

    /**
     * Retrieves the number of difficulty levels available for a given category.
     *
     * @param category the name of the category.
     * @return the number of difficulty levels for the specified category.
     */
    int getLevelsCount(String category);

    /**
     * Retrieves a random difficulty level for a given category.
     *
     * @param category the name of the category.
     * @return a randomly selected difficulty level for the specified category.
     */
    int getRandomDifficulty(String category);

    /**
     * Retrieves a random word and its corresponding hint for a given category and difficulty level.
     *
     * @param category the name of the category.
     * @param difficulty the difficulty level.
     * @return a {@link CluedWord} object containing the word and its hint.
     */
    CluedWord getRandomWord(String category, int difficulty);
}
