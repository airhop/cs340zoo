package client.MVC.devcards;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.bank.DevCardList;
import client.model.player.Player;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

import java.util.Observable;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

    private IBuyDevCardView buyCardView;
    private IAction soldierAction;
    private IAction roadAction;

    /**
     * DevCardController constructor
     *
     * @param view          "Play dev card" view
     * @param buyCardView   "Buy dev card" view
     * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
     * @param roadAction    Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
     */
    public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, IAction soldierAction, IAction roadAction) {

        super(view);

        this.buyCardView = buyCardView;
        this.soldierAction = soldierAction;
        this.roadAction = roadAction;
    }

    public IPlayDevCardView getPlayCardView() {
        return (IPlayDevCardView) super.getView();
    }

    public IBuyDevCardView getBuyCardView() {
        return buyCardView;
    }

    @Override
    public void startBuyCard() {
        getBuyCardView().showModal();
    }

    @Override
    public void cancelBuyCard() {
        getBuyCardView().closeModal();
    }

    @Override
    public void buyCard() {
        Facade.getInstance().buyDevCard(Facade.getInstance().getPlayerIndex());
        getBuyCardView().closeModal();
    }

    @Override
    public void startPlayCard() {
        Player myPlayer = Facade.getInstance().getPlayerByYourIndex();
        DevCardList myNewCards = myPlayer.getNewDevCards();
        DevCardList myOldCards = myPlayer.getOldDevCards();
        int soldier = myOldCards.getSoldier();
        int yearOfPlenty = myOldCards.getYearOfPlenty();
        int monopoly = myOldCards.getMonopoly();
        int roadBuilding = myOldCards.getRoadBuilding();
        int monument = myNewCards.getMonument() + myOldCards.getMonument();
        getPlayCardView().setCardAmount(DevCardType.SOLDIER, soldier);
        getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, yearOfPlenty);
        getPlayCardView().setCardAmount(DevCardType.MONOPOLY, monopoly);
        getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, roadBuilding);
        getPlayCardView().setCardAmount(DevCardType.MONUMENT, monument);
        if(soldier > 0){
            getPlayCardView().setCardEnabled(DevCardType.SOLDIER, true);
        }else{
            getPlayCardView().setCardEnabled(DevCardType.SOLDIER, false);
        }
        if(yearOfPlenty > 0){
            getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, true);
        }else{
            getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, false);
        }

        if(monopoly > 0){
            getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, true);
        }else{
            getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, false);
        }

        if(roadBuilding > 0){
            getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, true);
        }else{
            getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, false);
        }

        if(monument > 0 && Facade.getInstance().getPoints(Facade.getInstance().getPlayerIndex()) + monument == 10){
            getPlayCardView().setCardEnabled(DevCardType.MONUMENT, true);
        }else{
            getPlayCardView().setCardEnabled(DevCardType.MONUMENT, false);
        }
        getPlayCardView().showModal();
    }

    @Override
    public void cancelPlayCard() {

        getPlayCardView().closeModal();
    }

    @Override
    public void playMonopolyCard(ResourceType resource) {
        Facade.getInstance().playMonopoly(Facade.getInstance().getPlayerIndex(), resource);
    }

    @Override
    public void playMonumentCard() {

    }

    @Override
    public void playRoadBuildCard() {

        roadAction.execute();
    }

    @Override
    public void playSoldierCard() {

        soldierAction.execute();
    }

    @Override
    public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
        Facade.getInstance().playYearOfPlenty(Facade.getInstance().getPlayerIndex(), resource1, resource2);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

