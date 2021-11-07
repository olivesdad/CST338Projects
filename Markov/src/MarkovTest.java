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

    }

    @org.junit.jupiter.api.Test
    void getWords() {
        markov.addLine("hello my name is drew. do you like chicken? i like chicken because chicken tastes like chicken");
        System.out.println(markov);
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
    }

    @org.junit.jupiter.api.Test
    void getSentence() {
        markov.addLine("chicken bake chicken steak. so good.");
        markov.getSentence();
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        markov.toString();
    }
}