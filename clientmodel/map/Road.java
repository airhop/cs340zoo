package clientmodel.map;

public class Road {
  int owner;
  EdgeLocation location;
  
  Road(int x, int y, int id)
  {
    location = new EdgeLocation(x,y);
    owner = id;
  }
}
