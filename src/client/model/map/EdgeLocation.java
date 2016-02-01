package client.model.map;

public class EdgeLocation 
{
	int x;
	int y;
	String direction;

  public EdgeLocation(int X, int Y, String dir)
  {
	  x = X;
	  y = Y;
	  direction = dir;
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

  public String getDirection() 
  {
	  return direction;
  }

  public void setDirection(String direction) 
  {
	  this.direction = direction;
  }
  
}
