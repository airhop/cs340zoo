package client.model.bank;

import shared.exceptions.InsufficientResourcesException;

public class Bank {
  ResourceList resources;
  DevCardList dcl;

    /**
    * adds proper resource to resourcelist
    */
    public void addBrick(int brick) throws InsufficientResourcesException
    {
      //throws if trying to add negative number
      if(brick < 0)
      {
        throw new InsufficientResourcesException();  
      }
      else
      {
        int prevRsrcBrick = resources.getBrick();
        resources.setBrick((prevRsrcBrick + brick));
      }
    }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveBrick(int amount)
    {
      if(amount < 0){return false;}
      
      int prevRsrcBrick = resources.getBrick();
      if((prevRsrcBrick - amount) < 0){return false;}
      return true;
    }
     /**
    * gives proper resource from resourcelist
    */
     public void giveBrick(int amount) throws InsufficientResourcesException
  {
    //throws if there arent enough resources to give
  }
     /**
    * adds proper resource to resourcelist
    */
    public void addOre(int ore) throws InsufficientResourcesException
    {
      //throws if trying to add negative number
      if(ore < 0)
      {
        throw new InsufficientResourcesException();  
      }
      else
      {
        int prevRsrcOre = resources.getOre();
        resources.setOre((prevRsrcOre + ore));
      }
    }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveOre(int amount)
  {
      if(amount < 0){return false;}
      
      int prevRsrcOre = resources.getOre();
      if((prevRsrcOre - amount) < 0){return false;}
      return true;
  }
    /**
    * gives proper resource from resourcelist
    */
     public void giveOre(int amount) throws InsufficientResourcesException
  {
    //throws if there arent enough resources to give
  }
    /**
    * adds proper resource to resourcelist
    */
    public void addSheep(int sheep) throws InsufficientResourcesException
    {
      //throws if trying to add negative number
      if(sheep < 0)
      {
        throw new InsufficientResourcesException();  
      }
      else
      {
        int prevRsrcSheep = resources.getSheep();
        resources.setSheep((prevRsrcSheep + sheep));
      }
    }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveSheep(int amount)
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
    * adds proper resource to resourcelist
    */
    public void addWheat(int wheat) throws InsufficientResourcesException
    {
      //throws if trying to add negative number
      if(wheat < 0)
      {
        throw new InsufficientResourcesException();  
      }
      else
      {
        int prevRsrcWheat = resources.getWheat();
        resources.setWheat((prevRsrcWheat + wheat));
      }
    }
    /**
     *@param amount - number of resources needed to give out
    * checks to make sure the bank can give the proper resource amount
    */
    public boolean canGiveWheat(int amount)
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
    * adds proper resource to resourcelist
    */
    public void addWood(int wood) throws InsufficientResourcesException
    {
      //throws if trying to add negative number
      if(wood < 0)
      {
        throw new InsufficientResourcesException();  
      }
      else
      {
        int prevRsrcWood = resources.getWood();
        resources.setWood((prevRsrcWood + wood));
      }
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
