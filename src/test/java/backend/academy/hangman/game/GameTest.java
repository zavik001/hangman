package backend.academy.hangman.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import backend.academy.hangman.game.wordprovider.WordProvider;
import backend.academy.hangman.game.wordprovider.WordWithHint;
import backend.academy.hangman.ui.UserInputOutput;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameTest {

    private Game game;

    @Mock private UserInputOutput mockUI;

    @Mock private WordProvider mockWordProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new Game(mockUI, mockWordProvider);
    }

    @Test
    public void testStartGameWithCorrectGuesses() {
        when(mockUI.readWordInput()).thenReturn("animals");
        when(mockUI.readNumberInput()).thenReturn(1);

        WordWithHint wordWithHint = new WordWithHint("cat", "This is a pet");
        when(mockWordProvider.getRandomWordWithHint()).thenReturn(wordWithHint);

        when(mockUI.readLetterInput()).thenReturn('c').thenReturn('a').thenReturn('t');

        game.start();

        verify(mockUI).showMessage(Constants.MESSAGE_WIN);
    }

    @Test
    public void testStartGameWithIncorrectGuesses() {
        when(mockUI.readWordInput()).thenReturn("animals");
        when(mockUI.readNumberInput()).thenReturn(1);

        WordWithHint wordWithHint = new WordWithHint("dog", "This is a pet");
        when(mockWordProvider.getRandomWordWithHint()).thenReturn(wordWithHint);

        when(mockUI.readLetterInput())
                .thenReturn('x')
                .thenReturn('y')
                .thenReturn('z')
                .thenReturn('w')
                .thenReturn('v')
                .thenReturn('u');

        game.start();

        verify(mockUI).showMessage(Constants.MESSAGE_LOSS);
    }

    @Test
    public void testStartGameRandomCategoryWhenNoInput() {
        when(mockUI.readWordInput()).thenReturn("");

        when(mockUI.readNumberInput()).thenReturn(1);

        WordWithHint wordWithHint = new WordWithHint("cat", "This is a pet");
        when(mockWordProvider.getRandomWordWithHint()).thenReturn(wordWithHint);

        game.start();

        verify(mockWordProvider).validateAndSetOptions(anyString(), anyInt());
    }

    @Test
    public void testStartGameRandomDifficultyWhenNoInput() {
        when(mockUI.readWordInput()).thenReturn("animals");

        when(mockUI.readNumberInput()).thenReturn(-1);

        WordWithHint wordWithHint = new WordWithHint("dog", "This is a pet");
        when(mockWordProvider.getRandomWordWithHint()).thenReturn(wordWithHint);

        game.start();

        verify(mockWordProvider).validateAndSetOptions(anyString(), anyInt());
    }
}
