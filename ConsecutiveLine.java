import java.util.*;

/**
 * Represents the number and length of consectuive FILLED Cells in a row or column when solved.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class ConsecutiveLine {
  // this class is for the runs corresponding to a certain column or row
  private final ArrayList<Integer> runs = new ArrayList<Integer>();
  /*
    each run is represented as the number of consecutive filled cells that make it up, and the runs are
    stored in am arrayList for integers
  */
  
  public ConsecutiveLine(Cell[] line){
    int startFilledIndex = -1;
    boolean inaRun = false;
    for(int i = 0; i < line.length; i++){
      if(!inaRun && line[i].isFilled()){
        startFilledIndex = i;
        inaRun = true;
        for(int j = i; j < line.length; j++){
          if(!line[j].isFilled()){
            runs.add(j - i);
            startFilledIndex = -1;
            inaRun = false;
            i = j + 1;
            break;
          }
          if(line[j].isFilled() && j == line.length - 1){
            runs.add(j + 1 - i);
            // startFilledIndex = -1;
            // inaRun = false;
            // i = j;
            // break;
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