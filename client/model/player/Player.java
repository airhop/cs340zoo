package clientmodel.player;

public class Player {
  
  //player qualities
  String color;
  String name;
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
  void drawDevCard(){}
  /**
  * Places a city on the map if it is a legal move. 
  * 
  */
  void placeCity(){}
  /**
  * Places a road on the map if it is a legal move.
  * 
  */
  void placeRoad(){}
  /**
  * Places a settlement on the map if it is a legal move.
  * 
  */
  void placeSettlement(){}
  /**
  * uses a development card.
  * 
  */
  void playDevCard(){}
  /**
  * discards the card
  * 
  */
  void discardCard(){}
  
    /**
    * trades with one other player for given resources
    * ClientModel will call this method when the canAcceptTrade is called
    * 
    * @param TradeOffer offer - the offer from another player to dictate the trade
    * @return accomplished - if trade was done or not
    */
  boolean trade(TradeOffer offer){ return false;}
  /**
  * purchases a resource from the port based on a ratio
  * 
  * @param ResourceList resource - the resource to be purchased
  * @param Port portTile - the port tile to determine the ratio of trade
  */
  void purchaseFromPort(ResourceList resource, Port portTile){}
    /**
  * purchases a resource from the bank
  * 
  * @param ResourceList resource - the resource to be purchased
  */
  void purchaseFromBank(ResourceList resource){}
  /**
  * ends a players turn
  * 
  */
  void endTurn(){}
  /**
  * rolls the dice at the beginning of the turn
  * 
  */
  void rollDice(){}
  /**
  * uses Monopoly
  * @return boolean whether or not the player played a monopoly
  */
  boolean playMonopoly(){return false;}
  /**
  * plays the road build card
  * @return boolean 
  */
  boolean playRoadBuilding(){return false;}
  /**
  * plays a monument card
  * @return boolean whether or not the player placed a monument
  */
  boolean placeMonument(){return false;}
  /**
  * plays the year of plenty card for a given player
  * @return boolean whether or not the player played the year of plenty card
  */
  boolean playYearOfPlenty(){return false;}
  /**
  * Places a Soldier and grants the effects he brings
  * @return boolean whether or not the player played the soldier card
  */
  boolean placeSoldier(){return false;}
  /**
  * Robs a player of one resource card
  * @return boolean whether or not the player chose to rob 
  */
  boolean rob(){return false;}
  /**
  * Places a the robber at a specific location on the map
  * @return boolean whether or not the player moved the robber
  */
  boolean moveRobber(){return false;} 
  /**
  * ends the game and congratulates the player with 10 victory points
  * @return boolean whether or not the player has sufficient victory points to win
  */
  boolean win(){return false;}
  /**
    * Checks to see if buying a Developement Card is a legal move for the player
    * @return boolean whether or not the player can buy a Developement card
    */
  boolean canBuyDevcard(){return false;} 
  /**
  * Checks to see if playing a Developement Card is a legal move for the player
  * @return boolean whether or not the player can play a Developement card
  */
  boolean canPlayDevcard(){return false;}
  /**
  * Checks to see if Montoply is a legal move for the player
  * @return boolean whether or not the player can monopoly
  */
  boolean canMonopoly(){return false;} 
  /**
  * Checks to see if building a road in a specific place is a legal move for the player
  * @return boolean whether or not the player can road building
  */
  boolean canRoadBuilding(){return false;}
  /**
  * Checks to see if placing a Monument Card(?) is a legal move for the player
  * @return boolean whether or not the player can place a monument
  */
  boolean canPlaceMonument(){return false;}
  /**
  * Checks to see if placing a Year Of Plenty card is a legal move for the player
  * @return boolean whether or not the player can play the Year of Plenty card
  */
  boolean canYearOfPlenty(){return false;}
  /**
  * Checks to see if placing a Soldier card is a legal move for the player
  * @return boolean whether or not the player can place the Soldier card
  */
  boolean canPlaceSoldier(){return false;}
  /**
  * Checks to see if robbing another player is a legal move for the player
  * @return boolean whether or not the player can rob another player
  */
  boolean canRob(){return false;}
  /**
  * Checks to see if moving the robber is a legal move for the player
  * @return boolean whether or not the player can move the robber
  */
  boolean canMoveRobber(){return false;}
  /**
  * Checks to see if trading resource cards with another player is a legal move for the player
  * @return boolean whether or not the player can trade with another player
  */
  boolean canTradePlayer(){return false;}
  /**
  * Checks to see if trading resources with the bank is a legal move for the player
  * @return boolean whether or not the player can trade with the bank
  */
  boolean canTradeBank(){return false;} 
  /**
  * Checks to see accepting a trade request is a legal move for the player
  * @return boolean whether or not the player can accept a trade offer from another player
  */
  boolean canAcceptTrade(){return false;}
  /**
  * Checks to see if the player can win the game
  * @return boolean whether or not the player can win (have 10 victory points)
  */
  boolean canWin(){return false;} 
  /**
  * Checks to see if the player can roll the dice
  * @return boolean whether or not the player can roll the dice
  */
  boolean canRoll(){return false;}
  /**
  * Checks to see if building a city is a legal move for the player
  * @return boolean whether or not the player can build a city
  */
  boolean canBuildCity(){return false;}
  /**
  * Checks to see if building a settlement is a legal move for the player
  * @return boolean whether or not the player can build a settlement
  */
  boolean canBuildSettlement(){return false;}
  /**
  * Checks to see if building a road is a legal move for the player
  * @return boolean whether or not the player can build a road
  */
  boolean canBuildRoad(){return false;}
  //getters and setters

}
