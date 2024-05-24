
/**
 * Represents a tile in the game Aquarium. 
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class AquariumCell{

  /*
   * tank: Each AquariumCell is a part of an AquariumTank that makes water flood to adjacent or lower 
   *   AquariumCells, and makes air float to adjacent or higher AquariumCells.
   */
  private AquariumTank tank = null;
  private Cell cell;
  private int x; // 0 to width - 1 (inclusive)
  private int y; // 0 to height - 1 (inclusive)
  
  public AquariumCell(int newx, int newy){
    cell = Cell.UNMARKED;
    x = newx;
    y = newy;
  }

  public int getX(){return x;}
  public int getY(){return y;}
  public Cell getCell(){return cell;}
  public AquariumTank getTank(){return tank;}

  public void setCell(Cell newCell, boolean flood){
    cell = newCell.copy();
    if(flood){tank.updateFrom(this);}
  }

  public String toString(){
    return (cell.isUnmarked()) ? " " : cell.toString();
  }

  /*
   * A recursive method initiated by an AquariumTank to fill it with all the AquariumCells it should 
   * have by making each AquariumCell in it add their neighbors as appropriate, and making each new neighbor 
   * repeat the process.
   */
  public void setTank(AquariumTank newTank){
    if(tank == null){ // if not already in a tank,
      tank = newTank;
      tank.add(this); // join the tank,
      AquariumBoard aq = AquariumTank.getAquariumBoard();
      
      if(aq.isValidCoordinate(x, y-1) && aq.isHWallEmpty(x,y)){ // and then add neighbors if they are not blocked by a wall.
        aq.setAquariumCellsTank(x, y-1, tank);
      }
      if(aq.isValidCoordinate(x, y+1) && aq.isHWallEmpty(x,y+1)){
        aq.setAquariumCellsTank(x, y+1, tank);
      }
      if(aq.isValidCoordinate(x+1, y) && aq.isVWallEmpty(x+1,y)){
        aq.setAquariumCellsTank(x+1, y, tank);
      }
      if(aq.isValidCoordinate(x-1, y) && aq.isVWallEmpty(x,y)){
        aq.setAquariumCellsTank(x-1, y, tank);
      }
    }
  }
}