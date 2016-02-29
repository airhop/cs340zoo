package client.MVC.resources;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import shared.infoobjects.CurrentResources;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController {

    private Map<ResourceBarElement, IAction> elementActions;

    public ResourceBarController(IResourceBarView view) {

        super(view);

        elementActions = new HashMap<ResourceBarElement, IAction>();
        Facade.getInstance().addObserver(this);
    }

    @Override
    public IResourceBarView getView() {
        return (IResourceBarView) super.getView();
    }

    /**
     * Sets the action to be executed when the specified resource bar element is clicked by the user
     *
     * @param element The resource bar element with which the action is associated
     * @param action  The action to be executed
     */
    public void setElementAction(ResourceBarElement element, IAction action) {

        elementActions.put(element, action);
    }

    @Override
    public void buildRoad() {
        executeElementAction(ResourceBarElement.ROAD);
    }

    @Override
    public void buildSettlement() {
        executeElementAction(ResourceBarElement.SETTLEMENT);
    }

    @Override
    public void buildCity() {
        executeElementAction(ResourceBarElement.CITY);
    }

    @Override
    public void buyCard() {
        executeElementAction(ResourceBarElement.BUY_CARD);
    }

    @Override
    public void playCard() {
        executeElementAction(ResourceBarElement.PLAY_CARD);
    }

    private void executeElementAction(ResourceBarElement element) {

        if (elementActions.containsKey(element)) {
            IAction action = elementActions.get(element);
            action.execute();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(Facade.getInstance().isReady()){
            CurrentResources currentResources = Facade.getInstance().getCurrentResources();
            getView().setElementAmount(ResourceBarElement.WOOD,currentResources.getWood());
            getView().setElementAmount(ResourceBarElement.BRICK,currentResources.getBrick());
            getView().setElementAmount(ResourceBarElement.SHEEP,currentResources.getSheep());
            getView().setElementAmount(ResourceBarElement.WHEAT,currentResources.getWheat());
            getView().setElementAmount(ResourceBarElement.ORE,currentResources.getOre());
            if(currentResources.getBrick() > 0 && currentResources.getWheat() > 0 && currentResources.getSheep() > 0 && currentResources.getWood() > 0 && currentResources.getSettlement() > 0){
                getView().setElementEnabled(ResourceBarElement.SETTLEMENT, true);
            }else{
                getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
            }
            if(currentResources.getWheat() > 1 && currentResources.getOre() > 2 && currentResources.getCity() > 0){
                getView().setElementEnabled(ResourceBarElement.CITY, true);
            }else{
                getView().setElementEnabled(ResourceBarElement.CITY, false);
            }
            if(currentResources.getBrick() > 0 && currentResources.getWood() > 0 && currentResources.getRoad() > 0){
                getView().setElementEnabled(ResourceBarElement.ROAD, true);
            }else{
                getView().setElementEnabled(ResourceBarElement.ROAD, false);
            }

            getView().setElementAmount(ResourceBarElement.ROAD,currentResources.getRoad());
            getView().setElementAmount(ResourceBarElement.SETTLEMENT,currentResources.getSettlement());
            getView().setElementAmount(ResourceBarElement.CITY,currentResources.getCity());
            if(currentResources.getSheep() > 0 && currentResources.getWheat() > 0 && currentResources.getOre() > 0){
                getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
            }else{
                getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
            }
            getView().setElementAmount(ResourceBarElement.BUY_CARD,currentResources.getBuyCard());
            if(currentResources.getPlayCard() > 0){
                getView().setElementEnabled(ResourceBarElement.PLAY_CARD, true);
            }else{
                getView().setElementEnabled(ResourceBarElement.PLAY_CARD, true);
            }
            getView().setElementAmount(ResourceBarElement.PLAY_CARD,currentResources.getPlayCard());
            if(currentResources.getSoldiers() > 0){
                getView().setElementEnabled(ResourceBarElement.SOLDIERS, true);
            }else{
                getView().setElementEnabled(ResourceBarElement.SOLDIERS, false);
            }
            getView().setElementAmount(ResourceBarElement.SOLDIERS,currentResources.getSoldiers());
//            getView().setElementEnabled(ResourceBarElement.ROAD, true);
        }
    }
}

