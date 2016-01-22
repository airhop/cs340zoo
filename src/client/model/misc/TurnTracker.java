package client.model.misc;

public class TurnTracker {
  int currPlayer;
  String status;
  int longestRoad;
  int currLR = 4; //see note about largest army
  int largestArmy;
  int currLA = 2;  
  //must be at least 3 to claim largest army, therefore if player army >  currLA they earn largest army and currLA = player army size
  
  public TurnTracker(){}
//  void startTurn(){}
//  void endTurn(){}

/**
 * @param  pid - player id
if not turn don't do anything, else move turn to next player
*/
  public void updateStatus(int pid){}
  
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
