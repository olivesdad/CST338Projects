import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Markov {
    public final String PUNCTUATION = "__$";
    public final String PUNCTUATION_MARKS="";

    private HashMap<String, ArrayList<String>> words;
    private String prevWord=PUNCTUATION;

    //constructore
    Markov(){
    words = new HashMap<>();
    words.put(PUNCTUATION, new ArrayList<>());
    prevWord=PUNCTUATION;
    }

    public List getWords(){ //THIS DOESNT DO ANYTHING YET
        List<String> wordLisd = new ArrayList<>();
        return wordLisd;
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
        if (prevWord == PUNCTUATION){
            words.get(PUNCTUATION).add(word);
        }
        else {
            if (!words.containsKey(prevWord)){
                words.put(prevWord, new ArrayList<>());
            }
            words.get(prevWord).add(word);
        }
        if(endsWithPunctuation(word)){
            prevWord = PUNCTUATION;
        }
        else prevWord = word; 

    }

    public boolean endsWithPunctuation(String word){
    return false;
    }

//    public static void main (String args []){
//
//    }
}

