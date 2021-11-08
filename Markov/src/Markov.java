import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Markov {
    public final String PUNCTUATION = "__$";
    public final String PUNCTUATION_MARKS="!?.";

    private HashMap<String, ArrayList<String>> words;
    private String prevWord=PUNCTUATION;

    //constructore
    Markov(){
    words = new HashMap<>();
    words.put(PUNCTUATION, new ArrayList<>());
    prevWord=PUNCTUATION;
    }

    public HashMap getWords(){ //What is this for?
        return words;
    }

    public void addFromFile(String s){ //String s is file name. Reads each line into addLine method
        File file = new File(s);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.print("File not found");
        }
        while(scanner.hasNext()){
            addLine(scanner.nextLine());
        }
    }
    public void addLine(String line){ //checks that the string isnt empty then passes each word to add word
        line = line.trim();
        if (line.length() > 0) {
            for (String word : line.split(" ")){
                if(word.length() > 0) {
                    addWord(word);
                }
            }
        }
    }

    public void addWord(String word){
        if (Objects.equals(prevWord, PUNCTUATION)){ //checks if previous word ended with punctuation
            words.get(PUNCTUATION).add(word);//if it did, this word will be added to Punctuation array
        }
        else {
            if (!words.containsKey(prevWord)){ //check if yhe previous word is  NOYa key
                words.put(prevWord, new ArrayList<>());// if it previouse word is not a key make it a key
            }
            words.get(prevWord).add(word); //add new word to the array list
        }
        if (endsWithPunctuation(word)) prevWord = PUNCTUATION ; //check if word ends with punctuation. if it does indicte it with prev word
        else prevWord = word; //otherwise just advance prevWOrd.
    }

    public boolean endsWithPunctuation(String word){
        int chk = PUNCTUATION_MARKS.indexOf(word.charAt(word.length()-1));
        if (chk == -1) return false;
        else return true;
    }

    public String randomWord(String word){
        String nextWord;
        int wc = words.get(word).size();
        nextWord = words.get(word).get((int) Math.floor(Math.random() * (wc)));
       // System.out.println((int) Math.floor(Math.random()) * (wc+1));
        return nextWord;
    }

    public String getSentence(){
        String s = "";
        String word;
        boolean end = false;
       // pick random word from punctuation list
        int wc = words.get(PUNCTUATION).size();
        if (wc < 1) return ("No words found");
        //chooses random word from punctuation list
        word = words.get(PUNCTUATION).get((int) Math.floor(Math.random() * (wc)));
        //loop will continue until we hit punctuation word.
        while (!end) {
            if (!endsWithPunctuation(word)) {
                s = s + word + " ";
                word = randomWord(word);
            } else {
                s = s + word;
                end = true;
            }
        }
        prevWord = PUNCTUATION;
    return s;
    }

    @Override
    public String toString() {
        return words.toString();
    }

    //    public static void main (String args []){
//    endsWithPunctuation("booboo");
//    }
}

