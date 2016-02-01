package client.model.map;

public class Hex {
  
  HexLocation location;
  String resource;
  String number;

  public Hex(int x, int y, String resrc, String num)
  {
    location = new HexLocation(x,y);
    resource = resrc;
    number = num;
  }
  //Getters and Setters


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

  public String getNumber() 
  {
	  return number;
  }

  public void setNumber(String number) 
  {
	  this.number = number;
  }
  
}
