package client.model.map;

public class Hex {
  
  HexLocation location;
  String resource;
  String number;

  public Hex(int x, int y, String resrc, String num)
  {
    location = new HexLocation(x,y);
    resource = resrc;
    number = num;
  }
  //Getters and Setters
}
