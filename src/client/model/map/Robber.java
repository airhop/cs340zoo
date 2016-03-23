package client.model.map;

import shared.locations.HexLocation;

public class Robber {
    HexLocation hl;

    public Robber() {
        //TODO may take this out
//        hl = new HexLocation(0,0);
    }

    //need a getLocation method

    /**
     * @param h - HexLocation - find if robber is in a certain hex
     * @return true/false
     */
    public Boolean isLocated(HexLocation h) {
        return false;
    }

    public HexLocation getHl() {
        return hl;
    }

    public void setHl(HexLocation hl) {
        this.hl = hl;
    }

}
