package backend.academy.hangman.game.util;

import backend.academy.hangman.game.wordprovider.CluedWord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DictionaryFileReader {

    private DictionaryFileReader() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Map<String, Map<Integer, List<CluedWord>>> readDictionary(String filePath)
            throws IOException {
        Map<String, Map<Integer, List<CluedWord>>> dictionary = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));

        JsonNode categoriesNode = rootNode.get("categories");
        if (categoriesNode != null) {
            categoriesNode
                    .fieldNames()
                    .forEachRemaining(
                            category -> {
                                Map<Integer, List<CluedWord>> levels = new HashMap<>();
                                JsonNode categoryNode = categoriesNode.get(category);

                                categoryNode
                                        .fieldNames()
                                        .forEachRemaining(
                                                level -> {
                                                    List<CluedWord> words = new ArrayList<>();
                                                    JsonNode wordsNode = categoryNode.get(level);

                                                    wordsNode.forEach(
                                                            wordNode -> {
                                                                String word =
                                                                        wordNode.get("word")
                                                                                .asText();
                                                                String hint =
                                                                        wordNode.get("hint")
                                                                                .asText();
                                                                words.add(
                                                                        new CluedWord(word, hint));
                                                            });

                                                    int levelInt = Integer.parseInt(level);
                                                    levels.put(levelInt, words);
                                                });

                                dictionary.put(category, levels);
                            });
        }
        return dictionary;
    }
}
