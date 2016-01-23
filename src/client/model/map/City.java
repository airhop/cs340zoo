package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;

public class City extends VertexObject
{

	public boolean canPlaceAtLocation(EdgeLocation edge)
	{
		if(edge == null)
		{
			return false;
		}
		return true;
	}
	public void placeCity()
	{
		//placeCity();
	}
}
