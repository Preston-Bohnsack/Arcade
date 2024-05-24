import java.util.*;

/**
 * Represents the number and length of runs of FILLED Cells in a row or column when solved.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class ConsecutiveLine {
  /**
   * Each run is represented as the number of consecutive FILLED cells that make it up, and the runs 
   * are stored in an ArrayList<Integer> in order.
   */
  private final ArrayList<Integer> runs;
  
  public ConsecutiveLine(Cell[] line){
    runs = new ArrayList<Integer>();
    int length = line.length;
    for(int startIndex = 0; startIndex < length; startIndex++){ // for every Cell
      if(line[startIndex].isFilled()){ // if that Cell is filled
        for(int endIndex = startIndex + 1; endIndex < length; endIndex++){
          if(endIndex == length){ // If the first Cell is at the end of the Line, the run is 1 Cell long.
            runs.add(1);
            startIndex = endIndex;
            break;
          }
          else if(endIndex == length - 1){ // if the end of the run is at the end of the line, add the run.
            runs.add(endIndex - startIndex + ((line[endIndex].isFilled()) ? (1) : (0)));
            startIndex = endIndex;
            break;
          }
          else if(!line[endIndex].isFilled()){ // if the run end normally, with an EMPTY Cell, add the run.
            runs.add(endIndex - startIndex);
            startIndex = endIndex;
            break;
          }
        }
      }
    }
  }
  
  public ArrayList<Integer> getRuns(){return runs;}
  public int getNumRuns(){return runs.size();}
}