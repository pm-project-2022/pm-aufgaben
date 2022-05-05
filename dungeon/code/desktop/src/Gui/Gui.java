package Gui;

import Entities.Moveable.Hero.Classes.Knight;
import Entities.Moveable.Hero.Hero;

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

    /**
     * initiates gui for picking a character
     * @throws IOException file found, not found
     */
    public void initGui() throws IOException {
        //buttons
        Button r1 = new Button("Knight");
        Button r2 = new Button("Wizard");
        Button r3 = new Button("Hunter");

        //load images
        BufferedImage imgKnight =  ImageIO.read(new File("dungeon/code/assets/character/heroclasses/knight/Male/knight_m_hit_anim_f0.png"));
        ImageIcon icon = new ImageIcon(imgKnight);
        BufferedImage imgWizard =  ImageIO.read(new File("dungeon/code/assets/character/heroclasses/wizard/Male/wizzard_m_hit_anim_f0.png"));
        ImageIcon icon2 = new ImageIcon(imgWizard);
        BufferedImage imgHunter =  ImageIO.read(new File("dungeon/code/assets/character/heroclasses/hunter/male/elf_m_hit_anim_f0.png"));
        ImageIcon icon3 = new ImageIcon(imgHunter);

        //fill label and center
        JLabel lblKnight = new JLabel();
        JLabel lblKnightStats = new JLabel("<html>HP: 100<br/>Mana: 100<br/>AtkP: 50<br/>DefP: 100<br/>Eva: 50<br/>Accu: 50<html>");
        lblKnightStats.setFont(new Font("Arial",Font.PLAIN,10));
        lblKnight.setHorizontalAlignment(JLabel.CENTER);
        lblKnight.setVerticalAlignment(JLabel.CENTER);

        JLabel lblWizard = new JLabel();
        lblWizard.setHorizontalAlignment(JLabel.CENTER);
        lblWizard.setVerticalAlignment(JLabel.CENTER);
        JLabel lblWizardStats = new JLabel("<html>HP: 50<br/>Mana: 200<br/>AtkP: 100<br/>DefP: 50<br/>Eva: 50<br/>Accu: 50<html>");
        lblWizardStats.setFont(new Font("Arial",Font.PLAIN,10));

        JLabel lblHunter = new JLabel();
        lblHunter.setHorizontalAlignment(JLabel.CENTER);
        lblHunter.setVerticalAlignment(JLabel.CENTER);
        JLabel lblHunterStats = new JLabel("<html>HP: 75<br/>Mana: 125<br/>AtkP: 75<br/>DefP: 75<br/>Eva: 70<br/>Accu: 70<html>");
        lblHunterStats.setFont(new Font("Arial",Font.PLAIN,10));

        lblKnight.setIcon(icon);
        lblWizard.setIcon(icon2);
        lblHunter.setIcon(icon3);

        //set frame and add contents to panel
        JFrame f1 = new JFrame();
        JPanel p1 = new JPanel();
        f1.setLocation(700,500);
        f1.setResizable(false);
        f1.setTitle("Choose your Character!");
        f1.setSize(500,150);
        f1.setDefaultCloseOperation(3);
        p1.setLayout(new GridLayout());
        f1.add(p1);

        p1.add(r1);
        p1.add(lblKnight);
        p1.add(lblKnightStats);
        p1.add(r2);
        p1.add(lblWizard);
        p1.add(lblWizardStats);
        p1.add(r3);
        p1.add(lblHunter);
        p1.add(lblHunterStats);
        f1.setVisible(true);

        //ActionListener for buttons to choose a character
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

    /**
     * boolean for character
     * @return button pressed true, otherwise false
     */
    public boolean getBool(){
        return bool;
    };
    public void setChara(int chara){
        this.chara = chara;
    }
    public int getChara(){
        return chara;
    }


}

