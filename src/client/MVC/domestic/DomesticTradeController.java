package client.MVC.domestic;

import client.MVC.base.*;
import client.MVC.data.PlayerInfo;
import client.MVC.misc.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.misc.TradeOffer;
import client.model.player.Player;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;

import java.util.Observable;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController {

    private IDomesticTradeOverlay tradeOverlay;
    private IWaitView waitOverlay;
    private IAcceptTradeOverlay acceptOverlay;

    private ResourceList tradeList;
    private int brick;
    private int brickStatus;
    private int ore;
    private int oreStatus;
    private int sheep;
    private int sheepStatus;
    private int wheat;
    private int wheatStatus;
    private int wood;
    private int woodStatus;

    private boolean hasASend;
    private boolean hasARecieve;

    private int tradeSenderId;
    private int tradeAccepterId = -1;
    boolean usable = false;
    /**
     * DomesticTradeController constructor
     *
     * @param tradeView     Domestic trade view (i.e., view that contains the "Domestic Trade" button)
     * @param tradeOverlay  Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
     * @param waitOverlay   Wait overlay used to notify the user they are waiting for another player to accept a trade
     * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
     */
    public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
                                   IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

        super(tradeView);

        setTradeOverlay(tradeOverlay);
        setWaitOverlay(waitOverlay);
        setAcceptOverlay(acceptOverlay);
        Facade.getInstance().addObserver(this);
    }

    public IDomesticTradeView getTradeView() {

        return (IDomesticTradeView) super.getView();
    }

    public IDomesticTradeOverlay getTradeOverlay() {
        return tradeOverlay;
    }

    public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
        this.tradeOverlay = tradeOverlay;
    }

    public IWaitView getWaitOverlay() {
        return waitOverlay;
    }

    public void setWaitOverlay(IWaitView waitView) {
        this.waitOverlay = waitView;
    }

    public IAcceptTradeOverlay getAcceptOverlay() {
        return acceptOverlay;
    }

    public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
        this.acceptOverlay = acceptOverlay;
    }

    @Override
    public void startTrade() {
        if(!usable)
            return;
        System.out.println(Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().toString());
        tradeSenderId = Facade.getInstance().getCurrentPlayer().getPlayerId();
        PlayerInfo[] players = new PlayerInfo[3];
        int i=0;
        int j=0;
        for(Player player : Facade.getInstance().getGameModel().getPlayers())
        {
            if(Facade.getInstance().getCurrentPlayerInfo().getId() != j) {
                players[i] = new PlayerInfo(player.getPlayerID(), player.getPlayerIndex(), player.getUsername(), CatanColor.convert(player.getColor())/*CatanColor.BLUE*/);
                i++;
            }
            j++;
        }
        getTradeOverlay().setPlayers(players);
        getTradeOverlay().showModal();
        brick = 0;
        ore = 0;
        sheep = 0;
        wheat = 0;
        wood = 0;
        brickStatus = 0;
        oreStatus = 0;
        sheepStatus = 0;
        wheatStatus = 0;
        woodStatus = 0;
        setPlayerToTradeWith(-1);
        updateTradeStatus();
    }

    @Override
    public void decreaseResourceAmount(ResourceType resource) {
        if(resource == ResourceType.BRICK)
        {
            if(brick > 0)
            {
                brick--;
                if(brick < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getBrick()) {
                    tradeOverlay.setResourceAmountChangeEnabled(resource,true,true);
                }
            }
        }
        if(resource == ResourceType.ORE)
        {
            if(ore > 0)
            {
                ore--;
                if(ore < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getOre()) {
                    tradeOverlay.setResourceAmountChangeEnabled(resource,true,true);
                }
            }
        }
        if(resource == ResourceType.SHEEP)
        {
            if(sheep > 0)
            {
                sheep--;
                if(sheep < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getSheep()) {
                    tradeOverlay.setResourceAmountChangeEnabled(resource,true,true);
                }
            }
        }
        if(resource == ResourceType.WHEAT)
        {
            if(wheat > 0)
            {
                wheat--;
                if(wheat < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getWheat()) {
                    tradeOverlay.setResourceAmountChangeEnabled(resource,true,true);
                }
            }
        }
        if(resource == ResourceType.WOOD)
        {
            if(wood > 0)
            {
                wood--;
                if(wood < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getWood()) {
                    tradeOverlay.setResourceAmountChangeEnabled(resource,true,true);
                }
            }
        }

        printPlayerResourceStatus();
        updateTradeStatus();
    }

    @Override
    public void increaseResourceAmount(ResourceType resource) {
        if(resource == ResourceType.BRICK)
        {

//            if(brick+1 == Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getBrick()) {
//                tradeOverlay.setResourceAmountChangeEnabled(resource,false,true);
//            }
            brick++;
        }
        if(resource == ResourceType.ORE)
        {
//            if(ore+1 == Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getOre()) {
//                tradeOverlay.setResourceAmountChangeEnabled(resource,false,true);
//            }
            ore++;
        }
        if(resource == ResourceType.SHEEP)
        {
//            if(sheep+1 == Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getSheep()) {
//                tradeOverlay.setResourceAmountChangeEnabled(resource,false,true);
//            }
            sheep++;
        }
        if(resource == ResourceType.WHEAT)
        {
//            if(wheat+1 == Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getWheat()) {
//                tradeOverlay.setResourceAmountChangeEnabled(resource,false,true);
//            }
            wheat++;
        }
        if(resource == ResourceType.WOOD)
        {
//            if(wood+1 == Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getWood()) {
//                tradeOverlay.setResourceAmountChangeEnabled(resource,false,true);
//            }
            wood++;
        }
        printPlayerResourceStatus();
        updateTradeStatus();
    }

    @Override
    public void sendTradeOffer() {
        if(brickStatus == 1) {brick = brick - (2*brick);} if(brickStatus == 0) {brick = 0;}
        if(oreStatus == 1) {ore = ore - (2*ore);} if(oreStatus == 0) {ore = 0;}
        if(sheepStatus == 1) {sheep = sheep - (2*sheep);} if(sheepStatus == 0) {sheep = 0;}
        if(wheatStatus == 1) {wheat = wheat - (2*wheat);} if(wheatStatus == 0) {wheat = 0;}
        if(woodStatus == 1) {wood = wood - (2*wood);} if(wheatStatus == 0) {wheat = 0;}

        tradeList = new ResourceList(brick,ore,sheep,wheat,wood);

        if(Facade.getInstance().canTradePlayer(Facade.getInstance().getPlayerIndex(), tradeAccepterId, tradeList))
        {
            System.out.println(tradeList.toString());
            Facade.getInstance().tradePlayer(Facade.getInstance().getPlayerIndex(), tradeAccepterId, tradeList);
        }
        getTradeOverlay().closeModal();
        getWaitOverlay().showModal();
    }

    @Override
    public void setPlayerToTradeWith(int playerIndex) {
        tradeAccepterId = playerIndex;
        updateTradeStatus();
    }

    @Override
    public void setResourceToReceive(ResourceType resource) {
        if(resource == ResourceType.BRICK)
        {
            brickStatus = 1;
            brick = 0;
        }
        if(resource == ResourceType.ORE)
        {
            oreStatus = 1;
            ore = 0;
        }
        if(resource == ResourceType.SHEEP)
        {
            sheepStatus = 1;
            sheep = 0;
        }
        if(resource == ResourceType.WHEAT)
        {
            wheatStatus = 1;
            wheat = 0;
        }
        if(resource == ResourceType.WOOD)
        {
            woodStatus = 1;
            wood = 0;
        }
        printPlayerResourceStatus();
        updateTradeStatus();
        tradeOverlay.setResourceAmount(resource, "0");
    }

    @Override
    public void setResourceToSend(ResourceType resource) {
        if(resource == ResourceType.BRICK)
        {
            brickStatus = -1;
            brick = 0;
        }
        if(resource == ResourceType.ORE)
        {
            oreStatus = -1;
            ore = 0;
        }
        if(resource == ResourceType.SHEEP)
        {
            sheepStatus = -1;
            sheep = 0;
        }
        if(resource == ResourceType.WHEAT)
        {
            wheatStatus = -1;
            wheat = 0;
        }
        if(resource == ResourceType.WOOD)
        {
            woodStatus = -1;
            wood = 0;
        }
        printPlayerResourceStatus();
        updateTradeStatus();
        tradeOverlay.setResourceAmount(resource, "0");
    }

    @Override
    public void unsetResource(ResourceType resource) {
//        getTradeOverlay().setResourceAmountChangeEnabled(resource,true,false);
        if(resource == ResourceType.BRICK)
        {
            brickStatus = 0;
            brick = 0;
        }
        if(resource == ResourceType.ORE)
        {
            oreStatus = 0;
            ore = 0;
        }
        if(resource == ResourceType.SHEEP)
        {
            sheepStatus = 0;
            sheep = 0;
        }
        if(resource == ResourceType.WHEAT)
        {
            wheatStatus = 0;
            wheat = 0;
        }
        if(resource == ResourceType.WOOD)
        {
            woodStatus = 0;
            wood = 0;
        }
        printPlayerResourceStatus();
        updateTradeStatus();
        tradeOverlay.setResourceAmount(resource, "0");
    }

    @Override
    public void cancelTrade() {
        getTradeOverlay().closeModal();
    }

    @Override
    public void acceptTrade(boolean willAccept) {

        Facade.getInstance().acceptTrade(Facade.getInstance().getCurrentPlayer().getPlayerIndex(), willAccept);
        getAcceptOverlay().closeModal();
    }

    @Override
    public boolean checkResourceAdd(ResourceType resource, Integer amount) {
        if(resource == ResourceType.BRICK)
        {

            if(amount < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getBrick()) {
                return true;
            }
            else return false;
        }
        if(resource == ResourceType.ORE)
        {
            if(amount < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getOre()) {
                return true;
            }
            else return false;
        }
        if(resource == ResourceType.SHEEP)
        {
            if(amount < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getSheep()) {
                return true;
            }
            else return false;
        }
        if(resource == ResourceType.WHEAT)
        {
            if(amount < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getWheat()) {
                return true;
            }
            else return false;
        }
        if(resource == ResourceType.WOOD)
        {
            if(amount < Facade.getInstance().getGameModel().getPlayers().get(Facade.getInstance().getCurrentPlayer().getPlayerIndex()).getResources().getWood()) {
                return true;
            }
            else return false;
        }
        return false;
    }

    @Override
    public int checkResourceSubtract(ResourceType resource) {
        if(resource == ResourceType.BRICK)
        {
            if(brickStatus == 1)
            {
                return 1;
            }
        }
        if(resource == ResourceType.ORE)
        {
            if(oreStatus == 1)
            {
                return 1;
            }
        }
        if(resource == ResourceType.SHEEP)
        {
            if(sheepStatus == 1)
            {
                return 1;
            }
        }
        if(resource == ResourceType.WHEAT)
        {
            if(wheatStatus == 1)
            {
                return 1;
            }
        }
        if(resource == ResourceType.WOOD)
        {
            if(woodStatus == 1)
            {
                return 1;
            }

        }
        return 0;
    }

    @Override
    public void update(Observable o, Object arg)
    {

        if(Facade.getInstance().getCurrentPlayer().getPlayerIndex() == ((GameModel)o).getTurnTracker().getCurrentPlayer())
            ((IDomesticTradeView)getView()).enableDomesticTrade(true);
        else
            ((IDomesticTradeView)getView()).enableDomesticTrade(false);
        //System.out.println(((GameModel)o).getTradeOffer().toString());
//        System.out.println(((GameModel)o).getTradeOffer().toString());
        TradeOffer to = ((GameModel)o).getTradeOffer();
        if(((GameModel)o).getTurnTracker().getStatus().equalsIgnoreCase("Playing")) {
            if (to != null) {
                if (to.getReciever() == Facade.getInstance().getCurrentPlayer().getPlayerIndex()) {
                    getAcceptOverlay().showModal();
                    getAcceptOverlay().setAcceptEnabled(Facade.getInstance().canAcceptTrade());
                }
            }
        }
        if(((GameModel)o).getCurrentPlayer().getPlayerIndex() == ((GameModel)o).getTurnTracker().getCurrentPlayer())
            usable = true;
        else
            usable = false;

    }
    public void printPlayerResourceStatus()
    {
        System.out.println("BRICK: "+brick);
        System.out.println("ORE: "+ore);
        System.out.println("SHEEP: "+sheep);
        System.out.println("WHEAT: "+wheat);
        System.out.println("WOOD: "+wood);

    }
    public void updateTradeStatus()
    {

        if(brick == 0)
        {
            hasASend = false;
            hasARecieve = false;
        }
        if(ore == 0)
        {
            hasASend = false;
            hasARecieve = false;
        }
        if(sheep == 0)
        {
            hasASend = false;
            hasARecieve = false;
        }
        if(wheat == 0)
        {
            hasASend = false;
            hasARecieve = false;
        }
        if(wood == 0)
        {
            hasASend = false;
            hasARecieve = false;
        }
        if(brick > 0 && brickStatus == 1)
        {
            hasASend = true;
        }
        if(brick > 0 && brickStatus == -1)
        {
            hasARecieve = true;
        }
        if(ore > 0 && oreStatus == 1)
        {
            hasASend = true;
        }
        if(ore > 0 && oreStatus == -1)
        {
            hasARecieve = true;
        }
        if(sheep > 0 && sheepStatus == 1)
        {
            hasASend = true;
        }
        if(sheep > 0 && sheepStatus == -1)
        {
            hasARecieve = true;
        }
        if(wheat > 0 && wheatStatus == 1)
        {
            hasASend = true;
        }
        if(wheat > 0 && wheatStatus == -1)
        {
            hasARecieve = true;
        }
        if(wood > 0 && woodStatus == 1)
        {
            hasASend = true;
        }
        if(wood > 0 && woodStatus == -1)
        {
            hasARecieve = true;
        }
        if(hasARecieve && hasASend)
        {
            if(tradeAccepterId == -1)
            {
                tradeOverlay.setTradeEnabled(false);
                tradeOverlay.setStateMessage("Please select a player to trade with");
            }
            else {
                tradeOverlay.setTradeEnabled(true);
                tradeOverlay.setStateMessage("Trade!");
            }
        }
        else
        {
            tradeOverlay.setTradeEnabled(false);
            tradeOverlay.setStateMessage("Please select resources");
        }

    }


}

