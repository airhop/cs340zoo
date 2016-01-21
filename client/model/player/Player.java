package clientmodel.player;

public class Player {
  
  //player qualities
  String color;
  String name;
  int playerID;
  
  //placeable items
  int cities;
  int roads;
  int settlements;
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
    * uses a card.
    * 
    */
  void playCard(){}
    /**
    * discards the card
    * 
    */
  void discardCard(){}
    /**
    * trades with one other player for a given ratio.
    * 
    * @param int ratio the ratio that will decide how many cards are traded
    * @param ResourceList resource - the resources that will be traded
    * @param int playerIndex - the index of the player that the current player will be trading with
    */
  void trade(int ratio, ResourceList resource, int playerIndex){}
    /**
    * purchases a resource from the bank
    * 
    * @param ResourceList resource - the resource to be purchased
    * @param Port portTile - the port tile to determine the ratio of trade
    */
  void purchase(ResourceList resource, Port portTile){}
    /**
    * ends a players turn
    * 
    */
  void endTurn(){}
    /**
    * plays a monument from the players hand
    * 
    */
  void playMonument(){}
  
  //getters and setters
}
