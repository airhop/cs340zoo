package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;
import shared.locations.VertexLocation;

public class City extends VertexObject
{
	int owner;
	VertexLocation location;
	public City(VertexLocation vertexLocation, int player)
	{
		owner = player;
		location = vertexLocation;
	}
	
	public int getOwner() 
	{
		return owner;
	}
	public void setOwner(int owner) 
	{
		this.owner = owner;
	}
	public VertexLocation getVertextLocation()
	{
		return location;
	}
	public void setLocation(VertexLocation location) {
		this.location = location;
	}
	public boolean canPlaceAtLocation(VertexLocation location)
	{
		if(location == null)
		{
			return false;
		}
		return true;
	}
	public void placeCity()
	{
		
	}
}
