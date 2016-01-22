package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;

public class Settlement extends VertexObject {


	Settlement(int x, int y, int id) {
		super(x, y, id);
	}

	boolean canPlaceAtLocation() throws IllegalMoveException {return false;}
 	void placeSettlement(){}
}
