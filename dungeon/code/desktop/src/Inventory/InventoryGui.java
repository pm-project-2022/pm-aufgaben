package Inventory;

import Entities.Moveable.Hero.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InventoryGui extends JFrame {

    private boolean visibility = false;
    public JFrame frame;

    public void setVisibility(boolean v){
        this.visibility = v;
    }

    public boolean getVisibility(){
        return visibility;
    }

    public void initGui(Hero hero) {

        frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JButton button1 = new JButton("Drop");
        JButton button2 = new JButton("Drop");
        JButton button3 = new JButton("Drop");


        try {
            label1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + hero.getInventory().getFirstItem()))));
        } catch (NullPointerException ignored) {
        }

        try {
            label2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + hero.getInventory().getSecondItem()))));
        } catch (NullPointerException ignored) {
        }

        try {
            label3.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + hero.getInventory().getThirdItem()))));
        } catch (NullPointerException ignored) {
        }

        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(new GridLayout(2, 3));
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        panel.add(label1, 1, 0);
        panel.add(label2, 1, 1);
        panel.add(label3, 1, 2);
        panel.add(button1, 0, 0);
        panel.add(button2, 0, 1);
        panel.add(button3, 0, 2);
        frame.setAutoRequestFocus(false);

        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setVerticalAlignment(SwingConstants.CENTER);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.getInventory().equipItem(0);
                hero.getInventory().dropItem(0);
                label1.setIcon(null);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.getInventory().equipItem(1);
                hero.getInventory().dropItem(1);
                label2.setIcon(null);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.getInventory().equipItem(2);
                hero.getInventory().dropItem(2);
                label3.setIcon(null);
            }
        });
    }



}
