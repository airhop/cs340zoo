package client.model.map;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class VertexObject
{
  int owner;
  //EdgeLocation edgelocation;
  VertexLocation vertexLocation;

    public VertexObject(VertexLocation vl, int player)
    {
        owner = player;
        vertexLocation = vl;
    }
  //VertexLocation vertexLocation;
  //edgelocation?
  
  // public VertexObject(int x, int y, int id)
  // {
  //   location = new EdgeLocation(0,0, "this");
  //   owner = id;
  // }
  
//  public VertexLocation getVertexLocation() {
//	return vertexLocation;
//}
//
//public void setVertexLocation(VertexLocation vertexLocation) {
//	this.vertexLocation = vertexLocation;
//}

/**
   *@param VertexObject - an object to compareTo
   *@return if the location is being used already
   */
   boolean compareTo(VertexObject vo)
   {
	   return false;
   }

   public int getOwner() 
   {
	   return owner;
   }

   public void setOwner(int owner) 
   {
	   this.owner = owner;
   }

   public VertexLocation getLocation() 
   {
	   return vertexLocation;
   }

   public void setLocation(VertexLocation location) 
   {
	   this.vertexLocation = location;
   }
   
}
