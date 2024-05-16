
/**
 * Represents thin walls that can be FILLED or EMPTY.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public enum ThinWall{
  EMPTY(false),
  FILLED(true);

  /*
   * A boolean directly corresponding to which state its ThinWall is in, to make logic more 
   * readable and compact.
   */
  private final boolean filled;

  private ThinWall(boolean bool){filled = bool;}

  /**
   * Returns true if a ThinWall is EMPTY. 
   * 
   * @return If a ThinWall is EMPTY.
   */
  public boolean isEMPTY(){return !filled;}

  /**
   * Returns true if a ThinWall is FILLED. 
   * 
   * @return If a ThinWall is FILLED.
   */
  public boolean isFILLED(){return filled;}
}