package clientmodel.map;

public class Map {
  ArrayList<Hex> hexes = new ArrayList <Hex> ();
  ArrayList<Port> ports = new ArrayList <Port> ();
  ArrayList<Road> roads = new ArrayList <Road> ();
  ArrayList<VertexObject> settlements = new ArrayList<VertexObject> ();
  ArrayList<VertexObject> cities = new ArrayList<VertexObject> ();
  int radius = -1;
  HexLocation robber = null;
  
 addHex(int x, int y, String resource, int number)
 {
   
 }
 addPort(int x, int y, String resource, String direction, int ratio)
 {
   
 }
 addRoad(int x, int y, String direction, int owner)
 {
   
 }
 addSettlement(int x, int y, String direction, int owner)
 {
   
 }
 addCity(int x, int y, String direction, int owner)
 {
   
 }
 relocateRobber(int x, int y)
 {
   
 }
 
 //getters and setters
}
