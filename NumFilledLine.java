
/**
 * Represents the number of FILLED Cells in a row or column when solved.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class NumFilledLine {

  private int numFilled = 0;

  public NumFilledLine(Cell[] line){
    for(Cell c : line){
      numFilled += c.isFilled() ? 1 : 0;
    }
  }

  public int getNumFilled(){return numFilled;}
}