package client.model.map;

import java.util.ArrayList;

public class Map {
  ArrayList<Hex> hexes = new ArrayList <> ();
  ArrayList<Port> ports = new ArrayList <> ();
  ArrayList<Road> roads = new ArrayList <> ();
  ArrayList<VertexObject> settlements = new ArrayList<> ();
  ArrayList<VertexObject> cities = new ArrayList<> ();
  int radius = -1;
  Robber robber;
  
  //regular constructor to create map
  public Map()
  {
    
  }
  //have another to update, or just create a new one every time?
  
  
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
    * @param  x - horizontal location of hex
    * @param  y - diagonal location of hex
    * @param  resource - type of resource obtained from hex
    * @param  number - index of owner
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
    * @param  x - horizontal location of hex related to port
    * @param  y - diagonal location of hex related to port
    * @param  resource - type of resource obtained from hex
    * @param  direction - direction from hex the port is located
    * @param  ratio - the ratio of resources tradeable (i.e 1:2, 1:4)
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
    * @param  x - horizontal location of hex
    * @param  y - diagonal location of hex
    * @param  direction - direction from hex that road is located
    * @param  owner - index of owner
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
    * @param  x - horizontal location of hex
    * @param  y - diagonal location of hex
    * @param  direction - direction from hex that settlement is located
    * @param  owner - index of owner
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
    * @param  x - horizontal location of hex
    * @param  y - diagonal location of hex
    * @param  direction - direction from hex that city is located
    * @param  owner - index of owner
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
    * @param  x - horizontal location of hex robber is to be moved to
    * @param  y - diagonal location of hex robber is to be moved to
    */
 public void relocateRobber(int x, int y)
 {
   
 }
 
 //getters and setters
}
