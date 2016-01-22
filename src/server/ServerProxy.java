package server;

import client.model.ClientModel;
import client.model.bank.ResourceList;
import client.model.history.MessageList;
import client.model.map.EdgeLocation;
import client.model.map.HexLocation;
import client.model.map.VertexObject;
import shared.jsonobject.Login;
import shared.jsonobject.PassObject;
Import Java.net.HttpURLConnection;

public class ServerProxy implements IServerProxy{

	
	public ServerProxy()
	{
	
	}

	//User operations
	/** @param l - Login object with username and PassObjectword
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

	/** @param l - Login object with new username and PassObjectword
     * @return true/false whether a successful registry or not
     */
	@Override
	public boolean userRegister(Login l)
	{
		//check that user doesn't already exist
		return false;
	}

	//Games: Game queries/actions (pre-joining)

	/**no prereqs
     * @return array of Game objects
     */
	@Override
	public ClientModel[] gamesList()
	{
		ClientModel[] cm = new ClientModel[2];
		return cm;
	}

	/**@param p object - should have new Game name
     *@return = newly created game
     */
	@Override
	public ClientModel gamesCreate(PassObject p)
	{
		return new ClientModel();
	}

	/**@param p - object - hold the pid and string color
     *@return true/false based on if everything set
     */
	@Override
	public boolean gamesJoin(PassObject p)
	{
		return false;
	}

	//games save and load are not necessary on the proxy?

	//Game: operations for the game you're in, requires cookies

	/**@return  the Game model, true if version provided,
     */
	@Override
	public ClientModel gameModel()
	{
		return new ClientModel();
	}

	/**conditions - verify that user is logged in and joined a game
     *   there needs to be space for another player
     *@param p - PassObject object - "LARGEST ARMY" is only string accepted
     *return true/false if added
     */
	@Override
	public boolean gameAddAI(PassObject p)
	{
		return false;
	}

	/**no preconditions
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

	/**@param msg to be sent
     *no preconditions for sending a message
     */
	@Override
	public void movesSendChat(MessageList msg)
	{

	}

	/**@param p - PassObject = id of player
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

	/**@param p - object with player id and victim id
     *@param hl - HexLocation new location of robber
     *Robber stuff.  Set the robber, give around robbed cards
     */
	@Override
	public void movesRobPlayer(PassObject p, HexLocation hl)
	{

	}

	/**@param p - PassObject object with player id
     *Finish the turn and PassObject the torch
     */
	@Override
	public void movesFinishTurn(PassObject p)
	{

	}

	/**
	 * @param p - PassObject object with player id of player that it is happening to
     */
	@Override
	public void movesBuyDevCard(PassObject p)
	{

	}

	/**
	 * @param p - PassObject object with player that is making the move
     *@param rl - ResourceList - items to be taken?
     */
	@Override
	public void movesYearOfPlenty(PassObject p, ResourceList rl)
	{

	}

	/**
	 * @param p - PassObject player that is making the move
     *@param el - EdgeLocation1
     *@param e2 - EdgeLocation2
     */
	@Override
	public void movesRoad_Building(PassObject p, EdgeLocation el, EdgeLocation e2)
	{

	}

	/**
	 * @param  p - PassObject - Player id, victim ID
     *@param hl - Hexlocation - where robber will be
     */
	@Override
	public void movesSoldier(PassObject p, HexLocation hl)
	{

	}

	/**
	 * @param p - PassObject - player id, resource
     */
	@Override
	public void movesMonopoly(PassObject p)
	{

	}

	/**
	 * @param p - PassObject - player id
     */
	@Override
	public void movesMonument(PassObject p)
	{
	}

	/**
	 * @param p - PassObject - player id, availability
     *@param el - EdgeLocation - place in question
     */
	@Override
	public void movesBuildRoad(PassObject p, EdgeLocation el)
	{
	}

	/**
	 * @param p -PassObject - player id, bool availability
     *@param  vl -VertexObject - place in question
     */
	@Override
	public void movesBuildSettlement(PassObject p, VertexObject vl)
	{
	}

	/**
	 * @param p -PassObject - player id, bool availability
     *@param vl - VertexObject - place in question
     */
	@Override
	public void movesBuildCity(PassObject p, VertexObject vl)
	{
	}

	/**
	 * @param p - PassObject - player id reciever id
     *@param rl - ResourceList - items being offered
     */
	@Override
	public void movesOfferTrade(PassObject p, ResourceList rl)
	{
	}

	/**
	 * @param p - PassObject - player id reciever id
     *@return PassObject - player, acceptability
     */
	@Override
	public PassObject movesAcceptTrade(PassObject p)
	{
		return p;
	}

	/**
	 * @param p - PassObject - player id
     *@param rl - ResourceList - items to trade
     *@return - player id, acceptability
     */
	@Override
	public PassObject movesMaritimeTrade(PassObject p, ResourceList rl)
	{
		return p;
	}

	/**
	 * @param p - PassObject - player id
     *@param rl - ResourceList - cards to discard
     */
	@Override
	public void movesdiscardCards(PassObject p, ResourceList rl)
	{
	}
}
