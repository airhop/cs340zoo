package shared.definitions;

public enum PortType
{
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, THREE;

	public static PortType convert(String type)
	{
		if(type.equalsIgnoreCase("WOOD"))
			return WOOD;
		if(type.equalsIgnoreCase("BRICK"))
			return BRICK;
		if(type.equalsIgnoreCase("Sheep"))
			return SHEEP;
		if(type.equalsIgnoreCase("Wheat"))
			return WHEAT;
		if(type.equalsIgnoreCase("ore"))
			return ORE;
		else
			return THREE;
	}
}

