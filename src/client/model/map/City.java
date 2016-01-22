package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;

public class City extends VertexObject {


	public City(int x, int y, int id) {
		super(x, y, id);
	}

	public boolean canPlaceAtLocation() throws IllegalMoveException
	{
		return false;
	}
	public void placeCity(){}
}
