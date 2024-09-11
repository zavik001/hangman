package backend.academy.hangman.game;

import backend.academy.hangman.game.wordprovider.WordProvider;
import backend.academy.hangman.game.wordprovider.CluedWord;
import backend.academy.hangman.game.constants.Messages;
import backend.academy.hangman.ui.UserInterface;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GameTest {

    private Game game;

    @Mock
    private UserInterface mockUI;
    @Mock
    private WordProvider mockWordProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new Game(mockUI, mockWordProvider);
    }

    @Test
    void testStartGameWithCorrectGuesses() {
        when(mockWordProvider.getCategories()).thenReturn(List.of("animals", "sports"));
        when(mockUI.readWordInput()).thenReturn("animals");
        when(mockWordProvider.getLevelsCount("animals")).thenReturn(3);
        when(mockUI.readNumberInput()).thenReturn(1);

        CluedWord cluedWord = new CluedWord("cat", "This is a pet");
        when(mockWordProvider.getRandomWord("animals", 1)).thenReturn(cluedWord);
        when(mockUI.readLetterInput()).thenReturn('c', 'a', 't');

        game.start();

        verify(mockUI).showMessage(Messages.MESSAGE_WIN);
    }

    @Test
    void testStartGameWithIncorrectGuesses() {
        when(mockWordProvider.getCategories()).thenReturn(List.of("animals", "sports"));
        when(mockUI.readWordInput()).thenReturn("animals");
        when(mockWordProvider.getLevelsCount("animals")).thenReturn(3);
        when(mockUI.readNumberInput()).thenReturn(1);

        CluedWord cluedWord = new CluedWord("dog", "This is a pet");
        when(mockWordProvider.getRandomWord("animals", 1)).thenReturn(cluedWord);
        when(mockUI.readLetterInput()).thenReturn('x', 'y', 'z', 'w', 'v', 'u', 'r', 't', 'i', 'p');

        game.start();

        verify(mockUI).showMessage(Messages.MESSAGE_LOSS);
    }
}
