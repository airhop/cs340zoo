
package client.model.bank;

public class DevCardList {
  
  int monopoly;
  int monument;
  int roadBuilding;
  int soldier;
  int yearOfPlenty;
  int total; //the total number of devcards available
  
  //getters and setters
  /**
   * this method will randomly decide which card the bank will give
  @return cardtype of card to be returned
  */
  public String buyDevCard()
  {
    return "soldier";
  }
  
  /**
   * @return can a devcard be purchased
  */
  public boolean canBuyDevCard()
  {
    return false;
  }
  
  /**
  @param type - type of card that wants to be used
  probably should have multiple methods, but this will go though a giant switch on the enum types
  @return if can use a DevCard
  */
  public boolean canUseDevCard(String type)
  {
    return false;
  }
  
  //useDevCard is basically using the get/set.  The facade can take care of the rest of the stuff
}
