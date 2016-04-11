package client.MVC.catan;

import client.MVC.discard.DiscardController;
import client.MVC.discard.DiscardView;
import client.MVC.misc.WaitView;
import client.MVC.roll.RollController;
import client.MVC.roll.RollResultView;
import client.MVC.roll.RollView;
import client.facade.Facade;
import shared.definitions.ResourceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CatanPanel extends JPanel {
    private TitlePanel titlePanel;
    private LeftPanel leftPanel;
    private MidPanel midPanel;
    private RightPanel rightPanel;

    private DiscardView discardView;
    private WaitView discardWaitView;
    private DiscardController discardController;

    private RollView rollView;
    private RollResultView rollResultView;
    private RollController rollController;

    public CatanPanel() {
        this.setLayout(new BorderLayout());

        titlePanel = new TitlePanel();
        midPanel = new MidPanel();
        leftPanel = new LeftPanel(titlePanel, midPanel.getGameStatePanel());
        rightPanel = new RightPanel(midPanel.getMapController());

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(midPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);

        discardView = new DiscardView();
        discardWaitView = new WaitView();
        discardWaitView.setMessage("Waiting for other Players to Discard");
        discardController = new DiscardController(discardView, discardWaitView);
        discardView.setController(discardController);
        discardWaitView.setController(discardController);

        rollView = new RollView();
        rollResultView = new RollResultView();
        rollController = new RollController(rollView, rollResultView);
        rollView.setController(rollController);
        rollResultView.setController(rollController);

        JButton testButton = new JButton("Save");
        testButton.addActionListener(new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent e)
             {
                 Facade.getInstance().saveGame();
             }
        });
        this.add(testButton, BorderLayout.SOUTH);
    }

}

