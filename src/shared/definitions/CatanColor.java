package shared.definitions;

import java.awt.Color;

public enum CatanColor
{
	RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
	
	private Color color;
	
	static
	{
		RED.color = new Color(227, 66, 52);
		ORANGE.color = new Color(255, 165, 0);
		YELLOW.color = new Color(253, 224, 105);
		BLUE.color = new Color(111, 183, 246);
		GREEN.color = new Color(109, 192, 102);
		PURPLE.color = new Color(157, 140, 212);
		PUCE.color = new Color(204, 136, 153);
		WHITE.color = new Color(223, 223, 223);
		BROWN.color = new Color(161, 143, 112);
	}
	
	public Color getJavaColor()
	{
		return color;
	}

	public static CatanColor convert(String color)
	{
		if(color.equalsIgnoreCase("RED"))
			return CatanColor.RED;
		if(color.equalsIgnoreCase("ORANGE"))
			return ORANGE;
		if(color.equalsIgnoreCase("Yellow"))
			return YELLOW;
		if(color.equalsIgnoreCase("Blue"))
			return BLUE;
		if(color.equalsIgnoreCase("Green"))
			return GREEN;
		if(color.equalsIgnoreCase("Purple"))
			return PURPLE;
		if(color.equalsIgnoreCase("Puce"))
			return PUCE;
		if(color.equalsIgnoreCase("White"))
			return WHITE;
		if(color.equalsIgnoreCase("Brown"))
			return BROWN;

		System.out.println("major error here :) Color=" + color);
		return WHITE;
	}

	public static String toString(CatanColor color)
	{
		switch(color)
		{
			case RED: return "red";
			case ORANGE: return "orange";
			case YELLOW: return "yellow";
			case BLUE: return "blue";
			case GREEN: return "green";
			case PURPLE: return "purple";
			case PUCE: return "puce";
			case WHITE: return "white";
			default: return "brown";
		}
	}
}

