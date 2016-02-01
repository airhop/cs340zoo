
package client.model.map;

public class HexLocation {
  int x;
  int y;

  public HexLocation(int X, int Y)
  {
    x = X;
    y = Y;
  }
  //getters and setters

  /**@param hl - hexlocation to compare to
  compare to another hexlocation to see if they are the same for the robber
  @return whether they are the same or not
  */
  public boolean compareTo(HexLocation hl) 
  { 
	  return false; 
  }

  public int getX() 
  {
	return x;
  }

  public void setX(int x) 
  {
	this.x = x;
  }

  public int getY() 
  {
	return y;
  }

  public void setY(int y) 
  {
	this.y = y;
  }
  
}
