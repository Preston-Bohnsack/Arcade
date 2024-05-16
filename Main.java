import java.util.*;

public class Main{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while(true){
      System.out.println("Which game do you want to play? Nonogram, Aquarium, or 2048?");
      String str = in.nextLine();
      System.out.println();
      if(str.equals("Nonogram")){
        System.out.println("Which board do you want to play on? 1, 2, or 3?");
        int num = in.nextInt();
        in.nextLine();
        NonogramBoard.startGame(num);
      }
      else if(str.equals("Aquarium")){
       AquariumBoard.startGame();
      }
      else if(str.equals("2048")){
        TFEBoard.start();
      }
      else{
        System.out.print("Please try again.");
      }
    }
  }
}