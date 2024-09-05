package backend.academy.hangman.game.wordprovider;

import backend.academy.hangman.game.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
class FileWordProviderTest {

    private FileWordProvider wordProvider;

    @BeforeEach
    void setUp() {
        wordProvider = new FileWordProvider();
    }

    @Test
    void testValidCategoryAndDifficulty() {
        wordProvider.validateAndSetOptions("animals", 2);
        assertEquals("animals", wordProvider.getCategory(), "Category should be 'animals'");
        assertEquals(2, wordProvider.getDifficulty(), "Difficulty should be 2");
    }

    @Test
    void testInvalidCategory() {
        wordProvider.validateAndSetOptions("invalidCategory", 1);
        assertEquals("invalid", wordProvider.getCategory(), "Category should be set to 'invalid' when an invalid category is provided");
        assertEquals(-1, wordProvider.getDifficulty(), "Difficulty should be set to -1 when an invalid category is provided");
        assertTrue(wordProvider.getWordsWithHints().isEmpty(), "WordsWithHints should be empty for invalid category");
    }

    @Test
    void testInvalidDifficulty() {
        wordProvider.validateAndSetOptions("animals", 4); 
        assertEquals("invalid", wordProvider.getCategory(), "Category should be set to 'invalid' when an invalid difficulty is provided");
        assertEquals(-1, wordProvider.getDifficulty(), "Difficulty should be set to -1 when an invalid difficulty is provided");
        assertTrue(wordProvider.getWordsWithHints().isEmpty(), "WordsWithHints should be empty for invalid difficulty");
    }

    @Test
    void testEmptyCategory() {
        wordProvider.validateAndSetOptions("", 2);
        assertNotEquals("invalid", wordProvider.getCategory(), "Random category should be chosen, not 'invalid'");
        assertEquals(2, wordProvider.getDifficulty(), "Difficulty should be set to 2");
    }

    @Test
    void testZeroDifficulty() {
        wordProvider.validateAndSetOptions("animals", 0);
        assertEquals("animals", wordProvider.getCategory(), "Category should be set to 'animals'");
        assertTrue(wordProvider.getDifficulty() >= 1 && wordProvider.getDifficulty() <= Constants.TOTAL_DIFFICULTY_LEVELS, 
                   "Difficulty should be a valid random difficulty level between 1 and " + Constants.TOTAL_DIFFICULTY_LEVELS);
    }

    @Test
    void testGetRandomWordWithHint() {
        wordProvider.validateAndSetOptions("animals", 1);
        WordWithHint wordWithHint = wordProvider.getRandomWordWithHint();
        assertNotNull(wordWithHint, "WordWithHint should not be null");
        assertNotNull(wordWithHint.getWord(), "Word should not be null in WordWithHint");
        assertNotNull(wordWithHint.getHint(), "Hint should not be null in WordWithHint");
    }
}
