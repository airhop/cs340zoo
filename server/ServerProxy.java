import shared.jsonobject.*;
import client.model.map.*;

public class ServerProxy 
{
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
	/* @param Login object with username and password
	 * @return true/false whether a successful login or not.
	 */
	public boolean userLogin(Login l)
	{
//		if(username == null || password == null)
//			return false;
//		return Server.login(username, password);
		return false;
	}
	
	/* @param Login object with new username and password
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
	public Game[] gamesList()
	{
		
	}
	
	/*@param pass object - should have new Game name
	 *@return = newly created game
	 */
	public Game gamesCreate(Pass p)
	{
		
	}
	
	/*@param Pass object - hold the pid and string color
	 *@return true/false based on if everything set
	 */
	public boolean gamesJoin(Pass p)
	{
		return false;
	}
	
//games save and load are not necessary on the proxy?
	
//Game: operations for the game you're in, requires cookies
	
	/*@return  the Game model, true if version provided, 
	 */
	public Game gameModel()
	{
		
	}
	
	/*conditions - verify that user is logged in and joined a game
	 *   there needs to be space for another player
	 *@param Pass object - "LARGEST ARMY" is only string accepted
	 *return true/false if added 
	 */
	public boolean gameAddAI(Pass p)
	{
		return false;
	}
	
	/*no preconditions
	 *@return string array of AI types
	 */
	public String[] gameListAI()
	{
		
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
	public int movesRollNumber(Pass p)
	{
		return 2;
	}
	
	/*@Pass p - object with player id and victim id
	 *@param HexLocation new location of robber
	 *Robber stuff.  Set the robber, give around robbed cards
	 */
	public void movesRobPlayer(Pass p, HexLocation hl)
	{
		
	}
	
	/*@param Pass object with player id
	 *Finish the turn and pass the torch
	 */
	public void movesFinishTurn(Pass p)
	{
		
	}
	
	/*@param Pass object with player id of player that it is happening to
	 */
	public void movesBuyDevCard(Pass p)
	{
		
	}
	
	/*@param Pass object with player that is making the move
	 *@param ResourceList - items to be taken? 
	 */
	public void movesYearOfPlenty(Pass p, ResourceList rl)
	{
		
	}
	
	/*@param Pass player that is making the move
	 *@param EdgeLocation1
	 *@param EdgeLocation2
	 */
	public void movesRoad_Building(Pass p, EdgeLocation el, EdgeLocation e2)
	{
		
	}
	
	/*@param Pass - Player id, victim ID
	 *@param Hexlocation - where robber will be
	 */
	public void movesSoldier(Pass p, HexLocation hl)
	{
		
	}
	
	/*@param Pass - player id, resource
	 */
	public void movesMonopoly(Pass p)
	{
		
	}
	
	/*@param Pass - player id
	 */
	public void movesMonument(Pass p)
	{
	}
	
	/*@param Pass - player id, availability
	 *@param EdgeLocation - place in question
	 */
	public void movesBuildRoad(Pass p, EdgeLocation el)
	{
	}
	
	/*@param Pass - player id, bool availability
	 *@VertexLocation - place in question
	 */
	public void movesBuildSettlement(Pass p, VertexLocation vl)
	{
	}
	
	/*@param Pass - player id, bool availability
	 *@VertexLocation - place in question
	 */	
	public void movesBuildCity(Pass p, VertexLocation vl)
	{
	}
	
	/*@param Pass - player id reciever id
	 *@param ResourceList - items being offered
	 */
	public void movesOfferTrade(Pass p, ResourceList rl)
	{
	}

	/*@param Pass - player id reciever id
	 *@return Pass - player, acceptability
	 */
	public Pass movesAcceptTrade(Pass p)
	{
	}
	
	/*@param Pass - player id
	 *@param ResourceList - items to trade
	 *@return - player id, acceptability
	 */
	public Pass movesMaritimeTrade(Pass p, ResourceList rl)
	{
	}
	
	/*@param Pass - player id
	 *@param ResourceList - cards to discard
	 */
	public void movesdiscardCards(Pass p, ResourceList rl)
	{
	}
}
