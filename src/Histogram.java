/*
* Histogram.java
* Andrew Shiraki
* 2021-10-30
* This is the Histogram class it will read file input from the file name given by the user through cli
* and output a formatted histogram
*/
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.logging.FileHandler;

public class Histogram {

    public static void main(String[] args){
       //variables
        char[]Existing = new char[200];
        char[]letters = {'A','B','C','D','E','F','G','H','I','J','K'};
        int[] letterCount = new int[11];

        //-----<>..<>------//
       read(Existing,letterCount,getFileName());
       sort(letters, letterCount);
       for (char letter: letters){
           System.out.println(letter);
       }
       System.out.println("hold");
    }

    //Method to get filename from cli
    public static String getFileName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        return scanner.nextLine();
    }

    //read file and populate letter count and letter arrays
    public static void read(char[] letter, int[] letterCount, String filename){

        //make file object
        File file = new File(filename);
        int count=0;
        Scanner scanner=null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //populate letter array
        while (scanner.hasNext()){
        letter[count++] = scanner.nextLine().charAt(0);
        System.out.println(letter[count-1]);
        }

        //use ascii number to populate letter count array
        for (int i = 0 ; i < count ; i++){
            letterCount[(int) letter[i]-65]++;
        }
    }

    //pass the letter array and the count array, order them both from lowest to highest
    public static void sort(char[] letter, int [] letterCount){
        boolean swap;
        char tempChar;
        int tempInt;
        do{
            swap = false;
            for (int i = 0 ; i < letterCount.length-1; i++){
                if (letterCount[i] < letterCount[i+1]){ //do the swap
                    tempChar = letter[i];
                    tempInt = letterCount[i];
                    letter[i] = letter[i+1];
                    letterCount[i] = letterCount[i+1];
                    letterCount[i+1] = tempInt;
                    letter[i+1] = tempChar;
                    swap = true;
                }
            }
        }while (swap);
    }

    public static void display(char[] letter, int[] count){
        //This method will display the histogram
    }


}
