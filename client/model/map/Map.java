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
    *adds a hex to the maps list of hexes
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String resource - type of resource obtained from hex
    * @param int number - index of owner
    */
 addHex(int x, int y, String resource, int number)
 {
   
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
 addPort(int x, int y, String resource, String direction, int ratio)
 {
   
 }
   /**
    *adds a road to the maps list of roads
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String direction - direction from hex that road is located
    * @param int owner - index of owner
    */
 addRoad(int x, int y, String direction, int owner)
 {
   
 }
    /**
    *adds a settlement to the maps list of settlements
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String direction - direction from hex that settlement is located
    * @param int owner - index of owner
    */
 addSettlement(int x, int y, String direction, int owner)
 {
   
 }
    /**
    *adds a city to the maps list of cities
    * 
    * @param int x - horizontal location of hex
    * @param int y - diagonal location of hex
    * @param String direction - direction from hex that city is located
    * @param int owner - index of owner
    */
 addCity(int x, int y, String direction, int owner)
 {
   
 }
    /**
    *moves robber to a new hex location
    * 
    * @param int x - horizontal location of hex robber is to be moved to
    * @param int y - diagonal location of hex robber is to be moved to
    */
 relocateRobber(int x, int y)
 {
   
 }
 
 //getters and setters
}
