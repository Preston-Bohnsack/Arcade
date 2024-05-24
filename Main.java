import java.util.*;

public class Main{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while(true){
      System.out.println("Which game do you want to play? Nonogram, Aquarium, or 2048?");
      String str = in.nextLine();
      System.out.println();
      if(str.equals("Nonogram")){
        while(true){
          System.out.println("Which board do you want to play on? 1, 2, 3, or 4?");
          try{
            int num = in.nextInt();
            in.nextLine();
            NonogramBoard.startGame(num);
            return;
          }
          catch(Exception e){
            in.nextLine();
            System.out.println();
            System.out.println("Invalid input, please choose a number 1-4.");
          }
        }
      }
      else if(str.equals("Aquarium")){
       AquariumBoard.startGame();
       return;
      }
      else if(str.equals("2048")){
        TFEBoard.start();
        return;
      }
      else{
        System.out.print("Please try again. ");
      }
    }
  }
}