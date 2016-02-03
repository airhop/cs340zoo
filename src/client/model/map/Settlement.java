package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;
import shared.locations.VertexLocation;

public class Settlement extends VertexObject 
{
	int owner;
	VertexLocation vertextLocation;
	
	public Settlement(VertexLocation vertexLocation, int player)
	{
		owner = player;
		this.vertextLocation =vertexLocation;
	}
	
	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public VertexLocation getVertextLocation() {
		return vertextLocation;
	}

	public void setVertextLocation(VertexLocation vertextLocation) {
		this.vertextLocation = vertextLocation;
	}

	public boolean canPlaceAtLocation()  
	{
		return false;
	}
 	
	public void placeSettlement()throws IllegalMoveException
	{
		
	}
	
}
