/**
 * Represents a single thin wall in games.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public enum ThinWall{
  EMPTY(false),
  FILLED(true);

  private final boolean filled;

  private ThinWall(boolean bool){filled = bool;}
  public boolean isEMPTY(){return !filled;}
  public boolean isFILLED(){return filled;}
}