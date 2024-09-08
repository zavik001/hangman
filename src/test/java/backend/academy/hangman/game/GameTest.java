package backend.academy.hangman.game;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import backend.academy.hangman.game.wordprovider.WordProvider;
import backend.academy.hangman.game.wordprovider.CluedWord;
import backend.academy.hangman.ui.UserInterface;
import backend.academy.hangman.game.constants.Messages;
import backend.academy.hangman.game.constants.GameSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class GameTest {

    private Game game;

    @Mock private UserInterface mockUI;
    @Mock private WordProvider mockWordProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new Game(mockUI, mockWordProvider);
    }

    @Test
    void testStartGameWithCorrectGuesses() {
        when(mockWordProvider.getCategories()).thenReturn(List.of("animals"));
        when(mockUI.readWordInput()).thenReturn("animals");
        when(mockWordProvider.getLevelsCount("animals")).thenReturn(3);
        when(mockUI.readNumberInput()).thenReturn(1);

        CluedWord wordWithHint = new CluedWord("cat", "This is a pet");
        when(mockWordProvider.getRandomWord("animals", 1)).thenReturn(wordWithHint);

        when(mockUI.readLetterInput()).thenReturn('c', 'a', 't');

        game.start();

        verify(mockUI).showMessage(Messages.MESSAGE_WIN);
    }

    @Test
    void testStartGameWithIncorrectGuesses() {
        when(mockWordProvider.getCategories()).thenReturn(List.of("animals"));
        when(mockUI.readWordInput()).thenReturn("animals");
        when(mockWordProvider.getLevelsCount("animals")).thenReturn(3);
        when(mockUI.readNumberInput()).thenReturn(1);

        CluedWord wordWithHint = new CluedWord("dog", "This is a pet");
        when(mockWordProvider.getRandomWord("animals", 1)).thenReturn(wordWithHint);

        when(mockUI.readLetterInput()).thenReturn('x', 'y', 'z', 'w', 'v', 'u');

        game.start();

        verify(mockUI).showMessage(Messages.MESSAGE_LOSS);
    }

    @Test
    void testStartGameRandomCategoryWhenNoInput() {
        when(mockWordProvider.getCategories()).thenReturn(List.of("animals"));
        when(mockUI.readWordInput()).thenReturn("");
        when(mockWordProvider.getRandomCategory()).thenReturn("animals");
        when(mockWordProvider.getLevelsCount("animals")).thenReturn(3);
        when(mockUI.readNumberInput()).thenReturn(1);

        CluedWord wordWithHint = new CluedWord("cat", "This is a pet");
        when(mockWordProvider.getRandomWord("animals", 1)).thenReturn(wordWithHint);

        game.start();

        verify(mockWordProvider).getRandomCategory();
    }

    @Test
    void testStartGameRandomDifficultyWhenNoInput() {
        when(mockWordProvider.getCategories()).thenReturn(List.of("animals"));
        when(mockUI.readWordInput()).thenReturn("animals");
        when(mockWordProvider.getLevelsCount("animals")).thenReturn(3);
        when(mockUI.readNumberInput()).thenReturn(0);
        when(mockWordProvider.getRandomDifficulty("animals")).thenReturn(2);

        CluedWord wordWithHint = new CluedWord("dog", "This is a pet");
        when(mockWordProvider.getRandomWord("animals", 2)).thenReturn(wordWithHint);

        game.start();

        verify(mockWordProvider).getRandomDifficulty("animals");
    }
}
