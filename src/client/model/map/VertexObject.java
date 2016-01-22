package client.model.map;

public class VertexObject{
  int owner;
  EdgeLocation location;
  //edgelocation?
  
  VertexObject(int x, int y, int id)
  {
    location = new EdgeLocation(0,0, "this");
    owner = id;
  }
}
