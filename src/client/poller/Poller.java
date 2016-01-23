package client.poller;


import client.model.GameModel;
import client.proxy.IProxy;

public class Poller {
    IProxy myProxy;
    GameModel myModel;

    public Poller(IProxy givenProxy, GameModel givenModel){
        myProxy = givenProxy;
        myModel = givenModel;
    }

    /**
     * Every few seconds the poller grabs the model from the server
     * and updates the ClientsModel
     * @return
     */
    public GameModel getClientModel(){
        return null;
    }
}
