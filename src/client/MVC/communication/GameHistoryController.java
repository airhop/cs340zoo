package client.MVC.communication;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.history.MessageLine;
import client.model.player.Player;
import shared.definitions.CatanColor;

import java.util.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {

    public GameHistoryController(IGameHistoryView view) {

        super(view);
        Facade.getInstance().addObserver(this);
    }

    @Override
    public IGameHistoryView getView() {

        return (IGameHistoryView) super.getView();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(!Facade.getInstance().isReady())
            return;
        GameModel gm = (GameModel) o;
        ArrayList<MessageLine> ml = gm.getLog().getLogList().getMessages();
        System.out.println("Game History - " + ml.size());
        ArrayList<Player> players = gm.getPlayers();
        Map <String, CatanColor> conversion = new TreeMap<String, CatanColor>();

        for(int i = 0; i < players.size(); i++)
        {
            String color = players.get(i).getColor();
            if(color == null || color.isEmpty()) //happens at start up
                return;
            conversion.put(players.get(i).getUsername(), CatanColor.convert(color));
        }
        List<LogEntry> logs = new ArrayList<LogEntry>();
        for(int i = 0;  i< ml.size(); i++)
        {
            //message and source were mixed up on accident
            CatanColor c = conversion.get(ml.get(i).getMessage());
            logs.add(new LogEntry(c, ml.get(i).getSource()));
        }

        getView().setEntries(logs);

    }
}

