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

    public List getWords(){ //THIS DOESNT DO ANYTHING YET
        List<String> wordList = new ArrayList<>();
        return wordList;
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
        while(scanner != null && scanner.hasNext()){
            addLine(scanner.nextLine());
        }
    }
    public void addLine(String line){ //checks that the string isnt empty then passes each word to add word
        if (line != null && line.length() > 0) {
            for (String word : line.split(" ")){
                addWord(word);
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
//        if(endsWithPunctuation(word)){// check if this word ends with punctuation
//            prevWord = PUNCTUATION; //if it did set prev word to punctuation
//        }
//        else prevWord = word; // otherwise advance previous word to current word

    }

    public boolean endsWithPunctuation(String word){
        int chk = PUNCTUATION_MARKS.indexOf(word.charAt(word.length()-1));
        System.out.println(word.charAt(word.length()-1));
        System.out.println(chk);
        if (chk == -1) return false;
        else return true;
    }

    public String randomWord(){
        return "random word";
    }

    public String getSentence(){
        return "Sentence";
    }

    @Override
    public String toString() {
        return "Markov{" +
                "PUNCTUATION='" + PUNCTUATION + '\'' +
                ", PUNCTUATION_MARKS='" + PUNCTUATION_MARKS + '\'' +
                ", words=" + words +
                ", prevWord='" + prevWord + '\'' +
                '}';
    }

    //    public static void main (String args []){
//    endsWithPunctuation("booboo");
//    }
}

