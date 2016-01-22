package server;

import shared.jsonobject.*;
import client.model.*;
import client.model.map.*;
import client.model.history.*;
import client.model.bank.ResourceList;

public class MockServer implements IServerProxy{

    public MockServer()
    {
    }

    //User operations
	/** @param l object with username and PassObjectword
	 * @return true/false whether a successful login or not.
	 */
    public boolean userLogin(Login l)
    {
        return false;
    }

    /** @param l object with new username and PassObjectword
     * @return true/false whether a successful registry or not
     */
    public boolean userRegister(Login l)
    {
        //check that user doesn't already exist
        return false;
    }

//Games: Game queries/actions (pre-joining)

    /**no prereqs
     * @return array of Game objects
     */
    public ClientModel[] gamesList()
    {
        ClientModel[] cm = new ClientModel[2];
        return cm;
    }

    /**@param p object - should have new Game name
     *@return = newly created game
     */
    public ClientModel gamesCreate(PassObject p)
    {
        return new ClientModel();
    }

    /**@param p object - hold the pid and string color
     *@return true/false based on if everything set
     */
    public boolean gamesJoin(PassObject p)
    {
        return false;
    }

//games save and load are not necessary on the proxy?

//Game: operations for the game you're in, requires cookies

    /**@return  the Game model, true if version provided,
     */
    public ClientModel gameModel()
    {
        return new ClientModel();
    }

    /**conditions - verify that user is logged in and joined a game
     *   there needs to be space for another player
     *@param p object - "LARGEST ARMY" is only string accepted
     *return true/false if added
     */
    public boolean gameAddAI(PassObject p)
    {
        return false;
    }

    /**no preconditions
     *@return string array of AI types
     */
    public String[] gameListAI()
    {
        return new String[2];
    }
//game reset, and commands are not necessary on the proxy	
//no need for /util/changeLogLevel method

//Moves

    /**@param msg to be sent
     *no preconditions for sending a message
     */
    public void movesSendChat(MessageList msg)
    {

    }

    /**@param p = id of player
     *@return roll = 2-12
     *conditions - must be players turn in rolling status
     *
     *change players status, and return rolled number
     */
    public int movesRollNumber(PassObject p)
    {
        return 2;
    }

    /**@param p - object with player id and victim id
     *@param hl new location of robber
     *Robber stuff.  Set the robber, give around robbed cards
     */
    public void movesRobPlayer(PassObject p, HexLocation hl)
    {

    }

    /**@param p object with player id
     *Finish the turn and PassObject the torch
     */
    public void movesFinishTurn(PassObject p)
    {

    }

    /**@param p object with player id of player that it is happening to
     */
    public void movesBuyDevCard(PassObject p)
    {

    }

    /**@param p object with player that is making the move
     *@param rl - items to be taken?
     */
    public void movesYearOfPlenty(PassObject p, ResourceList rl)
    {

    }

    /**@param p player that is making the move
     *@param el - edge place 1
     *@param e2 - edge place 2
     */
    public void movesRoad_Building(PassObject p, EdgeLocation el, EdgeLocation e2)
    {

    }

    /**@param p - Player id, victim ID
     *@param hl - where robber will be
     */
    public void movesSoldier(PassObject p, HexLocation hl)
    {

    }

    /**@param p - player id, resource
     */
    public void movesMonopoly(PassObject p)
    {

    }

    /**@param p - player id
     */
    public void movesMonument(PassObject p)
    {
    }

    /**@param p - player id, availability
     *@param el - place in question
     */
    public void movesBuildRoad(PassObject p, EdgeLocation el)
    {
    }

    /**@param p - player id, bool availability
     *@param vl - place in question
     */
    public void movesBuildSettlement(PassObject p, VertexObject vl)
    {
    }

    /**@param p - player id, bool availability
     *@param vl - place in question
     */
    public void movesBuildCity(PassObject p, VertexObject vl)
    {
    }

    /**@param p - player id reciever id
     *@param rl - items being offered
     */
    public void movesOfferTrade(PassObject p, ResourceList rl)
    {
    }

    /**@param p - player id reciever id
     *@return PassObject - player, acceptability
     */
    public PassObject movesAcceptTrade(PassObject p)
    {
        return p;
    }

    /**@param p - player id
     *@param rl - items to trade
     *@return - player id, acceptability
     */
    public PassObject movesMaritimeTrade(PassObject p, ResourceList rl)
    {
        return p;
    }

    /**@param p - player id
     *@param rl - cards to discard
     */
    public void movesdiscardCards(PassObject p, ResourceList rl)
    {
    }
}
