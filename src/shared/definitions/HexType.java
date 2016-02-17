package shared.definitions;

public enum HexType
{
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, DESERT, WATER;

	public static HexType convert(String type)
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
		if(type.equalsIgnoreCase("desert"))
			return DESERT;
		else
			return WATER;
	}
}

