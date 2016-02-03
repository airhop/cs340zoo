package shared.jsonobject;

public class PassObject 
{
   int playerid, otherid;
   boolean acceptability;
   String information;
   public PassObject()
   {
      playerid = -1;
      otherid = -1;
      boolean acceptablility = false;
      information = null;
   }
   
   public int getPlayerid() {
		return playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}
	public int getOtherid() {
		return otherid;
	}
	public void setOtherid(int otherid) {
		this.otherid = otherid;
	}
	public boolean isAcceptability() {
		return acceptability;
	}
	public void setAcceptability(boolean acceptability) {
		this.acceptability = acceptability;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
}
