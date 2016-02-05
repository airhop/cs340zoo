/**
 *
 */

package client.facade;

import shared.definitions.*;
import shared.exceptions.*;
import client.model.*;
import client.model.map.*;
import client.model.bank.ResourceList;
import client.model.misc.*;
import client.proxy.*;
import shared.jsonobject.*;
import shared.locations.*;

public class Facade {
    GameModel game;
    IProxy proxy;

    public Facade(Proxy p) {
        game = null;
        proxy = p;
    }

    public Facade(MockProxy p) {
        game = null;
        proxy = p;
    }

    public Facade() {

    }

    public GameModel getGM() {return game;}
    public void Reinitialize(GameModel g) {
        game = g;
    }

    //login and register may need to print
    //register - user already exists, GUI in charge of checking lengths and that passwords match
    public boolean Login(String username, String password) {
        User u = new User(username, password);
        try {
            proxy.userLogin(u);
        } catch (InvalidUserException e) {
            return false;
        }
        return true;
    }

    public boolean register(String username, String password) {
        User u = new User(username, password);
        try {
            proxy.userRegister(u);
        } catch (InvalidUserException e) {
            return false;
        }
        return true;
    }

    public String[] gamesList() {

        return proxy.gamesList();
    }

    public void gamesCreate(String s) {
        try {
            proxy.gamesCreate(s);
        } catch (FailedCreateGameException e) {
            //exceptionair!!
        }
    }

    public void gamesJoin(String s, int playerId) {
        try {
            proxy.gamesJoin(s, playerId);
        } catch (InvalidUserException e) {
            //exceptionair!!
        }
    }

    /**
     * Checks to see if building a road is a legal move for the player
     *
     * @return boolean whether or not the player can build a road
     */
    public boolean canBuildRoad(int playerId) {
        if (game == null)
            return false;
        return game.canBuildRoad(playerId);
    }

    /**
     * Checks to see if placing a road is a legal move for the player
     *
     * @return boolean whether or not the player can place a road
     */
    public boolean canPlaceRoad(EdgeLocation el) {
        if (game == null)
            return false;
        return game.canPlaceRoad(el);
    }

    /**
     * Places a Road at a given location on the map
     *
     * @return boolean whether or not the player built the road (perhaps placeholder return values for all of the do methods)
     */
    public void placeRoad(int pid, EdgeLocation el) {
        if (game != null) {
            if (game.canBuildRoad(pid) && game.canPlaceRoad(el))
                proxy.buildRoad(pid, el);
        }
    }

    /**
     * Checks to see if building a settlement is a legal move for the player
     *
     * @return boolean whether or not the player can build a settlement
     */
    public boolean canBuildSettlement(int pid) {
        if (game == null)
            return false;
        return game.canBuildSettlement(pid);
    }

    /**
     * Checks to see if placing a settlement is a legal move for the player
     *
     * @return boolean whether or not the player can place a settlement
     */
    public boolean canPlaceSettlement(VertexLocation vl) {
        if (game == null)
            return false;
        return game.canPlaceSettlement(vl);
    }

    /**
     * Places a Settlement at a given location on the map
     *
     * @return boolean whether or not the player placed a settlement
     */
    public void placeSettlement(int pid, VertexLocation vl) {
        if (game != null) {
            if (canPlaceSettlement(vl) && canBuildSettlement(pid))
                try {
                    proxy.buildSettlement(pid, vl);
                } catch (IllegalBuildException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Checks to see if building a city is a legal move for the player
     *
     * @return boolean whether or not the player can build a city
     */
    public boolean canBuildCity(int pid) {
        if (game == null)
            return false;
        return game.canBuildCity(pid);
    }

    /**
     * Checks to see if placing a city is a legal move for the player
     *
     * @return boolean whether or not the player can place a city
     */
    public boolean canPlaceCity(VertexLocation vl) {
        if (game == null)
            return false;
        return game.canPlaceCity(vl);
    }

    /**
     * Places a City at a given location on the map
     *
     * @return boolean whether or not the player placed the city
     */
    public void placeCity(int pid, VertexLocation vl) {
        if (game != null) {
            if (game.canBuildCity(pid) && game.canPlaceCity(vl))
                try {
                    proxy.buildCity(pid, vl);
                } catch (IllegalBuildException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Checks to see if building a road in a specific place is a legal move for the player
     *
     * @return boolean whether or not the player can road building
     */
    public boolean canRoadBuilding(int pid) {
        if (game == null)
            return false;
        return game.canRoadBuilding(pid);
    }

    /**
     * plays the road build card
     *
     * @return boolean
     */
    public void playRoadBuilding(int pid, EdgeLocation el1, EdgeLocation el2) {
        if (game != null) {
            if (game.canRoadBuilding(pid) && game.canPlaceRoad(el1) && game.canPlaceRoad(el2))
                proxy.playRoadBuilding(pid, el1, el2);
        }
    }

    /**
     * Checks to see if placing a Monument Card(?) is a legal move for the player
     *
     * @return boolean whether or not the player can place a monument
     */
    public boolean canUseMonument(int pid) {
        if (game == null)
            return false;
        return game.canUseMonument(pid);
    }

    public void playMonument(int pid) {
        if (game != null)
            proxy.placeMonument(pid);
    }

    /**
     * Checks to see if placing a Year Of Plenty card is a legal move for the player
     *
     * @return boolean whether or not the player can play the Year of Plenty card
     */
    public boolean canYearOfPlenty(int pid) {
        if (game == null)
            return false;
        return game.canYearOfPlenty(pid);
    }

    /**
     * plays the year of plenty card for a given player
     *
     * @return boolean whether or not the player played the year of plenty card
     */
    public void playYearOfPlenty(int pid, ResourceType r, ResourceType r1) {
        if (game != null)
            if (game.canYearOfPlenty(pid))
                proxy.playYearOfPlenty(pid, r, r1);
    }

    /**
     * Checks to see if placing a Soldier card is a legal move for the player
     *
     * @return boolean whether or not the player can place the Soldier card
     */
    public boolean canPlaySoldier(int pid) {
        if (game == null)
            return false;
        return game.canPlaySoldier(pid);
    }

    /**
     * Places a Soldier and grants the effects he brings
     *
     * @return boolean whether or not the player played the soldier card
     */
    public void playSoldier(int pid, int vid, HexLocation hl) throws IllegalMoveException {
        if (game != null) {
            if (game.canPlaySoldier(pid) && game.canRob(pid, vid) && game.canMoveRobber(hl))
                proxy.playSoldier(pid, vid, hl);
        }
    }

    /**
     * Checks to see if robbing another player is a legal move for the player
     *
     * @return boolean whether or not the player can rob another player
     */
    public boolean canRob(int pid, int vid) {
        if (game != null)
            return game.canRob(pid, vid);
        return true;
    }

    /**
     * Checks to see if moving the robber is a legal move for the player
     *
     * @return boolean whether or not the player can move the robber
     */
    public boolean canMoveRobber(HexLocation hl) {
        if (game == null)
            return false;
        return game.canMoveRobber(hl);
    }

    /**
     * Robs a player of one resource card
     *
     * @return boolean whether or not the player chose to rob
     */
    public void rob(int pid, int vid, HexLocation hl) {
        if (pid == -1)
            System.out.println("No player to be robbed?");
        if (game != null) {
            if (game.canRob(pid, vid) && game.canMoveRobber(hl))
                proxy.robPlayer(pid, vid, hl);
        }
    }

    /**
     * Checks to see if trading resources with the bank is a legal move for the player
     *
     * @return boolean whether or not the player can trade with the bank
     */
    public boolean canTradeBank(int pid, ResourceList rl) {
        if (game == null)
            return false;
        return game.canTradeBank(pid, rl);
    }

    /**
     * completes a transaction of resources with the bank
     *
     * @return boolean whether or not the player traded with the bank
     */
    public void meritimeTrade(int playerId, int ratio, ResourceType in, ResourceType out) {
        if (proxy != null)
            proxy.meritimeTrade(playerId, ratio, in, out);
    }

    /**
     * Checks to see if the player can roll the dice
     *
     * @return boolean whether or not the player can roll the dice
     */
    public boolean canRoll(int pid) {
        if (game == null)
            return false;
        return game.canRoll(pid);
    }

    /**
     * rolls the dice for a number 1-12
     *
     * @return boolean whether or not the player rolled the dice
     */
    public void roll(int pid) {
        if (game != null) {
            if (canRoll(pid)) {
                int number = game.roll(pid);
                if (number != -1)
                    proxy.rollNumber(number, 7);
                else
                    System.out.println("not a rolling phase");
            }
        }
    }

    public boolean canFinishTurn(int pid) {
        if (game == null)
            return false;
        return game.canFinishTurn(pid);
    }

    public void FinishTurn(int pid) {
        proxy.finishTurn(pid);
    }

    public boolean canDiscardCards(int pid, ResourceList rl) {
        if (game == null)
            return false;
        return game.canDiscardCards(pid, rl);
    }

    public void DiscardCards(int pid, ResourceList rl) {
        if (game != null) {
            if (game.canDiscardCards(pid, rl))
                try {
                    proxy.discardCards(pid, rl);
                } catch (InsufficientResourcesException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Checks to see if buying a Developement Card is a legal move for the player
     *
     * @return boolean whether or not the player can buy a Developement card
     */
    public boolean canBuyDevcard(int pid) {
        if (game == null)
            return false;
        return game.canBuyDevcard(pid);
    }

    /**
     * Buys a developement card and increases the amount for the purchasing player
     *
     * @return boolean whether or not the player bought the dev card
     */
    public void buyDevCard(int pid) {
        if (game != null) {
            if (game.canBuyDevcard(pid)) {
                try {
                    proxy.buyDevCard(pid);
                } catch (InsufficientResourcesException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Checks to see if Montoply is a legal move for the player
     *
     * @return boolean whether or not the player can monopoly
     */
    public boolean canMonopoly(int pid) {
        if (game == null)
            return false;
        return game.canMonopoly(pid);
    }

    /**
     * uses Monopoly
     *
     * @return boolean whether or not the player played a monopoly
     */
    public void playMonopoly(int pid, ResourceType r) {
        if (game != null) {
            if (game.canMonopoly(pid))
                proxy.playMonopoly(pid, r);
        }
    }


    /**
     * Set up the TradeOffer
     */
    public boolean canTradePlayer(int pid, int rid, ResourceList rl) {
        try {
            if (game != null)
                return game.canTradePlayer(pid, rid, rl);
        } catch (InsufficientResourcesException e) {
            System.out.println("Not enough resources " + e.getMessage());
        } catch (IllegalMoveException e) {
            System.out.println("An illegal move" + e.getMessage());
        }
        return true;
    }

    /**
     * enacts the trade offer of the specified player
     *
     * @return boolean whether or not the player traded with another player
     */
    public void tradePlayer(int pid, int rid, ResourceList rl) {
        try {
            if (game != null) {
                if (game.canTradePlayer(pid, rid, rl))
                    proxy.offerTrade(pid, rid, rl);
            }
        } catch (InsufficientResourcesException e) {
            System.out.println("Not enough resources " + e.getMessage());
        } catch (IllegalMoveException e) {
            System.out.println("An illegal move" + e.getMessage());
        }
    }

    /**
     * Checks to see accepting a trade request is a legal move for the player
     *
     * @return boolean whether or not the player can accept a trade offer from another player
     */
    public boolean canAcceptTrade(int pid) {
        if (game == null)
            return false;
        return game.canAcceptTrade(pid);
    }

    /**
     * accepts the trade offer of another player
     *
     * @return boolean whether or not the player accepted a trade offer
     */
    public void acceptTrade(int pid, boolean acceptance) {
        if (game != null) {
            if (game.canAcceptTrade(pid))
                proxy.acceptTrade(pid, acceptance);
        }
    }

    /**
     * Checks to see if the player can win the game
     *
     * @return boolean whether or not the player can win (have 10 victory points)
     */
    public boolean canWin() {
        if (game == null)
            return false;
        return game.canWin();
    }

    ///might need to be changed
    public boolean canSendChat(String msg, int pid) {
        if (game == null)
            return false;
        boolean sc = game.canSendChat(msg, pid);
        if (sc) {
            proxy.sendChat(msg, pid);
            return true;
        }
        return false;
    }
}
