package client.model.misc;

import shared.exceptions.IllegalMoveException;

public class Dice {
  
  int diceRoll;//2-12
  
  //curiosities sake.  Roll 2 1-6 or roll 2-12
  int rollDice()
  {
    //RollResultView.setRollValue(int value);
    return 0;
  }
  boolean canRoll() throws IllegalMoveException { return false; }


}
