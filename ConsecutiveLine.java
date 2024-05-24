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
    for(int firstIndex = 0; firstIndex < line.length; firstIndex++){
      if(!inaRun && line[firstIndex].isFilled()){ // find the start of a run
        for(int secondIndex = firstIndex; secondIndex < line.length; secondIndex++){ 
          if(!line[secondIndex].isFilled()){ // find the end of a run
            runs.add(secondIndex - firstIndex; // add it to the list
            firstIndex = secondIndex + 1; // resume looking after the end of this run
            break;
          }
          else if(line[secondIndex].isFilled() && secondIndex == line.length - 1){ // if the run contiues to the end of the line
            runs.add(secondIndex - firstIndex + 1); // add it to the list
            return;
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