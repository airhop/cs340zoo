package shared.locations;

import com.sun.javafx.geom.Edge;

/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation
{

	private HexLocation hexLoc;
	private VertexDirection dir;

	public VertexLocation(HexLocation hexLoc, VertexDirection dir)
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

	public VertexDirection getDir()
	{
		return dir;
	}

	private void setDir(VertexDirection direction)
	{
		this.dir = direction;
	}

	@Override
	public String toString()
	{
		return "VertexLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
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
		VertexLocation other = (VertexLocation)obj;
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
	 * Returns a canonical (i.e., unique) value for this vertex location. Since
	 * each vertex has three different locations on a map, this method converts
	 * a vertex location to a single canonical form. This is useful for using
	 * vertex locations as map keys.
	 *
	 * @return Normalized vertex location
	 */
/*	public VertexLocation getNormalizedLocation()
	{

		// Return location that has direction NW or NE

		int x = hexLoc.getX();
		int y = hexLoc.getY();
		System.out.print( dir + " " + x + " " + y +  "\t ");
		switch (dir)
		{
			case NW:
				if(x == 1 && y == 2)
				{
					System.out.println("NW issue \t");
					return new VertexLocation(new HexLocation(0, 2), VertexDirection.E);
					//return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.N), VertexDirection.SW);
				}
				if(y == 3 || x == 3)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.N), VertexDirection.SW);
				if(x == 2 && y == 1)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.N), VertexDirection.SW);

				return this;
			case NE:
				if(y == 3 && x < 1)
				{
					System.out.println("NE issue\t" );
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.NE), VertexDirection.W);
				}
				if(x < -2)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.NE), VertexDirection.W);
				if(x == 1 && y == 2)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.NE), VertexDirection.W);
				if(x == 2 && y == 1)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.NE), VertexDirection.W);
				return this;
			case W:
				if(hexLoc.getX() == -2)
					return this;
				if(x == 3 && y == 0)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.NW), VertexDirection.SE);
				if(x == 2 && y ==1)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.NW), VertexDirection.SE);
				if(x == -1 && y == 2)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.NW), VertexDirection.SE);

				return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SW), VertexDirection.NE);
			case SW:
				if(y < 0 && y > -4)
				{
					System.out.println("kay");
					if(x == 3)
						return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SW), VertexDirection.E);
					else
						System.out.println("uhm");
				}
				if(y == 2)
					return this;
				if(x == 1 && y == 1)
					return this;
				if (x == 2 && y == 0)
					return this;
				return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.S), VertexDirection.NW);
			case SE:
				if(x == -3 && y == 2)
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SE), VertexDirection.W);
				if(y == 2)
					return new VertexLocation(hexLoc, VertexDirection.SE);
				if (x == 1 && y == 1)
					return this;
				if (x == 2 && y == 0)
					return this;
				if  (x == -1 && y == 2)
					return this;
				if(y < 3 && y > -1) {
					if (x <= -3)
						return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SE), VertexDirection.W);
				}
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.S),
										  VertexDirection.NE);
			case E:
				if(hexLoc.getX() == 2)
					return this;
				if(y == 3) //edge water case
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.N), VertexDirection.SE);
				if(x == 0 && y == 2)
					return this;
				return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SE), VertexDirection.NW);

			default:
				assert false;
				return null;
		}
	}
*/
	public VertexLocation getNormalizedLocation()
	{
			// Return location that has direction NW or NE

		int x = hexLoc.getX();
		int y = hexLoc.getY();
	//	System.out.print(dir + " " + x + " " + y + "\t");
			switch (dir)
			{
				case NW:
				case NE:
					return this;
				case W:
					if(x == -2)
					{
						if(y != -1 && y != 3)
							return this;
					}
					return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SW), VertexDirection.NE);
				case SW:
					return new VertexLocation(
							hexLoc.getNeighborLoc(EdgeDirection.S),
							VertexDirection.NW);
				case SE:
					return new VertexLocation(
							hexLoc.getNeighborLoc(EdgeDirection.S),
							VertexDirection.NE);
				case E:
					if(x == 2)
					{
						if(y!= 1 && y != -3)
							return this;
					}
					return new VertexLocation(
							hexLoc.getNeighborLoc(EdgeDirection.SE),
							VertexDirection.NW);
				default:
					assert false;
					return null;
			}
		}

}

