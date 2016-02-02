package client.model.player;

import client.model.bank.DevCardList;
import client.model.bank.ResourceList;
import client.model.map.Port;
import client.model.misc.TradeOffer;
import shared.exceptions.InsufficientResourcesException;

public class Player {
  public Player(String playerName, int ID)
  {
    this.setName(playerName);
    this.setPlayerID(ID);
  }
  public Player(String newColor, String newName, String newPassword, int newBiggestRoadLength, int ID, int newCityAmount, int newRoadAmount, int newSettlementAmount, int newMonumentAmount,
                int newSoldierAmount, DevCardList newNewDevCardList, DevCardList newOldDevCardList, boolean newPlayedDevCard, ResourceList newResourcesAmounts, boolean newDiscarded, int newVictoryPointAmount)
  {
    this.setColor(newColor);
    this.setName(newName);
    this.setPassword(newPassword);
    this.setBiggestRoadLength(newBiggestRoadLength);
    this.setPlayerID(ID);
    this.setCities(newCityAmount);
    this.setRoads(newRoadAmount);
    this.setSettlements(newSettlementAmount);
    this.setMonuments(newMonumentAmount);
    this.setSoldiers(newSoldierAmount);
    this.setNewDevCards(newNewDevCardList);
    this.setOldDevCards(newOldDevCardList);
    this.setPlayedDevCard(newPlayedDevCard);
    this.setResources(newResourcesAmounts);
    this.setDiscarded(newDiscarded);
    this.setVictoryPoints(newVictoryPointAmount);
  }

  //player qualities
  String color;
  String name;
  String password;
  int biggestRoadLength;
  int playerID;
  
  //placeable items
  int cities;//how many cities the player has left to play
  int roads;//how many roads the player has left to play
  int settlements;//how many settlements the player has left to play
  //cards, resources, etc
  int monuments;
  int soldiers;
  DevCardList newDevCards;
  DevCardList oldDevCards;
  boolean playedDevCard;
  ResourceList resources;
  boolean discarded;
  int victoryPoints;
  //these are the maximum values a player can have in a game and also the starting amount
  final int MAX_CITIES = 4;
  final int MAX_SETTLEMENTS = 5;
  final int MAX_ROADS = 15;
  /**
  * adds a devCard to the newDevCards DevCardList
  * 
  *
  */
  public void drawDevCard() throws InsufficientResourcesException {
    if(resources.getOre() < 1 || resources.getWheat() < 1 || resources.getSheep() < 1)
    {
      throw new InsufficientResourcesException();
    }
    else
    {
      newDevCards.buyDevCard();
    }

  }
  public void addResource(String resourceType, int numberOfResource)
  {
    //switch statement for each resource type adding them to the resource list
  }
  public void depleteResource(String resourceType)
  {
    //switch statement to set the resource that matches the resource type to 0
  }
  /**
  * Places a city on the map if it is a legal move. 
  * 
  */
  public void placeCity() throws InsufficientResourcesException {
    if(cities < 1)
    {
      throw new InsufficientResourcesException();
    }
    else
    {
      cities--;
    }

  }
  /**
  * Places a road on the map if it is a legal move.
  * 
  */
  public void placeRoad() throws InsufficientResourcesException {
    if(roads < 1)
    {
      throw new InsufficientResourcesException();
    }
    else
    {
      roads--;
    }
  }
  /**
  * Places a settlement on the map if it is a legal move.
  * 
  */
  public void placeSettlement() throws InsufficientResourcesException {
    if(settlements < 1)
    {
      throw new InsufficientResourcesException();
    }
    else
    {
      settlements--;
    }
  }
  /**
  * uses a development card.
  * 
  */
  public void playDevCard(String cardType)
  {
    //if(oldDevCards.exists(cardType) && playedDevCard == false);
    //oldDevCards.remove(cardType);
    playedDevCard = true;
  }
  /**
  * discards the card
  * 
  */
  public void discardCard(String cardType)
  {
    if(playedDevCard = true)
    {
      //card
    }
  }

  /**
   * trades with one other player for given resources
   * ClientModel will call this method when the canAcceptTrade is called
   * @param
   * @return
   */
  public TradeOffer trade()
  {
    return null;
  }
  public void AcceptTrade(ResourceList resourcesToTrade)
  {

  }

  /**
   * purchases a resource from the bank
   * @param resource
   */
  public void purchaseFromBank(ResourceList resource){}

  /**
  * uses Monopoly
  * @return boolean whether or not the player played a monopoly
  */
  public boolean playMonopoly(){return false;}
  /**
  * plays the road build card
  * @return boolean 
  */
  public boolean playRoadBuilding(){return false;}
  /**
  * plays a monument card
  * @return boolean whether or not the player placed a monument
  */
  public boolean placeMonument(){return false;}
  /**
  * plays the year of plenty card for a given player
  * @return boolean whether or not the player played the year of plenty card
  */
  public boolean playYearOfPlenty(){return false;}
  /**
  * Places a Soldier and grants the effects he brings
  * @return boolean whether or not the player played the soldier card
  */
  public boolean placeSoldier(){return false;}
  
  /**
   * If the player has more then 7 cards when the robber is moved half the cards must be discarded
   * @return boolean whether or not it is true
  */
  public boolean canRobberDiscard()
  {
    return false;
  }
  
  /**
   *Player  must discard half their hand if the robber was moved and there are more then 7 cards in hand 
   */
  public void RobberDiscard()
  {
    return;
  }
  
  /**
  * Robs a player of one resource card
  * @return boolean whether or not the player chose to rob 
  */
  public boolean rob(){return false;}
  /**
  * Places a the robber at a specific location on the map
  * @return boolean whether or not the player moved the robber
  */
  public boolean moveRobber(){return false;}
  /**
    * Checks to see if buying a Developement Card is a legal move for the player
    * @return boolean whether or not the player can buy a Developement card
    */
  public boolean canBuyDevcard()
  {
    return false;
  }
  /**
  * Checks to see if playing a Developement Card is a legal move for the player
  * @return boolean whether or not the player can play a Developement card
  */
  public boolean canPlayDevcard()
  {
    if(oldDevCards.getSize() > 0 || newDevCards.getSize() > 0)
    {
      return true;
    }
    return false;
  }
  /**
  * Checks to see if Montoply is a legal move for the player
  * @return boolean whether or not the player can monopoly
  */
  public boolean canMonopoly(){return false;}
  /**
  * Checks to see if building a road in a specific place is a legal move for the player
  * @return boolean whether or not the player can road building
  */
  public boolean canPlayRoadBuilding()
  {
    return false;
  }
  /**
  * Checks to see if placing a Monument Card(?) is a legal move for the player
  * @return boolean whether or not the player can place a monument
  */
  public boolean canPlaceMonument()
  {
    if(victoryPoints >= 10)
    {
      return true;
    }
    return false;
  }
  /**
  * Checks to see if placing a Year Of Plenty card is a legal move for the player
  * @return boolean whether or not the player can play the Year of Plenty card
  */
  public boolean canYearOfPlenty(){return false;}
  /**
  * Checks to see if placing a Soldier card is a legal move for the player
  * @return boolean whether or not the player can place the Soldier card
  */
  public boolean canPlaceSoldier()
  {
    if(oldDevCards.getSoldier() != 0 || newDevCards.getSoldier() != 0)
    {
      return true;
    }
    return false;
  }
  /**
  * Checks to see if robbing another player is a legal move for the player
  * @return boolean whether or not the player can rob another player
  */
  public boolean canRob(){return false;}
  /**
  * Checks to see if moving the robber is a legal move for the player
  * @return boolean whether or not the player can move the robber
  */
  public boolean canMoveRobber(){return false;}
  /**
  * Checks to see if trading resource cards with another player is a legal move for the player
  * @return boolean whether or not the player can trade with another player
  */
  public boolean canTradePlayer()
  {//check port value
    return false;
  }
  /**
  * Checks to see if trading resources with the bank is a legal move for the player
  * @return boolean whether or not the player can trade with the bank
  */
  public boolean canTradeBank(){return false;}
  /**
  * Checks to see accepting a trade request is a legal move for the player
  * @return boolean whether or not the player can accept a trade offer from another player
  */
  public boolean canAcceptTrade()
  {
    return false;
  }
  /**
  * Checks to see if the player can win the game
  * @return boolean whether or not the player can win (have 10 victory points)
  */
  public boolean canWin()
  {
    if(victoryPoints >=10)
    {
      return true;
    }
    return false;
  }
  /**
  * Checks to see if building a city is a legal move for the player
  * @return boolean whether or not the player can build a city
  */
  public boolean canBuildCity()
  {
    if(resources.getOre() > 2 && resources.getWheat() > 1 && settlements < MAX_SETTLEMENTS && cities > 0)
    {
      return true;
    }
    return false;
  }
  /**
  * Checks to see if the player has sufficient resources for a settlement
  * @return boolean whether or not the player can build a settlement
  */
  public boolean canBuildSettlement()
  {
    if(settlements > 0 && resources.getBrick() > 0 && resources.getWood() > 0 && resources.getSheep() > 0 && resources.getWheat() > 0 )
    {
      return true;
    }
    return false;
  }
  /**
  * Checks to see if the player has sufficient resources for a road
  * @return boolean whether or not the player can build a road
  */
  public boolean canBuildRoad()
  {
    if(resources.getBrick() > 0 && resources.getWood() > 0)
    {
      return true;
    }
    return false;
  }
  /**
  * Checks to see if the player can be robbed by another player
  * @return boolean whether or not the player can be robbed
  */
  public boolean canBeRobbed(){return false;}
  public int getRoads() {
    return roads;
  }

  public void setRoads(int roads) {
    this.roads = roads;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getBiggestRoadLength() {
    return biggestRoadLength;
  }

  public void setBiggestRoadLength(int biggestRoadLength) {
    this.biggestRoadLength = biggestRoadLength;
  }

  public int getPlayerID() {
    return playerID;
  }

  public void setPlayerID(int playerID) {
    this.playerID = playerID;
  }

  public int getCities() {
    return cities;
  }

  public void setCities(int cities) {
    this.cities = cities;
  }

  public int getSettlements() {
    return settlements;
  }

  public void setSettlements(int settlements) {
    this.settlements = settlements;
  }

  public int getMonuments() {
    return monuments;
  }

  public void setMonuments(int monuments) {
    this.monuments = monuments;
  }

  public int getSoldiers() {
    return soldiers;
  }

  public void setSoldiers(int soldiers) {
    this.soldiers = soldiers;
  }

  public DevCardList getNewDevCards() {
    return newDevCards;
  }

  public void setNewDevCards(DevCardList newDevCards) {
    this.newDevCards = newDevCards;
  }

  public DevCardList getOldDevCards() {
    return oldDevCards;
  }

  public void setOldDevCards(DevCardList oldDevCards) {
    this.oldDevCards = oldDevCards;
  }

  public boolean isPlayedDevCard() {
    return playedDevCard;
  }

  public void setPlayedDevCard(boolean playedDevCard) {
    this.playedDevCard = playedDevCard;
  }

  public ResourceList getResources() {
    return resources;
  }

  public void setResources(ResourceList resources) {
    this.resources = resources;
  }

  public boolean isDiscarded() {
    return discarded;
  }

  public void setDiscarded(boolean discarded) {
    this.discarded = discarded;
  }

  public int getVictoryPoints() {
    return victoryPoints;
  }

  public void setVictoryPoints(int victoryPoints) {
    this.victoryPoints = victoryPoints;
  }
}
