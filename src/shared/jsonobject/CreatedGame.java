package shared.jsonobject;


//Used to pass back info about a created game.
public class CreatedGame
{
    private String title;
    private int id;
 //   private Player[] players;
    //just a reminder that 4 empty players need to be returned.

    public CreatedGame(String t, int ID)
    {
        title = t;
        id = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
