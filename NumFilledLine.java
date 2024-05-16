
/**
 * Represents the number of FILLED Cells in a row or column when solved.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class NumFilledLine {

  /*
   * The number of FILLED Cells in the NumFilledLine when solved.
   */
  private int numFilled = 0;
  
  /**
   * Counts the number of FILLED Cells in the provided Cell[].
   * 
   * @param line The line of Cells to analyze.
   */
  public NumFilledLine(Cell[] line){
    for(Cell c : line){
      numFilled += c.isFilled() ? 1 : 0;
    }
  }

  public int getNumFilled(){return numFilled;}
}