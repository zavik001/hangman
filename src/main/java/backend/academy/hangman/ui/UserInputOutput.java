package backend.academy.hangman.ui;

public interface UserInputOutput {
    void clearWindow();
    String readWordInput();
    int readNumberInput();
    char readLetterInput();
    void showMessage(CharSequence message);
    void showCategoriesMessage(String[] categories);
}
