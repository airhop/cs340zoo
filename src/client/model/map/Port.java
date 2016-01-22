package client.model.map;

public class Port {
  
  HexLocation location;
  String resource;
  String direction;
  int ratio;  //ratio type?  what is ratio for?
  //do we need an owner value as well?
  
  Port(int x, int y, String resrc, String dir, int rat)
  {
    location = new HexLocation(x,y);
    resource = resrc;
    direction = dir;
    ratio = rat;
  }

}
