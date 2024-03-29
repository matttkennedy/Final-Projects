import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class ConnectFour {

  static JFrame mainWindow;
  static JButton firstArrow = new JButton("Drop");
  static JButton secondArrow = new JButton("Drop");
  static JButton thirdArrow = new JButton("Drop");
  static JButton fourthArrow = new JButton("Drop");
  static JButton fifthArrow = new JButton("Drop");
  static JButton sixthArrow = new JButton("Drop");
  static JButton seventhArrow = new JButton("Drop");
  static JPanel[][] gridArray = new JPanel[6][7];
  static JLabel emptyLabel = new JLabel();
  static JPanel emptyPanel;
  static ImageIcon emptyBox;
  static JLabel redLabel = new JLabel();
  static JPanel redPanel;
  static ImageIcon redBox;
  static JLabel blackLabel = new JLabel();
  static JPanel blackPanel;
  static ImageIcon blackBox;


  public static void main(String[] args) {
    JPanel mainPanel = new JPanel();
    JPanel gridPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    mainPanel.setLayout(new BorderLayout());
    gridPanel.setLayout(new GridLayout(6, 7));
    buttonPanel.setLayout(new GridLayout(1, 7));
    mainPanel.setBackground(new Color(23, 13, 44));

    emptyBox = new ImageIcon("emptyBox.jpg"); 
    emptyLabel = new JLabel(emptyBox); 
    emptyPanel = new JPanel();
    emptyPanel.add(emptyLabel);

    mainPanel.add(gridPanel, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.NORTH);
    gridPanel.add(emptyPanel);

    buttonPanel.add(firstArrow);
    buttonPanel.add(secondArrow);
    buttonPanel.add(thirdArrow);
    buttonPanel.add(fourthArrow);
    buttonPanel.add(fifthArrow);
    buttonPanel.add(sixthArrow);
    buttonPanel.add(seventhArrow);

    mainWindow = new JFrame("Connect Four");
    mainWindow.setContentPane(mainPanel);
    mainWindow.setSize(846, 730);
    mainWindow.setLocationRelativeTo(null);
    mainWindow.setVisible(true);
    mainWindow.setResizable(false);

    fillGrid();
  }

  public static void fillGrid() {

    for(int j = 0; j < 6; j++) {
      for (int k = 0; k < 7; k++) {
        gridArray[j][k] = emptyPanel;

      }
    }
  }
}