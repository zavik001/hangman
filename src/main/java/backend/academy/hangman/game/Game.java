package backend.academy.hangman.game;

import backend.academy.hangman.game.constants.GameSettings;
import backend.academy.hangman.game.constants.HangmanStages;
import backend.academy.hangman.game.constants.Messages;
import backend.academy.hangman.game.wordprovider.CluedWord;
import backend.academy.hangman.game.wordprovider.WordProvider;
import backend.academy.hangman.ui.UserInterface;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private final UserInterface userInterface;
    private final WordProvider wordProvider;
    private int remainingAttempts;

    public Game(UserInterface userInterface, WordProvider wordProvider) {
        this.userInterface = userInterface;
        this.wordProvider = wordProvider;
        this.remainingAttempts = GameSettings.MAX_ATTEMPTS;
    }

    public void start() {
        userInterface.clearWindow();
        userInterface.showMessage(Messages.MESSAGE_GREETING);

        String category = selectValidCategory();
        int difficulty = selectValidDifficulty(category);

        CluedWord cluedWord = wordProvider.getRandomWord(category, difficulty);
        String word = cluedWord.word();
        String hint = cluedWord.hint();

        StringBuilder finderWord = new StringBuilder("_".repeat(word.length()));
        Set<Character> guessedLetters = new HashSet<>();

        int result = -1;
        while (remainingAttempts > 0 && finderWord.indexOf("_") != -1) {
            userInterface.clearWindow();
            userInterface.showMessage(Messages.MESSAGE_REMAINING_ATTEMPTS + remainingAttempts);
            userInterface.showMessage(Messages.PROMPT_GUESS_WORD + finderWord);
            userInterface.showMessage(
                HangmanStages.HANGMAN_STAGES.get(GameSettings.MAX_ATTEMPTS - remainingAttempts));

            if (remainingAttempts < GameSettings.HINT_DISPLAY_THRESHOLD) {
                userInterface.showMessage(Messages.MESSAGE_HINT + hint);
            }

            if (result == 1) {
                userInterface.showMessage(Messages.MESSAGE_ALREADY_ENTERED);
            }

            char input = userInterface.readLetterInput();

            result = processGuess(input, word, finderWord, guessedLetters);
            if (result == -1) {
                remainingAttempts--;
            }
        }

        userInterface.clearWindow();
        userInterface.showMessage(Messages.PROMPT_GUESS_WORD + word);
        userInterface.showMessage(
                (remainingAttempts <= 0) ? Messages.MESSAGE_LOSS : Messages.MESSAGE_WIN);
    }

    String selectValidCategory() {
        String category;
        List<String> availableCategories = wordProvider.getCategories();
        do {
            userInterface.showMessage(Messages.PROMPT_CATEGORY_SELECTION);
            userInterface.showListMessage(availableCategories);
            category = userInterface.readWordInput();
            if (category.trim().isEmpty()) {
                return wordProvider.getRandomCategory();
            }
        } while (!availableCategories.contains(category));
        return category;
    }

    private int selectValidDifficulty(String category) {
        int levelCount = wordProvider.getLevelsCount(category);
        int difficulty;
        do {
            userInterface.showMessage(
                    Messages.PROMPT_DIFFICULTY_SELECTION + "(1-" + levelCount + ")");
            difficulty = userInterface.readNumberInput();
            if (difficulty == 0) {
                return wordProvider.getRandomDifficulty(category);
            }
        } while (difficulty < 1 || difficulty > levelCount);
        return difficulty;
    }

    private int processGuess(char input, String word, StringBuilder finderWord, Set<Character> guessedLetters) {
        if (input == '\0') {
            return 0;
        }

        boolean found = false;
        char lowerCaseInput = Character.toLowerCase(input);
        String lowerCaseWord = word.toLowerCase();

        if (guessedLetters.contains(lowerCaseInput)) {
            return 1;
        } else {
            guessedLetters.add(input);
            for (int i = 0; i < word.length(); i++) {
                if (lowerCaseWord.charAt(i) == lowerCaseInput) {
                    finderWord.setCharAt(i, word.charAt(i));
                    found = true;
                }
            }
            return found ? 0 : -1;
        }
    }
}
