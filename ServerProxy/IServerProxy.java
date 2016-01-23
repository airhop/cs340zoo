package server;

import shared.jsonobject.*;
import client.model.*;
import client.model.map.*;
import client.model.history.*;
import client.model.bank.ResourceList;


public interface IServerProxy {
    //User operations

    /**
     * verify that the user has logged in with the correct password and user exists
     *
     * @param l - Login object with username and PassObjectword
     * @return true/false whether a successful login or not.
     */
    public boolean userLogin(Login l);

    /**
     * verify that user does not exist and create a new player
     *
     * @param l - Login object with new username and PassObjectword
     * @return true/false whether a successful registry or not
     */
    public boolean userRegister(Login l);

//Games: Game queries/actions (pre-joining)

    /**
     * list the unfinished games
     *
     * @return array of Game objects
     */
    public ClientModel[] gamesList();

    /**
     * find if the game was created.  otherwise create a new game
     *
     * @param p -PassObject object - should have new Game name
     * @return = newly created game
     */
    public ClientModel gamesCreate(PassObject p);

    /**
     * If there is room join an open game
     *
     * @param p - PassObject object - hold the pid and string color
     * @return true/false based on if everything set
     */
    public boolean gamesJoin(PassObject p);

//games save and load are not necessary on the proxy?

//Game: operations for the game you're in, requires cookies

    /**
     * Retrieve the current model of the going game
     *
     * @return the Game model, true if version provided,
     */
    public ClientModel gameModel();

    /**
     * conditions - verify that user is logged in and joined a game
     * there needs to be space for another player
     *
     * @param p - PassObject object - "LARGEST ARMY" is only string accepted
     *          return true/false if added
     */
    public boolean gameAddAI(PassObject p);

    /**
     * no preconditions
     *
     * @return string array of AI types
     */
    public String[] gameListAI();

//game reset, and commands are not necessary on the proxy	
//no need for /util/changeLogLevel method

//Moves

    /**
     * @param msg to be sent
     *            no preconditions for sending a message
     */
    public void movesSendChat(MessageList msg);

    /**
     * @param p -playerid = id of player
     * @return roll = 2-12
     * conditions - must be players turn in rolling status
     * <p>
     * change players status, and return rolled number
     */
    public int movesRollNumber(PassObject p);

    /**
     * @param p  - object with player id and victim id
     * @param hl - HexLocation new location of robber
     *           Robber stuff.  Set the robber, give around robbed cards
     */
    public void movesRobPlayer(PassObject p, HexLocation hl);

    /**
     * @param p - PassObject object with player id
     *          Finish the turn and PassObject the torch
     */
    public void movesFinishTurn(PassObject p);

    /**
     * Given that preconditions are met, purchase a devcard
     *
     * @param p - PassObject object with player id of player that it is happening to
     */
    public void movesBuyDevCard(PassObject p);

    /**
     * Use a year of plenty dev card
     *
     * @param p  - PassObject object with player that is making the move
     * @param rl - ResourceList - items to be taken?
     */
    public void movesYearOfPlenty(PassObject p, ResourceList rl);

    /**
     * Use the road building card
     *
     * @param p  - PassObject player that is making the move
     * @param el - EdgeLocation1
     * @param e2 - EdgeLocation2
     */
    public void movesRoad_Building(PassObject p, EdgeLocation el, EdgeLocation e2);

    /**
     * use the move soldier card
     *
     * @param p   - PassObject - Player id, victim ID
     * @param hl- Hexlocation - where robber will be
     */
    public void movesSoldier(PassObject p, HexLocation hl);

    /**
     * use the monopoly card
     *
     * @param p -PassObject - player id, resource
     */
    public void movesMonopoly(PassObject p);

    /**
     * @param p - PassObject - player id
     */
    public void movesMonument(PassObject p);

    /**
     * build a road at location el
     *
     * @param p  - PassObject - player id, availability
     * @param el - EdgeLocation - place in question
     */
    public void movesBuildRoad(PassObject p, EdgeLocation el);

    /**
     * build a settlement at vl
     *
     * @param p  -PassObject - player id, bool availability
     * @param vl - VertexObject - place in question
     */
    public void movesBuildSettlement(PassObject p, VertexObject vl);

    /**
     * build a city at location vl
     *
     * @param p  - PassObject - player id, bool availability
     * @param vl - VertexObject - place in question
     */

    public void movesBuildCity(PassObject p, VertexObject vl);

    /**
     * offer a trade from player id to reciever id of items on the resourcelist
     *
     * @param p  - PassObject - player id reciever id
     * @param rl - ResourceList - items being offered
     */
    void movesOfferTrade(PassObject p, ResourceList rl);

    /**
     * accept the trade that was offered
     *
     * @param p - PassObject - player id reciever id
     * @return PassObject - player, acceptability
     */
    public PassObject movesAcceptTrade(PassObject p);

    /**
     * Perform a maritime trade
     *
     * @param p  - PassObject - player id
     * @param rl - ResourceList - items to trade
     * @return - player id, acceptability
     */
    public PassObject movesMaritimeTrade(PassObject p, ResourceList rl);

    /**
     * discard cards whether from robber or other methods
     *
     * @param p  - PassObject - player id
     * @param rl - ResourceList - cards to discard
     */
    public void movesdiscardCards(PassObject p, ResourceList rl);

}
