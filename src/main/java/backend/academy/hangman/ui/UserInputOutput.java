package backend.academy.hangman.ui;

import java.util.List;

public interface UserInputOutput {
    void clearWindow();

    String readWordInput();

    int readNumberInput();

    char readLetterInput();

    void showMessage(CharSequence message);

    void showListMessage(List<String> wordCategories);
}
