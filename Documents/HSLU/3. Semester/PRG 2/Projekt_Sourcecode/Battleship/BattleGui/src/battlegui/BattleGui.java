/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battlegui;

/**
 *
 * @author User
 */
    
/**
 * BattleGui
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JButton;


public class BattleGui implements ActionListener 
{
    private JButton [][] grid; 
    
    public static void main(String[] args) 
    {

        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {

                createAndShowGUI();

            }
        });
    }


public JMenuBar createMenu() 
    {
        JMenuBar menuBar  = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuOption = new JMenu("Options");
        
        JMenuItem menuNew;
        JMenuItem menuQuit;
        JMenu menuMode;
        JMenuItem menuHelp;
        JCheckBoxMenuItem single;
        JCheckBoxMenuItem multi; 

        menuBar.add(menuGame);
        menuBar.add(menuOption);

        menuNew = new JMenuItem("New Game");
        menuNew.addActionListener(this);
        menuGame.add(menuNew);


        menuQuit = new JMenuItem("Quit");
        menuQuit.addActionListener(this);
        menuGame.add(menuQuit);  
        
        menuMode = new JMenu ("Mode");
        menuMode.addActionListener(this);
        menuOption.add(menuMode);
        
        single = new JCheckBoxMenuItem ("SinglePlayer");
        single.setSelected(true);
        single.addActionListener(this);
        menuMode.add(single);
        
        multi = new JCheckBoxMenuItem ("Mulitplayer");
        multi.addActionListener(this);
        menuMode.add(multi);
        
        
        menuHelp = new JMenuItem ("Help");
        menuHelp.addActionListener(this);
        menuOption.add(menuHelp);
        
        return menuBar;
    }

public  Container createOpponentField() 
    {

        JPanel opponentPanel = new JPanel(new GridLayout(10,10,1,1));
        opponentPanel.setBorder(new TitledBorder("Opponent Field"));
        opponentPanel.setBackground(Color.white);
        
        grid=new JButton[10][10];
        
        for (int y = 0; y< 10; y++)
        {
            for(int x=0; x<10; x++)
            {
                 grid[x][y] = new JButton ();
                 grid[x][y].setIcon(new ImageIcon("C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\water.jpg"));
                 grid[x][y].setBorder(null);
                 opponentPanel.add(grid[x][y]);
                 grid[x][y].setActionCommand("" + x+y); 
                 grid[x][y].addActionListener(this);
            }
           
        }
        opponentPanel.setVisible(true);
        return opponentPanel;
    }


public  Container createHomeField() 
    {
        JPanel homePanel = new JPanel(new GridLayout(10,10,1,1));
        homePanel.setBorder(new TitledBorder("Home Field"));
        homePanel.setBackground (Color.white);
        grid=new JButton[10][10];
        
        for (int y = 0; y< 10; y++)
        {
            for(int x=0; x<10; x++)
            {
                 grid[x][y] = new JButton ();
                 grid[x][y].setBorder(null);
                 grid[x][y].setIcon(new ImageIcon("C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\water.jpg"));
                 homePanel.add(grid[x][y]);
                 
            }
           
        }
        homePanel.setVisible(true);
        return homePanel;
    }    

public Container setShip ()
    {
        JPanel ShipPanel = new JPanel (new GridLayout(2,0));
        
        JButton bship = new JButton("Kreuzer");
        
        bship.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
                

            }
        });
        bship.setBackground(Color.white);
        bship.setBorder (null);

        JButton cruiser = new JButton ("Schlachtschiff");
        cruiser.setBorder (null);
        cruiser.setBackground(Color.white);
        
        JButton destroyer = new JButton ("Zerstörer");
        destroyer.setBorder (null);
        destroyer.setBackground (Color.white);
        JButton uboot = new JButton("UBoot");
        uboot.setBorder (null);
        uboot.setBackground(Color.white);
                
        bship.setIcon (new ImageIcon("C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\Kreuzer.jpg"));
        cruiser.setIcon (new ImageIcon("C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\Schlachtschiff.jpg"));
        destroyer.setIcon(new ImageIcon("C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\Zerstörer.jpg"));
        uboot.setIcon(new ImageIcon("C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\UBoot.jpg"));

        ShipPanel.add(bship);
        ShipPanel.add(cruiser);
        ShipPanel.add(destroyer);
        ShipPanel.add(uboot);
  
        return ShipPanel;
        
    }


    
public void actionPerformed(ActionEvent e) 
    {
        String classname = getClassName(e.getSource());
        JComponent component = (JComponent)(e.getSource());

        if (classname.equals("JMenuItem"))
        {
            JMenuItem menusource = (JMenuItem)(e.getSource());
            String menutext  = menusource.getText();

            if (menutext.equals("New Game"))
            {
                createAndShowGUI();              
            }
            else if (menutext.equals("Quit"))
            {
                System.exit(0); 
            }
            else if (menutext.equals("Help"))
            {
                try {
                    help();
                }
                catch (Exception ex) {
                    Logger.getLogger(BattleGui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        else if (classname.equals("JButton"))
        {
            JButton button = (JButton)(e.getSource());
            int bnum = Integer.parseInt(button.getActionCommand());
            int row = bnum / 10;
            int col = bnum % 10;
            button.setIcon(new ImageIcon("C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\fire.jpg"));
            System.out.println("Fire shot: (" + row + " / " + col + ")");
        }  
    }

    /**
     *  Returns the class name
     */
protected String getClassName(Object o) 
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /**
     * Create the GUI and show it.
     */
private static void createAndShowGUI() 
    {

        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        frame.setSize(800, 530);
        frame.setResizable(false);

        BattleGui battlegui = new BattleGui();
        JPanel gui = new JPanel(new FlowLayout());
        
        frame.setJMenuBar(battlegui.createMenu());
        frame.setContentPane(gui);

        gui.setBorder(new EmptyBorder(5,5,100,5));
        gui.add(battlegui.createOpponentField());
        gui.add(battlegui.createHomeField());
        gui.add(battlegui.setShip());
        gui.setBackground(Color.white);
        
        frame.setVisible(true);
    }  
    
public void help() throws Exception
    {
                Runtime rt= Runtime.getRuntime();
                String file ="C:\\Loana\\Schule\\TA.BA_PRG2.H1301\\BattleGui\\src\\Game_Instruction.txt";
                Process p = rt.exec ("notepad "+ file);
    }
    

}