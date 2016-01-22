package client.poller;

import client.model.ClientModel;
import client.proxy.IProxy;

public class Poller {
    IProxy myProxy;
    ClientModel myModel;

    public Poller(IProxy givenProxy, ClientModel givenModel){
        myProxy = givenProxy;
        myModel = givenModel;
    }

    /**
     * Every few seconds the poller grabs the model from the server
     * and updates the ClientsModel
     * @return
     */
    public ClientModel getClientModel(){
        return null;
    }
}
