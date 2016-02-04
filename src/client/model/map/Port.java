package client.model.map;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;

public class Port {
  
  HexLocation location;
  String resource;
  VertexDirection direction;
  int ratio;  //ratio type?  what is ratio for?
  //do we need an owner value as well?
  
  public Port(int x, int y, String resrc, VertexDirection dir, int rat)
  {
	  location = new HexLocation(x,y);
	  resource = resrc;
	  direction = dir;
	  ratio = rat;
  }

  public HexLocation getLocation() 
  {
	  return location;
  }

  public void setLocation(HexLocation location) 
  {
	  this.location = location;
  }

  public String getResource() 
  {
	  return resource;
  }

  public void setResource(String resource) 
  {
	  this.resource = resource;
  }

  public VertexDirection getDirection()
  {
	  return direction;
  }

  public void setDirection(VertexDirection direction)
  {
	  this.direction = direction;
  }

  public int getRatio() 
  {
	  return ratio;
  }

  public void setRatio(int ratio) 
  {
	  this.ratio = ratio;
  }
  
}
