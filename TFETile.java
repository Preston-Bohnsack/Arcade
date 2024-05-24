import java.util.*;

/**
 * The TFETile represents a single tile in the game 2048 (TFE).
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class TFETile{

  /**
   * value is the numerical value typically shown when playing TFE. TFETiles start with a value of 2, 
   * and double for each merge, eg. 2, 4, 8, 16...
   */
  private int value;

  /**
   * tier represents the 'tier' of a TFETile, and is also one less than the number of merges a TFETile 
   * has gone through. TFETiles start with a tier of 1 and increment it each merge, eg. 1, 2, 3, 4...
   */
  private int tier;

  /**
   * Creates a TFETile object with the appropriate value and tier.
   * Precondition: tier >= 1.
   * 
   * @param rank The tier the new TFETile will be.
   */
  public TFETile(int rank){
    tier = rank;
    value = ((int) Math.pow(2, tier));
  }

  public int getTier(){return tier;}
  public int getValue(){return value;}

  public boolean equals(TFETile other){
    return ((other != null) && (tier == other.getTier()));
  }


  /**
   * Increases a TFETile's value and tier as appropriate when merging.
   */
  public void merge(){
    tier++;
    value *= 2;
  }

  /**
   * Creates a TFETile object to be placed into an empty spot on the board. 10% of the time the 
   * TFETile is a 2nd tier one, and the other 90% of the time, the TFETile is a 1st tier one.
   * 
   * @return The TFETile object to be placed into an empty spot on the board.
   */
  public static TFETile generate(){
    return new TFETile(((int)(Math.random() * 1.1)) + 1);
  }


/*
 * toString uses the tier of a TFETile, unlike the original 2048, which uses value, as since this 
 * program uses the console to print the board. This makes managing multiple digit numbers, without 
 * being able to resize text with an actual GUI, is very difficult. Using tier instead of value 
 * delays this, so the user wins when they reach a tier 10 tile, which has a value of 1024, not 2048.
 */
  public String toString(){
    return "" + tier;
  }

}