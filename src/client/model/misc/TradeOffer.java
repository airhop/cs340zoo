package client.model.misc;

import client.model.bank.ResourceList;

public class TradeOffer {
  
  //The index of the person offering the trade
  int sender;
  //The index of the person the trade was offered to.
  int reciever;
  //Positive numbers are resources being offered. Negative are resources being asked for.
  ResourceList offer;
  
  public TradeOffer(){}
  
  //getters and setters
}