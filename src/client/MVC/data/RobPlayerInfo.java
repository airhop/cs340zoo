package client.MVC.data;

import shared.definitions.CatanColor;

/**
 * Used to pass player information into the rob view<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * <li>NumCards: Number of development cards the player has (>= 0)</li>
 * </ul>
 */
public class RobPlayerInfo {

    private int numCards;
    private int id;
    private int playerIndex;
    private String name;
    private CatanColor color;

    public RobPlayerInfo(int ID, int pid, String n, CatanColor c, int noc)
    {
        id = ID;
        playerIndex = pid;
        name = n;
        color = c;
        numCards = noc;
    }
    public int getNumCards() {
        return numCards;
    }

    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatanColor getColor() {
        return color;
    }

    public void setColor(CatanColor color) {
        this.color = color;
    }

}

