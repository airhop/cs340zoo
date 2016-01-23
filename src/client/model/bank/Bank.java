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
    public void addBrick() throws InsufficientResourcesException
    {
      //throws if trying to add negative number
    }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveBrick(int amount)
    {
      return false;
    }
     /**
    * gives proper resource from resourcelist
    */
     public void giveBrick(int amount) throws InsufficientResourcesException
  {
    //throws if there arent enough resources to give
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
    public void addOre() throws InsufficientResourcesException
    {
      //throws if trying to add negative number
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
     public void giveOre(int amount) throws InsufficientResourcesException
  {
    //throws if there arent enough resources to give
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
    public void addSheep() throws InsufficientResourcesException
    {
      //throws if trying to add negative number
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
     public void giveSheep(int amount) throws InsufficientResourcesException
  {
    //throws if there arent enough resources to give
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
    public void addWheat() throws InsufficientResourcesException
    {
      //throws if trying to add negative number
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
     public void giveWheat(int amount) throws InsufficientResourcesException
  {
    //throws if there arent enough resources to give
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
    public void addWood() throws InsufficientResourcesException
    {
      //throws if trying to add negative number
    }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveWood(int amount)
  {
    return false;
  }
    /**
    * gives proper resource from resourcelist
    */
     public void giveWood(int amount) throws InsufficientResourcesException
  {
    //throws if there arent enough resources to give
  }
  
  /**
   * verify that the bank does have dev cards to give
  */
  public boolean canBuyDevCard(ResourceList playerResources)
  {
    return false;
  }
  
  /**
   * purchase of a devcard
  */
  public void BuyDevCard(ResourceList playerResources) throws InsufficientResourcesException
  {
    //if player doesnt have 1 sheep 1 wheat 1 ore throw exception
  }
}
