package client.model.map;

public class Road {
  int owner;
  shared.locations.EdgeLocation location;
  
  public Road(int x, int y, int id)
  {
    location = new EdgeLocation(0,0, "");
    owner = id;
  }

  public int getOwner()
  {
	  return owner;
  }

  public void setOwner(int owner)
  {
	  this.owner = owner;
  }

  public EdgeLocation getLocation()
  {
	  return location;
  }

  public void setLocation(EdgeLocation location) 
  {
	  this.location = location;
  }

}
