package backend.academy.hangman.ui;

import backend.academy.hangman.ui.ConsoleUserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleUserInterfaceTest {

    private ConsoleUserInterface ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testReadWordInput_ValidInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("apple\n".getBytes())));
        ui = new ConsoleUserInterface(reader);

        String result = ui.readWordInput();
        assertEquals("apple", result);
    }

    @Test
    public void testReadWordInput_EmptyInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("\n".getBytes())));
        ui = new ConsoleUserInterface(reader);

        String result = ui.readWordInput();
        assertEquals(" ", result);
    }

    @Test
    public void testReadNumberInput_ValidNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("123\n".getBytes())));
        ui = new ConsoleUserInterface(reader);

        int result = ui.readNumberInput();
        assertEquals(123, result);
    }

    @Test
    public void testReadNumberInput_InvalidInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("abc\n".getBytes())));
        ui = new ConsoleUserInterface(reader);

        int result = ui.readNumberInput();
        assertEquals(0, result);
    }

    @Test
    public void testReadLetterInput_ValidInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("a\n".getBytes())));
        ui = new ConsoleUserInterface(reader);

        char result = ui.readLetterInput();
        assertEquals('a', result);
    }

    @Test
    public void testReadLetterInput_EmptyInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("\n".getBytes())));
        ui = new ConsoleUserInterface(reader);

        char result = ui.readLetterInput();
        assertEquals(' ', result);
    }

    @Test
    public void testShowMessage() {
        ui = new ConsoleUserInterface();
        ui.showMessage("Test Message");
        assertEquals("Test Message\n", outputStream.toString());
    }
}
