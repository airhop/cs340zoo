package client.MVC.communication;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.history.Chat;
import client.model.history.MessageLine;
import client.model.history.MessageList;
import client.model.player.Player;
import shared.definitions.CatanColor;

import java.util.*;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

    public ChatController(IChatView view) {

        super(view);
        Facade.getInstance().addObserver(this);
    }

    @Override
    public IChatView getView() {
        return (IChatView) super.getView();
    }

    @Override
    public void sendMessage(String message)
    {
        Facade f = Facade.getInstance();
        f.canSendChat(message, f.getPlayerIndex());

    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(!Facade.getInstance().isReady())
            return;
        GameModel gm = (GameModel)o;
        ArrayList<MessageLine> ml = gm.getChat().getChatList().getMessages();
        ArrayList<Player> players = gm.getPlayers();
        Map<String, CatanColor> conversion = new TreeMap<String, CatanColor>();

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
            CatanColor c = conversion.get(ml.get(i).getSource());
            logs.add(new LogEntry(c, ml.get(i).getMessage()));
        }

        getView().setEntries(logs);

    }
}

