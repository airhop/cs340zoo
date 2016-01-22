
package client.model.map;

public class HexLocation {
  int x;
  int y;
  
  HexLocation(int X, int Y)
  {
    x = X;
    y = Y;
  }
  //getters and setters

  /*@param hl - hexlocation to compare to
  compare to another hexlocation to see if they are the same for the robber
  @return whether they are the same or not
  */
  boolean compareTo(HexLocation hl) { return false; }
}
