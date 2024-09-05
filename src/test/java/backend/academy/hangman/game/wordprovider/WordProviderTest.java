package backend.academy.hangman.game.wordprovider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordProviderTest {

    private WordProvider wordProvider;

    @BeforeEach
    void setUp() {
        wordProvider = new FileWordProvider();
    }

    @Test
    void testValidCategoryAndDifficulty() {
        wordProvider.validateAndSetOptions("animals", 2);
        assertDoesNotThrow(() -> wordProvider.getRandomWordWithHint(), "Should not throw exception for valid options");
    }

    @Test
    void testInvalidCategory() {
        wordProvider.validateAndSetOptions("invalidCategory", 1);
        WordWithHint wordWithHint = wordProvider.getRandomWordWithHint();
        assertNotNull(wordWithHint, "WordWithHint should not be null for invalid category");
        assertEquals("invalid", wordWithHint.getWord(), "Word should be invalid for invalid category");
        assertEquals("No hint available, please enter invalid and try again", wordWithHint.getHint(), "Hint should be 'No hint available' for invalid category");
    }

    @Test
    void testInvalidDifficulty() {
        wordProvider.validateAndSetOptions("animals", 4); 
        WordWithHint wordWithHint = wordProvider.getRandomWordWithHint();
        assertNotNull(wordWithHint, "WordWithHint should not be null for invalid difficulty");
        assertEquals("invalid", wordWithHint.getWord(), "Word should be invalid for invalid difficulty");
        assertEquals("No hint available, please enter invalid and try again", wordWithHint.getHint(), "Hint should be 'No hint available' for invalid difficulty");
    }

    @Test
    void testEmptyCategory() {
        wordProvider.validateAndSetOptions("", 2);
        WordWithHint wordWithHint = wordProvider.getRandomWordWithHint();
        assertNotNull(wordWithHint, "WordWithHint should not be null for empty category");
        assertNotEquals("", wordWithHint.getWord(), "Random category should be chosen, not empty string");
    }

    @Test
    void testZeroDifficulty() {
        wordProvider.validateAndSetOptions("animals", 0);
        WordWithHint wordWithHint = wordProvider.getRandomWordWithHint();
        assertNotNull(wordWithHint, "WordWithHint should not be null for zero difficulty");
        assertNotEquals("", wordWithHint.getWord(), "Word should be obtained with valid difficulty");
    }
}
