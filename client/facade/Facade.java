package clientmodel.client;

public class Facade {

    //can methods
    /**
    * Checks to see if building a road is a legal move for the player
    * @return boolean whether or not the player can build a road
    */
    public boolean canBuildRoad(){return false;} 
    /**
    * Checks to see if placing a road is a legal move for the player
    * @return boolean whether or not the player can place a road
    */
    public boolean canPlaceRoad(){return false;}
    /**
    * Checks to see if building a settlement is a legal move for the player
    * @return boolean whether or not the player can build a settlement
    */
    public boolean canBuildSettlement(){return false;}
    /**
    * Checks to see if placing a settlement is a legal move for the player
    * @return boolean whether or not the player can place a settlement
    */
    public boolean canPlaceSettlement(){return false;}
    /**
    * Checks to see if building a city is a legal move for the player
    * @return boolean whether or not the player can build a city
    */
    public boolean canBuildCity(){return false;}
    /**
    * Checks to see if placing a city is a legal move for the player
    * @return boolean whether or not the player can place a city
    */
    public boolean canPlaceCity(){return false;}
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
    
    //do methods
    /**
    * Places a Road at a given location on the map
    * @return boolean whether or not the player built the road (perhaps placeholder return values for all of the do methods)
    */
    public boolean placeRoad(){return false;}
    /**
    * Places a Settlement at a given location on the map
    * @return boolean whether or not the player placed a settlement
    */
    public boolean placeSettlement(){return false;}
    /**
    * Places a City at a given location on the map
    * @return boolean whether or not the player placed the city
    */
    public boolean placeCity(){return false;}
    /**
    * Buys a developement card and increases the amount for the purchasing player
    * @return boolean whether or not the player bought the dev card
    */
    public boolean buyDevCard(){return false;}
    /**
    * uses a specific development card
    * @return boolean whether or not the player played a devcard
    */
    public boolean playDevCard(){return false;}
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
    * enacts the trade offer of the specified player
    * @return boolean whether or not the player traded with another player
    */
    public boolean tradePlayer(){return false;}
    /**
    * completes a transaction of resources with the bank
    * @return boolean whether or not the player traded with the bank
    */
    public boolean tradeBank(){return false;}
    /**
    * accepts the trade offer of another player
    * @return boolean whether or not the player accepted a trade offer
    */
    public boolean acceptTrade(){return false;}
    /**
    * ends the game and congratulates the player with 10 victory points
    * @return boolean whether or not the player has sufficient victory points to win
    */
    public boolean win(){return false;}
    /**
    * rolls the dice for a number 1-12
    * @return boolean whether or not the player rolled the dice
    */
    public boolean roll(){return false;}
}
