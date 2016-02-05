package shared.test;

/**
 * Created by GaryPetersen on 2/5/2016.
 */
 
public class PollerTest {
  @test public void pollerTest()
  {
    GameModel gameModel = new GameModel()
    IProxy iProxy = new MockProxy(gameModel);
    Poller poller = new Poller(iProxy);
  }
}
