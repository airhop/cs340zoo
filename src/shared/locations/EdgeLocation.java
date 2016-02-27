package shared.locations;

/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation
{
	
	private HexLocation hexLoc;
	private EdgeDirection dir;
	
	public EdgeLocation(HexLocation hexLoc, EdgeDirection dir)
	{
		setHexLoc(hexLoc);
		setDir(dir);
	}
	
	public HexLocation getHexLoc()
	{
		return hexLoc;
	}
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}
	
	public EdgeDirection getDir()
	{
		return dir;
	}
	
	private void setDir(EdgeDirection dir)
	{
		this.dir = dir;
	}
	
	@Override
	public String toString()
	{
		return "EdgeLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		EdgeLocation other = (EdgeLocation)obj;
		if(dir != other.dir)
			return false;
		if(hexLoc == null)
		{
			if(other.hexLoc != null)
				return false;
		}
		else if(!hexLoc.equals(other.hexLoc))
			return false;
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this edge location. Since
	 * each edge has two different locations on a map, this method converts a
	 * hex location to a single canonical form. This is useful for using hex
	 * locations as map keys.
	 * 
	 * @return Normalized hex location
	 */
	public EdgeLocation getNormalizedLocation()
	{// Return an EdgeLocation that has direction NW, N, or NE
		
		switch (dir)
		{
			case NW:
			case N:
			case NE:
				return this;
			case SW:
			case S:
			case SE:
				return new EdgeLocation(hexLoc.getNeighborLoc(dir),
										dir.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}

	public int compareTo(EdgeLocation edgeLocation)
	{
		if(edgeLocation.getHexLoc().compareTo(getHexLoc()) == 0)
			if(getDir() == edgeLocation.getDir())
				return 0;
		return -1;
	}

	public boolean neighbor(VertexLocation vl)
	{
		vl = vl.getNormalizedLocation();
		switch(vl.getDir())
		{
			case NW: if(vl.getHexLoc() == getHexLoc() && (getDir() == EdgeDirection.N || getDir() == EdgeDirection.NW))
						return true;
					if(getHexLoc() == new HexLocation(vl.getHexLoc().getX() - 1, vl.getHexLoc().getY()) && getDir() == EdgeDirection.NE)
						return true;
			case NE: if(getHexLoc() == vl.getHexLoc() && (getDir() == EdgeDirection.N || getDir() == EdgeDirection.NE))
						return true;
				     if(getHexLoc() == new HexLocation(vl.getHexLoc().getX()+1, vl.getHexLoc().getY() - 1) && getDir() == EdgeDirection.NW)
						 return true;
			case E: if(vl.getHexLoc() == getHexLoc() && getDir() == EdgeDirection.NE)
						return true;
					HexLocation hl = new HexLocation(vl.getHexLoc().getX() + 1, vl.getHexLoc().getY());
					if(hl == getHexLoc() && (getDir() == EdgeDirection.NW || getDir() == EdgeDirection.N))
						return true;
			default: if(vl.getHexLoc() == getHexLoc() && getDir() == EdgeDirection.NW)
						return true;
				     HexLocation hl2 = new HexLocation(vl.getHexLoc().getX() - 1, vl.getHexLoc().getY() - 1);
					 if(hl2 == getHexLoc() && (getDir() == EdgeDirection.NE || getDir() == EdgeDirection.N))
						return true;

		}
		return false;
	}
}

