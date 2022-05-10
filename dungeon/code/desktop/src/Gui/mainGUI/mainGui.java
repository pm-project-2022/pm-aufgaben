/*
 * Created by JFormDesigner on Tue May 10 17:40:43 CEST 2022
 */

package Gui.mainGUI;

import javax.swing.border.*;
import Gui.hunterGUI.hunterGUI;
import Gui.knightGUI.knightGui;
import Gui.wizardGUI.wizardGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author unknown
 */
public class mainGui extends JFrame {
    int chara = 0;
    boolean bool = false;
    public mainGui() {
        initComponents();

        knightGui kGUI = new knightGui();
        JButton knightConfirm = kGUI.getButtonConfirm();
        JButton knightDecline = kGUI.getButtonDecline();


        wizardGUI wGUI = new wizardGUI();
        JButton wizardConfirm = wGUI.getButtonConfirm();
        JButton wizardDecline = wGUI.getButtonDecline();


        hunterGUI hGUI = new hunterGUI();
        JButton hunterConfirm = hGUI.getButtonConfirm();
        JButton hunterDecline = hGUI.getButtonDecline();

        JFrame f1 = new JFrame();
        knightButton.setBackground(Color.DARK_GRAY);
        wizardButton.setBackground(Color.DARK_GRAY);
        hunterButton.setBackground(Color.DARK_GRAY);

        f1.setTitle("Choose your Character");
        f1.setSize(675,325);
        f1.setLocation(600,350);
        f1.setDefaultCloseOperation(3);
        f1.add(panel1);
        f1.setAlwaysOnTop(true);
        f1.setVisible(true);

        knightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.remove(panel1);
                f1.setVisible(false);
                f1.setLocation(800,300);
                f1.add(kGUI.getPanel());
                f1.setSize(580,310);
                f1.setVisible(true);
            }
        });

        wizardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.remove(panel1);
                f1.setVisible(false);
                f1.setLocation(800,300);
                f1.add(wGUI.getPanel());
                f1.setSize(580,310);
                f1.setVisible(true);
            }
        });

        hunterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.remove(panel1);
                f1.setVisible(false);
                f1.setLocation(800,300);
                f1.add(hGUI.getPanel());
                f1.setSize(580,315);
                f1.setVisible(true);
            }
        });

        knightDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.remove(kGUI.getPanel());
                f1.setVisible(false);
                f1.add(panel1);
                f1.setSize(675,325);
                f1.setLocation(600,350);
                f1.setVisible(true);
            }
        });

        wizardDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.remove(wGUI.getPanel());
                f1.setVisible(false);
                f1.add(panel1);
                f1.setSize(675,325);
                f1.setLocation(600,350);
                f1.setVisible(true);
            }
        });

        hunterDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.remove(hGUI.getPanel());
                f1.setVisible(false);
                f1.add(panel1);
                f1.setSize(675,325);
                f1.setLocation(600,350);
                f1.setVisible(true);
            }
        });

        knightConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                bool = true;
                setChara(1);
            }
        });

        wizardConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                bool = true;
                setChara(2);
            }
        });

        hunterConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                bool = true;
                setChara(3);
            }
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        knightButton = new JButton();
        wizardButton = new JButton();
        hunterButton = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/logo/logo16x16.png")).getImage());
        setTitle("Choose your Character!");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/guiAssets/forestwithall.png")));
            panel1.add(label1);
            label1.setBounds(0, -5, 660, 125);

            //---- knightButton ----
            knightButton.setText("Knight");
            knightButton.setForeground(Color.white);
            panel1.add(knightButton);
            knightButton.setBounds(0, 255, 220, 25);

            //---- wizardButton ----
            wizardButton.setText("Wizard");
            wizardButton.setForeground(Color.white);
            panel1.add(wizardButton);
            wizardButton.setBounds(220, 255, 220, 25);

            //---- hunterButton ----
            hunterButton.setText("Hunter");
            hunterButton.setForeground(Color.white);
            panel1.add(hunterButton);
            hunterButton.setBounds(440, 255, 225, 25);

            //---- label2 ----
            label2.setText("<html>A knight is a proud, skilled melee<br/>\ncombatant who fights in the name<br/>\nof honor and chivalry.<html>\n");
            label2.setVerticalAlignment(SwingConstants.TOP);
            label2.setBorder(new TitledBorder(null, "Knight", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
            label2.setForeground(Color.black);
            panel1.add(label2);
            label2.setBounds(0, 120, 220, 135);

            //---- label3 ----
            label3.setText("<html>Wizards are supreme magic-users,<br/>\ndefined and united as a class by the<br/>\nspells they cast.<br/>\nTheir magic conjures elementals from other planes<html>\n\n");
            label3.setVerticalAlignment(SwingConstants.TOP);
            label3.setBorder(new TitledBorder(null, "Wizard", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
            label3.setForeground(Color.black);
            panel1.add(label3);
            label3.setBounds(220, 120, 220, 135);

            //---- label4 ----
            label4.setText("<html>Secretive. Elusive. Tactical.<br/>\nThese three words best describes a<br/>\nhunter. They are clean, methodical killers, striking fear<br/>\ninto the hearts of their enemies.<html>");
            label4.setVerticalAlignment(SwingConstants.TOP);
            label4.setBorder(new TitledBorder(null, "Hunter", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
            label4.setForeground(Color.black);
            panel1.add(label4);
            label4.setBounds(440, 120, 220, 135);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, panel1.getPreferredSize().width, 295);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(660, 325);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public boolean getBool(){
        return bool;
    };
    public void setChara(int chara){
        this.chara = chara;
    }
    public int getChara(){
        return chara;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JButton knightButton;
    private JButton wizardButton;
    private JButton hunterButton;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
