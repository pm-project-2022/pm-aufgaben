package Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gui extends JFrame {
    int chara = 0;
    boolean bool = false;
    public Gui() throws IOException {

        initGui();
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

    public void initGui() throws IOException {
        Button r1 = new Button("Knight");
        Button r2 = new Button("Wizard");
        Button r3 = new Button("Hunter");

        BufferedImage imgKnight =  ImageIO.read(new File("dungeon/code/assets/character/heroclasses/knight/Male/knight_m_hit_anim_f0.png"));
        ImageIcon icon = new ImageIcon(imgKnight);
        BufferedImage imgWizard =  ImageIO.read(new File("dungeon/code/assets/character/heroclasses/wizard/Male/wizzard_m_hit_anim_f0.png"));
        ImageIcon icon2 = new ImageIcon(imgWizard);
        BufferedImage imgHunter =  ImageIO.read(new File("dungeon/code/assets/character/heroclasses/hunter/male/elf_m_hit_anim_f0.png"));
        ImageIcon icon3 = new ImageIcon(imgHunter);

        JLabel lblKnight = new JLabel();
        JLabel lblWizard = new JLabel();
        JLabel lblHunter = new JLabel();

        lblKnight.setIcon(icon);
        lblWizard.setIcon(icon2);
        lblHunter.setIcon(icon3);

        JFrame f1 = new JFrame();
        JPanel p1 = new JPanel();
        f1.setLocation(700,500);
        f1.setResizable(false);
        f1.setTitle("Choose");
        f1.setSize(300,100);
        f1.setDefaultCloseOperation(3);
        p1.setLayout(new GridLayout());
        f1.add(p1);

        p1.add(r1);
        p1.add(lblKnight);
        p1.add(r2);
        p1.add(lblWizard);
        p1.add(r3);
        p1.add(lblHunter);
        f1.setVisible(true);

        r1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChara(1);
                bool=true;
                f1.dispose();
            }
        });
        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChara(2);
                bool=true;
                f1.dispose();
            }
        });
        r3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChara(3);
                bool=true;
                f1.dispose();
            }
        });
    }



}

