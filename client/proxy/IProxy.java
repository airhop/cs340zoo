package clientmodel.proxy;


public interface IProxy {

    /**
     * Returns the ClientModel when called
     * @return
     */
    public ClientModel getGameModel(PassObject pass){

    }

    /**
     * Given paramaters of the playerID and where to place the road in the PassObject
     * we send the server the information needed to place the road
     * @param pass
     * @return
     */
    public ClientModel placeRoad(PassObject pass){

    }

    /**
     * Given paramaters of the playerID and where to place the Settlement in the PassObject
     * we send the server the information needed to place the Settlement
     * @param pass
     * @return
     */
    public ClientModel placeSettlement(PassObject pass){

    }

    /**
     * Given paramaters of the playerID and where to place the City in the PassObject
     * we send the server the information needed to place the City
     * @param pass
     * @return
     */
    public ClientModel placeCity(PassObject pass){

    }

    /**
     * Parameters are passed for the buying of the DevCard on the PassObject
     * a clientModel is returned
     * @param pass
     * @return
     */
    public ClientModel buyDevCard(PassObject pass){

    }

    /**
     * The parameters that are needed to play a DevCard are passed on the
     * PassObject
     * @param pass
     * @return
     */
    public ClientModel playDevCard(PassObject pass){

    }

    /**
     * To play a Monopoly Card you call this function which contacts the Server proxy
     * @param pass
     * @return
     */
    public ClientModel playMonopoly(PassObject pass){

    }

    /**
     * To play the Card road building you finally get here in the proxy and this finalizes the
     * playing of the card on the server
     * @param pass
     * @return
     */
    public ClientModel playRoadBuilding(PassObject pass){

    }

    /**
     * This method is used to play a victory od monument card finalizing the move on the server
     * @param pass
     * @return
     */
    public ClientModel placeMonument(PassObject pass){

    }

    /**
     * This method finalizes the playing of the Year of plenty card from a player on the server
     * @param pass
     * @return
     */
    public ClientModel playYearOfPlenty(PassObject pass){

    }

    /**
     * This finalizes the playing or placing of a soldier card which will increase the players count
     * towards the largest army
     * @param pass
     * @return
     */
    public ClientModel placeSoldier(PassObject pass){

    }

    /**
     * This method is used by the player when they either move the robber or they roll a seven
     * @param pass
     * @return
     */
    public ClientModel rob(PassObject pass){

    }

    /**
     * This is used by the player to move the robberer to a new location
     * @param pass
     * @return
     */
    public ClientModel moveRobber(PassObject pass){

    }

    /**
     * This is to propose a trade to another player which will update a list
     * in the server model of trades taht can be made
     * @param pass
     * @return
     */
    public ClientModel tradePlayer(PassObject pass){

    }

    /**
     * This is when you are going to trade with the bank this is fairly simple
     * so it only requires that you send information to finalize the trade on the server
     * @param pass
     * @return
     */
    public ClientModel tradeBank(PassObject pass){

    }

    /**
     * This is used by the player to accept the trade that a player offer to them
     * updating their resources and the other players resources
     * @param pass
     * @return
     */
    public ClientModel acceptTrade(PassObject pass){

    }

    /**
     * This is when a player wins the game!!! Wohooo!!!
     * @param pass
     * @return
     */
    public ClientModel win(PassObject pass){

    }
}
