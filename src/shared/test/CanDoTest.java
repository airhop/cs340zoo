package shared.test;

import java.util.ArrayList;
import client.model.bank.Bank;
import client.model.bank.DevCardList;
import client.model.bank.ResourceList;
import org.junit.Test;
import client.model.map.*;
import client.facade.*;
import client.proxy.*;
import client.model.misc.*;
import client.model.history.*;
import client.model.GameModel;
import client.model.player.*;
import shared.definitions.ResourceType;
import shared.locations.*;


public class CanDoTest
{
    Facade f;
    IProxy p;

    public void initializeEmpty()
    {
        f = new Facade();
        p = new Proxy(new GameModel());
        f.Reinitialize(new GameModel());
    }

    //create a full game
    public void initializeFull()
    {
        f = new Facade();
        Bank b = new Bank(new ResourceList(13, 16, 12, 16, 18), new DevCardList(1, 4, 1, 10, 1));
        TurnTracker tt = new TurnTracker(3, "roll");
        tt.setLongestRoad(1);

        Player p1 = new Player("Sam", 0);
        p1.setCities(3);
        p1.setSettlements(3);
        p1.setRoads(9);
        p1.setNewDevCards(new DevCardList(0, 0, 0 , 2, 0));
        p1.setResources(new ResourceList(0, 3, 0, 2, 0));

        Player p2 = new Player("Suzzie", 1);
        p2.setCities(3);
        p2.setSettlements(3);
        p2.setRoads(13);
        p2.setOldDevCards(new DevCardList(1, 0, 0, 1, 0));
        p2.setResources(new ResourceList(1, 1, 1, 1, 1));

        Player p3 = new Player("Bobbie", 2);
        p3.setCities(4);
        p3.setSettlements(3);
        p3.setRoads(10);
        p3.setNewDevCards(new DevCardList(0, 1, 0, 0, 1));
        p3.setResources(new ResourceList(0, 1, 1, 1 , 0));

        Player p4 = new Player("Finale", 3);
        p4.setCities(4);
        p4.setSettlements(2);
        p4.setRoads(12);
        p4.setNewDevCards(new DevCardList(0, 0, 1, 1, 0));
        p4.setResources(new ResourceList(0, 0, 3, 2, 1));

        ArrayList<Player> ps = new ArrayList<Player>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);

        Map m = new Map();
        Robber r = new Robber();
        r.setHl(new HexLocation(3,4));
        m.setRobber(r);

        ArrayList<VertexObject> bldgs = new ArrayList<VertexObject>();
        bldgs.add(new City(new VertexLocation(new HexLocation(1, 2), VertexDirection.NW), 0));
        bldgs.add(new City(new VertexLocation(new HexLocation(3, 2), VertexDirection.NE), 1));
        bldgs.add(new Settlement(new VertexLocation(new HexLocation(2, 4), VertexDirection.E), 0));
        bldgs.add(new Settlement(new VertexLocation(new HexLocation(1, 4), VertexDirection.E), 1));
        bldgs.add(new Settlement(new VertexLocation(new HexLocation(2, 3), VertexDirection.NE), 2));
        bldgs.add(new Settlement(new VertexLocation(new HexLocation(2, 3), VertexDirection.NW), 2));
        bldgs.add(new Settlement(new VertexLocation(new HexLocation(4, 3), VertexDirection.E), 3));
        bldgs.add(new Settlement(new VertexLocation(new HexLocation(4, 3), VertexDirection.W), 3));
        bldgs.add(new Settlement(new VertexLocation(new HexLocation(5, 1), VertexDirection.W), 3));
        m.setBuildings(bldgs);

        ArrayList<Port> ports = new ArrayList<Port>();
        Port p = new Port(5, 1, EdgeDirection.NW, 3);
        p.setOwner(3);
        ports.add(p);
        p = (new Port(1, 2, "Wood", EdgeDirection.SE, 2));
        p.setOwner(0);
        ports.add(p);
        p = (new Port(3, 4, "Wheat", EdgeDirection.NW, 2));
        p.setOwner(1);
        ports.add(p);
        m.setPorts(ports);

        GameModel g = new GameModel(m, b, ps, tt, new TradeOffer(), new Chat(), new Log());
        f.Reinitialize(g);
    }

    @Test
    public void testCanBuildSettlement()
    {
        initializeFull();
        TurnTracker tt = f.getGM().getTt();
        tt.setCurrentPlayer(1);
        f.getGM().setTt(tt);

        assert(f.canBuildSettlement(1));
        assert(!f.canBuildSettlement(0));

        tt.setCurrentPlayer(0);
        f.getGM().setTt(tt);
        assert(!f.canBuildSettlement(0));

        System.out.println("Can Build Settlement - can Do, Not turn, Insufficent Materials");
    }

    @Test
    public void testCanBuildRoad()
    {
        initializeFull();
        TurnTracker tt = f.getGM().getTt();
        tt.setCurrentPlayer(1);
        f.getGM().setTt(tt);

        assert(f.canBuildRoad(1));
        assert(!f.canBuildRoad(0));

        tt.setCurrentPlayer(0);
        f.getGM().setTt(tt);
        assert(!f.canBuildRoad(0));

        System.out.println("Can Build Road - can Do, Not turn, Insufficent Materials");

    }

    @Test
    public void testCanBuildCity()
    {
        initializeFull();
        TurnTracker turnTracker = f.getGM().getTt();
        turnTracker.setCurrentPlayer(1);
        f.getGM().setTt(turnTracker);

        assert(f.canBuildCity(1));
        assert(!f.canBuildCity(0));

        turnTracker.setCurrentPlayer(0);
        f.getGM().setTt(turnTracker);
        assert(!f.canBuildCity(0));

        System.out.println("Can Build City - can Do, Not turn, Insufficient Materials");

    }


//    @Test
//    public void testCanDiscardCards()
//    {
//        initializeFull();
//        TurnTracker turnTracker = f.getGM().getTt();
//        ResourceList resourceList = new ResourceList(1,);
//        turnTracker.getCurrentPlayer(1);
//        f.getGM().setTt(turnTracker);
//
//        assert(f.canDiscardCards(1))
//
//    }


    @Test
    public void testCanRollNumber()
    {

    }


    @Test
    public void testCanOfferTrade()
    {

    }
    @Test
    public void canMaritimeTrade()
    {
        
    }
    @Test
    public void canFinishTurn()
    {
        
    }
    @Test
    public void canBuyDevCard()
    {
        
    }
    @Test
    public void canUseYearOfPlenty()
    {
        
    }
    @Test
    public void canUseRoadBuilding()
    {
        
    }
    @Test
    public void canUseSoldier()
    {
        
    }
    @Test
    public void canUseMonopoly()
    {
        
    }
    @Test
    public void canUseMonument()
    {
        
    }
    @Test
    public void canPlaceRobber()
    {
        initializeFull();
        TurnTracker tt = f.getGM().getTt();
        tt.setCurrentPlayer(1);
        f.getGM().setTt(tt);
        
        assert(f.canPlaceRobber());
    }
    @Test
    public void canSendChat()
    {
        
    }
    @Test
    public void canAcceptTrade()
    {
        
    }
}
