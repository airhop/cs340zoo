package client.model.map;

public class Hex {
  
  shared.locations.HexLocation location;
  String resource;
  String number;

  public Hex(int x, int y, String resrc, String num)
  {
    location = new  shared.locations.HexLocation(x,y);
    resource = resrc;
    number = num;
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

  public String getNumber() 
  {
	  return number;
  }

  public void setNumber(String number) 
  {
	  this.number = number;
  }
  
}
