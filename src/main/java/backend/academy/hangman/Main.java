package backend.academy.hangman;

import backend.academy.hangman.game.Game;
import backend.academy.hangman.game.wordprovider.FileWordProvider;
import backend.academy.hangman.ui.ConsoleUserInterface;

public final class Main {

    private Main() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(String[] args) {
        ConsoleUserInterface userInterface = new ConsoleUserInterface();
        FileWordProvider wordProvider = new FileWordProvider();
        Game game = new Game(userInterface, wordProvider);
        game.start();
    }
}
