import java.util.*;

/**
 * Represents the number and length of consectuive FILLED Cells in a row or column when solved.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class ConsecutiveLine {
  /**
   * Each run is represented as the number of consecutive FILLED cells that make it up, and the runs 
   * are stored in an arrayList<Integer> in order.
   */
  private final ArrayList<Integer> runs = new ArrayList<Integer>();
  
  public ConsecutiveLine(Cell[] line){
    int length = line.length;
    for(int startIndex = 0; startIndex < length; startIndex++){
      if(line[startIndex].isFilled()){
        for(int endIndex = startIndex + 1; endIndex < length; endIndex++){
          if(endIndex == length){
            runs.add(1);
            startIndex = endIndex;
            break;
          }
          else if(endIndex == length - 1){
            runs.add(endIndex - startIndex + ((line[endIndex].isFilled()) ? (1) : (0)));
            startIndex = endIndex;
            break;
          }
          else if(!line[endIndex].isFilled()){
            runs.add(endIndex - startIndex);
            startIndex = endIndex;
            break;
          }
        }
      }
    }
  }
  
  public ArrayList<Integer> getRuns(){
    return runs;
  }
  
  public int getNumRuns(){
    return runs.size();
  }
}