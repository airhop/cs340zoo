package clientmodel.bank;

public class ResourceList {
  
    int numOfBrick;
  int numOfOre;
  int numOfSheep;
  int numOfWheat;
  int numOfWood;
  
  ResourceList("map")
  {
    numOfBrick = 19;
    numOfOre = 19;
    numOfSheep = 19;
    numOfWheat = 19;
    numOfWood = 19;
  }
  int getBrick()
  {
    return numOfBrick;
  }
  int getOre()
  {
    return numOfOre;
  }
  int getSheep()
  {
    return numOfSheep;
  }
  int getWheat()
  {
    return numOfWheat;
  }
  int getWood()
  {
    return numOfWood;
  }
  void setBrick(int brick)
  {
    numOfBrick = brick;
  }
  void setOre(int ore)
  {
    numOfOre = ore;
  }
  void setSheep(int sheep)
  {
    numOfSheep = sheep;
  }
  void setWheat(int wheat)
  {
    numOfWheat = wheat;
  }
  void setWood(int wood)
  {
    numOfWood = wood;
  }
  void addWood(){}
  void addOre(){}
  void addSheep(){}
  void addBrick(){}
}
