package client.model.map;

public abstract class VertexObject{
  int owner;
  EdgeLocation location;
  //edgelocation?
  
  // public VertexObject(int x, int y, int id)
  // {
  //   location = new EdgeLocation(0,0, "this");
  //   owner = id;
  // }
  
  /**
   *@param VertexObject - an object to compareTo
   *@return if the location is being used already
   */
   boolean compareTo(VertexObject vo)
   {
     return false;
   }
}
