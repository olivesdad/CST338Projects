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
        char[] Existing = new char[200];
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        int[] letterCount = new int[11];

        read(Existing, letterCount, getFileName());
        sort(letters, letterCount);
        display(letters, letterCount);
    }

    //Method to get filename from cli
    public static String getFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        return scanner.nextLine();
    }

    //read file and populate letter count and letter arrays
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

        //populate letter array
        while (scanner.hasNext()) {
            letter[count++] = scanner.nextLine().charAt(0);
        }

        //use ascii number to populate letter count array
        for (int i = 0; i < count; i++) {
            letterCount[(int) letter[i] - 65]++;
        }
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
    }

    public static void display(char[] letter, int[] count) {
        //print section 1 all occurrences in descending order
        System.out.println("Char Occurrence:");
        //find lowest occurrence
        int marker = 0;
        while (marker < 10 && count[marker+1] != 0 ){
            marker++;
        }
        //print from marker down
        for (int i = marker; i >= 0; i--) {
                System.out.println(letter[i] + " " + count[i]);
        }

        //print section 2
        System.out.println("\n" + "================");
        //loop through the count array
        marker = 0;
        String letters = "";
        int letterCount = count [0];
        while (marker < 11 && count[marker] !=0){
            letterCount = count[marker];
            while (letterCount == count[marker] && marker < 11){
                letters = letters + letter[marker++];
            }
            if(letterCount > 0){
                System.out.println(outputString(letterCount, alphabetical(letters)));
            }
        }
        System.out.println("----------------");

        //print the last thing
        String theEnd = "";
        for (int i = letter.length -1 ; i >=0; i--){
            theEnd = theEnd + letter[i];
        }
        System.out.println(String.format("%16s", theEnd));
    }
    public static String outputString(int num, String letters){
        String s;
        s = String.format("|%3d|",num);
        s = s + String.format("%11s", new StringBuilder(letters).reverse().toString());
        return s;
    }
    public static String alphabetical(String s){
        char [] chArr = s.toCharArray();
        boolean swap;
        char tempChar;
        do{
            swap = false;
            for (int i = 0 ; i < chArr.length - 1 ; i++ ){
                if (chArr[i] < chArr[i+1]){
                    swap = true;
                    tempChar = chArr[i];
                    chArr[i] = chArr[i+1];
                    chArr[i+1] = tempChar;
                }
            }
        }while (swap == true);
        String sorted="";
        for (char letter : chArr){
            sorted = sorted + letter;
        }
        return sorted;

    }

}
