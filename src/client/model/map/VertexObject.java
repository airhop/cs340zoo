package client.model.map;

public class VertexObject{
  int owner;
  EdgeLocation location;
  //edgelocation?
  
  VertexObject(int x, int y, int id)
  {
    location = new EdgeLocation(x,y);
    owner = id;
  }
}
