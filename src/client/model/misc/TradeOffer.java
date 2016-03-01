package client.model.misc;

import client.model.bank.ResourceList;

public class TradeOffer {
  
  //The index of the person offering the trade
  int sender;
  //The index of the person the trade was offered to.
  int reciever;
  //Positive numbers are resources being offered. Negative are resources being asked for.
  ResourceList offer;
  //Resource List of whats being sent (negative)
  ResourceList sentList = new ResourceList(0);
  //Resource List of whats being recieved (positive)
  ResourceList recievedList = new ResourceList(0);
  
    public TradeOffer()
  {
    offer = null;
  }
  
  public TradeOffer(ResourceList offr)
  {
    offer = offr;
  }

  public TradeOffer(int pid, int rid, ResourceList o)
  {
    sender = pid;
    reciever = rid;
    offer = o;
  }
  /*
  * Separates the given offer into two resource lists, one that is filled with only the positive number
  * of resources being recieved, and the other a list filled with only the negative number of resources
  * being sent.
  */
  public void separateOffer()
  {
    int brick = offer.getBrick();
    int ore = offer.getOre(); 
    int sheep = offer.getSheep();
    int wheat = offer.getWheat();
    int wood = offer.getWood();
    if(brick > 0){recievedList.setBrick(brick); sentList.setBrick(0);} else if(brick < 0){sentList.setBrick(brick); recievedList.setBrick(0);} else {sentList.setBrick(0); recievedList.setBrick(0);}
    if(ore > 0){recievedList.setOre(ore); sentList.setOre(0);} else if(ore < 0){sentList.setOre(ore); recievedList.setOre(0);} else {sentList.setOre(0); recievedList.setOre(0);}
    if(sheep > 0){recievedList.setSheep(sheep); sentList.setSheep(0);} else if(sheep < 0){sentList.setSheep(sheep); recievedList.setSheep(0);} else {sentList.setSheep(0); recievedList.setSheep(0);}
    if(wheat > 0){recievedList.setWheat(wheat); sentList.setWheat(0);} else if(wheat < 0){sentList.setWheat(wheat); recievedList.setWheat(0);} else {sentList.setWheat(0); recievedList.setWheat(0);}
    if(wood > 0){recievedList.setWood(wood); sentList.setWood(0);} else if(wood < 0){sentList.setWood(wood); recievedList.setWood(0);} else {sentList.setWood(0); recievedList.setWood(0);}
  }
  
  public int getSender()
  {
    return sender;
  }
  public void setSender(int send)
  {
    sender = send;
  }  
  public int getReciever()
  {
    return reciever;
  }
  public void setReciever(int recieve)
  {
    reciever = recieve;
  }  
  public ResourceList getOffer()
  {
    return offer;
  }
  public void setOffer(ResourceList off)
  {
    offer = off;
  }
  public ResourceList getSentList() {return sentList; }
  public ResourceList getRecievedList() {return recievedList; }
  public String toString()
  {
    return sender + " " + reciever + " " + offer.toString();
  }
}
