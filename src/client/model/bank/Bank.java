package client.model.bank;

import shared.exceptions.InsufficientResourcesException;

public class Bank {
  ResourceList resources;
  DevCardList dcl;
    /**
    * checks to make sure the bank can add the proper resource amount
    */

    public boolean canAddBrick()
  {
    return false;
  }
    /**
    * adds proper resource to resourcelist
    */

    public void addBrick()
    {
    }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */



    public boolean canGiveBrick(int amount) throws InsufficientResourcesException
    {
      return false;
    }
     /**
    * gives proper resource from resourcelist
    */
     public int giveBrick(int amount)
  {
    return 1;
  }
    /**
    * checks to make sure the bank can add the proper resource amount
    */
    public boolean canAddOre()
  {
    return false;
  }
     /**
    * adds proper resource to resourcelist
    */
     public void addOre()
  {
    
  }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveOre(int amount)  throws InsufficientResourcesException
  {
    return false;
  }
    /**
    * gives proper resource from resourcelist
    */
    public int giveOre(int amount)
  {
    return 1;
  }
    /**
    * checks to make sure the bank can add the proper resource amount
    */
    public boolean canAddSheep()
  {
    return false;
  }
    /**
    * adds proper resource to resourcelist
    */
    public void addSheep()
  {
    
  }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveSheep(int amount) throws InsufficientResourcesException
  {
    return false;
  }
    /**
    * gives proper resource from resourcelist
    */
    public int giveSheep(int amount)
  {
    return 1;
  }
    /**
    * checks to make sure the bank can add the proper resource amount
    */
    public boolean canAddWheat()
  {
    return false;
  }
    /**
    * adds proper resource to resourcelist
    */
    public void addWheat()
  {
    
  }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveWheat(int amount) throws InsufficientResourcesException
  {
    return false;
  }
    /**
    * gives proper resource from resourcelist
    */
    public int giveWheat(int amount)
  {
    return 1;
  }
    /**
    * checks to make sure the bank can add the proper resource amount
    */
    public boolean canAddWood()
  {
    return false;
  }
    /**
    * adds proper resource to resourcelist
    */
    public void addWood()
  {
    
  }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveWood(int amount) throws InsufficientResourcesException
  {
    return false;
  }
    /**
    * gives proper resource from resourcelist
    */
    public int giveWood(int amount)
  {
    return 1;
  }
  
  /**
   * verify that the bank does have dev cards to give
  */
  public boolean canBuyDevCard() throws InsufficientResourcesException
  {
    return false;
  }
  
  /**
   * purchase of a devcard
  */
  public void BuyDevCard()
  {
    
  }
}
