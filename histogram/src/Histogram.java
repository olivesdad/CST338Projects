/*
* Histogram.java
* Andrew Shiraki
* 2021-10-30
* This is the Histogram class it will read file input from the file name given by the user through cli
* and output a formatted histogram
*/
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Histogram {
    public static void main(String[] args) {
        //variables
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        int[] letterCount = new int[11];
        //~~~~~~~~~~//do the things//~~~~~~~~~~~~//
        read(letters, letterCount, getFileName());
        sort(letters, letterCount);
        display(letters, letterCount);
    }

    //Method to get filename from cli
    public static String getFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        return scanner.nextLine();
    }

    //read file and populate letter count and letter arrays idk why we need letter array. It is unused but passed because requirements state it must be
    public static void read(char[] letter, int[] letterCount, String filename) {
        //make file object
        File file = new File(filename);
        int count = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //scan read through each line of file and increment occurrence of letter
        do {
            assert scanner != null;//intellij says i need this?
            letterCount[(int) scanner.nextLine().charAt(0) - 65]++;
        } while (scanner.hasNext());
    }

    //pass the letter array and the count array, order them both from lowest to highest
    public static void sort(char[] letter, int[] letterCount) {
        boolean swap;
        char tempChar;
        int tempInt;
        do {
            swap = false;
            for (int i = 0; i < letterCount.length - 1; i++) {
                if (letterCount[i] < letterCount[i + 1]) { //do the swap
                    tempChar = letter[i];
                    tempInt = letterCount[i];
                    letter[i] = letter[i + 1];
                    letterCount[i] = letterCount[i + 1];
                    letterCount[i + 1] = tempInt;
                    letter[i + 1] = tempChar;
                    swap = true;
                }
            }
        } while (swap);
        //loop to sort letter array
        int start = 0;
        for (int mark = 0 ; mark < letterCount.length; mark++ ){
            if (mark == letterCount.length - 1){
                sectionSort(letter, start, mark);
            }
            else if (letterCount[mark] != letterCount[mark+1]){
                sectionSort(letter, start,mark);
                start = mark + 1;
            }
        }
    }
    public static void display(char[] letter, int[] count) {
        //~~~~~~~//Print the First Style thing~~~~~~~~~~~//
        System.out.println("Char Occurrence:");
        //find lowest occurrence so that we can count backwards skipping 0s
        int marker = 0;
        while (marker < 10 && count[marker+1] != 0 ){
            marker++;
        }
        //print from marker and count down
        for (int i = marker; i >= 0; i--) {
                System.out.println(letter[i] + " " + count[i]);
        }
        //~~~~~~~//Print the Second Style thing~~~~~~~~~~~//
        System.out.println("================");
        //loop through the count array
        marker = 0;
        String letters = "";
        int letterCount = count [0];//start here
        while (marker < 11 && count[marker] !=0){ //<~iterate through the entire char array of A-K and add any letters that occur to the string
            letterCount = count[marker];
            while  (marker < 11 && letterCount == count[marker] ){//put marker limit check first to not test out of bounds
                letters = letters + letter[marker++]; //at letter to string
            }
            if(letterCount > 0){ //if there was more than 0 of this letter print the number of occurrences. We need to sort after all strings are added.
                System.out.println(outputString(letterCount, letters));
            }
        }
        System.out.println("----------------");
        //~~~~~//just print the sorted letter array reversed//~~~~~~~//
        StringBuilder theEnd = new StringBuilder();//intellij says I should use stringbuilder
        for (int i = letter.length -1 ; i >=0; i--){
            theEnd.append(letter[i]);
        }
        System.out.printf("%16s%n", theEnd.toString());
    }
    //takes the letter count string and the letters string, formats them and returns string in the required format
    public static String outputString(int num, String letters){
        String s;
        s = String.format("|%3d|",num);
        s = s + String.format("%11s", new StringBuilder(letters).reverse().toString());
        return s;
    }
    //method to alphabetically sort a section of an array from start to end
    public static void sectionSort(char[] letters, int start, int end){
        boolean swap;
        char tempLetter;
        do{ //might as well keep using bubble sort
            swap = false;
            for (int i = start; i < end; i++){
                if (letters[i] < letters[i+1]){
                    tempLetter = letters[i+1];
                    letters[i+1] = letters[i];
                    letters [i] = tempLetter;
                    swap = true;
                }
            }
        }while (swap);
    }
}
