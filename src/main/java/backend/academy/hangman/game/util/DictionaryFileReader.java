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
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));

        Map<String, Map<Integer, List<CluedWord>>> dictionary = new HashMap<>();
        JsonNode categoriesNode = rootNode.get("categories");

        if (categoriesNode != null) {
            categoriesNode
                .fieldNames()
                .forEachRemaining(category -> {
                    JsonNode categoryNode = categoriesNode.get(category);
                    dictionary.put(category, processCategoryNode(categoryNode));
                });
        }
        return dictionary;
    }

    private static Map<Integer, List<CluedWord>> processCategoryNode(JsonNode categoryNode) {
        Map<Integer, List<CluedWord>> levels = new HashMap<>();
        categoryNode
            .fieldNames()
            .forEachRemaining(level -> {
                int levelInt = Integer.parseInt(level);
                JsonNode wordsNode = categoryNode.get(level);
                levels.put(levelInt, processWordsNode(wordsNode));
            });
        return levels;
    }

    private static List<CluedWord> processWordsNode(JsonNode wordsNode) {
        List<CluedWord> words = new ArrayList<>();
        wordsNode.forEach(wordNode -> {
            String word = wordNode.get("word").asText();
            String hint = wordNode.get("hint").asText();
            words.add(new CluedWord(word, hint));
        });
        return words;
    }
}
