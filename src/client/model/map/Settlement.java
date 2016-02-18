package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;
import shared.locations.VertexLocation;

public class Settlement extends VertexObject 
{
	public Settlement (VertexLocation vl, int player)
	{
		super(vl, player);
	}

	public boolean canPlaceAtLocation()  
	{
		return false;
	}
 	
	public void placeSettlement()throws IllegalMoveException
	{
		
	}
	
}
