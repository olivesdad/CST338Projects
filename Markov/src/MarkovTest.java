import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MarkovTest {
    Markov markov;
    @BeforeEach
    void setUp() {
        markov = new Markov();
    }

    @AfterEach
    void tearDown() {
    markov = null;
    }

    @org.junit.jupiter.api.Test
    void getWords() {
        markov.addLine("hello my name is drew. do you like chicken? i like chicken because chicken tastes like chicken");
        System.out.println(markov.getWords());
    }

    @org.junit.jupiter.api.Test
    void addFromFile() {

    }

    @org.junit.jupiter.api.Test
    void addLine() {
    }

    @org.junit.jupiter.api.Test
    void addWord() {
    }

    @org.junit.jupiter.api.Test
    void endsWithPunctuation() {
        assertEquals(markov.endsWithPunctuation("hello"), false);
        assertEquals(markov.endsWithPunctuation("Hello?!"), true);
    }

    @org.junit.jupiter.api.Test
    void randomWord() {
        markov.addLine("hello my name is drew. Do you like chicken? I like chicken because chicken tastes like chicken. Also my dog is called olive and I like mechanical keyboards.");
        System.out.println("random word:" + markov.randomWord("chicken"));
    }

    @org.junit.jupiter.api.Test
    void getSentence() {
        markov.addLine("hello my name is drew. Do you like chicken? I like chicken because chicken tastes like chicken. Also my dog is called olive and I like mechanical keyboards.");
        System.out.println(markov.getSentence());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        markov.toString();
    }
}