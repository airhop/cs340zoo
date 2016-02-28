// canPlaceRoad(EdgeLocation) samething to settlement,city road, canRelcationRobber(HexLocation),
// need arraylist of vertexobject. Need to know which one is build on port.
package client.model.map;

import java.util.*;

import client.facade.Facade;
import client.model.bank.ResourceList;
import client.proxy.Proxy;
import shared.exceptions.FailureToAddException;
import shared.exceptions.InvalidPositionException;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.Deserializer;
import shared.locations.*;
import shared.definitions.*;
import sun.security.provider.certpath.Vertex;

public class Map
{
	TreeMap<HexLocation,Hex> hexes;
	ArrayList<Port> ports;
	ArrayList<Road> roads;
	//ArrayList<VertexObject> settlements;
	//ArrayList<VertexObject> cities;
	ArrayList<VertexObject> buildings;// that replace settlements and cities.
	ArrayList<VertexObject> placements = new ArrayList<VertexObject>();
	ArrayList<ResourceList> resources;
	int radius = -1;
	Robber robber;
	Deserializer deserializer;
	//regular constructor to create map
	public Map()
	{
		hexes = new TreeMap<HexLocation,Hex>();
		ports = new ArrayList<Port>();
		roads = new ArrayList<Road>();
		robber = new Robber();
		//settlements = new ArrayList<VertexObject>();
		//cities = new ArrayList<VertexObject>();
		buildings = new ArrayList<VertexObject>();
		resources = new ArrayList<ResourceList>();
		deserializer = new Deserializer();
		addOcean();
		fixBuildings();
	}

	public void clearHexes()
	{
		hexes.clear();
	}
	public void clearBuildings()
	{
		buildings.clear();
	}

	private void addOcean()
	{
		for(int i = 0; i < 4; i++)
		{
			hexes.put(new HexLocation(-3, i), new Hex(-3, i, "WATER", 0));
			hexes.put(new HexLocation(3, (-1*i)), new Hex(3, (-1*i), "WATER", 0));
		}
		hexes.put(new HexLocation(-2,-1), new Hex(-2, -1, "WATER", 0));
		hexes.put(new HexLocation(-2,3), new Hex(-2, 3, "WATER", 0));
		hexes.put(new HexLocation(-1,-2), new Hex(-1, -2, "WATER", 0));
		hexes.put(new HexLocation(-1,3), new Hex(-1, 3, "WATER", 0));
		hexes.put(new HexLocation(0,3), new Hex(0, 3, "WATER", 0));
		hexes.put(new HexLocation(0,-3), new Hex(0, -3, "WATER", 0));
		hexes.put(new HexLocation(1,-3), new Hex(1, -3, "WATER", 0));
		hexes.put(new HexLocation(1,2), new Hex(1, 2, "WATER", 0));
		hexes.put(new HexLocation(2,-3), new Hex(2, -3, "WATER", 0));
		hexes.put(new HexLocation(2,1), new Hex(2, 1, "WATER", 0));
	}

	//fix Buildings adds empty vertexObjects to the main array of buildings to make it easier to
		//determine where to put settlements
	public void fixBuildings()
	{
		ArrayList<VertexObject> extras = new ArrayList<VertexObject>();
		for(int i =0; i < buildings.size(); i++)
		{
			//cases NW, NE, W, E
			VertexLocation vl = buildings.get(i).getLocation();
			vl = vl.getNormalizedLocation();

			if(vl.getDir() == VertexDirection.E)
			{
				//System.out.println("E case");
				VertexLocation v = vl;
				v.setDir(VertexDirection.NE);
				extras.add(new VertexObject(v, -1));
				VertexLocation vertex =
						new VertexLocation(new HexLocation(vl.getHexLoc().getX() + 1, vl.getHexLoc().getY() - 1),
								VertexDirection.NE);
				extras.add(new VertexObject(vertex, -1));
				VertexLocation x = 	new VertexLocation(new HexLocation(vertex.getHexLoc().getX(), vertex.getHexLoc().getY()),
						VertexDirection.W);
				extras.add(new VertexObject(x, -1));
			}
			else if(vl.getDir() == VertexDirection.W)
			{
				//System.out.println("W case");
				VertexLocation v = vl;
				v.setDir(VertexDirection.NW);
				extras.add(new VertexObject(v, -1));
				VertexLocation vertex =
						new VertexLocation(new HexLocation(vl.getHexLoc().getX() - 1, vl.getHexLoc().getY() + 1),
								VertexDirection.NW);
				extras.add(new VertexObject(vertex, -1));
				VertexLocation x = new VertexLocation(new HexLocation(vertex.getHexLoc().getX(), vertex.getHexLoc().getY()),
						VertexDirection.E);
				extras.add(new VertexObject(x, -1));

			}
			else if(vl.getDir() == VertexDirection.NW)
			{
				//System.out.println("NW case");
				VertexLocation x = new VertexLocation(new HexLocation(vl.getHexLoc().getX(), vl.getHexLoc().getY()), VertexDirection.W);
				extras.add(new VertexObject(x, -1));
				VertexLocation v = new VertexLocation(new HexLocation(vl.getHexLoc().getX(), vl.getHexLoc().getY()), VertexDirection.NE);
				extras.add(new VertexObject(v, -1));
				VertexLocation vertex =
						new VertexLocation(new HexLocation(vl.getHexLoc().getX() - 1, vl.getHexLoc().getY()),
								VertexDirection.NE);
				extras.add(new VertexObject(vertex, -1));
			}
			else //(vl.getDir() == VertexDirection.NE) //should be here . . .
			{
				//System.out.println("NE case");
				VertexLocation v = vl;
				v.setDir(VertexDirection.E);
				extras.add(new VertexObject(v, -1));
				VertexLocation x = new VertexLocation(new HexLocation(vl.getHexLoc().getX(), vl.getHexLoc().getY()),
						VertexDirection.NW);
				extras.add(new VertexObject(x, -1));
				VertexLocation vertex =
						new VertexLocation(new HexLocation(vl.getHexLoc().getX() + 1, vl.getHexLoc().getY()- 1),
								VertexDirection.NW);
				extras.add(new VertexObject(vertex, -1));
			}
		}

		placements = new ArrayList<VertexObject>();
//		placements.addAll(extras);
		for(int i = 0; i < extras.size(); i++)
			placements.add(extras.get(i));
		placements.addAll(buildings);
	}

	public ArrayList<Hex> getHexMap()
	{
		ArrayList<Hex> returnHexes = new ArrayList<>();

		Set keys = hexes.keySet();
		for(Iterator i = keys.iterator(); i.hasNext();)
        {
            HexLocation hl = (HexLocation)i.next();
            returnHexes.add(hexes.get(hl));
			if(hexes.get(hl) == null)
				System.out.println("oops");
        }

//		for(int i = 0; i < 4; i++)
//		{
//			returnHexes.add(new Hex(-3, i, "WATER", 0));
//			returnHexes.add(new Hex(3, (-1*i), "WATER", 0));
//		}
//		returnHexes.add(new Hex(-2, -1, "WATER", 0));
//		returnHexes.add(new Hex(-2, 3, "WATER", 0));
//		returnHexes.add(new Hex(-1, -2, "WATER", 0));
//		returnHexes.add(new Hex(-1, 3, "WATER", 0));
//		returnHexes.add(new Hex(0, -3, "WATER", 0));
//		returnHexes.add(new Hex(0, 3, "WATER", 0));
//		returnHexes.add(new Hex(1, -3, "WATER", 0));
//		returnHexes.add(new Hex(1, 2, "WATER", 0));
//		returnHexes.add(new Hex(2, -3, "WATER", 0));
//		returnHexes.add(new Hex(2, 1, "WATER", 0));
		return returnHexes;
	}

	/**
	 * initialize a new map when game is created
	 */
	public void initialize() {}

//    public void initialize()
//    {
//    }

	/**
	 * checks to see if hex can be added
	 */
	public boolean canAddHex()
	{
		if (hexes.size() > 37)
		{
			return false;
		}
		return true;

	}//may not be used


	public void addHexDesert(int x, int y) throws FailureToAddException//may not be used
	{
		Hex hex = new Hex(x,y);
		//hex.setResource(HexType.DESERT.toString());
		hexes.put(hex.getLocation(), hex);
	}

	/**
	 * adds a hex to the maps list of hexes
	 *
	 * @param x        - horizontal location of hex
	 * @param y        - diagonal location of hex
	 * @param resource - type of resource obtained from hex
	 * @param number   - index of owner
	 */
	public void addHex(int x, int y, String resource, int number) throws FailureToAddException//may not be used
	{
		
		String numberString = new String(Integer.toString(number));
		Hex hex = new Hex(x,y,resource,number);
		Hex xomething = hexes.put(hex.getLocation(), hex);
//		if(xomething == null)
//			System.out.println("awesome!!");
//		else
//			System.out.println(xomething.getLocation().getX() + " " + xomething.getLocation().getY() + " " + xomething.getResource() + "\n");
//		System.out.println("added " + x + " " + y + " \t" + hex.getLocation().getX() + " " + hex.getLocation().getY() + " " +  hexes.size());
	}
// This section suppose to be rolling dice method
//    public void
//    {
//    	
//    }
	/**
	 * checks to see if port can be added
	 */
	public boolean canAddPort(Port port)
	{
		if(port == null)
		{
			return false;
		}
		if(ports.contains(port))
		{
			return false;
		}
		return true;
	}

	/**
	 * adds a port to the maps list of ports
	 *
	 * @param x         - horizontal location of hex related to port
	 * @param y         - diagonal location of hex related to port
	 * @param resource  - type of resource obtained from hex
	 * @param ed - direction from hex the port is located
	 * @param ratio     - the ratio of resources tradeable (i.e 1:2, 1:4)
	 */
	public void addPort(int x, int y, String resource, EdgeDirection ed, int ratio) throws FailureToAddException
	{
		Port port = new Port(x,y,resource, ed, ratio);
		ports.add(port);
	}

	/**
	 * checks to see if road can be added
	 */
	//public boolean canAddRoad(Road road)
	public boolean canPlaceRoad(EdgeLocation edgeLocation, boolean isDisconnected)
	{
		if (edgeLocation == null)
		{
			return false;
		}
		for(int i = 0; i < roads.size(); i++)
		{
			int el = roads.get(i).getLocation().getNormalizedLocation().compareTo(edgeLocation.getNormalizedLocation());
			if(el == 0)
				return false;
		}

		Hex h = hexes.get(edgeLocation.getHexLoc());
		if( h == null)
			return false;
		if(HexType.convert(h.getResource())== HexType.WATER)
		{
			return roadOceanPlayable(edgeLocation);
		}

		return true;
	}

	public boolean canPlaceRoadSetup(EdgeLocation el)
	{

		if (el == null)
		{
			return false;
		}
		for(int i = 0; i < roads.size(); i++)
		{
			int edgecompare = roads.get(i).getLocation().getNormalizedLocation().compareTo(el.getNormalizedLocation());
			if(edgecompare == 0)
				return false;
		}
		for(int i = 0; i < buildings.size(); i++)
		{
			if(el.neighbor(buildings.get(i).getLocation()))
				return false;
		}

		Hex h = hexes.get(el.getHexLoc());
		if( h == null)
			return false;
		if(HexType.convert(h.getResource())== HexType.WATER)
		{
			return roadOceanPlayable(el);
		}

		return true;
	}

	/**
	 * adds a road to the maps list of roads
	 *
	 * @param x         - horizontal location of hex
	 * @param y         - diagonal location of hex
	 * @param direction - direction from hex that road is located
	 * @param      - index of owner
	 */
	//public void addRoad(int x, int y, String direction, int owner) throws FailureToAddException
	public void addRoad(int x, int y, EdgeDirection direction, int pid) throws FailureToAddException
	{
		HexLocation hexLocation = new HexLocation(x,y);
//    	EdgeDirection edgeDirection;
//    	EdgeLocation edgeLocation = new EdgeLocation(hexLocation, edgeDirection);
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation,direction);
		Road road = new Road(edgeLocation,pid);
		roads.add(road);
	}

	/**
	 * checks to see if settlement can be added
	 */
	//public boolean canAddSettlement(Settlement settlement,VertexObject settlement)
	public boolean canPlaceSettlement(VertexLocation settlementLocation)
	{
		//pass and check isDisconnected.  Must be touching a road!!
		if(placements.size() != (buildings.size()*4))
			fixBuildings();
		//System.out.println(buildings.size());
		//System.out.println("settlement - " + settlementLocation.toString());
		if (settlementLocation == null)
		{
			return false;
		}
		for (VertexObject VObjIter: placements)
		{
		//	System.out.println("hey " + VObjIter.getLocation().getNormalizedLocation());
		//	System.out.println(settlementLocation.getNormalizedLocation() + "\n");
			int vl = VObjIter.getLocation().getNormalizedLocation().compareTo(settlementLocation.getNormalizedLocation());
			if (vl == 0)
			{
				return false;
			}
		}
		Hex h = hexes.get(settlementLocation.getHexLoc());
		if(h == null)
		{
			//System.out.println("oops " + settlementLocation.toString());
			return false;
		}
//		if(HexType.convert(h.getResource()) == HexType.WATER)
//		{
//			return oceanPlacable(settlementLocation);
//		}

		int pid = Facade.getInstance().getPlayerID();
		for(int i = 0; i < roads.size(); i++)
		{
			//System.out.println("here");
			Road r = roads.get(i);
			if(r.getOwner() == pid)
			{
				EdgeLocation rel = r.getLocation().getNormalizedLocation();
				VertexLocation sel = settlementLocation.getNormalizedLocation();
				if(rel.getHexLoc().compareTo(sel.getHexLoc()) == 0)
				{
					if (placable(rel.getDir(), sel.getDir()))
					{
						//System.out.println("here");
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * adds a settlement to the maps list of settlements
	 *
	 * @param x         - horizontal location of hex
	 * @param y         - diagonal location of hex
	 * @param direction - direction from hex that settlement is located
	 * @param      - index of owner
	 */
	//public void addSettlement(int x, int y, VertexDirection direction, int owner) throws FailureToAddException
	public void addSettlement(int x, int y, VertexDirection direction, int pid) throws FailureToAddException
	{
		HexLocation hex = new HexLocation(x,y);
		VertexLocation location =  new VertexLocation(hex, direction);
		location = location.getNormalizedLocation();
		//Settlement settlement = new Settlement(location,owner);
		Settlement settlement = new Settlement(location, pid);
		buildings.add(settlement);
	}

	/**
	 * checks to see if City can be added
	 */
	//public boolean canAddCity(City city)
	public boolean canPlaceCity(VertexLocation vertexLocation)
	{
		if (vertexLocation == null)
		{
			return false;
		}
		for (VertexObject VObjIter: buildings)
		{
			int vl = VObjIter.getLocation().getNormalizedLocation().compareTo(vertexLocation.getNormalizedLocation());
			if (vl == 0 && !(VObjIter instanceof Settlement))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * adds a city to the maps list of cities
	 *
	 * @param x         - horizontal location of hex
	 * @param y         - diagonal location of hex
	 * @param direction - direction from hex that city is located
	 * @param      - index of owner
	 */
	//public void addCity(int x, int y, VertexDirection direction, int owner) throws FailureToAddException
	public void addCity(int x, int y, VertexDirection direction, int pid) throws FailureToAddException
	{
		HexLocation hex = new HexLocation(x,y);
		VertexLocation location =  new VertexLocation(hex, direction);
		//City city = new City(location,owner);
		City city = new City(location, pid);
		buildings.add(city);
	}

	/**
	 * checks to see if robber can be relocated
	 */
	//public boolean canRelocateRobber(int x, int y)
	public boolean canRelocateRobber(HexLocation targetHex)
	{
		//HexLocation targetHex = new HexLocation(x,y);
		if(targetHex == robber.getHl())
			return false;
		if (HexType.convert(hexes.get(targetHex).resource) == HexType.WATER)
			return false;

		return true;
	}
	public ArrayList<Port> checkForPorts(ArrayList<VertexObject> builds)
	{	
		ArrayList<Port> playerPorts = new ArrayList<Port>();
		for(int i=0; i<ports.size(); i++)
		{
			for(int j=0; j<builds.size(); j++)
			{
				if(ports.get(i).getLocation().equals(builds.get(j).getLocation().getHexLoc()) && ports.get(i).getDirection().equals(builds.get(j).getLocation().getDir()))
				{
					playerPorts.add(ports.get(i));
				}
			}
		}
		return playerPorts;
	}
	/**
	 * moves robber to a new hex location
	 *
	 * @param  - horizontal location of hex robber is to be moved to
	 * @param  - diagonal location of hex robber is to be moved to
	 */
	//public void relocateRobber(int x, int y) throws InvalidPositionException
	public void relocateRober(HexLocation targetHex)
	{
		//HexLocation hexLocation = new HexLocation(x,y);
		robber.setHl(targetHex);
	}
	//getters and setters
	public TreeMap<HexLocation, Hex> getHexes() {
		return hexes;
	}

	public void setHexes(TreeMap<HexLocation, Hex> hexes) {
		this.hexes = hexes;
	}

	public ArrayList<Port> getPorts() {
		return ports;
	}

	/**
	 * Returns all of the ports that belong to a player
	 * @param playerID
	 * @return
	 */
	public List<Port> getPlayerPorts(int playerID)
	{
		List<Port> playerPorts = new ArrayList<Port>();
		for(Port port: ports)
		{
			if(port.getOwner() == playerID)
			{
				playerPorts.add(port);
			}
		}
		return playerPorts;
	}
	public void setPorts(ArrayList<Port> ports) {
		this.ports = ports;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

//	public ArrayList<VertexObject> getSettlements() {
//		return settlements;
//	}
//
//	public void setSettlements(ArrayList<VertexObject> settlements) {
//		this.settlements = settlements;
//	}
//
//	public ArrayList<VertexObject> getCities() {
//		return cities;
//	}
//
//	public void setCities(ArrayList<VertexObject> cities) {
//		this.cities = cities;
//	}

	public int getRadius() {
		return radius;
	}

	public ArrayList<VertexObject> getBuildings() {
		return buildings;
	}

	public void setBuildings(ArrayList<VertexObject> buildings)
	{
		this.buildings = buildings;
		fixBuildings();
	}

	public ArrayList<ResourceList> getResources() {
		return resources;
	}

	public void setResources(ArrayList<ResourceList> resources) {
		this.resources = resources;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Robber getRobber() {
		return robber;
	}

	public void setRobber(Robber robber) {
		this.robber = robber;
	}

	public boolean oceanPlacable(VertexLocation vl) {
		int x = vl.getHexLoc().getX();
		int y = vl.getHexLoc().getY();

		if (x == 3 && vl.getDir() == VertexDirection.NW) {
			if (y == -3)
				return false;
			return true;
		}
		else if (x == -3 && vl.getDir() == VertexDirection.NE)
		{
			if(y == 0)
				return false;
			return true;
		}
		else if(y < 1)
			return false;
		else if(x == -3)
			return false;
		return true;

	}

	public boolean roadOceanPlayable(EdgeLocation el)
	{
		int x = el.getHexLoc().getX();
		int y = el.getHexLoc().getY();

		//System.out.println(el.getDir() + " " + x + " " + y);
		if(y <= 0)
		{
			if(x == 3 && el.getDir() == EdgeDirection.NW)
			{
				if( y != -3)
					return true;
				return false;
			}
			return false;
		}
		if(x == -3 && el.getDir() == EdgeDirection.NE)
			return true;
		if(y == 3)
		{
			if(el.getDir() == EdgeDirection.NE && x != 0)
				return true;
			if((el.getDir() == EdgeDirection.N) && x != -3)
				return true;
			if(x == 0 && el.getDir() == EdgeDirection.N)
				return true;

			return false;
		}
		if(x == 1 && y == 2)
		{
			if(el.getDir() == EdgeDirection.N || el.getDir() == EdgeDirection.NW)
				return true;
			return false;
		}
		if(x == 2 && y == 1)
		{
			if(el.getDir() == EdgeDirection.N || el.getDir() == EdgeDirection.NW)
				return true;
			return false;
		}
		return false;
	}

	public boolean placable(EdgeDirection roadDir, VertexDirection settlementDir)
	{
		switch(roadDir)
		{
			case NW:
				if (settlementDir == VertexDirection.NW || settlementDir == VertexDirection.W)
					return true;
				return false;
			case NE:
				if (settlementDir == VertexDirection.NE || settlementDir == VertexDirection.E)
					return true;
				return false;
			case N:
				if (settlementDir == VertexDirection.NW || settlementDir == VertexDirection.NE)
					return true;
				return false;
		}

		System.out.println("Oops " + settlementDir);

		return false;
	}
}
