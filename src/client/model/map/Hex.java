package client.model.map;

import shared.definitions.HexType;

public class Hex {
  String resource;
  int chit;

  public Hex()//desert tile
  {
    resource = HexType.DESERT.toString();
    chit = 0;
  }

  public Hex(String resrc, int num)
  {
    resource = resrc.toUpperCase();
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
  
}
