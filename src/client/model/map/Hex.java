package client.model.map;

public class Hex {
  
  shared.locations.HexLocation location;
  String resource;
  int chit;

  public Hex(int x, int y, String resrc, int num)
  {
    location = new  shared.locations.HexLocation(x,y);
    resource = resrc;
    chit = num;
  }
  //Getters and Setters


  public shared.locations.HexLocation getLocation() 
  {
	  return location;
  }

  public void setLocation(shared.locations.HexLocation location) 
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

  public int getNumber()
  {
	  return chit;
  }

  public void setNumber(int chit)
  {
	  this.chit = chit;
  }
  
}
