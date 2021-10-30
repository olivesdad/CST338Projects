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
import java.util.logging.FileHandler;

public class Histogram {

    public static void main(String[] args){
       //variables
        char[]letters = new char [200];
        int[] letterCount = new int[11];

        //-----<>..<>------//
       read(letters,letterCount,getFileName());
    }

    //Method to read filename from cli
    public static String getFileName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        return scanner.nextLine();
    }

    public static void read(char[] letter, int[] letterCount, String filename){
        //This method will read the letters from the filename provided in the previous method.
        //Use this to populate the int[] letterCount
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
    }

    public static void sort(char[] letter, int[] letterCount){
        //This method will sort the letters
    }

    public static void display(char[] letter, int[] count){
        //This method will display the histogram
    }


}
