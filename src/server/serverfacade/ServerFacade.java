package server.serverfacade;

import client.MVC.data.GameInfo;
import client.MVC.data.PlayerInfo;
import client.model.bank.Bank;
import client.model.bank.DevCardList;
import client.model.map.Map;
import client.model.misc.TradeOffer;
import client.model.player.CurrentPlayer;
import client.proxy.Cookie;
import server.factories.MapFactory;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.exceptions.FailureToAddException;
import shared.exceptions.InsufficientResourcesException;
import shared.jsonobject.CreatedGame;
import shared.jsonobject.Login;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Josh on 3/10/2016.
 */
public class ServerFacade implements IServerFacade {

    private List<GameModel> gamesList;
    private List<GameInfo> gameInfoList;
    private TreeMap<String, Login> players; // first is the username, next is the password
    int createPlayerIndex; //the currentID for the player to be added
    int createGameIndex;
    CurrentPlayer currPlayer;
    MapFactory myMapFactory;

    public ServerFacade() {
        gamesList = new ArrayList<>();
        gameInfoList = new ArrayList<>();
        players = new TreeMap<String, Login>();
        players.put("Sam", new Login("Sam", "sam", 0));
        players.put("Brooke", new Login("Brooke", "brooke", 1));
        players.put("Bob", new Login("Bob", "bob", 2));
        players.put("Suzzie", new Login("Suzzie", "suzzie", 3));
        createPlayerIndex = players.size();
        createGameIndex = gamesList.size();
        currPlayer = new CurrentPlayer();
        myMapFactory = new MapFactory();

        GameModel gm = myMapFactory.newModel(true, false, false, "First Game");
        ArrayList<Player> ps = new ArrayList<Player>();
        ps.add(new Player("Sam", 0));
        ps.add(new Player("Brooke", 1));
        ps.add(new Player("Bob", 2));
        ps.add(new Player("Suzzie", 3));
        gm.setPlayers(ps);
        gm.setID(createGameIndex++);
        gamesList.add(gm);

        List<PlayerInfo> info = new ArrayList<>();
        info.add(new PlayerInfo(0, 0, "Sam", CatanColor.BLUE));
        info.add(new PlayerInfo(1, 1, "Brooke", CatanColor.GREEN));
        info.add(new PlayerInfo(2, 2, "Bob", CatanColor.PUCE));
        info.add(new PlayerInfo(3, 3, "Suzzie", CatanColor.ORANGE));
        gameInfoList.add(new GameInfo(0, "First Game", info));

    }

    public CurrentPlayer getCurrPlayer() {
        return currPlayer;
    }

    public void setCurrPlayerCook() {

    }

    public void buildCurrentPlayer(Cookie userCookie, Cookie gameCookie) {
        if (userCookie.isActive()) {
            currPlayer.setUsername(userCookie.getCookieName());
            currPlayer.setPassword(userCookie.getCookieValue());
        } else {
            currPlayer.setUsername("");
            currPlayer.setPassword("");
        }
        if (gameCookie.isActive()) {
            currPlayer.setGameId(gameCookie.retrieveID());
            List<PlayerInfo> playerList = gameInfoList.get(currPlayer.getGameId()).getPlayers();
            int playerIndex = -1;
            for (int i = 0; i < playerList.size(); i++) {
                if (playerList.get(i).getName().equals(currPlayer.getUsername())) {
                    playerIndex = i;
                }
            }
            currPlayer.setPlayerIndex(playerIndex);
        } else {
            currPlayer.setGameId(-1);
            currPlayer.setPlayerIndex(-1);
        }
    }

    public void setCurrPlayer(CurrentPlayer currPlayer) {
        this.currPlayer = currPlayer;
    }

    private static ServerFacade facade = null;

    public static ServerFacade getInstance() {
        if (facade == null) {
            facade = new ServerFacade();
        }
        return facade;
    }

    /**
     * The command objects will call this method to run a server operation
     *
     * @param username - the username the player is attempting
     * @param password - the password the player is attempting
     */
    @Override
    public Login userLogin(String username, String password) {
        System.out.println("User logging in through facade");
        if (players.containsKey(username)) {
            Login log = players.get(username);
            if (password.equals(log.getPassword())) {
                return new Login(username, password, log.getID());
            }
        }

        return new Login("", "", -1);
    }

    /**
     * The command objects will call this method to run a server operation
     *
     * @param username - the player chosen username
     * @param password - the player chosen password
     */
    @Override
    public Login userRegister(String username, String password) {
        if (players.containsKey(username)) {
            //cannot register a player that already exists
            return new Login("", "", -1);
        }
        Login noob = new Login(username, password, createPlayerIndex++);
        players.put(username, noob);
        return noob;
    }

    /**
     * The command objects will call this method to run a server operation
     *
     * @return GameModel -
     */
    @Override
    public List<GameInfo> getGamesList() {
        System.out.println("here! " + gameInfoList.size());
        return gameInfoList;
    }

    /**
     * The command objects will call this method to run a server operation of creating a game
     */
    @Override
    public CreatedGame createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name) {
        CreatedGame theGame;
        System.out.println("Creating a brand new game of " + name + " " + gameInfoList.size());
        theGame = new CreatedGame(name, gameInfoList.size());
        GameModel myModel = myMapFactory.newModel(randomTiles, randomNumbers, randomPorts, name);
        gamesList.add(myModel);
        myModel.setID(gamesList.size() - 1);
        GameInfo gameObject = new GameInfo();
        gameObject.setId(myModel.getID());
        gameObject.setTitle(name);
        gameInfoList.add(gameObject);
        return theGame;
    }

    /**
     * The command objects will call this method to run the server operation to join a game.
     *
     * @param id    - the id of the game
     * @param color - the color chosen by the player for the game.
     */
    @Override
    public CreatedGame joinGame(int id, String color) {
        GameModel myModel = gamesList.get(id);
        List<Player> gamePlayers = myModel.getPlayers();
        int playerIndex = -1;
        boolean firstJoin = false;
        for(int i = 0; i < 4; i++){
            if(gamePlayers.get(i).getUsername().equals(currPlayer.getUsername()) && playerIndex == -1){
                playerIndex = i;
            }
        }
        for(int i = 0; i < 4; i++){
            if(gamePlayers.get(i).getUsername().equals("") && playerIndex == -1){
                playerIndex = i;
                firstJoin = true;
            }
        }


        if(firstJoin){
            PlayerInfo myPlayer = new PlayerInfo();
            myPlayer.setColor(CatanColor.valueOf(color.toUpperCase()));
            myPlayer.setId(currPlayer.getPlayerId());
            myPlayer.setName(currPlayer.getUsername());
            myPlayer.setPlayerIndex(playerIndex);
            gameInfoList.get(id).addPlayer(myPlayer);
        }
        Player changePlayer = gamePlayers.get(playerIndex);

        changePlayer.setColor(color);
        changePlayer.setPlayerIndex(playerIndex);

        return new CreatedGame(gameInfoList.get(id).getTitle(), id);
    }

    /**
     * The command objects will call this method to run the server operation to save a game
     *
     * @param id   the id of the game being saved
     * @param name the name of the game being saved.
     */
    @Override
    public void save(int id, String name) {

    }

    /**
     * The command objects will call this method to load an existing game that has been saved
     *
     * @param name the name of the game being loaded
     */
    @Override
    public void load(String name) {

    }

    /**
     * The command objects will call this method to run the server operation of getting the game model
     *
     * @return the game model
     */
    @Override
    public GameModel getModel() {
        if (currPlayer.getGameId() != -1) {
            return gamesList.get(currPlayer.getGameId());
        } else {
            return null;
        }
    }

    /**
     * The comand objects will call this method to run the server operation of adding an AI player to a game
     *
     * @param AIType the type of AI that the player wants to play against
     */
    @Override
    public void addAI(String AIType) {

    }

    /**
     * The command objects will call this method to run the server operation listing the AI in a game
     */
    @Override
    public void listAI() {

    }

    /**
     * The command objects will call this method to run the server operation of sending a chat message
     *
     * @param playerIndex the id of the player sending the message
     * @param content     the content of the message being sent
     */
    @Override
    public void sendChat(int playerIndex, String content) {
        if (!currPlayer.getUsername().equals("")) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            game.getChat().addMessage(currPlayer.getUsername(), content);
        }
    }

    /**
     * The command objects will call this method to run the server operation of rolling a number
     *
     * @param playerIndex the id of the player rolling
     * @param number      the number that was rolled which takes place in the client
     */
    @Override
    public void rollNumber(int playerIndex, int number) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            List<ResourceList> resources = game.getMap().giveResources(number);
            for (int i = 0; i < 4; i++) {
                game.getPlayers().get(i).addResource(ResourceType.BRICK, resources.get(i).getBrick());
                game.getPlayers().get(i).addResource(ResourceType.ORE, resources.get(i).getOre());
                game.getPlayers().get(i).addResource(ResourceType.SHEEP, resources.get(i).getSheep());
                game.getPlayers().get(i).addResource(ResourceType.WHEAT, resources.get(i).getWheat());
                game.getPlayers().get(i).addResource(ResourceType.WOOD, resources.get(i).getWood());
            }
        }
    }

    /**
     * The command objects will call this method to run the server operation of robbing a player
     *
     * @param plyerIndex  the id of the player doing the robbing
     * @param victimIndex the id of the player being robbed
     * @param location    the location of the robber
     */
    @Override
    public void robPlayer(int plyerIndex, int victimIndex, HexLocation location) {
        GameModel game = gamesList.get(currPlayer.getGameId());
        //Ask aaron about pull random card
    }

    /**
     * The command ojects will call this method to run the server operation of finishing a turn
     *
     * @param playerIndex the id of the player ending their turn
     */
    @Override
    public void finishTurn(int playerIndex) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            if (playerIndex == game.getTurnTracker().getCurrentPlayer()) {
                if (playerIndex == 3) {
                    playerIndex = -1;
                    if (game.getTurnTracker().getStatus().equals("FirstRound")) {
                        game.getTurnTracker().updateStatus("SecondRound");
                    } else if (game.getTurnTracker().getStatus().equals("SecondRound")) {
                        game.getTurnTracker().updateStatus("Rolling");
                    }
                }
                if (!game.getTurnTracker().getStatus().equals("FirstRound") && !game.getTurnTracker().getStatus().equals("SecondRound")) {
                    game.getTurnTracker().updateStatus("Rolling");
                }
            }
            for (int i = 0; i < 3; i++) {

            }
            playerIndex++;
            game.getTurnTracker().setCurrentPlayer(playerIndex);
            game.getTurnTracker().updateStatus("");
        }
    }

    /**
     * Thee command objects will call this method to run the server operation of buying a developemnt card
     *
     * @param playerIndex the id of the player buying the developement card
     */
    @Override
    public void buyDevCard(int playerIndex) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Player buyPlayer = game.getPlayers().get(currPlayer.getPlayerIndex());
            DevCardList playerDevCards = buyPlayer.getNewDevCards();
            DevCardList bankCards = game.getBank().getDevCards();
            if (bankCards.getSize() > 0 && buyPlayer.canBuyDevcard()) {//get a random if it is not there than fall down through the switch
                try {
                    String buyCard = bankCards.buyDevCard();

                    switch (buyCard) {
                        case "monopoly":
                            bankCards.setMonopoly(bankCards.getMonopoly() - 1);
                            playerDevCards.setMonopoly(1);
                            break;
                        case "monument":
                            bankCards.setMonument(bankCards.getMonument() - 1);
                            playerDevCards.setMonument(1);
                            break;
                        case "roadbuilding":
                            bankCards.setRoadBuilding(bankCards.getRoadBuilding() - 1);
                            playerDevCards.setRoadBuilding(1);
                            break;
                        case "soldier":
                            bankCards.setSoldier(bankCards.getSoldier() - 1);
                            playerDevCards.setSoldier(1);
                            break;
                        case "yearofplenty":
                            bankCards.setYearOfPlenty(bankCards.getYearOfPlenty() - 1);
                            playerDevCards.setYearOfPlenty(1);
                            break;
                    }
                } catch (InsufficientResourcesException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * the command objects will call this method to tun the server operation of playing a Year of Plenty card
     *
     * @param playerIndex the id of the player using the Year of Plenty card
     * @param res1        -
     * @param res2        -
     */
    @Override
    public void yearOfPlenty(int playerIndex, ResourceType res1, ResourceType res2) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Player addPlayer = game.getPlayers().get(playerIndex);
            Bank myBank = game.getBank();
            ResourceList bankResources = myBank.getResources();
            boolean resourceOne = false;
            boolean resourceTwo = false;
            resourceOne = bankResources.canTakeResource(res1);
            resourceTwo = bankResources.canTakeResource(res2);
            if (resourceOne && resourceTwo) {
                bankResources.addResourceType(res1.toString(), -1);
                bankResources.addResourceType(res2.toString(), -1);
                addPlayer.addResource(res1, 1);
                addPlayer.addResource(res2, 1);
            }
        }
    }

    /**
     * The command objects will call this method to run a server operation
     *
     * @param playerIndex the index of the current player
     * @param spot1       -
     * @param spot2       -
     */
    @Override
    public void roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Map ourMap = game.getMap();
            if (ourMap.canPlaceRoad(spot1, false) && ourMap.canPlaceRoad(spot1, false)) {
                try {
                    ourMap.addRoad(spot1.getHexLoc().getX(), spot1.getHexLoc().getY(), spot1.getDir(), playerIndex);
                    ourMap.addRoad(spot2.getHexLoc().getX(), spot2.getHexLoc().getY(), spot2.getDir(), playerIndex);
                } catch (FailureToAddException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * The command objects will call this method to run the server operation of playing a soldier card
     *
     * @param playerIndex the index of the player using the soldier card
     * @param victimIndex the index of the player being robbed
     * @param location    the new location of the robber
     */
    @Override
    public void soldier(int playerIndex, int victimIndex, HexLocation location) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            List<Player> players = game.getPlayers();
            Player robber = players.get(playerIndex);
            Player victim = players.get(victimIndex);
            robber.getResources().addResourceType(victim.getResources().stealCard().toString(), 1);
            game.relocateRobber(location);
        }

    }

    /**
     * The command objects will call this method to run the server operation of playing a monopoly card
     *
     * @param playerIndex the id of the player using the monopoly card
     * @param resource    -
     */
    @Override
    public void monopoly(int playerIndex, String resource) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            List<Player> players = game.getPlayers();
            int addAmount = 0;
            for (int i = 0; i < players.size(); i++) {
                if (i != playerIndex) {
                    addAmount += players.get(i).getResources().getNumOfResource(resource);
                    players.get(i).depleteResource(ResourceType.valueOf(resource));
                }
            }
            players.get(playerIndex).addResource(ResourceType.valueOf(resource), addAmount);
        }
    }

    /**
     * The command objects will call this method to run the server operation of using a monument card
     *
     * @param playerIndex the id of the player using the monument card
     */
    @Override
    public void monument(int playerIndex) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Player addPointPlayer = game.getPlayers().get(playerIndex);
            addPointPlayer.setVictoryPoints(addPointPlayer.getVictoryPoints() + 1);
        }
    }

    /**
     * The command objects will call this method to run the server operation of building a road
     *
     * @param playerIndex  the id of the player building the road
     * @param roadLocation the location the player wishes to place the road
     * @param free         whether or not the spot they are trying to build on is available
     */
    @Override
    public void buildRoad(int playerIndex, EdgeLocation roadLocation, Boolean free) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Map ourMap = game.getMap();
            Player roadPlayer = game.getPlayers().get(playerIndex);
            if (!ourMap.canPlaceRoad(roadLocation, false)) {
                return;
            }
            if (free) {
                try {
                    ourMap.addRoad(roadLocation.getHexLoc().getX(), roadLocation.getHexLoc().getY(), roadLocation.getDir(), playerIndex);
                } catch (FailureToAddException e) {
                    e.printStackTrace();
                }
            } else {
                if (roadPlayer.getResources().getBrick() > 0 && roadPlayer.getResources().getWood() > 0) {
                    try {
                        roadPlayer.addResource(ResourceType.WOOD, -1);
                        roadPlayer.addResource(ResourceType.BRICK, -1);
                        ourMap.addRoad(roadLocation.getHexLoc().getX(), roadLocation.getHexLoc().getY(), roadLocation.getDir(), playerIndex);
                    } catch (FailureToAddException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * The command objects will call this method to run the server operation of building a settlement
     *
     * @param playerIndex    the id of the player building the settlement
     * @param vertexLocation the location the player wishes to place the settlement
     * @param free           whether or not the spot the player is attempting to build on is available
     */
    @Override
    public void buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Map ourMap = game.getMap();
            Player setPlayer = game.getPlayers().get(playerIndex);
            if (!ourMap.canPlaceSettlement(vertexLocation)) {
                return;
            }
            if (free) {
                try {
                    ourMap.addSettlement(vertexLocation.getHexLoc().getX(), vertexLocation.getHexLoc().getY(), vertexLocation.getDir(), playerIndex);
                } catch (FailureToAddException e) {
                    e.printStackTrace();
                }
            } else {
                if (setPlayer.enoughResourcesForSett()) {
                    try {
                        setPlayer.addResource(ResourceType.WOOD, -1);
                        setPlayer.addResource(ResourceType.BRICK, -1);
                        setPlayer.addResource(ResourceType.WHEAT, -1);
                        setPlayer.addResource(ResourceType.SHEEP, -1);
                        ourMap.addSettlement(vertexLocation.getHexLoc().getX(), vertexLocation.getHexLoc().getY(), vertexLocation.getDir(), playerIndex);
                    } catch (FailureToAddException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * The command objects will call this method to run the server operation of building a city
     *
     * @param playerIndex    the id of the player wishing to build a city
     * @param vertexLocation the location the player wants to build the city at (on an existing settlement)
     */
    @Override
    public void buildCity(int playerIndex, VertexLocation vertexLocation) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Map ourMap = game.getMap();
            Player cityPlayer = game.getPlayers().get(playerIndex);
            if (cityPlayer.enoughResourcesForCity() && ourMap.canPlaceCity(vertexLocation)) {
                try {
                    ourMap.addCity(vertexLocation.getHexLoc().getX(), vertexLocation.getHexLoc().getY(), vertexLocation.getDir(), playerIndex);
                } catch (FailureToAddException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * The command objects will call this method to run the server operation of making a trade offer
     *
     * @param playerIndex the id of the player making an offer
     * @param offer       the offered resources by the offeree
     * @param receiver    the id of the player receiving the trade offer
     */
    @Override
    public void offerTrade(int playerIndex, ResourceList offer, int receiver) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            game.setTradeOffer(new TradeOffer(playerIndex, receiver, offer));
        }
    }

    /**
     * The command objects will call this method to run the server operation of accepting a trade
     *
     * @param playerIndex the id of the player who will accept a trade
     * @param willAccept  whether or not the player did accept the trade (true = yes)
     */
    @Override
    public void acceptTrade(int playerIndex, boolean willAccept) {
        //TODO check the null value on the trade offer :/
        //TODO check the values on the send and receive
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            TradeOffer trade;
            Player sender;
            Player receiver;
            if (willAccept) {
                trade = game.getTradeOffer();
                sender = game.getPlayers().get(trade.getSender());
                receiver = game.getPlayers().get(trade.getReciever());
                sender.getResources().alterAllResources(trade.getSentList());
                receiver.getResources().alterAllResources(trade.getRecievedList());
            }
            game.setTradeOffer(null);
        }
    }

    /**
     * The command objects will call this method to run the server operation of conducting a maritime trade
     *
     * @param playerIndex    the id of the player wanting to maritime trade
     * @param ratio          the ratio the player is able to trade at
     * @param inputResource  the resource that the player is offering
     * @param outputResource the resource the player wants to receive
     */
    @Override
    public void maritimeTrade(int playerIndex, int ratio, String inputResource, String outputResource) {
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Player tradePlayer = game.getPlayers().get(playerIndex);
            if (ratio < 4 && ratio > 1) {
                if (tradePlayer.getResources().getNumOfResource(inputResource) >= ratio) {
                    if (game.getBank().getResources().getNumOfResource(outputResource) > 0) {
                        tradePlayer.addResource(ResourceType.valueOf(inputResource), -ratio);
                        tradePlayer.addResource(ResourceType.valueOf(outputResource), 1);
                        game.getBank().getResources().addResourceType(outputResource, -1);
                    }
                }
            }
        }
    }

    /**
     * The command objects will call this method to run the server operation of discarding cards
     *
     * @param playerIndex    the id of the player being forced to discard cards
     * @param discardedCards the resources the player is discarding
     */
    @Override
    public void discardCards(int playerIndex, ResourceList discardedCards) {
        //TODO check if these values need to be flipped
        if (currPlayer.getGameId() != -1) {
            GameModel game = gamesList.get(currPlayer.getGameId());
            Player disPlayer = game.getPlayers().get(playerIndex);
            disPlayer.addResource(ResourceType.BRICK, discardedCards.getBrick());
            disPlayer.addResource(ResourceType.ORE, discardedCards.getOre());
            disPlayer.addResource(ResourceType.SHEEP, discardedCards.getSheep());
            disPlayer.addResource(ResourceType.WHEAT, discardedCards.getWheat());
            disPlayer.addResource(ResourceType.WOOD, discardedCards.getWood());
        }
    }
}
