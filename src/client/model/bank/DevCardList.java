
package client.model.bank;

import shared.exceptions.InsufficientResourcesException;

public class DevCardList {
  
  int monopoly = -1;
  int monument = -1;
  int roadBuilding = -1;
  int soldier = -1;
  int yearOfPlenty = -1;
  
  DevCardList(int mono, int monu, int road, int sldr, int yop)
  {
    monopoly = mono;
    monument = monu;
    roadBuilding = road;
    soldier = sldr;
    yearOfPlenty = yop;
  }
  /**
   * this method will randomly decide which card the bank will give
  @return cardtype of card to be returned
  */
  public String buyDevCard() throws InsufficientResourcesException
  {
    return null;
  }
  
  /**
   * @return can a devcard be purchased
  */
  public boolean canBuyDevCard(ResourceList playersHand)
  {
    int ore = playersHand.getOre();
    int sheep = playersHand.getSheep();
    int wheat = playersHand.getWheat();
    if(ore < 1 || sheep < 1 || wheat < 1)
    {
    return false;
    }
    return true;
  }
  
  /**
  @param type of card that wants to be used
  probably should have multiple methods, but this will go though a giant switch on the enum types
  @return if can use a DevCard
  */
  public boolean canUseDevCard(String type)
  {
    return false;
  }
  
  //useDevCard is basically using the get/set.  The facade can take care of the rest of the stuff
  
  public int getSize()
  {
    int total = 0;
    total = total + monopoly + monument + yearOfPlenty + roadBuilding + soldier;
    return total;
  }
  
  public int getMonopoly() 
  {
    return monopoly;
  }
  public void setMonopoly(int monopoly) 
  {
	this.monopoly = monopoly;
  }
  public int getMonument()
  {
	return monument;
  }
  public void setMonument(int monument)
  {
	this.monument = monument;
  }
  public int getRoadBuilding
  {
	return roadBuilding;
  }
  public void setRoadBuilding(int roadBuilding) 
  {
	this.roadBuilding = roadBuilding;
  }
  public int getSoldier()
  {
	return soldier;
  }
  public void setSoldier(int soldier) 
  {
	this.soldier = soldier;
  }
  public int getYearOfPlenty()
  {
	return yearOfPlenty;
  }
  public void setYearOfPlenty(int yearOfPlenty)
  {
	this.yearOfPlenty = yearOfPlenty;
  }
}
