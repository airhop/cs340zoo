package client.model.map;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class Hex {
  String resource;
  int chit;
  HexLocation hl;

  public Hex()//desert tile
  {
    resource = HexType.DESERT.toString();
    chit = 0;
  }

  public Hex(int x, int y)
  {
    hl = new HexLocation(x, y);
    chit = 0;
    resource = HexType.DESERT.toString();
  }

  public Hex(String resrc, int num)
  {
    resource = resrc.toUpperCase();
    chit = num;
  }

  public Hex(String resc, int num, HexLocation HL)
  {
    resource = resc;
    chit = num;
    HL = hl;
  }

  public Hex(int x, int y, String resc, int num)
  {
    hl = new HexLocation(x, y);
    resource = resc;
    chit = num;
  }
  //Getters and Setters

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

  public HexLocation getLocation()  {return hl; }
  public void setLocation(HexLocation HL)  {hl = HL; }
  public String toString()
  {
    return hl.getX() + " " + hl.getY() + " " + resource + " " + chit;
  }
}
