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
    int n = r.nextInt(2 - 12) + 3;
    return n;
  }
  public boolean canRoll(int playerID)
  { 
    return false;
  }


}
