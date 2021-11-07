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
        assertEquals(markov.endsWithPunctuation("fart"), false);
        assertEquals(markov.endsWithPunctuation("fart!"), true);
    }

    @org.junit.jupiter.api.Test
    void randomWord() {
    }

    @org.junit.jupiter.api.Test
    void getSentence() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }
}