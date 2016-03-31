package shared.test;

import client.model.GameModel;
import client.proxy.Cookie;
import org.junit.Test;
import server.serverfacade.ServerFacade;
import server.servermain.Server;

public class P3Testing
{
    public P3Testing()
    {
//        Server.main(new String[0]);
    }

    @Test
    public void sendChat()
    {
        ServerFacade facade = ServerFacade.getInstance();
        facade.userLogin("David", "david");
        facade.buildCurrentPlayer(new Cookie("David", "david", "0"), new Cookie());
        facade.joinGame(0, "blue");
        facade.buildCurrentPlayer(new Cookie("David", "david", "0"), new Cookie(0));
        facade.sendChat(0, "Hello World!");
        facade.joinGame(1, "red");
        facade.buildCurrentPlayer(new Cookie("David", "david", "0"), new Cookie(1));
        facade.sendChat(0, "Whazup?");

        GameModel gm1 = facade.getModel();
        assert(gm1.getChat().getChatList().size() == 1);
        assert(gm1.getChat().getChatList().getMessages().get(0).getMessage().equals("Whazup?"));
        assert(gm1.getChat().getChatList().getMessages().get(0).getSource().equals("David"));

        facade.joinGame(0, "blue");
        facade.buildCurrentPlayer(new Cookie("David", "david", "0"), new Cookie(0));
        gm1 = facade.getModel();
        assert(gm1.getChat().getChatList().size() == 1);
        assert(gm1.getChat().getChatList().getMessages().get(0).getMessage().equals("Hello World!"));
        assert(gm1.getChat().getChatList().getMessages().get(0).getSource().equals("David"));

    }



}
