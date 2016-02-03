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
  ResourceList sentList = new ResourceList();
  //Resource List of whats being recieved (positive)
  ResourceList recievedList = new ResourceList();
  
  public TradeOffer(ResourceList offr)
  {
    offer = offr;
  }
  
  public void separateOffer()
  {
    int brick = offer.getBrick();
    int ore = offer.getOre(); 
    int sheep = offer.getSheep();
    int wheat = offer.getWheat();
    int wood = offer.getWood();
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
}
