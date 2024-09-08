package backend.academy.hangman.game.wordprovider;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CluedWord {
    private final String word;
    private final String hint;
}
