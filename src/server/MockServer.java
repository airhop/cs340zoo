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
	/* @param Login object with username and PassObjectword
	 * @return true/false whether a successful login or not.
	 */
    public boolean userLogin(Login l)
    {
        return false;
    }

    /* @param Login object with new username and PassObjectword
     * @return true/false whether a successful registry or not
     */
    public boolean userRegister(Login l)
    {
        //check that user doesn't already exist
        return false;
    }

//Games: Game queries/actions (pre-joining)

    /*no prereqs
     * @return array of Game objects
     */
    public ClientModel[] gamesList()
    {
        ClientModel[] cm = new ClientModel[2];
        return cm;
    }

    /*@param PassObject object - should have new Game name
     *@return = newly created game
     */
    public ClientModel gamesCreate(PassObject p)
    {
        return new ClientModel();
    }

    /*@param PassObject object - hold the pid and string color
     *@return true/false based on if everything set
     */
    public boolean gamesJoin(PassObject p)
    {
        return false;
    }

//games save and load are not necessary on the proxy?

//Game: operations for the game you're in, requires cookies

    /*@return  the Game model, true if version provided,
     */
    public ClientModel gameModel()
    {
        return new ClientModel();
    }

    /*conditions - verify that user is logged in and joined a game
     *   there needs to be space for another player
     *@param PassObject object - "LARGEST ARMY" is only string accepted
     *return true/false if added
     */
    public boolean gameAddAI(PassObject p)
    {
        return false;
    }

    /*no preconditions
     *@return string array of AI types
     */
    public String[] gameListAI()
    {
        return new String[2];
    }
//game reset, and commands are not necessary on the proxy	
//no need for /util/changeLogLevel method

//Moves

    /*@param msg to be sent
     *no preconditions for sending a message
     */
    public void movesSendChat(MessageList msg)
    {

    }

    /*@param playerid = id of player
     *@return roll = 2-12
     *conditions - must be players turn in rolling status
     *
     *change players status, and return rolled number
     */
    public int movesRollNumber(PassObject p)
    {
        return 2;
    }

    /*@PassObject p - object with player id and victim id
     *@param HexLocation new location of robber
     *Robber stuff.  Set the robber, give around robbed cards
     */
    public void movesRobPlayer(PassObject p, HexLocation hl)
    {

    }

    /*@param PassObject object with player id
     *Finish the turn and PassObject the torch
     */
    public void movesFinishTurn(PassObject p)
    {

    }

    /*@param PassObject object with player id of player that it is happening to
     */
    public void movesBuyDevCard(PassObject p)
    {

    }

    /*@param PassObject object with player that is making the move
     *@param ResourceList - items to be taken?
     */
    public void movesYearOfPlenty(PassObject p, ResourceList rl)
    {

    }

    /*@param PassObject player that is making the move
     *@param EdgeLocation1
     *@param EdgeLocation2
     */
    public void movesRoad_Building(PassObject p, EdgeLocation el, EdgeLocation e2)
    {

    }

    /*@param PassObject - Player id, victim ID
     *@param Hexlocation - where robber will be
     */
    public void movesSoldier(PassObject p, HexLocation hl)
    {

    }

    /*@param PassObject - player id, resource
     */
    public void movesMonopoly(PassObject p)
    {

    }

    /*@param PassObject - player id
     */
    public void movesMonument(PassObject p)
    {
    }

    /*@param PassObject - player id, availability
     *@param EdgeLocation - place in question
     */
    public void movesBuildRoad(PassObject p, EdgeLocation el)
    {
    }

    /*@param PassObject - player id, bool availability
     *@VertexLocation - place in question
     */
    public void movesBuildSettlement(PassObject p, VertexObject vl)
    {
    }

    /*@param PassObject - player id, bool availability
     *@VertexLocation - place in question
     */
    public void movesBuildCity(PassObject p, VertexObject vl)
    {
    }

    /*@param PassObject - player id reciever id
     *@param ResourceList - items being offered
     */
    public void movesOfferTrade(PassObject p, ResourceList rl)
    {
    }

    /*@param PassObject - player id reciever id
     *@return PassObject - player, acceptability
     */
    public PassObject movesAcceptTrade(PassObject p)
    {
        return p;
    }

    /*@param PassObject - player id
     *@param ResourceList - items to trade
     *@return - player id, acceptability
     */
    public PassObject movesMaritimeTrade(PassObject p, ResourceList rl)
    {
        return p;
    }

    /*@param PassObject - player id
     *@param ResourceList - cards to discard
     */
    public void movesdiscardCards(PassObject p, ResourceList rl)
    {
    }
}
