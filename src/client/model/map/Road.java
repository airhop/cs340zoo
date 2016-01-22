package client.model.map;

public class Road {
  int owner;
  EdgeLocation location;
  
  public Road(int x, int y, int id)
  {
    location = new EdgeLocation(0,0, "");
    owner = id;
  }
}
