package backend.academy.hangman.game;

import backend.academy.hangman.game.wordprovider.WordProvider;
import backend.academy.hangman.game.wordprovider.WordWithHint;
import backend.academy.hangman.ui.UserInputOutput;

public class Game {
    private final UserInputOutput userInterface;
    private final WordProvider wordProvider;
    private int remainingAttempts;

    public Game(UserInputOutput userInterface, WordProvider wordProvider) {
        this.userInterface = userInterface;
        this.wordProvider = wordProvider;
        this.remainingAttempts = Constants.MAX_ATTEMPTS;
    }

    public void start() {
        userInterface.clearWindow();
        userInterface.showMessage(Constants.MESSAGE_GREETING);
        userInterface.showMessage(Constants.PROMPT_CATEGORY_SELECTION);
        userInterface.showCategoriesMessage(Constants.WORD_CATEGORIES);

        String category = userInterface.readWordInput();
        userInterface.showMessage(Constants.PROMPT_DIFFICULTY_SELECTION);
        int difficulty = userInterface.readNumberInput();

        wordProvider.validateAndSetOptions(category, difficulty);
        WordWithHint wordWithHint = wordProvider.getRandomWordWithHint(); 
        String word = wordWithHint.getWord(); 
        String hint = wordWithHint.getHint();

        StringBuilder finderWord = new StringBuilder("_".repeat(word.length()));
        @SuppressWarnings("unused")
        int garbage = processGuess(' ', word, finderWord);
        
        while (remainingAttempts > 0 && finderWord.indexOf("_") != -1) {
            userInterface.clearWindow();
            userInterface.showMessage(Constants.MESSAGE_REMAINING_ATTEMPTS + remainingAttempts);
            userInterface.showMessage(Constants.PROMPT_GUESS_WORD + finderWord);
            userInterface.showMessage(Constants.HANGMAN_STAGES[Constants.MAX_ATTEMPTS - remainingAttempts]);
            userInterface.showMessage(Constants.MESSAGE_HINT + hint);

            char input = userInterface.readLetterInput();
            remainingAttempts += processGuess(input, word, finderWord); 
        }
        userInterface.clearWindow();
        userInterface.showMessage(Constants.PROMPT_GUESS_WORD + word);
        userInterface.showMessage((remainingAttempts <= 0) ? Constants.MESSAGE_LOSS : Constants.MESSAGE_WIN);
    }

    private int processGuess(char input, String word, StringBuilder finderWord) {
        char lowerCaseInput = Character.toLowerCase(input);
        String lowerCaseWord = word.toLowerCase();
        boolean found = false;
    
        for (int i = 0; i < word.length(); i++) {
            if (lowerCaseWord.charAt(i) == lowerCaseInput) {
                finderWord.setCharAt(i, word.charAt(i)); 
                found = true;
            }
        }
    
        return found ? 0 : -1;
    }
}
