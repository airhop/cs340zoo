package client.model.player;

import client.model.bank.DevCardList;
import client.model.bank.ResourceList;
import client.model.map.Port;
import client.model.misc.TradeOffer;

public class Player {
  
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
  
  /**
  * adds a devCard to the newDevCards DevCardList
  * 
  *
  */
  public void drawDevCard(){}
  /**
  * Places a city on the map if it is a legal move. 
  * 
  */
  public void placeCity(){}
  /**
  * Places a road on the map if it is a legal move.
  * 
  */
  public void placeRoad(){}
  /**
  * Places a settlement on the map if it is a legal move.
  * 
  */
  public void placeSettlement(){}
  /**
  * uses a development card.
  * 
  */
  public void playDevCard(){}
  /**
  * discards the card
  * 
  */
  public void discardCard(){}

  /**
   * trades with one other player for given resources
   * ClientModel will call this method when the canAcceptTrade is called
   * @param offer
   * @return
   */
  public boolean trade(TradeOffer offer){ return false;}

  /**
   * purchases a resource from the port based on a ratio
   * @param resource
   * @param portTile
   */
  public void purchaseFromPort(ResourceList resource, Port portTile){}

  /**
   * purchases a resource from the bank
   * @param resource
   */
  public void purchaseFromBank(ResourceList resource){}
  /**
  * rolls the dice at the beginning of the turn
  * 
  */
  public void rollDice(){}
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
  * ends the game and congratulates the player with 10 victory points
  * @return boolean whether or not the player has sufficient victory points to win
  */
  public boolean win(){return false;}
  /**
    * Checks to see if buying a Developement Card is a legal move for the player
    * @return boolean whether or not the player can buy a Developement card
    */
  public boolean canBuyDevcard(){return false;}
  /**
  * Checks to see if playing a Developement Card is a legal move for the player
  * @return boolean whether or not the player can play a Developement card
  */
  public boolean canPlayDevcard(){return false;}
  /**
  * Checks to see if Montoply is a legal move for the player
  * @return boolean whether or not the player can monopoly
  */
  public boolean canMonopoly(){return false;}
  /**
  * Checks to see if building a road in a specific place is a legal move for the player
  * @return boolean whether or not the player can road building
  */
  public boolean canRoadBuilding(){return false;}
  /**
  * Checks to see if placing a Monument Card(?) is a legal move for the player
  * @return boolean whether or not the player can place a monument
  */
  public boolean canPlaceMonument(){return false;}
  /**
  * Checks to see if placing a Year Of Plenty card is a legal move for the player
  * @return boolean whether or not the player can play the Year of Plenty card
  */
  public boolean canYearOfPlenty(){return false;}
  /**
  * Checks to see if placing a Soldier card is a legal move for the player
  * @return boolean whether or not the player can place the Soldier card
  */
  public boolean canPlaceSoldier(){return false;}
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
  public boolean canTradePlayer(){return false;}
  /**
  * Checks to see if trading resources with the bank is a legal move for the player
  * @return boolean whether or not the player can trade with the bank
  */
  public boolean canTradeBank(){return false;}
  /**
  * Checks to see accepting a trade request is a legal move for the player
  * @return boolean whether or not the player can accept a trade offer from another player
  */
  public boolean canAcceptTrade(){return false;}
  /**
  * Checks to see if the player can win the game
  * @return boolean whether or not the player can win (have 10 victory points)
  */
  public boolean canWin(){return false;}
  /**
  * Checks to see if the player can roll the dice
  * @return boolean whether or not the player can roll the dice
  */
  public boolean canRoll(){return false;}
  /**
  * Checks to see if building a city is a legal move for the player
  * @return boolean whether or not the player can build a city
  */
  public boolean canBuildCity(){return false;}
  /**
  * Checks to see if building a settlement is a legal move for the player
  * @return boolean whether or not the player can build a settlement
  */
  public boolean canBuildSettlement(){return false;}
  /**
  * Checks to see if building a road is a legal move for the player
  * @return boolean whether or not the player can build a road
  */
  public boolean canBuildRoad(){return false;}
  //getters and setters
  /**
  * Checks to see if the player can be robbed by another player
  * @return boolean whether or not the player can be robbed
  */
  public boolean canBeRobbed(){return false;}
}
