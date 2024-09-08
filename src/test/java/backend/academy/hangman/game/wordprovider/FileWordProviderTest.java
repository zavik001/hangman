package backend.academy.hangman.game.wordprovider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class FileWordProviderTest {

    private FileWordProvider wordProvider;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = createTempJsonFile();
        wordProvider = new FileWordProvider(tempFile.toString());
    }

    @Test
    void testGetCategoriesShouldReturnAllCategories() {
        List<String> categories = wordProvider.getCategories();
        assertThat(categories).containsExactlyInAnyOrder("animals", "fruits");
    }

    @Test
    void testGetLevelsCountValidCategoryShouldReturnCorrectCount() {
        int levelCount = wordProvider.getLevelsCount("animals");
        assertThat(levelCount).isEqualTo(2);

        levelCount = wordProvider.getLevelsCount("fruits");
        assertThat(levelCount).isEqualTo(1);
    }

    @Test
    void testGetRandomWordValidCategoryAndDifficultyShouldReturnWord() {
        CluedWord randomWord = wordProvider.getRandomWord("animals", 1);
        assertThat(randomWord).isNotNull();
        assertThat(randomWord.word()).isIn("Dog", "Cat");
        assertThat(randomWord.hint()).isIn("A common domestic animal, known for loyalty.",
                                             "A small, domesticated carnivorous mammal, often kept as a pet.");
    }

    @Test
    void testGetRandomCategoryShouldReturnValidCategory() {
        String randomCategory = wordProvider.getRandomCategory();
        assertThat(randomCategory).isIn("animals", "fruits");
    }

    @Test
    void testGetRandomDifficultyValidCategoryShouldReturnDifficulty() {
        int randomDifficulty = wordProvider.getRandomDifficulty("animals");
        assertThat(randomDifficulty).isBetween(1, 2);
    }

    @Test
    void testGetRandomWordInvalidCategoryShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> wordProvider.getRandomWord("invalid", 1));
    }

    @Test
    void testGetRandomDifficultyInvalidCategoryShouldThrowException() {
        assertThrows(IllegalStateException.class, () -> wordProvider.getRandomDifficulty("invalid"));
    }

    @Test
    void testGetLevelsCountInvalidCategoryShouldReturnZero() {
        int levelCount = wordProvider.getLevelsCount("invalid");
        assertThat(levelCount).isEqualTo(0);
    }

    private Path createTempJsonFile() throws IOException {
        Path file = Files.createTempFile("test-dictionary", ".json");
        String jsonContent = """
            {
                "categories": {
                    "animals": {
                        "1": [
                            { "word": "Dog", "hint": "A common domestic animal, known for loyalty." },
                            { "word": "Cat", "hint": "A small, domesticated carnivorous mammal, often kept as a pet." }
                        ],
                        "2": [
                            { "word": "Elephant", "hint": "The largest land animal with a trunk." }
                        ]
                    },
                    "fruits": {
                        "1": [
                            { "word": "Apple", "hint": "A common, round fruit that is usually red or green." }
                        ]
                    }
                }
            }
            """;

        try (BufferedWriter writer = Files.newBufferedWriter(file, java.nio.charset.StandardCharsets.UTF_8)) {
            writer.write(jsonContent);
        }
        return file;
    }
}
