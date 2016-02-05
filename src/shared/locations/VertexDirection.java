package shared.locations;

public enum VertexDirection
{
	W, NW, NE, E, SE, SW;
	
	private VertexDirection opposite;
	
	static
	{
		W.opposite = E;
		NW.opposite = SE;
		NE.opposite = SW;
		E.opposite = W;
		SE.opposite = NW;
		SW.opposite = NE;
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}

	public String toString(VertexDirection vd)
	{
		switch(vd)
		{
			case W: return "W";
			case NW: return "NW";
			case NE: return "NE";
			case E: return "E";
			case SE: return "SE";
			case SW: return "SW";
		}
		return "";
	}
}

