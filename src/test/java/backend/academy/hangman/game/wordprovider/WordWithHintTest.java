package backend.academy.hangman.game.wordprovider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CluedWordTest {

    @Test
    void testCluedWordConstructorAndGetters() {
        String expectedWord = "Cat";
        String expectedHint = "A small, domesticated carnivorous mammal, often kept as a pet.";

        CluedWord cluedWord = new CluedWord(expectedWord, expectedHint);

        assertEquals(expectedWord, cluedWord.word(), "The word should be 'Cat'");
        assertEquals(expectedHint, cluedWord.hint(), "The hint should be as expected");
    }
}
