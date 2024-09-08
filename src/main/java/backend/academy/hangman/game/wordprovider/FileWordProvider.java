package backend.academy.hangman.game.wordprovider;

import backend.academy.hangman.game.util.DictionaryFileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@Slf4j
public class FileWordProvider implements WordProvider {
    private static final SecureRandom RANDOM = new SecureRandom();
    private String category;
    private int difficulty;
    private Map<String, Map<Integer, List<CluedWord>>> dictionary = new HashMap<>();

    public FileWordProvider(String filePath) {
        try {
            this.dictionary = DictionaryFileReader.readDictionary(filePath);
        } catch (IOException e) {
            log.error("Failed to read dictionary file: {}", filePath, e);
        }
    }

    @Override
    public List<String> getCategories() {
        return dictionary.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public int getLevelsCount(String category) {
        Map<Integer, List<CluedWord>> levels = dictionary.get(category.toLowerCase());
        return levels != null ? levels.size() : 0;
    }

    @Override
    public CluedWord getRandomWord(String category, int difficulty) {
        String selectedCategory = category;
        int selectedDifficulty = difficulty;

        if (selectedCategory == null || selectedCategory.trim().isEmpty()) {
            selectedCategory = getRandomCategory();
        }

        if (selectedDifficulty == 0) {
            selectedDifficulty = getRandomDifficulty(selectedCategory);
        }

        Map<Integer, List<CluedWord>> levels = dictionary.get(selectedCategory.toLowerCase());
        if (levels == null) {
            throw new IllegalArgumentException("Invalid category: " + selectedCategory);
        }

        List<CluedWord> words = levels.get(selectedDifficulty);
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid difficulty level or no words available for level: " + selectedDifficulty);
        }

        int randomIndex = RANDOM.nextInt(words.size());
        return words.get(randomIndex);
    }

    @Override
    public String getRandomCategory() {
        List<String> categories = getCategories();
        if (categories.isEmpty()) {
            throw new IllegalStateException("No categories available.");
        }
        int randomIndex = RANDOM.nextInt(categories.size());
        return categories.get(randomIndex);
    }

    @Override
    public int getRandomDifficulty(String category) {
        int levelCount = getLevelsCount(category);
        if (levelCount <= 0) {
            throw new IllegalStateException("No levels available for category: " + category);
        }
        return RANDOM.nextInt(levelCount) + 1;
    }
}
