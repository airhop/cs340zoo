package client.model.map;

import client.model.map.VertexObject;
import shared.exceptions.IllegalMoveException;

public class Settlement extends VertexObject {


	public boolean canPlaceAtLocation()  {return false;}
 	public void placeSettlement()throws IllegalMoveException{}
}
