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
        List<Port> currentPlayerPorts = Facade.getInstance().getGameModel().getMap().getPlayerPorts(Facade.getInstance().getCurrentPlayer().getPlayerIndex());
        ResourceList currResources = Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources();
        int canGiveResourcesAmount = 0;
        List<ResourceType> canGiveResources = new ArrayList<ResourceType>();
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
                    canGiveResources.add(ResourceType.WHEAT);
                }
                if(currResources.getSheep() >= 3)
                {
                    canGiveResources.add(ResourceType.SHEEP);
                }
                if(currResources.getOre() >= 3)
                {
                    canGiveResources.add(ResourceType.ORE);
                }
                if(currResources.getWood() >= 3)
                {
                    canGiveResources.add(ResourceType.WOOD);
                }
                if(currResources.getBrick() >= 3)
                {
                    canGiveResources.add(ResourceType.BRICK);
                }
            }
            else if(port.getResource() == "Wheat")
            {
                if(currResources.getWheat() >= 2 && !canGiveResources.contains(ResourceType.WHEAT));
                {
                    canGiveResources.add(ResourceType.WHEAT);
                }
            }
            else if(port.getResource() == "Sheep")
            {
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
                if(currResources.getBrick() >= 2)
                {
                    if(currResources.getBrick() >= 2 && !canGiveResources.contains(ResourceType.BRICK));
                    {
                        canGiveResources.add(ResourceType.BRICK);
                    }
                }
            }
        }
        ResourceType[] canGet = new ResourceType[canGiveResources.size()];
        for(int i = 0; i < canGiveResources.size() ;i++ )
        {
            canGet[i] = canGiveResources.get(i);
            System.out.println(canGet[i]);
        }
        getTradeOverlay().showGetOptions(canGet);
        getTradeOverlay().showModal();

    }

    @Override
    public void makeTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void cancelTrade() {

        getTradeOverlay().closeModal();
    }

    @Override
    public void setGetResource(ResourceType resource) {

    }

    @Override
    public void setGiveResource(ResourceType resource) {

    }

    @Override
    public void unsetGetValue() {

    }

    @Override
    public void unsetGiveValue() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

