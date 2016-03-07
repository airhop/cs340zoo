package client.MVC.maritime;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.map.Port;
import client.model.map.VertexObject;
import shared.definitions.ResourceType;
import sun.security.provider.certpath.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

    private IMaritimeTradeOverlay tradeOverlay;
    int playerId = Facade.getInstance().getCurrentPlayer().getPlayerIndex();
    int ratio = 4;
    ResourceType in;
    ResourceType out;
    ResourceType[] canGiveToBank;
    boolean portIsBrick = false;
    boolean portIsWheat = false;
    boolean portIsOre = false;
    boolean portIsSheep = false;
    boolean portIsWood = false;
    boolean portIsThree = false;

    private void hasFourOfResource(ResourceList currResources, List<ResourceType> canGiveResources) {
        if(currResources.getWheat() >= 4)
        {
            canGiveResources.add(ResourceType.WHEAT);
        }
        if(currResources.getSheep() >= 4)
        {
            canGiveResources.add(ResourceType.SHEEP);
        }
        if(currResources.getOre() >= 4)
        {
            canGiveResources.add(ResourceType.ORE);
        }
        if(currResources.getWood() >= 4)
        {
            canGiveResources.add(ResourceType.WOOD);
        }
        if(currResources.getBrick() >= 4)
        {
            canGiveResources.add(ResourceType.BRICK);
        }
    }
    public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {

        super(tradeView);

        setTradeOverlay(tradeOverlay);
        Facade.getInstance().addObserver(this);
    }

    public IMaritimeTradeView getTradeView() {

        return (IMaritimeTradeView) super.getView();
    }

    public IMaritimeTradeOverlay getTradeOverlay() {
        return tradeOverlay;
    }

    public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
        this.tradeOverlay = tradeOverlay;
    }

    @Override
    public void startTrade() {
        System.out.println("Starting start trade");

        getTradeOverlay().reset();
        //List<Port> currentPlayerPorts = Facade.getInstance().getGameModel().getMap().getPlayerPorts(Facade.getInstance().getCurrentPlayer().getPlayerIndex());
        ArrayList<VertexObject> buildings = Facade.getInstance().getGameModel().getMap().getBuildings();
        ArrayList<Port> currentPlayerPorts = Facade.getInstance().getGameModel().getMap().checkPorts(playerId);

        ResourceList currResources = Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources();
        List<ResourceType> canGiveResources = new ArrayList<>();
        int lowestRatio = 4;
        hasFourOfResource(currResources, canGiveResources);
        for(Port port : currentPlayerPorts)
        {

            if(port.getRatio() == 3)
            {
                portIsThree = true;
                if(currResources.getWheat() >= 3)
                {
                    this.ratio = 3;
                    portIsThree = true;
                    canGiveResources.add(ResourceType.WHEAT);
                }
                if(currResources.getSheep() >= 3)
                {
                    this.ratio = 3;
                    portIsThree = true;
                    canGiveResources.add(ResourceType.SHEEP);
                }
                if(currResources.getOre() >= 3)
                {
                    this.ratio = 3;
                    portIsThree = true;
                    canGiveResources.add(ResourceType.ORE);
                }
                if(currResources.getWood() >= 3)
                {
                    this.ratio = 3;
                    portIsThree = true;
                    canGiveResources.add(ResourceType.WOOD);
                }
                if(currResources.getBrick() >= 3)
                {
                    this.ratio = 3;
                    portIsThree = true;
                    canGiveResources.add(ResourceType.BRICK);
                }
            }
            else if(port.getResource().equals("wheat"))
            {
                this.ratio = 2;
                portIsWheat = true;
                if(currResources.getWheat() >= 2)
                {
                    if (currResources.getWheat() >= 2 && !canGiveResources.contains(ResourceType.WHEAT)) ;
                    {
                        canGiveResources.add(ResourceType.WHEAT);
                    }
                }
            }
            else if(port.getResource().equals("sheep"))
            {
                this.ratio = 2;
                portIsSheep = true;
                if(currResources.getSheep() >= 2)
                {
                    if(currResources.getSheep() >= 2 && !canGiveResources.contains(ResourceType.SHEEP));
                    {
                        canGiveResources.add(ResourceType.SHEEP);
                    }
                }
            }
            else if(port.getResource().equals("ore"))
            {
                this.ratio = 2;
                portIsOre = true;
                if(currResources.getOre() >= 2)
                {
                    if(currResources.getOre() >= 2 && !canGiveResources.contains(ResourceType.ORE));
                    {
                        canGiveResources.add(ResourceType.ORE);
                    }
                }
            }
            else if(port.getResource().equals("wood"))
            {
                this.ratio = 2;
                portIsWood = true;
                if(currResources.getWood() >= 2)
                {
                    if(currResources.getWood() >= 2 && !canGiveResources.contains(ResourceType.WOOD));
                    {
                        canGiveResources.add(ResourceType.WOOD);
                    }
                }
            }
            else if(port.getResource().equals("brick"))
            {
                this.ratio = 2;
                portIsBrick = true;
                if(currResources.getBrick() >= 2)
                {
                    if(currResources.getBrick() >= 2 && !canGiveResources.contains(ResourceType.BRICK));
                    {
                        canGiveResources.add(ResourceType.BRICK);
                    }
                }
            }
        }
        if(canGiveResources.size() == 0)
        {
            getTradeOverlay().setStateMessage("Not Enough Resources");
        }
        else
        {
            getTradeOverlay().setStateMessage("Choose What To Give Up");
        }
        ResourceType[] canGive = new ResourceType[canGiveResources.size()];
        for(int i = 0; i < canGiveResources.size() ;i++ )
        {
            canGive[i] = canGiveResources.get(i);

            System.out.println(canGive[i]);
        }
            this.canGiveToBank = canGive;
        for(ResourceType resource: canGive)
        {
            System.out.println(resource);
        }
            getTradeOverlay().setTradeEnabled(false);
        if(Facade.getInstance().getCurrentPlayer().getPlayerIndex() == Facade.getInstance().getGameModel().getTurnTracker().getCurrentPlayer())
        {
            getTradeOverlay().showGiveOptions(canGive);
        }
        else
        {
            ResourceType[] disableAll = new ResourceType[0];
            getTradeOverlay().showGiveOptions(disableAll);
            getTradeOverlay().setStateMessage("Not Your Turn");
        }

        getTradeOverlay().showModal();

    }



    @Override
    public void makeTrade() {
        System.out.println(playerId);
        System.out.println(ratio);
        System.out.println(in);
        System.out.println(out);
        Facade.getInstance().maritimeTrade(playerId,ratio,in,out);
        Facade.getInstance().retrieveGameModel();
        getTradeOverlay().closeModal();
    }

    @Override
    public void cancelTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void setGetResource(ResourceType resource) {
        getTradeOverlay().setTradeEnabled(true);
        getTradeOverlay().setStateMessage("Trade!");
        getTradeOverlay().hideGetOptions();
        if(resource == ResourceType.BRICK)
        {
            this.out = ResourceType.BRICK;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.ORE)
        {
            this.out = ResourceType.ORE;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.WHEAT)
        {
            this.out = ResourceType.WHEAT;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.WOOD)
        {
            this.out = ResourceType.WOOD;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.SHEEP)
        {
            this.out = ResourceType.SHEEP;
            getTradeOverlay().selectGetOption(resource,1);
        }
    }

    @Override
    public void setGiveResource(ResourceType resource) {

        ResourceType[] getResources = {ResourceType.BRICK,ResourceType.ORE,ResourceType.SHEEP,ResourceType.WHEAT,ResourceType.WOOD};
        getTradeOverlay().setStateMessage("Choose What To Get");
        getTradeOverlay().hideGiveOptions();
        getTradeOverlay().showGetOptions(getResources);
        int numBrick;
        int numOre;
        int numWheat;
        int numSheep;
        int numWood;
        if(resource == ResourceType.BRICK)
        {
            this.in = ResourceType.BRICK;
            numBrick = 4;
            this.ratio = 4;
            if(portIsThree)
            {
                numBrick = 3;
                this.ratio = 3;
            }
            if(portIsBrick)
            {
                numBrick = 2;
                this.ratio = 2;
            }
            getTradeOverlay().selectGiveOption(resource,numBrick);
        }
        if(resource == ResourceType.ORE)
        {
            this.in = ResourceType.ORE;
            numOre = 4;
            this.ratio = 4;
            if(portIsThree)
            {
                numOre = 3;
                this.ratio = 3;
            }
            if(portIsOre)
            {
                numOre = 2;
                this.ratio = 2;
            }
            getTradeOverlay().selectGiveOption(resource,numOre);
        }
        if(resource == ResourceType.WHEAT)
        {
            this.in = ResourceType.WHEAT;
            numWheat = 4;
            this.ratio = 4;
            if(portIsThree)
            {
                numWheat = 3;
                this.ratio = 3;
            }
            if(portIsWheat)
            {
                numWheat = 2;
                this.ratio = 2;
            }
            getTradeOverlay().selectGiveOption(resource,numWheat);
        }
        if(resource == ResourceType.WOOD)
        {
            this.in = ResourceType.WOOD;
            numWood = 4;
            this.ratio = 4;
            if(portIsThree)
            {
                numWood = 3;
                this.ratio = 3;
            }
            if(portIsWood)
            {
                numWood = 2;
                this.ratio = 2;
            }
            getTradeOverlay().selectGiveOption(resource,numWood);
        }
        if(resource == ResourceType.SHEEP)
        {
            this.in = ResourceType.SHEEP;
            numSheep = 4;
            this.ratio = 4;
            if(portIsThree)
            {
                numSheep = 3;
                this.ratio = 3;
            }
            if(portIsSheep)
            {
                numSheep = 2;
                this.ratio = 2;
            }
            getTradeOverlay().selectGiveOption(resource,numSheep);
        }

    }

    @Override
    public void unsetGetValue() {
        ResourceType[] getResources = {ResourceType.BRICK,ResourceType.ORE,ResourceType.SHEEP,ResourceType.WHEAT,ResourceType.WOOD};
        getTradeOverlay().setStateMessage("Choose What to Get");
        getTradeOverlay().setTradeEnabled(false);
        getTradeOverlay().showGetOptions(getResources);
    }

    @Override
    public void unsetGiveValue() {
        getTradeOverlay().setStateMessage("Choose What to Give");
        getTradeOverlay().hideGetOptions();
        getTradeOverlay().setTradeEnabled(false);
        getTradeOverlay().showGiveOptions(canGiveToBank);
    }

    @Override
    public void update(Observable o, Object arg) {

        if(Facade.getInstance().getCurrentPlayer().getPlayerIndex() == ((GameModel)o).getTurnTracker().getCurrentPlayer() &&
                ((GameModel)o).isNotSetup())
            ((IMaritimeTradeView)getView()).enableMaritimeTrade(true);
        else
            ((IMaritimeTradeView)getView()).enableMaritimeTrade(false);

    }
}

