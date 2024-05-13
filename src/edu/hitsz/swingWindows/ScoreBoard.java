package edu.hitsz.swingWindows;

import edu.hitsz.Score.ShowScoreBoard;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class ScoreBoard {
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel ButtonPanel;
    private JLabel headLabel;
    private JScrollPane tableScrollPanel;
    private JTable scoreTable;
    private JButton deleteButton;


    public ScoreBoard() {

        ShowScoreBoard.show();
        String[] columnName = {"名次","玩家名","得分","记录时间"};
        //String[][]tableData={{"001","Lily","78"},{"002","Jane","89"},{"003","Alex","67"}, {"004","Macy","83"},{"005","Nancy","66"},{"006","John","99"}};
        //表格模型
        String[][]tableData=new String[ShowScoreBoard.getRecordSize()][4];
        for(int i=0;i<ShowScoreBoard.getRecordSize();i=i+1){
            tableData[i][0]=Integer.toString(ShowScoreBoard.getRecords().get(i).getRank());
            tableData[i][1]=ShowScoreBoard.getRecords().get(i).getName();
            tableData[i][2]=Integer.toString(ShowScoreBoard.getRecords().get(i).getMark());
            tableData[i][3]=ShowScoreBoard.getRecords().get(i).getTime().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
        }




        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        //JTable 并不存储自己的数据，而是从表格模型那里获取它的数据
        scoreTable.setModel(model);
        tableScrollPanel.setViewportView(scoreTable);




        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoreTable.getSelectedRow();

                int result = JOptionPane.showConfirmDialog(deleteButton,
                        "是否确定删除选中的玩家？", "确认删除", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION && row != -1) {
                    //model.removeRow(row);
                    ShowScoreBoard.deleteOne(row);

                    System.out.println("delete row" + row + " successfully");
                    ScoreBoard newScoreBoard=new ScoreBoard();
                    Main.cardPanel.add(newScoreBoard.getMainPanel());
                    Main.cardLayout.last(Main.cardPanel);
                }
            }
        });


    }

    public JPanel getMainPanel() {
        return MainPanel;
    }
    public void drawBoard(){

    }
    public void getName(){
        String username = JOptionPane.showInputDialog(null,
                "游戏结束，请输入你的名字",
                "输入用户名",
                JOptionPane.QUESTION_MESSAGE);
        ShowScoreBoard.addOne(username, Game.score);
        ScoreBoard newScoreBoard=new ScoreBoard();
        Main.cardPanel.add(newScoreBoard.getMainPanel());
        Main.cardLayout.last(Main.cardPanel);
    }
}
