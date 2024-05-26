package edu.hitsz.swingWindows;

import edu.hitsz.application.*;

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
    private static Game game;

    public int getDifficulty() {
        return difficulty;
    }

    public StartMenu() {
        simpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty=1;
                game=new SimpleGame();
                Start();
            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty=2;
                game=new NormalGame();
                Start();
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty=3;
                game=new HardGame();
                Start();
            }
        });
    }
    private void Start(){
        String selectedOption = (String) musicChoiceBox.getSelectedItem();
        //Game game =new Game();
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
