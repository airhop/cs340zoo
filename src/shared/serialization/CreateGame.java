package shared.serialization;

/**
 * Created by Joshua on 2/22/2016.
 */
public class CreateGame {
    private boolean randomTiles;
    private boolean randomNumbers;
    private boolean randomPorts;
    private String gameName;

    public CreateGame(){

    }

    public boolean isRandomTiles() {
        return randomTiles;
    }

    public void setRandomTiles(boolean randomTiles) {
        this.randomTiles = randomTiles;
    }

    public boolean isRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(boolean randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public boolean isRandomPorts() {
        return randomPorts;
    }

    public void setRandomPorts(boolean randomPorts) {
        this.randomPorts = randomPorts;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }



}
