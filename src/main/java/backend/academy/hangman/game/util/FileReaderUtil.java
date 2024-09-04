package backend.academy.hangman.game.util;

import backend.academy.hangman.game.wordprovider.WordWithHint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<List<WordWithHint>> readWordsWithHints(String filePath) throws IOException {
        List<List<WordWithHint>> levels = new ArrayList<>();
        List<WordWithHint> currentLevel = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("# Level")) {
                    if (!currentLevel.isEmpty() || levels.size() > 0) {
                        levels.add(currentLevel);
                    }
                    currentLevel = new ArrayList<>();
                } else if (!line.isEmpty()) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String word = parts[0].trim();
                        String hint = parts[1].trim();
                        currentLevel.add(new WordWithHint(word, hint));
                    } else {
                        System.err.println("Invalid format: " + line); 
                    }
                }
            }

            if (!currentLevel.isEmpty() || levels.size() > 0) {
                levels.add(currentLevel);
            }
        }
        return levels;
    }
}
