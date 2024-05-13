package edu.hitsz.swingWindows;

import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    private JButton simpleButton;
    private JButton normalButton;
    private JButton hardButton;
    private JPanel simplePanel;
    private JPanel normalPanel;
    private JPanel hardPanel;
    private JPanel musicPanel;
    private JComboBox musicChoiceBox;
    private JLabel musicLabel;
    private JPanel MainPanel;

    public static int difficulty=0;

    public int getDifficulty() {
        return difficulty;
    }

    public StartMenu() {
        simpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty=1;
                Start();
            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty=2;
                Start();
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty=3;
                Start();
            }
        });
    }
    private void Start(){
        String selectedOption = (String) musicChoiceBox.getSelectedItem();
        Game game =new Game();
        if(selectedOption=="开")game.setMusic_on(1);
        else game.setMusic_on(0);
        Main.cardPanel.add(game);
        Main.cardLayout.last(Main.cardPanel);
        game.action();
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }
}
