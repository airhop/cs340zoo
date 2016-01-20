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
  
  
  void drawDevCard(){}
  void placeCity(){}
  void placeRoad(){}
  void placeSettlement(){}
  void playCard(){}
  void discardCard(){}
  void trade(){}
  void purchase(){}
  void endTurn(){}
  void playMonument(){}
  
  //getters and setters
}
