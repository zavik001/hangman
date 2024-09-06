package backend.academy.hangman.ui;

import backend.academy.hangman.game.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleUserInterface implements UserInputOutput {

    private static final Logger LOGGER = LogManager.getLogger(ConsoleUserInterface.class);
    private final BufferedReader reader;

    public ConsoleUserInterface() {
        BufferedReader tempReader = null;
        try {
            tempReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOGGER.error("Failed to initialize BufferedReader.", e);
        }
        this.reader = (tempReader != null)
            ? tempReader
            : new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    public ConsoleUserInterface(BufferedReader reader) {
        this.reader = (reader != null)
            ? reader
            : new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    @Override
    public void clearWindow() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
            LOGGER.info("Console cleared successfully.");
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Failed to clear console.", e);
        }
    }

    @Override
    public String readWordInput() {
        LOGGER.info(Constants.MESSAGE_COLON);
        try {
            String input = reader.readLine();
            if (input == null) {
                return " ";
            }
            input = input.trim();
            LOGGER.info("User input: {}", input);
            return input.isEmpty() ? " " : input.toLowerCase();
        } catch (IOException e) {
            LOGGER.error("Failed to read word input.", e);
            return " ";
        }
    }

    @Override
    public int readNumberInput() {
        LOGGER.info(Constants.MESSAGE_COLON);
        try {
            String input = reader.readLine();
            if (input == null) {
                return 0;
            }
            input = input.trim();
            LOGGER.info("User number input: {}", input);
            if (input.isEmpty() || !input.matches("\\d+")) {
                return 0;
            }
            return Integer.parseInt(input);
        } catch (IOException e) {
            LOGGER.error("Failed to read number input.", e);
            return -1;
        }
    }

    @Override
    public char readLetterInput() {
        LOGGER.info(Constants.MESSAGE_COLON);
        try {
            String input = reader.readLine();
            if (input == null || input.isEmpty()) {
                return ' ';
            }
            input = input.trim();
            LOGGER.info("User letter input: {}", input);
            return Character.toLowerCase(input.charAt(0));
        } catch (IOException e) {
            LOGGER.error("Failed to read letter input.", e);
            return ' ';
        }
    }

    @Override
    public void showMessage(CharSequence message) {
        LOGGER.info("Message: {}", message);
    }

    @Override
    public void showListMessage(List<String> wordCategories) {
        if (wordCategories == null || wordCategories.isEmpty()) {
            LOGGER.info("No categories available.");
            return;
        }
        String categories = String.join(" ", wordCategories);
        LOGGER.info("Categories: {}", categories);
    }
}
