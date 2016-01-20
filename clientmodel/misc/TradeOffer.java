package clientmodel.misc;

public class TradeOffer {
  
  //The index of the person offering the trade
  int sender;
  //The index of the person the trade was offered to.
  int reciever;
  //Positive numbers are resources being offered. Negative are resources being asked for.
  ResourceList offer;
  
  //getters and setters
}
