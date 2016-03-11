package server.serverfacade;

/**
 * Created by Joshua on 3/9/2016.
 */
public interface IServerFacade 
{
	// user: total 2 methods
	void postLogin();
	void postRegister();
	//games: total 5 methods
	void getList();
	void postCreate();
	void postJoin();
	void postSave();
	void postLoad();
	//game: total 6 methods
	void getModel(); //FYI getModel is the only required method from this section.
					//the rest are crossed out on the Phase specs

	void postRest(); //I think you meant reset not rest
	void postCommands();
	void getCommands();
	void postAddAI();
	void getListAI();
	//moves: total 17 methods
	void postSendChat();
	void postRollNumber();
	void postRobPlayer();
	void postFinishTurn();
	void postBuyDevCard();
	void postYearOfPlenty();
	void postRoadBuilding();
	void postSoldier();
	void postMonopoly();
	void postMonument();
	void postBuildRoad();
	void postBuildSettlement();
	void postBuildCity();
	void postOfferTrade();
	void postAcceptTrade();
	void postMaritimeTrade();
	void postDiscardCards();
	//util: total 1 mehtod
	void postChangeLogLevel();
}
