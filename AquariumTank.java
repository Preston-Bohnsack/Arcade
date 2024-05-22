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

  /**
   * Starts an AquariumTank with its first AquariumCell, that recursively adds others to said
   * AquariumTank. Precondition: 0 <= x <= width - 1, 0 <= y <= height - 1.
   * 
   * @param x The x of the first AquariumCell. Precondition: 0 <= x <= width - 1.
   * @param y The y of the first AquariumCell. Precondition: 0 <= y <= height - 1.
   */
  public AquariumTank(int x, int y){
    acells = new ArrayList<AquariumCell>();
    aquariumBoard.setAquariumCellsTank(x,y,this);
  }

  public void setAquariumBoard(AquariumBoard aqBoard){
    aquariumBoard = aqBoard;
  }

  /**
   * Adds the given AquariumCell to an AquariumTank's ArrayList of AquariumCells.
   * 
   * @param ac The AquariumCell to be added.
   */
  public void add(AquariumCell ac){acells.add(ac);}

  /**
   * Updates all AquariumCells in the tank, when one is changed by the user.
   * 
   * @param acell The AquariumCell initially changed.
   */
  public void updateFrom(AquariumCell acell){
    int y = acell.getY();
    Cell cell = acell.getCell();

    boolean floodDown = cell.isFilled();
    for(AquariumCell ac : acells){
      if((ac.getY() == y) || (((floodDown) && (ac.getY() > y)) || ((!floodDown) && (ac.getY() < y)))){
        // larger y value is lower
        ac.setCell(cell, false);
      }
    }
    
  }
}