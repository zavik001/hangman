package backend.academy.hangman.game.util;

import backend.academy.hangman.game.wordprovider.WordWithHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("unused")
class FileReaderUtilTest {

    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("test-files");
    }

    @Test
    void testReadWordsWithHints_validFile_shouldReturnCorrectData() throws IOException {
        Path file = createTempFileWithContent(
                "# Level 1\n" +
                        "Dog: A common domestic animal, known for loyalty.\n" +
                        "Cat: A small, domesticated carnivorous mammal, often kept as a pet.\n" +
                        "\n" +
                        "# Level 2\n" +
                        "Elephant: The largest land animal with a trunk.\n" +
                        "Giraffe: The tallest land animal with a long neck.\n");

        List<List<WordWithHint>> levels = FileReaderUtil.readWordsWithHints(file.toString());

        assertThat(levels).hasSize(2);

        assertThat(levels.get(0)).hasSize(2);
        assertThat(levels.get(0).get(0).getWord()).isEqualTo("Dog");
        assertThat(levels.get(0).get(0).getHint()).isEqualTo("A common domestic animal, known for loyalty.");
        assertThat(levels.get(0).get(1).getWord()).isEqualTo("Cat");
        assertThat(levels.get(0).get(1).getHint()).isEqualTo("A small, domesticated carnivorous mammal, often kept as a pet.");

        assertThat(levels.get(1)).hasSize(2);
        assertThat(levels.get(1).get(0).getWord()).isEqualTo("Elephant");
        assertThat(levels.get(1).get(0).getHint()).isEqualTo("The largest land animal with a trunk.");
        assertThat(levels.get(1).get(1).getWord()).isEqualTo("Giraffe");
        assertThat(levels.get(1).get(1).getHint()).isEqualTo("The tallest land animal with a long neck.");
    }

    @Test
    void testReadWordsWithHints_invalidFormat_shouldHandleGracefully() throws IOException {
        Path file = createTempFileWithContent(
                "# Level 1\n" +
                "Dog A common domestic animal, known for loyalty.\n" + 
                "Cat: A small, domesticated carnivorous mammal, often kept as a pet.\n" +
                "\n" +
                "# Level 2\n" +
                "Elephant The largest land animal with a trunk.\n"); 
    
        List<List<WordWithHint>> levels = FileReaderUtil.readWordsWithHints(file.toString());
    
        assertThat(levels).hasSize(2); 
    
        assertThat(levels.get(0)).hasSize(1); 
        assertThat(levels.get(0).get(0).getWord()).isEqualTo("Cat");
        assertThat(levels.get(0).get(0).getHint()).isEqualTo("A small, domesticated carnivorous mammal, often kept as a pet.");
    
        assertThat(levels.get(1)).isEmpty();
    }
    
    

    @Test
    void testReadWordsWithHints_nonExistingFile_shouldThrowIOException() {
        assertThrows(IOException.class, () -> FileReaderUtil.readWordsWithHints("non_existing_file.txt"));
    }

    private Path createTempFileWithContent(String content) throws IOException {
        Path file = Files.createTempFile(tempDir, "test-file", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
            writer.write(content);
        }
        return file;
    }
}
