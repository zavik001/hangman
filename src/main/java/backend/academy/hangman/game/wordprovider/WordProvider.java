package backend.academy.hangman.game.wordprovider;

public interface WordProvider {
    void validateAndSetOptions(String category, int difficulty);
    WordWithHint getRandomWordWithHint(); 
}
