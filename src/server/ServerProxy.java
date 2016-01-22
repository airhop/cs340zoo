package server;

import client.model.ClientModel;
import client.model.bank.ResourceList;
import client.model.history.MessageList;
import client.model.map.EdgeLocation;
import client.model.map.HexLocation;
import client.model.map.VertexObject;
import shared.jsonobject.Login;
import shared.jsonobject.PassObject;

public class ServerProxy implements IServerProxy{

	boolean valid;
	//first check.  if(!valid) return hard coded statement
	//				else do everything and return normal

	//	Server s;
	//@param boolean v - if true valid server, else mock server
	public ServerProxy(boolean v)
	{
		valid = v;
		//		if(v)
		//			s = new Server();
	}

	//User operations
	/* @param Login object with username and PassObjectword
	 * @return true/false whether a successful login or not.
	 */
	@Override
	public boolean userLogin(Login l)
	{
		//		if(username == null || PassObjectword == null)
		//			return false;
		//		return Server.login(username, PassObjectword);
		return false;
	}

	/* @param Login object with new username and PassObjectword
     * @return true/false whether a successful registry or not
     */
	@Override
	public boolean userRegister(Login l)
	{
		//check that user doesn't already exist
		return false;
	}

	//Games: Game queries/actions (pre-joining)

	/*no prereqs
     * @return array of Game objects
     */
	@Override
	public ClientModel[] gamesList()
	{
		ClientModel[] cm = new ClientModel[2];
		return cm;
	}

	/*@param PassObject object - should have new Game name
     *@return = newly created game
     */
	@Override
	public ClientModel gamesCreate(PassObject p)
	{
		return new ClientModel();
	}

	/*@param PassObject object - hold the pid and string color
     *@return true/false based on if everything set
     */
	@Override
	public boolean gamesJoin(PassObject p)
	{
		return false;
	}

	//games save and load are not necessary on the proxy?

	//Game: operations for the game you're in, requires cookies

	/*@return  the Game model, true if version provided,
     */
	@Override
	public ClientModel gameModel()
	{
		return new ClientModel();
	}

	/*conditions - verify that user is logged in and joined a game
     *   there needs to be space for another player
     *@param PassObject object - "LARGEST ARMY" is only string accepted
     *return true/false if added
     */
	@Override
	public boolean gameAddAI(PassObject p)
	{
		return false;
	}

	/*no preconditions
     *@return string array of AI types
     */
	@Override
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
	@Override
	public void movesSendChat(MessageList msg)
	{

	}

	/*@param playerid = id of player
     *@return roll = 2-12
     *conditions - must be players turn in rolling status
     *
     *change players status, and return rolled number
     */
	@Override
	public int movesRollNumber(PassObject p)
	{
		return 2;
	}

	/*@PassObject p - object with player id and victim id
     *@param HexLocation new location of robber
     *Robber stuff.  Set the robber, give around robbed cards
     */
	@Override
	public void movesRobPlayer(PassObject p, HexLocation hl)
	{

	}

	/*@param PassObject object with player id
     *Finish the turn and PassObject the torch
     */
	@Override
	public void movesFinishTurn(PassObject p)
	{

	}

	/*@param PassObject object with player id of player that it is happening to
     */
	@Override
	public void movesBuyDevCard(PassObject p)
	{

	}

	/*@param PassObject object with player that is making the move
     *@param ResourceList - items to be taken?
     */
	@Override
	public void movesYearOfPlenty(PassObject p, ResourceList rl)
	{

	}

	/*@param PassObject player that is making the move
     *@param EdgeLocation1
     *@param EdgeLocation2
     */
	@Override
	public void movesRoad_Building(PassObject p, EdgeLocation el, EdgeLocation e2)
	{

	}

	/*@param PassObject - Player id, victim ID
     *@param Hexlocation - where robber will be
     */
	@Override
	public void movesSoldier(PassObject p, HexLocation hl)
	{

	}

	/*@param PassObject - player id, resource
     */
	@Override
	public void movesMonopoly(PassObject p)
	{

	}

	/*@param PassObject - player id
     */
	@Override
	public void movesMonument(PassObject p)
	{
	}

	/*@param PassObject - player id, availability
     *@param EdgeLocation - place in question
     */
	@Override
	public void movesBuildRoad(PassObject p, EdgeLocation el)
	{
	}

	/*@param PassObject - player id, bool availability
     *@VertexLocation - place in question
     */
	@Override
	public void movesBuildSettlement(PassObject p, VertexObject vl)
	{
	}

	/*@param PassObject - player id, bool availability
     *@VertexLocation - place in question
     */
	@Override
	public void movesBuildCity(PassObject p, VertexObject vl)
	{
	}

	/*@param PassObject - player id reciever id
     *@param ResourceList - items being offered
     */
	@Override
	public void movesOfferTrade(PassObject p, ResourceList rl)
	{
	}

	/*@param PassObject - player id reciever id
     *@return PassObject - player, acceptability
     */
	@Override
	public PassObject movesAcceptTrade(PassObject p)
	{
		return p;
	}

	/*@param PassObject - player id
     *@param ResourceList - items to trade
     *@return - player id, acceptability
     */
	@Override
	public PassObject movesMaritimeTrade(PassObject p, ResourceList rl)
	{
		return p;
	}

	/*@param PassObject - player id
     *@param ResourceList - cards to discard
     */
	@Override
	public void movesdiscardCards(PassObject p, ResourceList rl)
	{
	}
}
