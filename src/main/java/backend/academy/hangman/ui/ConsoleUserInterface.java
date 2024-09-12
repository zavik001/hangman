package backend.academy.hangman.ui;

import backend.academy.hangman.game.constants.Messages;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleUserInterface implements UserInterface {

    private final BufferedReader reader;

    public ConsoleUserInterface() {
        BufferedReader tempReader = null;
        try {
            tempReader =
                    new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Failed to initialize BufferedReader. ", e);
        }
        this.reader =
                (tempReader != null)
                        ? tempReader
                        : new BufferedReader(
                                new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    public ConsoleUserInterface(BufferedReader reader) {
        this.reader =
                (reader != null)
                        ? reader
                        : new BufferedReader(
                                new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    @Override
    public void clearWindow() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            log.error("Failed to clear console.", e);
        }
    }

    @Override
    public String readWordInput() {
        log.info("Waiting for word input from the user{}", Messages.MESSAGE_COLON);
        try {
            String input = reader.readLine();
            if (input == null) {
                return " ";
            }
            input = input.trim();
            log.info("User input: {}", input);
            return input.isEmpty() ? " " : input.toLowerCase();
        } catch (IOException e) {
            log.error("Failed to read word input.", e);
            return " ";
        }
    }

    @Override
    public int readNumberInput() {
        log.info("Waiting for number input from the user{}", Messages.MESSAGE_COLON);
        try {
            String input = reader.readLine();
            if (input == null || input.trim().isEmpty()) {
                return 0;
            }
            input = input.trim();
            log.info("User number input: {}", input);
            if (input.matches("\\d+") && Integer.parseInt(input) > 0) {
                    return Integer.parseInt(input);
            } else {
                return -1;
            }
        } catch (IOException e) {
            log.error("Failed to read number input.", e);
            return -1;
        }
    }

    @Override
    public char readLetterInput() {
        log.info("Waiting for letter input from the user{}", Messages.MESSAGE_COLON);
        try {
            String input = reader.readLine();
            if (input == null || input.trim().isEmpty()) {
                return '\0';
            }
            input = input.trim();
            log.info("User letter input: {}", input);
            return Character.toLowerCase(input.charAt(0));
        } catch (IOException e) {
            log.error("Failed to read letter input.", e);
            return '\0';
        }
    }

    @Override
    public void showMessage(CharSequence message) {
        log.info("Message: {}", message);
    }

    @Override
    public void showListMessage(List<String> wordCategories) {
        if (wordCategories == null || wordCategories.isEmpty()) {
            log.info("No categories available.");
            return;
        }
        String categories = String.join(" ", wordCategories);
        log.info("Categories: {}", categories);
    }
}
