package server.shared;

/**
 * Created by Joshua on 3/10/2016.
 */
public enum CommandType {
    sendChat, rollNumber, robPlayer, finishTurn, buyDevCard, Year_Of_Plenty, Road_Building, Soldier, 
        Monopoly, Monument, buildRoad, buildSettlement, buildCity, offerTrade, acceptTrade, maritimeTrade, discardCards;


    /**
     * The type will come in through the handler as a string from the URI  This method will return
     * the correct commandType so that it can be passed to the commandFactory correctly
     *
     * @param type - incoming type that needs to be converted to an enum
     * @return the associated CommandType
     */
    public CommandType convert(String type)
    {
        return sendChat;
    }
}
