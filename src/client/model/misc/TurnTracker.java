package client.model.misc;

public class TurnTracker {
  int currPlayer;
  int status;
  int longestRoad;
  int currLR = 4; 
  int largestArmy;
  int currLA = 2;  
  //must be at least 3 to claim largest army, therefore if player army >  currLA they earn largest army and currLA = player army size
  
  //know current player, status of turn
  //rolling trading, building, finish

public TurnTracker()
{
  currPlayer = 0;
  status = 0;
}
public TurnTracker(int currentP, int stat)
{
  currPlayer = currentP;
  status = stat;
}

public int getCurrentPlayer()
{
  return currPlayer;
}
public void setCurrentPlayer(int pid)
{
  currPlayer = pid;
  return;
}

/**
 * @param  pid - player id
if not turn don't do anything, else move turn to next player
*/
  public void updateStatus()
  {
    if(status == 3)
    {
      status = 0;
      if(currPlayer == 3)
      {
        currPlayer = 0;
      }
      else currPlayer += 1;
    }
    else status += 1;
  }
   public void getStatus()
  {
    return status;
  }
/**
 * @param pid - player id of player wishing to try for largest army
    might return bool in future, not a necessity at the moment though
*/
  public void calcLargestArmy(int pid){}
  
  /**
   * @param pid = player id of player wishing to try for longest road
      again might return bool in future, not a necessity at the moment
  */
  public void calcLongestRoad(int pid){}
  
}
