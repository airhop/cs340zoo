package client.model.bank;

public class ResourceList {
  
    int numOfBrick;
  int numOfOre;
  int numOfSheep;
  int numOfWheat;
  int numOfWood;
  
  public ResourceList()
  {
    numOfBrick = 19;
    numOfOre = 19;
    numOfSheep = 19;
    numOfWheat = 19;
    numOfWood = 19;
  }
  
  //overload the constructor so that RL can be used for trades, bank, map, players etc.

  public int getBrick()
  {
    return numOfBrick;
  }
  public int getOre()
  {
    return numOfOre;
  }
  public int getSheep()
  {
    return numOfSheep;
  }
  public int getWheat()
  {
    return numOfWheat;
  }
  public int getWood()
  {
    return numOfWood;
  }
  public void setBrick(int brick)
  {
    numOfBrick = brick;
  }
  public void setOre(int ore)
  {
    numOfOre = ore;
  }
  public void setSheep(int sheep)
  {
    numOfSheep = sheep;
  }
  public void setWheat(int wheat)
  {
    numOfWheat = wheat;
  }
  public void setWood(int wood)
  {
    numOfWood = wood;
  }
}
