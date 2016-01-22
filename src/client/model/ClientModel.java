package client.model;

import client.model.bank.Bank;
import client.model.map.Map;
import client.model.misc.TradeOffer;
import client.model.misc.TurnTracker;
import client.model.player.Player;
import shared.exceptions.InvalidWinner;

public class ClientModel {
  private Map map;
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
    * @param winnerid - id of the winning player
    */
  public void decideWinner(int winnerid) throws InvalidWinner {
    if(winnerid<0 || winnerid >3)
    {
      throw new InvalidWinner();

    }else{
      winner = winnerid;
    }

  }
}
