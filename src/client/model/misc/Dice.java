package client.model.misc;
import java.util.Random;
import shared.exceptions.IllegalMoveException;

public class Dice {
  
  int diceRoll;//2-12
  /**
   * Returns a random number from 2-12
   */
  public int rollDice()
  {
    Random r = new Random();
    int x = r.nextInt(6);
    int y = r.nextInt(6);
    return x+y+2;
  }
  public boolean canRoll(int playerID)
  { 
    return false;
  }


}
