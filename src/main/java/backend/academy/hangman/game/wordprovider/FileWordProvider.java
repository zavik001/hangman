package backend.academy.hangman.game.wordprovider;

import backend.academy.hangman.game.Constants;
import backend.academy.hangman.game.util.FileReaderUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileWordProvider implements WordProvider {
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
    
            if (!Arrays.asList(Constants.WORD_CATEGORIES).contains(this.category)) {
                throw new IllegalArgumentException("Invalid category: " + this.category);
            }
    
            this.difficulty = (difficulty == 0) ? getRandomDifficulty() : difficulty;
    
            if (this.difficulty < 1 || this.difficulty > Constants.TOTAL_DIFFICULTY_LEVELS) {
                throw new IllegalArgumentException("Invalid difficulty level: " + this.difficulty);
            }
    
            wordsWithHints = FileReaderUtil.readWordsWithHints(Constants.PATH_TO_DICTIONARY + this.category + ".txt");
    
        } catch (IOException e) {
            System.err.println("Error when obtaining words for category '" + this.category + "': " + e.getMessage());
            handleInvalidOptions();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            handleInvalidOptions();
        }
    }
    
    private void handleInvalidOptions() {
        this.category = "invalid"; 
        this.difficulty = -1; 
        wordsWithHints = List.of();
    }

    public List<List<WordWithHint>> getWordsWithHints() {
        return wordsWithHints;
    }

    private String getRandomCategory() throws IOException {
        int randomIndex = getRandomNumber(1, Constants.TOTAL_CATEGORIES);
        return Constants.WORD_CATEGORIES[randomIndex - 1];
    }

    private int getRandomDifficulty() throws IOException {
        return getRandomNumber(1, Constants.TOTAL_DIFFICULTY_LEVELS);
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public WordWithHint getRandomWordWithHint() {
        if (wordsWithHints == null || wordsWithHints.isEmpty()) {
            return new WordWithHint("invalid", "No hint available, please enter invalid and try again");
        }

        if (difficulty > wordsWithHints.size() || difficulty <= 0) {
            return new WordWithHint("invalid", "Invalid difficulty level, please enter invalid and try again");
        }

        List<WordWithHint> currentLevelWords = wordsWithHints.get(difficulty - 1);

        if (currentLevelWords.isEmpty()) {
            return new WordWithHint("invalid", "No words available for this level, please enter invalid and try again");
        }

        int randomIndex = getRandomNumber(0, currentLevelWords.size() - 1);
        return currentLevelWords.get(randomIndex);
    }
}
