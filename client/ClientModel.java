package clientmodel;

public class ClientModel {
  int version = 0;
  int winner = -1;
  
  public void updateVersion()
  {
    version++;
  }
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
