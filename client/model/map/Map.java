package clientmodel.map;

public class Map {
  ArrayList<Hex> hexes = new ArrayList <Hex> ();
  ArrayList<Port> ports = new ArrayList <Port> ();
  ArrayList<Road> roads = new ArrayList <Road> ();
  ArrayList<VertexObject> settlements = new ArrayList<VertexObject> ();
  ArrayList<VertexObject> cities = new ArrayList<VertexObject> ();
  int radius = -1;
  HexLocation robber = null;
  
     /**
    *checks to see if hex can be added
    */
  public boolean canAddHex()
  {
    return false;
  }
  /**
    *adds a hex to the maps list of hexes
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String resource - type of resource obtained from hex
    * @param int number - index of owner
    */
 public void addHex(int x, int y, String resource, int number)
 {
   
 }
      /**
    *checks to see if port can be added
    */
  public boolean canAddPort()
  {
    return false;
  }
   /**
    *adds a port to the maps list of ports
    * 
    * @param int x - horizontal location of hex related to port
    * @param int y - diagonal location of hex related to port
    * @param String resource - type of resource obtained from hex
    * @param String direction - direction from hex the port is located
    * @param int ratio - the ratio of resources tradeable (i.e 1:2, 1:4)
    */
 public void addPort(int x, int y, String resource, String direction, int ratio)
 {
   
 }
      /**
    *checks to see if road can be added
    */
   public boolean canAddRoad()
  {
    return false;
  }
   /**
    *adds a road to the maps list of roads
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String direction - direction from hex that road is located
    * @param int owner - index of owner
    */
 public void addRoad(int x, int y, String direction, int owner)
 {
   
 }
     /**
    *checks to see if settlement can be added
    */ 
   public boolean canAddSettlement()
  {
    return false;
  }
    /**
    *adds a settlement to the maps list of settlements
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String direction - direction from hex that settlement is located
    * @param int owner - index of owner
    */
 public void addSettlement(int x, int y, String direction, int owner)
 {
   
 }
     /**
    *checks to see if City can be added
    */ 
   public boolean canAddCity()
  {
    return false;
  }
    /**
    *adds a city to the maps list of cities
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String direction - direction from hex that city is located
    * @param int owner - index of owner
    */
 public void addCity(int x, int y, String direction, int owner)
 {
   
 }
     /**
    *checks to see if robber can be relocated
    */ 
   public boolean canRelocateRobber()
  {
    return false;
  }
    /**
    *moves robber to a new hex location
    * 
    * @param int x - horizontal location of hex robber is to be moved to
    * @param int y - diagonal location of hex robber is to be moved to
    */
 public void relocateRobber(int x, int y)
 {
   
 }
 
 //getters and setters
}
