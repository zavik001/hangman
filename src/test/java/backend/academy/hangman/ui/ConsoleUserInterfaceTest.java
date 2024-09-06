package backend.academy.hangman.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

class ConsoleUserInterfaceTest {
    @Test
    void testClearWindow() throws IOException, InterruptedException {
        ConsoleUserInterface consoleUI = new ConsoleUserInterface();
        consoleUI.clearWindow();
    }

    @Test
    void testReadWordInput() throws IOException {
        String input = "  testInput  ";
        BufferedReader mockReader = new BufferedReader(new StringReader(input));
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(mockReader);
        String result = consoleUI.readWordInput();
        assertThat(result).isEqualTo("testinput");
    }

    @Test
    void testReadWordInputHandlesNull() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn(null);
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(mockReader);
        String result = consoleUI.readWordInput();
        assertThat(result).isEqualTo(" ");
    }

    @Test
    void testReadNumberInput() throws IOException {
        String input = "  123  ";
        BufferedReader mockReader = new BufferedReader(new StringReader(input));
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(mockReader);
        int result = consoleUI.readNumberInput();
        assertThat(result).isEqualTo(123);
    }

    @Test
    void testReadNumberInputHandlesInvalidInput() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn("abc");
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(mockReader);
        int result = consoleUI.readNumberInput();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testReadLetterInput() throws IOException {
        String input = "  A  ";
        BufferedReader mockReader = new BufferedReader(new StringReader(input));
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(mockReader);
        char result = consoleUI.readLetterInput();
        assertThat(result).isEqualTo('a');
    }

    @Test
    void testReadLetterInputHandlesEmptyInput() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn("");
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(mockReader);
        char result = consoleUI.readLetterInput();
        assertThat(result).isEqualTo(' ');
    }

    @Test
    void testShowMessage() {
        ConsoleUserInterface consoleUI = spy(new ConsoleUserInterface());
        String message = "Hello, World!";
        consoleUI.showMessage(message);
        verify(consoleUI).showMessage(message);
    }

    @Test
    void testShowListMessage() {
        ConsoleUserInterface consoleUI = spy(new ConsoleUserInterface());
        var categories = Arrays.asList("Category1", "Category2");
        consoleUI.showListMessage(categories);
        verify(consoleUI).showListMessage(categories);
    }

    @Test
    void testShowListMessageHandlesEmptyList() {
        ConsoleUserInterface consoleUI = spy(new ConsoleUserInterface());
        consoleUI.showListMessage(Arrays.asList());
        verify(consoleUI).showListMessage(Arrays.asList());
    }
}
