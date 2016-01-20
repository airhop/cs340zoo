package clientmodel.map;

public class Hex {
  
  HexLocation location;;
  String resource;
  String number;
  
  Hex(int x, int y, String resrc, String num)
  {
    location = new HexLocation(x,y);
    resource = resrc;
    number = num;
  }
  //Getters and Setters
}
