package backend.academy.hangman.ui;

import backend.academy.hangman.game.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleUserInterface implements UserInputOutput {

    private final BufferedReader reader;

    public ConsoleUserInterface() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public ConsoleUserInterface(BufferedReader reader) {
        this.reader = reader;
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
            System.out.println("Failed to clear console.");
            e.printStackTrace();
        }
    }

    @Override
    public String readWordInput() {
        System.out.print(Constants.MESSAGE_COLON);
        try {
            String input = reader.readLine().trim();
            return input.isEmpty() ? " " : input.toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
            return " ";
        }
    }

    @Override
    public int readNumberInput() {
        System.out.print(Constants.MESSAGE_COLON);
        try {
            String input = reader.readLine().trim();
            if (input.isEmpty() || !input.matches("\\d+")) {
                return 0;
            }
            return Integer.parseInt(input);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public char readLetterInput() {
        System.out.print(Constants.MESSAGE_COLON);
        try {
            String input = reader.readLine().trim();
            if (input.isEmpty()) {
                return ' ';
            }
            return Character.toLowerCase(input.charAt(0));
        } catch (IOException e) {
            e.printStackTrace();
            return ' ';
        }
    }

    @Override
    public void showMessage(CharSequence message) {
        System.out.println(message);
    }

    public void showCategoriesMessage(String[] categories) {
        for (String cat : categories) {
            System.out.print(cat + " ");
        }
        System.out.println();
    }
}
