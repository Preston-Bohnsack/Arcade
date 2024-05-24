import java.util.*;

/**
 * Represents a group of AquariumCells that are connected and have water flow between them.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class AquariumTank{
  private ArrayList<AquariumCell> acells;
  public static AquariumBoard aquariumBoard;

  public AquariumTank(int x, int y){
    acells = new ArrayList<AquariumCell>();
    aquariumBoard.setAquariumCellsTank(x,y,this);
  }

  public static void setAquariumBoard(AquariumBoard aqBoard){
    aquariumBoard = aqBoard;
  }
  
  public static AquariumBoard getAquariumBoard(){return aquariumBoard;}

  public void add(AquariumCell ac){acells.add(ac);}

  public void updateFrom(AquariumCell acell){ // if an AquariumCell is changed by a player, flood connected AquariumCells appropriately.
    int y = acell.getY();
    Cell cell = acell.getCell();

    if(cell.isFilled()){ // if the AquariumCell was filled with water, fill AquariumCells below and next to it with water.
      for(AquariumCell ac : acells){
        if(ac.getY() >= y){ // larger y value is lower
          ac.setCell(cell, false);
        }
      }
    }
    else if(!cell.isFilled()){ // if the AquariumCell was filled with air, fill AquariumCells above and next to it with air.
      for(AquariumCell ac : acells){
        if(ac.getY() <= y){ // smaller y value is higher
          ac.setCell(cell, false);
        }
      }
    }


    
    
  }
}