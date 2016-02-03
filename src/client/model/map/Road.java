package client.model.map;

import shared.locations.EdgeLocation;

public class Road {
  int owner;
  EdgeLocation location;
  
//  public Road(int x, int y, int id)
//  {
//    location = new EdgeLocation(0,0,"");
//    owner = id;
//  }
  public Road(EdgeLocation edgeLocation, int id)
  {
	  location = edgeLocation;
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
