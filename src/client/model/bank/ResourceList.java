package client.model.bank;

import shared.definitions.ResourceType;

import javax.annotation.Resource;
import java.util.ArrayList;

public class ResourceList {

    int numOfBrick;
    int numOfOre;
    int numOfSheep;
    int numOfWheat;
    int numOfWood;

    public ResourceList() {
        numOfBrick = 0;
        numOfOre = 0;
        numOfSheep = 0;
        numOfWheat = 0;
        numOfWood = 0;
    }

    public ResourceList(int given) {
        if (given > 20 || given < 0) {
            given = 19;
        }
        numOfBrick = given;
        numOfOre = given;
        numOfSheep = given;
        numOfWheat = given;
        numOfWood = given;
    }

    public ResourceList(int br, int or, int sh, int wh, int wo) {
        numOfBrick = br;
        numOfOre = or;
        numOfSheep = sh;
        numOfWheat = wh;
        numOfWood = wo;
    }

    //copy constructor
    public ResourceList(ResourceList resourcesToCopy) {
        numOfBrick = resourcesToCopy.getBrick();
        numOfOre = resourcesToCopy.getOre();
        numOfSheep = resourcesToCopy.getSheep();
        numOfWheat = resourcesToCopy.getWheat();
        numOfWood = resourcesToCopy.getWood();
    }
    //overload the constructor so that RL can be used for trades, bank, map, players etc.

    public void setAllResources(ResourceList first){
        numOfBrick = first.numOfBrick;
        numOfOre = first.numOfOre;
        numOfSheep = first.numOfSheep;
        numOfWheat = first.numOfWheat;
        numOfWood = first.numOfWood;
    }
    public void alterAllResources(ResourceList first){
        numOfBrick += first.numOfBrick;
        numOfOre += first.numOfOre;
        numOfSheep += first.numOfSheep;
        numOfWheat += first.numOfWheat;
        numOfWood += first.numOfWood;
    }

    public int getBrick() {
        return numOfBrick;
    }

    public int getOre() {
        return numOfOre;
    }

    public int getSheep() {
        return numOfSheep;
    }

    public int getWheat() {
        return numOfWheat;
    }

    public int getWood() {
        return numOfWood;
    }

    public void setBrick(int brick) {
        numOfBrick = brick;
    }

    public void setOre(int ore) {
        numOfOre = ore;
    }

    public void setSheep(int sheep) {
        numOfSheep = sheep;
    }

    public void setWheat(int wheat) {
        numOfWheat = wheat;
    }

    public void setWood(int wood) {
        numOfWood = wood;
    }

    public ResourceList merge(ResourceList first, ResourceList second) {
        int newbrick = first.getBrick() + second.getBrick();
        int newore = first.getOre() + second.getOre();
        int newsheep = first.getSheep() + second.getSheep();
        int newwheat = first.getWheat() + second.getWheat();
        int newwood = first.getWood() + second.getWood();

        ResourceList result = new ResourceList(newbrick, newore, newsheep, newwheat, newwood);
        return result;
    }

    public ResourceType stealCard() {
        ResourceType card = null;
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for (int i = 0; i < numOfBrick; i++) {
            ints.add(0);
        }
        for (int i = 0; i < numOfOre; i++) {
            ints.add(1);
        }
        for (int i = 0; i < numOfSheep; i++) {
            ints.add(2);
        }
        for (int i = 0; i < numOfWheat; i++) {
            ints.add(3);
        }
        for (int i = 0; i < numOfWood; i++) {
            ints.add(4);
        }
        if (ints.size() == 0) {
            return null;
        }
        int random = ints.get((int) (Math.random() * ints.size() - 1));
        if (random == 0) {
            card = ResourceType.BRICK;
            this.numOfBrick--;
        }
        if (random == 1) {
            card = ResourceType.ORE;
            this.numOfOre--;
        }
        if (random == 2) {
            card = ResourceType.SHEEP;
            this.numOfSheep--;
        }
        if (random == 3) {
            card = ResourceType.WHEAT;
            this.numOfWheat--;
        }
        if (random == 4) {
            card = ResourceType.WOOD;
            this.numOfWood--;
        }
        return card;
    }

    public void addResourceType(String type, int num) {
        if (type.equals("BRICK")) {
            numOfBrick += num;
        }
        if (type.equals("ORE")) {
            numOfOre += num;
        }
        if (type.equals("SHEEP")) {
            numOfSheep += num;
        }
        if (type.equals("WHEAT")) {
            numOfWheat += num;
        }
        if (type.equals("WOOD")) {
            numOfWood += num;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BRICK AMOUNT: " + getBrick() + "\n");
        sb.append("ORE AMOUNT: " + getOre() + "\n");
        sb.append("SHEEP AMOUNT: " + getSheep() + "\n");
        sb.append("WHEAT AMOUNT: " + getWheat() + "\n");
        sb.append("WOOD AMOUNT: " + getWood() + "\n");
        return sb.toString();
    }

    public int getSize() {
        int total = numOfBrick + numOfOre + numOfSheep + numOfWheat + numOfWood;
        return total;
    }

    public int getNumOfResource(String resource) {
        int resourceNum = 0;
        switch (resource.toUpperCase()) {
            case "BRICK":
                resourceNum = getBrick();
                break;
            case "ORE":
                resourceNum = getOre();
                break;
            case "SHEEP":
                resourceNum = getSheep();
                break;
            case "WHEAT":
                resourceNum = getWheat();
                break;
            case "WOOD":
                resourceNum = getWood();
                break;
        }
        return resourceNum;
    }

    public boolean canTakeResource(ResourceType resource) {
        boolean canTake = false;
        switch (resource) {
            case BRICK:
                if (numOfBrick > 0) {
                    canTake = true;
                }
                break;
            case ORE:
                if (numOfOre > 0) {
                    canTake = true;
                }
                break;
            case SHEEP:
                if (numOfSheep > 0) {
                    canTake = true;
                }
                break;
            case WHEAT:
                if (numOfWheat > 0) {
                    canTake = true;
                }
                break;
            case WOOD:
                if (numOfWood > 0) {
                    canTake = true;
                }
                break;
        }
        return canTake;
    }

    public int size() {
        return getBrick() + getOre() + getSheep() + getWheat() + getWood();
    }

}
