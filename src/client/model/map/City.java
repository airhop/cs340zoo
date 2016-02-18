package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;
import shared.locations.VertexLocation;

public class City extends VertexObject
{
	public City(VertexLocation vertexLocation, int player)
	{
		super(vertexLocation, player);
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
