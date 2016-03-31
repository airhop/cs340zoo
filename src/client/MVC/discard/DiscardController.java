package client.MVC.discard;

import client.MVC.base.*;
import client.MVC.misc.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.bank.ResourceList;
import shared.definitions.ResourceType;

import javax.swing.*;
import java.util.Observable;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

    private IWaitView waitView;
    int brick, ore, sheep, wheat, wood;
    int discardamount;
    ResourceList rl;

    /**
     * DiscardController constructor
     *
     * @param view     View displayed to let the user select cards to discard
     * @param waitView View displayed to notify the user that they are waiting for other players to discard
     */
    public DiscardController(IDiscardView view, IWaitView waitView) {

        super(view);
        Facade.getInstance().addObserver(this);
        this.waitView = waitView;
        refresh();
    }

    public void refresh()
    {
        brick = 0;
        ore = 0;
        sheep = 0;
        wheat = 0;
        wood = 0;
        discardamount = 0;
        rl = new ResourceList(0,0,0,0,0);
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
                        verify();
                        return;
            case ORE: ore += change;
                        verify();
                        return;
            case SHEEP: sheep += change;
                        verify();
                        return;
            case WHEAT: wheat += change;
                        verify();
                        return;
            case WOOD: wood += change;
                        verify();
                        return;
            default:
                System.out.println("oops!!!");
        }
    }

    @Override
    public void discard()
    {
        int playerIndex = Facade.getInstance().getPlayerIndex();
        ResourceList rl = new ResourceList(brick, ore, sheep, wheat, wood);

        //canDiscardCards is called by Discard cards so no need to call it twice
        if(Facade.getInstance().DiscardCards(playerIndex, rl))
        {
            refresh();
            getDiscardView().closeModal();
        }
        else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Server had some issues . . .", "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verify()
    {
        boolean discardPossible = false;
        if((brick+ore+sheep+wheat+wood) == discardamount)
        {
            discardPossible = true;
            getDiscardView().setDiscardButtonEnabled(true);
        }
        else
            getDiscardView().setDiscardButtonEnabled(false);
        getDiscardView().setStateMessage("" + (brick+ore+sheep+wood+wheat) + "/" + discardamount);


        int r = rl.getBrick();
        boolean increase = false;
        boolean decrease = false;
        getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, brick);
        getDiscardView().setResourceMaxAmount(ResourceType.BRICK, r);
        if(brick >= 0 && brick < r && !discardPossible)
            increase = true;
        if(brick <= r && brick > 0)
            decrease = true;
        getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, increase, decrease);

        r = rl.getOre();
        increase = false;
        decrease = false;
        getDiscardView().setResourceDiscardAmount(ResourceType.ORE, ore);
        getDiscardView().setResourceMaxAmount(ResourceType.ORE, r);
        if(ore >= 0 && ore < r && !discardPossible)
            increase = true;
        if(ore <= r && ore > 0)
            decrease = true;
        getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, increase, decrease);

        r = rl.getSheep();
        increase = false;
        decrease = false;
        getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, sheep);
        getDiscardView().setResourceMaxAmount(ResourceType.SHEEP, r);
        if(sheep >= 0 && sheep < r && !discardPossible)
            increase = true;
        if(sheep <= r && sheep > 0)
            decrease = true;
        getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, increase, decrease);

        r = rl.getWheat();
        increase = false;
        decrease = false;
        getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, wheat);
        getDiscardView().setResourceMaxAmount(ResourceType.WHEAT, r);
        if(wheat >= 0 && wheat < r && !discardPossible)
            increase = true;
        if(wheat <= r && wheat > 0)
            decrease = true;
        getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, increase, decrease);

        r = rl.getWood();
        increase = false;
        decrease = false;
        getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, wood);
        getDiscardView().setResourceMaxAmount(ResourceType.WOOD, r);
        if(wood >= 0 && wood < r && !discardPossible)
            increase = true;
        if(wood <= r && wood > 0)
            decrease = true;
        getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, increase, decrease);

    }

    @Override
    public void update(Observable o, Object arg)
    {

        GameModel gm = (GameModel) o;
//        if(gm.getTurnTracker().getCurrentPlayer() != gm.getCurrentPlayer().getPlayerIndex())
//            return;

        rl = gm.getPlayers().get(gm.getCurrentPlayer().getPlayerIndex()).getResources();

        if (gm.getTurnTracker().getStatus().equalsIgnoreCase("Discarding"))
        {
            discardamount = rl.size()/2;
            verify();
            if(!getDiscardView().isModalShowing()){
                getDiscardView().showModal();
            }
        }
      //  else
      //      getDiscardView().closeModal();
    }
}

