package clientmodel.map;

public class Port {
  
  HexLocation location;
  String resource;
  String direction;
  int ratio;
  
  Port(int x, int y, String resrc, String dir, int rat)
  {
    location = new HexLocation(x,y);
    resource = resrc;
    direction = dir;
    ratio = rat;
  }

}
