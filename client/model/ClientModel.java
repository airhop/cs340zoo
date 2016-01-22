package clientmodel;

public class ClientModel {
  Map map;
  Bank bank;
  Player[] players;
  TurnTracker tt;
  TradeOffer to;
  int version = 0;
  int winner = -1;
  
    /**
    * updates version of the game model
    *
    */
  public void updateVersion()
  {
    version++;
  }
    /**
    * decides game winner and sets the winner to the id of the winning player
    * 
    * @param int winnerid - id of the winning player
    */
  public void decideWinner(int winnerid)
  {
    if(winnerid<0 || winnerid >3)
    {
      throw someIllegalException;
      return;
    }
    winner = winnerid;
  }
}
