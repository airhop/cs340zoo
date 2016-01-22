package clientmodel.proxy;


public interface IProxy {

    /**
     * Returns the ClientModel when called
     * @return
     */
    public ClientModel getGameModel(){

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
     * we send the server the information needed to place Settlement
     * @param pass
     * @return
     */
    public ClientModel placeSettlement(PassObject pass){

    }

    /**
     *
     * @param pass
     * @return
     */
    public ClientModel placeCity(PassObject pass){

    }
    public ClientModel buyDevCard(PassObject pass){

    }
    public ClientModel playDevCard(PassObject pass){

    }
    public ClientModel playMonopoly(PassObject pass){

    }
    public ClientModel playRoadBuilding(PassObject pass){

    }
    public ClientModel placeMonument(PassObject pass){

    }
    public ClientModel playRoadBuilding(PassObject pass){

    }
    public ClientModel playYearOfPlenty(PassObject pass){

    }
    public ClientModel placeSoldier(PassObject pass){

    }
    public ClientModel rob(PassObject pass){

    }
    public ClientModel moveRobber(PassObject pass){

    }
    public ClientModel tradePlayer(PassObject pass){

    }
    public ClientModel tradeBank(PassObject pass){

    }
    public ClientModel acceptTrade(PassObject pass){

    }
    public ClientModel win(PassObject pass){

    }
}
