package clientmodel.client;

public class Facade {

    //can methods
    /**
    * Checks to see if building a road is a legal move for the player
    * @return boolean 
    */
    public boolean canBuildRoad(){return false;} 
    /**
    * Checks to see if placing a road is a legal move for the player
    * @return boolean 
    */
    public boolean canPlaceRoad(){return false;}
    /**
    * Checks to see if building a settlement is a legal move for the player
    * @return boolean 
    */
    public boolean canBuildSettlement(){return false;}
    /**
    * Checks to see if placing a settlement is a legal move for the player
    * @return boolean 
    */
    public boolean canPlaceSettlement(){return false;}
    /**
    * Checks to see if building a city is a legal move for the player
    * @return boolean 
    */
    public boolean canBuildCity(){return false;}
    /**
    * Checks to see if placing a city is a legal move for the player
    * @return boolean 
    */
    public boolean canPlaceCity(){return false;}
    /**
    * Checks to see if buying a Developement Card is a legal move for the player
    * @return boolean 
    */
    public boolean canBuyDevcard(){return false;} 
    /**
    * Checks to see if playing a Developement Card is a legal move for the player
    * @return boolean 
    */
    public boolean canPlayDevcard(){return false;}
    /**
    * Checks to see if Montoply is a legal move for the player
    * @return boolean 
    */
    public boolean canMonopoly(){return false;} 
    /**
    * Checks to see if building a road in a specific place is a legal move for the player
    * @return boolean 
    */
    public boolean canRoadBuilding(){return false;}
    /**
    * Checks to see if placing a Monument Card(?) is a legal move for the player
    * @return boolean 
    */
    public boolean canPlaceMonument(){return false;}
    /**
    * Checks to see if placing a Year Of Plenty card is a legal move for the player
    * @return boolean 
    */
    public boolean canYearOfPlenty(){return false;}
    /**
    * Checks to see if placing a Soldier card is a legal move for the player
    * @return boolean 
    */
    public boolean canPlaceSoldier(){return false;}
    /**
    * Checks to see if robbing another player is a legal move for the player
    * @return boolean 
    */
    public boolean canRob(){return false;}
    /**
    * Checks to see if moving the robber is a legal move for the player
    * @return boolean 
    */
    public boolean canMoveRobber(){return false;}
    /**
    * Checks to see if trading resource cards with another player is a legal move for the player
    * @return boolean 
    */
    public boolean canTradePlayer(){return false;}
    /**
    * Checks to see if trading resources with the bank is a legal move for the player
    * @return boolean 
    */
    public boolean canTradeBank(){return false;} 
    /**
    * Checks to see accepting a trade request is a legal move for the player
    * @return boolean 
    */
    public boolean canAcceptTrade(){return false;}
    /**
    * Checks to see if the player can win the game
    * @return boolean 
    */
    public boolean canWin(){return false;} 
    /**
    * Checks to see if the player can roll the dice
    * @return boolean 
    */
    public boolean canRoll(){return false;}
    
    //do methods
    /**
    * Places a Road at a given location on the map
    * @return boolean 
    */
    public boolean placeRoad(){return false;}
    /**
    * Places a Settlement at a given location on the map
    * @return boolean 
    */
    public boolean placeSettlement(){return false;}
    /**
    * Places a City at a given location on the map
    * @return boolean 
    */
    public boolean placeCity(){return false;}
    /**
    * Buys a developement card and increases the amount for the purchasing player
    * @return boolean 
    */
    public boolean buyDevCard(){return false;}
    /**
    * uses a specific development card
    * @return boolean 
    */
    public boolean playDevCard(){return false;}
    /**
    * uses Monopoly
    * @return boolean 
    */
    public boolean playMonopoly(){return false;}
    /**
    * 
    * @return boolean 
    */
    public boolean playRoadBuilding(){return false;}
    /**
    * plays a monument card
    * @return boolean 
    */
    public boolean placeMonument(){return false;}
    /**
    * plays the year of plenty card for a given player
    * @return boolean 
    */
    public boolean playYearOfPlenty(){return false;}
    /**
    * Places a Soldier and grants the effects he brings
    * @return boolean 
    */
    public boolean placeSoldier(){return false;}
    /**
    * Robs a player of one resource card
    * @return boolean 
    */
    public boolean rob(){return false;}
    /**
    * Places a the robber at a specific location on the map
    * @return boolean 
    */
    public boolean moveRobber(){return false;}
    /**
    * enacts the trade offer of the specified player
    * @return boolean 
    */
    public boolean tradePlayer(){return false;}
    /**
    * completes a transaction of resources with the bank
    * @return boolean 
    */
    public boolean tradeBank(){return false;}
    /**
    * accepts the trade offer of another player
    * @return boolean 
    */
    public boolean acceptTrade(){return false;}
    /**
    * ends the game and congratulates the player with 10 victory points
    * @return boolean 
    */
    public boolean win(){return false;}
    /**
    * rolls the dice for a number 1-12
    * @return boolean 
    */
    public boolean roll(){return false;}
}
