import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
  int x,y;


  public Test ( int x, int y )
  {
    this.x=x;
    this.y = y;
  }

  public static void main(String[] args) {
    Test test1 = new Test(5,6);
    int x =5;
    int y =6;
    Test test2= new Test(x,y);
    System.out.println(test2.equals(test1));
    midTermQuestion03("foo\n bar\n baz");

  }
  public static String midTermQuestion03(String input){

    Scanner scan = new Scanner(System.in);

    System.out.print("What is your name: ");

    String name = scan.nextLine();

    scan.close();

    scan = new Scanner(input);

    StringBuilder sb = new StringBuilder();

    while(scan.hasNext()){

      sb.append(scan.nextLine());

    }

    scan.close();

    System.out.print(name + " pick a value: ");

    System.out.println(sb);

    scan = new Scanner(System.in);

    String choice = scan.nextLine();

    System.out.println("your choice was " + choice);

    return choice;

  }







}
