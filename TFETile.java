import java.util.*;

/**
 * Represents a single Tile in the game TFE
 */
public class TFETile{
  /**
   * value is the numerical value of a Piecetypically shown when playing TFE. Tiles start with a 
   * value of 2, and increase by a factor of 2 each time they merge, eg. 2, 4, 8, 16... 
   */
  private int value;

  /**
   * tier represents the rank of a Tile, and is also equivalent to the number of merges a Tile 
   * has gone through - 1. Tiles start with a tier of 1 and increase by 1 each time they merge,
   * eg. 1, 2, 3, 4
   */
  private int tier;

  public TFETile(int rank){
    tier = rank;
    value = ((int) Math.pow(2, tier));
  }

  public int getTier(){return tier;}
  public int getValue(){return value;}

  public boolean equals(TFETile other){
    return ((other != null) && (tier == other.getTier()));
  }
  
  public static boolean equals(TFETile a, TFETile b){
    return ((a != null) && (a.equals(b)));
  }

  /**
   * Each time after a player gives an input, Tiles are merged, then shifted over, during the merging 
   * 1 of the 2 Tiles will be set to empty, and the other will go to the next tier, via this method. 
   */
  public void merge(){
    tier++;
    value *= 2;
  }

  /**
   * Each time after a player gives an input, and Tiles on the board have been shifted appropriately, 
   * a single empty Tile will be filled. 10% of the time the new Tile has a value of 4 and tier of 2. 
   * The other 90% of the time the new Tile has a value of 2 and a tier of 1. This method creates the 
   * new Tile with the appropriate random values.
   */
  public static TFETile generate(){
    return ((Math.random() < 0.1) ? (new TFETile(2)) : (new TFETile(1)));
  }

  /** 
   * The default merge method uses tier, because the AutomatedBoard uses the console. It would be
   * very difficult to make a board look good with many different length numbers using only
   * monospaced text. 
   */
  public String toString(){
    String str = "" + tier;
    return str;
  }
  
  public String toString(boolean useTier){
    String str = "" + ((useTier) ? tier : value);
    return str;
  }

}