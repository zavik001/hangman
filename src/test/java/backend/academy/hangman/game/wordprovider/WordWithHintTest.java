package backend.academy.hangman.game.wordprovider;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordWithHintTest {

    @Test
    public void testWordWithHintConstructorAndGetters() {
        String expectedWord = "Cat";
        String expectedHint = "A small, domesticated carnivorous mammal, often kept as a pet.";

        WordWithHint wordWithHint = new WordWithHint(expectedWord, expectedHint);

        assertEquals(expectedWord, wordWithHint.getWord(), "The word should be 'Cat'");
        assertEquals(expectedHint, wordWithHint.getHint(), "The hint should be as expected");
    }
}
