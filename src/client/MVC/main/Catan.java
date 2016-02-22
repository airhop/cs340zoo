package client.MVC.main;

import client.MVC.base.*;
import client.MVC.catan.*;
import client.MVC.join.*;
import client.MVC.login.*;
import client.MVC.misc.*;
import client.facade.Facade;
import client.model.GameModel;
import client.poller.Poller;
import client.proxy.Proxy;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame {

    private CatanPanel catanPanel;

    public Catan() {

        client.MVC.base.OverlayView.setWindow(this);

        this.setTitle("Settlers of Catan");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        catanPanel = new CatanPanel();
        this.setContentPane(catanPanel);

        display();
    }

    private void display() {
        pack();
        setVisible(true);
    }

    //
    // Main
    //

    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Catan();

                Facade facade = Facade.getInstance();
                GameModel gameModel = new GameModel();
                Proxy myProxy = new Proxy(gameModel);
                facade.setProxy(myProxy);
                Poller poller = new Poller(myProxy);

                PlayerWaitingView playerWaitingView = new PlayerWaitingView();
                final PlayerWaitingController playerWaitingController = new PlayerWaitingController(
                        playerWaitingView);
                playerWaitingView.setController(playerWaitingController);

                JoinGameView joinView = new JoinGameView();
                NewGameView newGameView = new NewGameView();
                SelectColorView selectColorView = new SelectColorView();
                MessageView joinMessageView = new MessageView();
                final JoinGameController joinController = new JoinGameController(
                        joinView,
                        newGameView,
                        selectColorView,
                        joinMessageView);
                joinController.setJoinAction(new IAction() {
                    @Override
                    public void execute() {
                        playerWaitingController.start();
                    }
                });
                joinView.setController(joinController);
                newGameView.setController(joinController);
                selectColorView.setController(joinController);
                joinMessageView.setController(joinController);

                LoginView loginView = new LoginView();
                MessageView loginMessageView = new MessageView();
                LoginController loginController = new LoginController(
                        loginView,
                        loginMessageView);
                loginController.setLoginAction(new IAction() {
                    @Override
                    public void execute() {
                        joinController.start();
                    }
                });
                loginView.setController(loginController);
                loginView.setController(loginController);

                loginController.start();

            }
        });
    }

}

