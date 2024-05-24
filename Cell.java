
/**
 * Represents the 3 possible states a tile in a game can be, UNMARKED, CROSSED_OUT, and 
 * FILLED.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public enum Cell{
  UNMARKED((short)0),
  CROSSED_OUT((short)-1),
  FILLED((short)1);
  
  private final short flag;
  
  private Cell(short newFlag){flag = (short)newFlag;}
  
  public int getFlag(){return flag;}

  public boolean equals(Cell other){return (other != null) && (flag == other.getFlag());}

  /**
   * Returns true if both Cells are both filled or both not filled. This is used when determining 
   * if the board is solved, and correctly returns false if passed a null value.
   * 
   * @param other The other Cell to be used in the comparison, which can be null.
   * @return If 2 Cells are both filled or both not filled.
   */
  public boolean solvedEquals(Cell other){
    /*
     * If the Cells are normally equal, they will always be solvedEqual, but UNMARKED and CROSSED_OUT 
     * Cells should register as equal.
     */
    return ((other != null) && ((flag == other.getFlag()) || (!isFilled() == !other.isFilled())));
  }

  public boolean isUnmarked(){return flag == 0;}
  public boolean isCrossedOut(){return flag == -1;}
  public boolean isFilled(){return flag == 1;}

  public Cell copy(){
    switch(flag){
      case -1 : return Cell.CROSSED_OUT;
      case 0 : return Cell.UNMARKED;
      case 1 : return Cell.FILLED;
    }

    return null;

  }

  public String toString(){
    switch(flag){
      case -1 : return "X";
      case 0 : return ".";
      case 1 : return "#";
    }
    return "";
  }
}