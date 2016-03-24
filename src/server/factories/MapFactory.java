package server.factories;

import client.model.GameModel;
import client.model.map.Hex;
import client.model.map.Port;
import client.model.map.Robber;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joshua on 3/19/2016.
 */
public class MapFactory
{
    private Random rand;
    public MapFactory()
    {
        rand = new Random();
    }

    public GameModel newModel(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name)
    {
        ArrayList<Hex> hexes = initializeHexes();
        Robber robber = null;
        if(randomTiles)
            hexes = initializeRandomHexes(hexes);

        if(randomNumbers)
            hexes = randomizeNumbers(hexes);

        ArrayList<Port> ports = initializePorts();
        if(randomPorts)
            ports = randomizePorts(ports);

        robber = addRobber(hexes);
        return new GameModel(name, hexes, ports, robber);
    }


    //predefined with unrandomized hexes and values
    public ArrayList<Hex> initializeHexes()
    {
        ArrayList<Hex> hexes = new ArrayList<Hex>();
        hexes.add(new Hex(-2, 0, "ore", 5));
        hexes.add(new Hex(-2 , 1, "wheat", 2));
        hexes.add(new Hex (-2, 2, "wood", 6));
        hexes.add(new Hex(-1, -1, "brick", 8));
        hexes.add(new Hex(-1, 0, "sheep", 10));
        hexes.add(new Hex(-1, 1, "sheep", 9));
        hexes.add(new Hex(-1, 2, "ore", 3));
        hexes.add(new Hex(0, -2, "Desert", 0));
        hexes.add(new Hex(0, -1, "wood", 3));
        hexes.add(new Hex(0, 0, "wheat", 11));
        hexes.add(new Hex(0, 1, "wood", 4));
        hexes.add(new Hex(0, 2, "wheat", 8));
        hexes.add(new Hex(1, -2, "brick", 4));
        hexes.add(new Hex(1, -1, "ore", 9));
        hexes.add(new Hex(1, 0, "brick", 5));
        hexes.add(new Hex(1, 1, "sheep", 10));
        hexes.add(new Hex(2, -2, "wood", 11));
        hexes.add(new Hex(2, -1, "sheep", 12));
        hexes.add(new Hex(2, 0, "wheat", 6));

        return hexes;
    }

    public ArrayList<Hex> initializeRandomHexes(ArrayList<Hex> hexes)
    {
        String[] resources = {"ore", "wheat", "wood", "brick", "sheep", "sheep", "ore",
                "Desert", "wood", "wheat", "wood", "wheat", "brick", "ore",
                "brick", "sheep", "wood", "sheep", "wheat"};

        //randomize the resources
        for(int i= 0; i < resources.length; i++)
        {
            int a = rand.nextInt(resources.length);
            int b = rand.nextInt(resources.length);

            String type = resources[a];
            resources[a] = resources[b];
            resources[b] = type;
        }

        //reassign the resources
        for(int i = 0; i < resources.length; i++)
        {
            Hex hex = hexes.get(i);
            hex.setResource(resources[i]);
            if(resources[i].equalsIgnoreCase("Desert"))
            {
                hexes.get(7).setNumber(hex.getNumber());
                hex.setNumber(0);
            }
            hexes.set(i, hex);
        }
        return hexes;
    }

    public ArrayList<Hex> randomizeNumbers(ArrayList<Hex> hexes)
    {
        int[] values = {5, 2, 6, 8, 10, 9, 3, 3, 11, 4, 8, 4, 9, 5, 10, 11, 12, 6};

        //randomize the resources
        for(int i= 0; i < values.length; i++)
        {
            int a = rand.nextInt(values.length);
            int b = rand.nextInt(values.length);

            int type = values[a];
            values[a] = values[b];
            values[b] = type;
        }

        int j = 0;
        for(int i = 0; i < (hexes.size() - 1); i++)
        {
            Hex hes1 = hexes.get(j);
            if(hes1.getResource().equalsIgnoreCase("DESERT")){
                hes1.setNumber(0);
                j++;
            }
            Hex hes = hexes.get(j);
            hes.setNumber(values[i]);
            hexes.set(j, hes);
            j++;
        }

        return hexes;
    }

    public ArrayList<Port> initializePorts()
    {
        ArrayList<Port> ports = new ArrayList<Port>();
        ports.add(new Port(-3, 0, EdgeDirection.SE, 3));
        ports.add(new Port(-3, 2, "Wood", EdgeDirection.NE, 2));
        ports.add(new Port(-2, 3, "brick", EdgeDirection.NE, 2));
        ports.add(new Port(0, 3, EdgeDirection.N, 3));
        ports.add(new Port(2, 1, EdgeDirection.NW, 3));
        ports.add(new Port(3, -1, "sheep", EdgeDirection.NW, 2));
        ports.add(new Port(3, -3, EdgeDirection.SW, 3));
        ports.add(new Port(1, -3, "Ore", EdgeDirection.S, 2));
        ports.add(new Port(-1, -2, "wheat", EdgeDirection.S, 2));
        return ports;
    }

    public ArrayList<Port> randomizePorts(ArrayList<Port> ports)
    {
        String[] type = {"three", "wood", "brick", "three",
                "three", "sheep", "three", "ore", "wheat"};
        for(int i= 0; i < type.length; i++)
        {
            int a = rand.nextInt(type.length);
            int b = rand.nextInt(type.length);

            String temp = type[a];
            type[a] = type[b];
            type[b] = temp;
        }

        for(int i = 0; i < type.length; i++)
        {
            Port p = ports.get(i);
            p.setType(type[i]);
            ports.set(i, p);
        }
        return ports;

    }

    public Robber addRobber(ArrayList<Hex> hexes) {
        Robber robber = new Robber();
        for (Hex hex : hexes) {
            if (hex.getResource() == "desert") {
                robber.setHl(hex.getLocation());
            }
        }
        return robber;
    }

}