package client.MVC.maritime;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.bank.ResourceList;
import client.model.map.Port;
import shared.definitions.ResourceType;

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
    public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {

        super(tradeView);

        setTradeOverlay(tradeOverlay);
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
        getTradeOverlay().reset();
        List<Port> currentPlayerPorts = Facade.getInstance().getGameModel().getMap().getPlayerPorts(Facade.getInstance().getCurrentPlayer().getPlayerIndex());
        ResourceList currResources = Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources();
        List<ResourceType> canGiveResources = new ArrayList<>();
        for(Port port : currentPlayerPorts)
        {
            if(port.getRatio() == 4)
            {
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
            if(port.getRatio() == 3)
            {
                if(currResources.getWheat() >= 3)
                {
                    this.ratio = 3;
                    canGiveResources.add(ResourceType.WHEAT);
                }
                if(currResources.getSheep() >= 3)
                {
                    this.ratio = 3;
                    canGiveResources.add(ResourceType.SHEEP);
                }
                if(currResources.getOre() >= 3)
                {
                    this.ratio = 3;
                    canGiveResources.add(ResourceType.ORE);
                }
                if(currResources.getWood() >= 3)
                {
                    this.ratio = 3;
                    canGiveResources.add(ResourceType.WOOD);
                }
                if(currResources.getBrick() >= 3)
                {
                    this.ratio = 3;
                    canGiveResources.add(ResourceType.BRICK);
                }
            }
            else if(port.getResource() == "Wheat")
            {
                this.ratio = 2;
                if(currResources.getWheat() >= 2 && !canGiveResources.contains(ResourceType.WHEAT));
                {
                    canGiveResources.add(ResourceType.WHEAT);
                }
            }
            else if(port.getResource() == "Sheep")
            {
                this.ratio = 2;
                if(currResources.getSheep() >= 2)
                {
                    if(currResources.getSheep() >= 2 && !canGiveResources.contains(ResourceType.SHEEP));
                    {
                        canGiveResources.add(ResourceType.SHEEP);
                    }
                }
            }
            else if(port.getResource() == "Ore")
            {
                this.ratio = 2;
                if(currResources.getOre() >= 2)
                {
                    if(currResources.getOre() >= 2 && !canGiveResources.contains(ResourceType.ORE));
                    {
                        canGiveResources.add(ResourceType.ORE);
                    }
                }
            }
            else if(port.getResource() == "Wood")
            {
                this.ratio = 2;
                if(currResources.getWood() >= 2)
                {
                    if(currResources.getWood() >= 2 && !canGiveResources.contains(ResourceType.WOOD));
                    {
                        canGiveResources.add(ResourceType.WOOD);
                    }
                }
            }
            else if(port.getResource() == "Brick")
            {
                this.ratio = 2;
                if(currResources.getBrick() >= 2)
                {
                    if(currResources.getBrick() >= 2 && !canGiveResources.contains(ResourceType.BRICK));
                    {
                        canGiveResources.add(ResourceType.BRICK);
                    }
                }
            }
        }
        ResourceType[] canGive = new ResourceType[canGiveResources.size()];
        for(int i = 0; i < canGiveResources.size() ;i++ )
        {
            canGive[i] = canGiveResources.get(i);

            System.out.println(canGive[i]);
        }
            this.canGiveToBank = canGive;
            getTradeOverlay().setTradeEnabled(false);
            getTradeOverlay().showGiveOptions(canGive);

        getTradeOverlay().showModal();

    }

    @Override
    public void makeTrade() {
        Facade.getInstance().maritimeTrade(playerId,ratio,in,out);
        getTradeOverlay().closeModal();
    }

    @Override
    public void cancelTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void setGetResource(ResourceType resource) {
        getTradeOverlay().setTradeEnabled(true);
        getTradeOverlay().hideGetOptions();
        if(resource == ResourceType.BRICK)
        {
            this.in = ResourceType.BRICK;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.ORE)
        {
            this.in = ResourceType.ORE;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.WHEAT)
        {
            this.in = ResourceType.WHEAT;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.WOOD)
        {
            this.in = ResourceType.WOOD;
            getTradeOverlay().selectGetOption(resource,1);
        }
        if(resource == ResourceType.SHEEP)
        {
            this.in = ResourceType.SHEEP;
            getTradeOverlay().selectGetOption(resource,1);
        }
    }

    @Override
    public void setGiveResource(ResourceType resource) {

        ResourceType[] getResources = {ResourceType.BRICK,ResourceType.ORE,ResourceType.SHEEP,ResourceType.WHEAT,ResourceType.WOOD};
        getTradeOverlay().hideGiveOptions();
        getTradeOverlay().showGetOptions(getResources);
        int currPlayerIndex = Facade.getInstance().getCurrentPlayer().getPlayerIndex();
        int numBrick = Facade.getInstance().getGameModel().getPlayers().get(currPlayerIndex).getResources().getBrick();
        int numOre = Facade.getInstance().getGameModel().getPlayers().get(currPlayerIndex).getResources().getOre();
        int numWheat = Facade.getInstance().getGameModel().getPlayers().get(currPlayerIndex).getResources().getWheat();
        int numSheep = Facade.getInstance().getGameModel().getPlayers().get(currPlayerIndex).getResources().getSheep();
        int numWood = Facade.getInstance().getGameModel().getPlayers().get(currPlayerIndex).getResources().getWood();
        if(resource == ResourceType.BRICK)
        {
            this.out = ResourceType.BRICK;
            getTradeOverlay().selectGiveOption(resource,numBrick);
        }
        if(resource == ResourceType.ORE)
        {
            this.out = ResourceType.ORE;
            getTradeOverlay().selectGiveOption(resource,numOre);
        }
        if(resource == ResourceType.WHEAT)
        {
            this.out = ResourceType.WHEAT;
            getTradeOverlay().selectGiveOption(resource,numWheat);
        }
        if(resource == ResourceType.WOOD)
        {
            this.out = ResourceType.WOOD;
            getTradeOverlay().selectGiveOption(resource,numWood);
        }
        if(resource == ResourceType.SHEEP)
        {
            this.out = ResourceType.SHEEP;
            getTradeOverlay().selectGiveOption(resource,numSheep);
        }

    }

    @Override
    public void unsetGetValue() {
        ResourceType[] getResources = {ResourceType.BRICK,ResourceType.ORE,ResourceType.SHEEP,ResourceType.WHEAT,ResourceType.WOOD};
        getTradeOverlay().showGetOptions(getResources);
    }

    @Override
    public void unsetGiveValue() {
        getTradeOverlay().hideGetOptions();
        getTradeOverlay().showGiveOptions(canGiveToBank);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

