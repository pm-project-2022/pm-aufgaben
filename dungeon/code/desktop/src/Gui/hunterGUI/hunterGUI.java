/*
 * Created by JFormDesigner on Tue May 10 18:38:57 CEST 2022
 */

package Gui.hunterGUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class hunterGUI extends JFrame {
    public hunterGUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        knightStats = new JLabel();
        label2 = new JLabel();
        buttonConfirm = new JButton();
        buttonDecline = new JButton();
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- knightStats ----
            knightStats.setText("<html><pre>HP: 75     Mana: 125<br/>AtkP: 75   DefP: 75<br/>Accu: 70   Eva: 70<br/>This Character?<pre><html>");
            knightStats.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(knightStats);
            knightStats.setBounds(0, 120, 220, 104);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/guiAssets/hunterGif.gif")));
            panel1.add(label2);
            label2.setBounds(0, 0, 220, 110);

            //---- buttonConfirm ----
            buttonConfirm.setText("Yes");
            panel1.add(buttonConfirm);
            buttonConfirm.setBounds(0, 235, 105, buttonConfirm.getPreferredSize().height);

            //---- buttonDecline ----
            buttonDecline.setText("No");
            panel1.add(buttonDecline);
            buttonDecline.setBounds(115, 235, 103, buttonDecline.getPreferredSize().height);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/guiAssets/Screenshot 2022-05-10 221807hunter.png")));
            panel1.add(label1);
            label1.setBounds(220, 0, label1.getPreferredSize().width, 280);

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
        panel1.setBounds(0, 0, 585, 305);

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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    public JPanel getPanel(){
        return panel1;
    }
    public JButton getButtonConfirm(){
        return buttonConfirm;
    }

    public JButton getButtonDecline(){
        return buttonDecline;
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel knightStats;
    private JLabel label2;
    private JButton buttonConfirm;
    private JButton buttonDecline;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
