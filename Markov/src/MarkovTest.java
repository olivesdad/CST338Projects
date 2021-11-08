/*
*Andrew Shiraki
* 2021-11-07
* This is MarkovTest. It tests some of methods of the markov class. We use assert in some tests but for others, I found it
* more useful to just use sout to make sure it's doing what I think it's doing
 */
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
        markov.addLine("Hello. This is the toString line. I'm going to put the string to string.");
        System.out.println( "to string: " +  markov.toString());
    }
}