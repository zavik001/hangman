package backend.academy.hangman.game.wordprovider;

import backend.academy.hangman.game.Constants;
import backend.academy.hangman.game.util.FileReaderUtil;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

public class FileWordProvider implements WordProvider {
    private static final SecureRandom RANDOM = new SecureRandom();
    private String category;
    private int difficulty;
    private List<List<WordWithHint>> wordsWithHints;

    public String getCategory() {
        return category;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public void validateAndSetOptions(String category, int difficulty) {
        try {
            this.category = category.trim().isEmpty() ? getRandomCategory() : category.trim();

            if (!Constants.WORD_CATEGORIES.contains(this.category)) {
                throw new IllegalArgumentException("Invalid category: " + this.category);
            }

            this.difficulty = (difficulty == 0) ? getRandomDifficulty() : difficulty;

            if (this.difficulty < 1 || this.difficulty > Constants.TOTAL_DIFFICULTY_LEVELS) {
                throw new IllegalArgumentException("Invalid difficulty level: " + this.difficulty);
            }

            wordsWithHints =
                    FileReaderUtil.readWordsWithHints(
                            Constants.PATH_TO_DICTIONARY + this.category + ".txt");

        } catch (IOException e) {
            System.err.println(
                    "Error when obtaining words for category '"
                            + this.category
                            + "': "
                            + e.getMessage());
            handleInvalidOptions();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            handleInvalidOptions();
        }
    }

    private void handleInvalidOptions() {
        this.category = Constants.INVALID_CATEGORY;
        this.difficulty = -1;
        wordsWithHints = Collections.emptyList();
    }

    public List<List<WordWithHint>> getWordsWithHints() {
        return wordsWithHints;
    }

    private String getRandomCategory() {
        int randomIndex = getRandomNumber(1, Constants.TOTAL_CATEGORIES);
        return Constants.WORD_CATEGORIES.get(randomIndex - 1);
    }

    private int getRandomDifficulty() {
        return getRandomNumber(1, Constants.TOTAL_DIFFICULTY_LEVELS);
    }

    private int getRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min should be less than or equal to max");
        }
        return RANDOM.nextInt(max - min + 1) + min;
    }

    @Override
    public WordWithHint getRandomWordWithHint() {
        if (wordsWithHints == null || wordsWithHints.isEmpty()) {
            return new WordWithHint(Constants.INVALID_CATEGORY, Constants.NO_HINT_AVAILABLE);
        }

        if (difficulty > wordsWithHints.size() || difficulty <= 0) {
            return new WordWithHint(Constants.INVALID_CATEGORY, Constants.INVALID_DIFFICULTY);
        }

        List<WordWithHint> currentLevelWords = wordsWithHints.get(difficulty - 1);

        if (currentLevelWords.isEmpty()) {
            return new WordWithHint(Constants.INVALID_CATEGORY, Constants.NO_WORDS_AVAILABLE);
        }

        int randomIndex = getRandomNumber(0, currentLevelWords.size() - 1);
        return currentLevelWords.get(randomIndex);
    }
}
