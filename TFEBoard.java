import java.util.*;

public class TFEBoard{
  private final TFETile[][] board;
  private final Scanner in;
  
  public static void start(){
    new TFEBoard();
  }

  private TFEBoard(){
    in = new Scanner(System.in);
    board = new TFETile[4][4];
    
    while(shouldContinue()){
      generateNewTile();
      print();
      userInput();
    }
    print();
    System.out.println();
  }
  
  private boolean shouldContinue(){
    boolean enoughSpace = false;
    
    for(int row = 0; row < 4; row++){
      for(int col = 0; col < 4; col++){
        if(!enoughSpace && isEmpty(row, col)){
          enoughSpace = true;
        }
        else if(!isEmpty(row,col) && board[row][col].getTier() >= 10){
          endingMessage(true);
          return false;
        }
      }
    }
    
    if(!enoughSpace){
      endingMessage(false);
      return false;
    }
    else{
      return true;
    }
  }

  private boolean isEmpty(int row, int col){
    return board[row][col] == null;
  }

  private void endingMessage(boolean win){
    if(win){
      System.out.println("You Won, You Got A Tier 10 Tile!");
    }
    else{
      System.out.println("You Failed, Your Final Score Was: " + getHighestTier());
    }
  }

  private void generateNewTile(){
    int nulls = 0;

    for(int row = 0; row < 4; row++){
      for(int col = 0; col < 4; col++){
        if(isEmpty(row, col)){
          nulls++;
        }
      }
    }

    int num = (int)(nulls * Math.random());
    nulls = 0;
    boolean placed = false;
    
    for(int row = 0; !placed && row < 4; row++){
      for(int col = 0; col < 4; col++){
        if(isEmpty(row, col)){
          if(num != nulls){
            nulls++;
          }
          else{
            board[row][col] = TFETile.generate();
            placed = true;
            break;
          }
        }
      }
    }
  }
  
  private void print(){
    for(TFETile[] row : board){
      for(TFETile t : row){
        System.out.print((t != null) ? t : "-");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  private int getHighestTier(){
    int highestTier = 0;
    for(int row = 0; row < 4; row++){
      for(int col = 0; col < 4; col++){
        if(!isEmpty(row,col) && board[row][col].getTier() > highestTier){
          highestTier = board[row][col].getTier();
        }
      }
    }
    
    return highestTier;
  }
  
  private void userInput(){
    String str = in.nextLine();
    System.out.println();
    
    if(str.equals("w")){
      for(int col = 0; col < 4; col++){
        
        for(int row = 1; row < 4; row++){
          if(!isEmpty(row,col)){
            int lastSpace = row;
            for(int row2 = row - 1; row2 >= 0; row2--){
              if(isEmpty(row2,col)){
                lastSpace = row2;
                if(row2 == 0){
                  board[lastSpace][col] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row2][col])){
                board[row2][col].merge();
                board[row][col] = null;
                break;
              }
              else if(lastSpace != row){
                board[lastSpace][col] = board[row][col];
                board[row][col] = null;
                break;
              }
            }
          }
        }
      
      }
    }
    
    else if(str.equals("s")){
      for(int col = 0; col < 4; col++){
        
        for(int row = 2; row >= 0; row--){
          if(!isEmpty(row,col)){
            int lastSpace = row;
            for(int row2 = row + 1; row2 < 4; row2++){
              if(isEmpty(row2,col)){
                lastSpace = row2;
                if(row2 == 3){
                  board[lastSpace][col] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row2][col])){
                board[row2][col].merge();
                board[row][col] = null;
                break;
              }
              else if(lastSpace != row){
                board[lastSpace][col] = board[row][col];
                board[row][col] = null;
                break;
              }
            }
          }
        }
        
      }
    }
    
    else if(str.equals("a")){
      for(int row = 0; row < 4; row++){
        
        for(int col = 1; col < 4; col++){
          if(!isEmpty(row,col)){
            int lastSpace = col;
            for(int col2 = col - 1; col2 >= 0; col2--){
              if(isEmpty(row,col2)){
                lastSpace = col2;
                if(col2 == 0){
                  board[row][lastSpace] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row][col2])){
                board[row][col2].merge();
                board[row][col] = null;
                break;
              }
              else if(lastSpace != col){
                board[row][lastSpace] = board[row][col];
                board[row][col] = null;
                break;
              }
            }
          }
        }
        
      }
    }
    
    else if(str.equals("d")){
      for(int row = 0; row < 4; row++){
        
        for(int col = 2; col >= 0; col--){
          if(!isEmpty(row,col)){
            int lastSpace = col;
            for(int col2 = col + 1; col2 < 4; col2++){
              if(isEmpty(row,col2)){
                lastSpace = col2;
                if(col2 == 3){
                  board[row][lastSpace] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row][col2])){
                board[row][col2].merge();
                board[row][col] = null;
                break;
              }
              else if(lastSpace != col){
                board[row][lastSpace] = board[row][col];
                board[row][col] = null;
                break;
              }
            }
          }
        }
        
      }
    }
  }
  
}