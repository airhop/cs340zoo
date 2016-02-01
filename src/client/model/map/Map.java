package client.model.map;

import java.util.ArrayList;
import java.util.TreeMap;

import shared.exceptions.FailureToAddException;
import shared.exceptions.InvalidPositionException;

public class Map {
    TreeMap<HexLocation,Hex> hexes;
    ArrayList<Port> ports;
    ArrayList<Road> roads;
    ArrayList<VertexObject> settlements;
    ArrayList<VertexObject> cities;
    int radius = -1;
    Robber robber;

    //regular constructor to create map
    public Map() 
    {
        hexes = new TreeMap<HexLocation,Hex>();
        ports = new ArrayList<Port>();
        roads = new ArrayList<Road>();
        settlements = new ArrayList<VertexObject>();
        cities = new ArrayList<VertexObject>();
    }
    //have another to update, or just create a new one every time?

    /**
     * initialize a new map when game is created
     */
    public void initialize() 
    {
    	if (hexes.size() < 33)
    	{
    		//addHex();
    		if(canAddPort())//several canDo Methods
    		{
    			//addPort();
    		}
    		if(canAddRoad())
    		{
    			//addRoad();
    		}
    		if(canAddCity())
    		{
    			//addCity();
    		}
    		//relocateRobber();
    	}
    }

    /**
     * checks to see if hex can be added
     */
    public boolean canAddHex() {
        return true;
    }//may not be used

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

    }

    /**
     * checks to see if port can be added
     */
    public boolean canAddPort() {
        return false;
    }

    /**
     * adds a port to the maps list of ports
     *
     * @param x         - horizontal location of hex related to port
     * @param y         - diagonal location of hex related to port
     * @param resource  - type of resource obtained from hex
     * @param direction - direction from hex the port is located
     * @param ratio     - the ratio of resources tradeable (i.e 1:2, 1:4)
     */
    public void addPort(int x, int y, String resource, String direction, int ratio) throws FailureToAddException{

    }

    /**
     * checks to see if road can be added
     */
    public boolean canAddRoad() {
        return false;
    }

    /**
     * adds a road to the maps list of roads
     *
     * @param x         - horizontal location of hex
     * @param y         - diagonal location of hex
     * @param direction - direction from hex that road is located
     * @param owner     - index of owner
     */
    public void addRoad(int x, int y, String direction, int owner) throws FailureToAddException {

    }

    /**
     * checks to see if settlement can be added
     */
    public boolean canAddSettlement() {
        return false;
    }

    /**
     * adds a settlement to the maps list of settlements
     *
     * @param x         - horizontal location of hex
     * @param y         - diagonal location of hex
     * @param direction - direction from hex that settlement is located
     * @param owner     - index of owner
     */
    public void addSettlement(int x, int y, String direction, int owner) throws FailureToAddException{

    }

    /**
     * checks to see if City can be added
     */
    public boolean canAddCity() {
        return false;
    }

    /**
     * adds a city to the maps list of cities
     *
     * @param x         - horizontal location of hex
     * @param y         - diagonal location of hex
     * @param direction - direction from hex that city is located
     * @param owner     - index of owner
     */
    public void addCity(int x, int y, String direction, int owner) throws FailureToAddException
    {

    }

    /**
     * checks to see if robber can be relocated
     */
    public boolean canRelocateRobber() {
        return false;
    }

    /**
     * moves robber to a new hex location
     *
     * @param x - horizontal location of hex robber is to be moved to
     * @param y - diagonal location of hex robber is to be moved to
     */
    public void relocateRobber(int x, int y) throws InvalidPositionException
    {

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

	public void setPorts(ArrayList<Port> ports) {
		this.ports = ports;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	public ArrayList<VertexObject> getSettlements() {
		return settlements;
	}

	public void setSettlements(ArrayList<VertexObject> settlements) {
		this.settlements = settlements;
	}

	public ArrayList<VertexObject> getCities() {
		return cities;
	}

	public void setCities(ArrayList<VertexObject> cities) {
		this.cities = cities;
	}

	public int getRadius() {
		return radius;
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
}
