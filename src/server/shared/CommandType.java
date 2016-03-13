package server.shared;

/**
 * Created by Joshua on 3/10/2016.
 */
public enum CommandType {
    Model, list, addAI, listAI, login, register, create, join,
    sendChat, rollNumber, robPlayer, finishTurn, buyDevCard, Year_Of_Plenty, Road_Building, Soldier, 
        Monopoly, Monument, buildRoad, buildSettlement, buildCity, offerTrade, acceptTrade, maritimeTrade, discardCards;


    /**
     * The type will come in through the handler as a string from the URI  This method will return
     * the correct commandType so that it can be passed to the commandFactory correctly
     *
     * @param type - incoming type that needs to be converted to an enum
     * @return the associated CommandType
     */
    public static CommandType convert(String type)
    {
        if(type.equalsIgnoreCase("sendChat"))
            return sendChat;
        if(type.equalsIgnoreCase("rollNumber"))
            return rollNumber;
        if(type.equalsIgnoreCase("robPlayer"))
            return robPlayer;
        if(type.equalsIgnoreCase("finishTurn"))
            return finishTurn;
        if(type.equalsIgnoreCase("buyDevCard"))
            return buyDevCard;
        if(type.equalsIgnoreCase("Year_of_plenty"))
            return Year_Of_Plenty;
        if(type.equalsIgnoreCase("Road_Building"))
            return Road_Building;
        if(type.equalsIgnoreCase("Soldier"))
            return Soldier;
        if(type.equalsIgnoreCase("Monopoly"))
            return Monopoly;
        if(type.equalsIgnoreCase("Monument"))
            return Monument;
        if(type.equalsIgnoreCase("buildRoad"))
            return buildRoad;
        if(type.equalsIgnoreCase("buildSettlement"))
            return buildSettlement;
        if(type.equalsIgnoreCase("buildCity"))
            return buildCity;
        if(type.equalsIgnoreCase("offerTrade"))
            return offerTrade;
        if(type.equalsIgnoreCase("acceptTrade"))
            return acceptTrade;
        if(type.equalsIgnoreCase("maritimeTrade"))
            return maritimeTrade;
        if(type.equalsIgnoreCase("discradCards"))
            return discardCards;

        return listAI;
    }

    //or maybe do case by case bases in the handler.
}
