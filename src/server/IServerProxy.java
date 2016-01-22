package server;

import shared.jsonobject.*;
import client.model.*;
import client.model.map.*;
import client.model.history.*;
import client.model.bank.ResourceList;


public interface IServerProxy {
    //User operations
	/* @param Login object with username and PassObjectword
	 * @return true/false whether a successful login or not.
	 */
    boolean userLogin(Login l);

    /* @param Login object with new username and PassObjectword
     * @return true/false whether a successful registry or not
     */
    boolean userRegister(Login l);

//Games: Game queries/actions (pre-joining)

    /*no prereqs
     * @return array of Game objects
     */
    ClientModel[] gamesList();

    /*@param PassObject object - should have new Game name
     *@return = newly created game
     */
    ClientModel gamesCreate(PassObject p);

    /*@param PassObject object - hold the pid and string color
     *@return true/false based on if everything set
     */
    boolean gamesJoin(PassObject p);

//games save and load are not necessary on the proxy?

//Game: operations for the game you're in, requires cookies

    /*@return  the Game model, true if version provided,
     */
    ClientModel gameModel();

    /*conditions - verify that user is logged in and joined a game
     *   there needs to be space for another player
     *@param PassObject object - "LARGEST ARMY" is only string accepted
     *return true/false if added
     */
    boolean gameAddAI(PassObject p);

    /*no preconditions
     *@return string array of AI types
     */
    String[] gameListAI();

//game reset, and commands are not necessary on the proxy	
//no need for /util/changeLogLevel method

//Moves

    /*@param msg to be sent
     *no preconditions for sending a message
     */
    void movesSendChat(MessageList msg);

    /*@param playerid = id of player
     *@return roll = 2-12
     *conditions - must be players turn in rolling status
     *
     *change players status, and return rolled number
     */
    int movesRollNumber(PassObject p);

    /*@PassObject p - object with player id and victim id
     *@param HexLocation new location of robber
     *Robber stuff.  Set the robber, give around robbed cards
     */
    void movesRobPlayer(PassObject p, HexLocation hl);

    /*@param PassObject object with player id
     *Finish the turn and PassObject the torch
     */
    void movesFinishTurn(PassObject p);

    /*@param PassObject object with player id of player that it is happening to
     */
    void movesBuyDevCard(PassObject p);

    /*@param PassObject object with player that is making the move
     *@param ResourceList - items to be taken?
     */
    void movesYearOfPlenty(PassObject p, ResourceList rl);

    /*@param PassObject player that is making the move
     *@param EdgeLocation1
     *@param EdgeLocation2
     */
    void movesRoad_Building(PassObject p, EdgeLocation el, EdgeLocation e2);

    /*@param PassObject - Player id, victim ID
     *@param Hexlocation - where robber will be
     */
    void movesSoldier(PassObject p, HexLocation hl);

    /*@param PassObject - player id, resource
     */
    void movesMonopoly(PassObject p);

    /*@param PassObject - player id
     */
    void movesMonument(PassObject p);

    /*@param PassObject - player id, availability
     *@param EdgeLocation - place in question
     */
    void movesBuildRoad(PassObject p, EdgeLocation el);

    /*@param PassObject - player id, bool availability
     *@VertexLocation - place in question
     */
    void movesBuildSettlement(PassObject p, VertexObject vl);

    /*@param PassObject - player id, bool availability
     *@VertexLocation - place in question
     */
    void movesBuildCity(PassObject p, VertexObject vl);

    /*@param PassObject - player id reciever id
     *@param ResourceList - items being offered
     */
    void movesOfferTrade(PassObject p, ResourceList rl);

    /*@param PassObject - player id reciever id
     *@return PassObject - player, acceptability
     */
    PassObject movesAcceptTrade(PassObject p);

    /*@param PassObject - player id
     *@param ResourceList - items to trade
     *@return - player id, acceptability
     */
    PassObject movesMaritimeTrade(PassObject p, ResourceList rl);

    /*@param PassObject - player id
     *@param ResourceList - cards to discard
     */
    void movesdiscardCards(PassObject p, ResourceList rl);

}
