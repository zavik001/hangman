package backend.academy.hangman.game.util;

import backend.academy.hangman.game.wordprovider.CluedWord;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class DictionaryFileReaderTest {

    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("test-files");
    }

    @Test
    void testReadDictionaryValidFileShouldReturnCorrectData() throws IOException {
        Path file = createTempFileWithContent(
                "{ \"categories\": { " +
                "\"Animals\": { " +
                "\"1\": [ { \"word\": \"Dog\", \"hint\": \"A common domestic animal, known for loyalty.\" }, " +
                "{ \"word\": \"Cat\", \"hint\": \"A small, domesticated carnivorous mammal, often kept as a pet.\" } ], " +
                "\"2\": [ { \"word\": \"Elephant\", \"hint\": \"The largest land animal with a trunk.\" }, " +
                "{ \"word\": \"Giraffe\", \"hint\": \"The tallest land animal with a long neck.\" } ] " +
                "} } }");

        Map<String, Map<Integer, List<CluedWord>>> dictionary = DictionaryFileReader.readDictionary(file.toString());

        assertThat(dictionary).containsKey("Animals");
        Map<Integer, List<CluedWord>> animalsLevels = dictionary.get("Animals");

        assertThat(animalsLevels).hasSize(2);

        List<CluedWord> level1Words = animalsLevels.get(1);
        assertThat(level1Words).hasSize(2);
        assertThat(level1Words.get(0).word()).isEqualTo("Dog");
        assertThat(level1Words.get(0).hint()).isEqualTo("A common domestic animal, known for loyalty.");
        assertThat(level1Words.get(1).word()).isEqualTo("Cat");
        assertThat(level1Words.get(1).hint()).isEqualTo("A small, domesticated carnivorous mammal, often kept as a pet.");

        List<CluedWord> level2Words = animalsLevels.get(2);
        assertThat(level2Words).hasSize(2);
        assertThat(level2Words.get(0).word()).isEqualTo("Elephant");
        assertThat(level2Words.get(0).hint()).isEqualTo("The largest land animal with a trunk.");
        assertThat(level2Words.get(1).word()).isEqualTo("Giraffe");
        assertThat(level2Words.get(1).hint()).isEqualTo("The tallest land animal with a long neck.");
    }

    @Test
    void testReadDictionaryInvalidFileShouldThrowIOException() {
        assertThrows(IOException.class, () -> DictionaryFileReader.readDictionary("non_existing_file.json"));
    }

    @Test
    void testReadDictionaryEmptyFileShouldReturnEmptyMap() throws IOException {
        Path file = createTempFileWithContent("{ \"categories\": {} }");

        Map<String, Map<Integer, List<CluedWord>>> dictionary = DictionaryFileReader.readDictionary(file.toString());

        assertThat(dictionary).isEmpty();
    }

    @Test
    void testReadDictionaryMalformedFileShouldThrowIOException() throws IOException {
        final Path file = createTempFileWithContent(
            "{ \"categories\": { \"Animals\": { \"1\": [ { \"word\": \"Dog\", \"hint\": \"Loyal\" } }"
            + " } }"
        );

        assertThrows(IOException.class, () -> DictionaryFileReader.readDictionary(file.toString()));
    }

    private Path createTempFileWithContent(String content) throws IOException {
        Path file = Files.createTempFile(tempDir, "test-file", ".json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile(), StandardCharsets.UTF_8))) {
            writer.write(content);
        }
        return file;
    }
}
