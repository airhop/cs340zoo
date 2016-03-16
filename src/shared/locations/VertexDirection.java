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

	//used to make the compareto for VertexLocation easier
	public int convert(VertexDirection vd)
	{
		switch(vd)
		{
			case W: return 1;
			case NW: return 2;
			case NE: return 3;
			case E: return 4;
			case SE: return 5;
			case SW: return 6;

		}
		return 20;
	}
}

