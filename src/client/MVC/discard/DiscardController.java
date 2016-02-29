package client.MVC.discard;

import client.MVC.base.*;
import client.MVC.misc.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.bank.ResourceList;
import shared.definitions.ResourceType;

import java.util.Observable;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

    private IWaitView waitView;
    int brick, ore, sheep, wheat, wood;

    /**
     * DiscardController constructor
     *
     * @param view     View displayed to let the user select cards to discard
     * @param waitView View displayed to notify the user that they are waiting for other players to discard
     */
    public DiscardController(IDiscardView view, IWaitView waitView) {

        super(view);

        this.waitView = waitView;
        brick = 0;
        ore = 0;
        sheep = 0;
        wheat = 0;
        wood = 0;
    }

    public IDiscardView getDiscardView() {
        return (IDiscardView) super.getView();
    }

    public IWaitView getWaitView() {
        return waitView;
    }

    @Override
    public void increaseAmount(ResourceType resource)
    {
        changeAmount(resource, 1);
    }

    @Override
    public void decreaseAmount(ResourceType resource)
    {
        changeAmount(resource, -1);
    }


    public void changeAmount(ResourceType resource, int change)
    {
        switch(resource)
        {
            case BRICK: brick += change;
                        return;
            case ORE: ore += change;
                        return;
            case SHEEP: sheep += change;
                        return;
            case WHEAT: wheat += change;
                        return;
            case WOOD: wood += change;
                        return;
            default:
                System.out.println("oops!!!");
        }
    }

    @Override
    public void discard()
    {
        int pid = Facade.getInstance().getPlayerID();
        ResourceList rl = new ResourceList(brick, ore, sheep, wheat, wood);

        //canDiscardCards is called by Discard cards so no need to call it twice
        Facade.getInstance().DiscardCards(pid, rl);
        getDiscardView().closeModal();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        //nothing to update
        GameModel gm = (GameModel) o;
        if(gm.getTurnTracker().getStatus().equalsIgnoreCase("Discard"))
            getDiscardView().showModal();
      //  else
      //      getDiscardView().closeModal();
    }
}

