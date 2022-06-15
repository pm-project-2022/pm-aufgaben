package Shop;

import Entities.Items.HPPot;
import Entities.Items.Item;
import Entities.Items.MPPot;
import Entities.Items.StrengthOrb;
import Entities.Moveable.Hero.Hero;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.*;

public class ShopGui {
    public Item mp, hp, orb;
    private static int quantityMP = 3;
    private static int quantityHP = 3;
    private static int quantityStr = 3;
    private static int priceMP = 10;
    private static int priceHP = 10;
    private static int priceStr = 15;
    private Hero hero;
    private Item boughtItem;
    Logger log = Logger.getLogger("ShopGui");

    public ShopGui(Hero hero, graphic.Painter painter, SpriteBatch batch) {
        initComponents();
        this.hero = hero;
    }

    /**
     * initializes components
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mainFrame = new JFrame();
        label2 = new JLabel();
        label3 = new JLabel();
        anzahlMP = new JLabel();
        anzahlHP = new JLabel();
        anzahlStr = new JLabel();
        tfKaufen = new JTextField();
        tfFeilschen = new JTextField();
        tfVerkaufen = new JTextField();
        kaufen = new JLabel();
        verkaufen = new JLabel();
        feilschen = new JLabel();
        buttonKaufen = new JButton();
        preisHP = new JLabel();
        preisStr = new JLabel();
        buttonVerk = new JButton();
        burronFeilschen = new JButton();
        preisMP = new JLabel();
        label1 = new JLabel();

        //======== mainFrame ========
        {
            mainFrame.setVisible(true);
            var mainFrameContentPane = mainFrame.getContentPane();
            mainFrameContentPane.setLayout(null);

            //---- label2 ----
            label2.setText("Health Potion");
            label2.setIcon(new ImageIcon(getClass().getResource("/potions/flask_red.png")));
            mainFrameContentPane.add(label2);
            label2.setBounds(135, 0, 135, 90);

            //---- label3 ----
            label3.setText("Strengthorb");
            label3.setIcon(new ImageIcon(getClass().getResource("/buffEntity/StrengthbuffOrb.png")));
            mainFrameContentPane.add(label3);
            label3.setBounds(270, 0, 130, 90);

            //---- anzahlMP ----
            anzahlMP.setText(String.valueOf(quantityMP));
            mainFrameContentPane.add(anzahlMP);
            anzahlMP.setBounds(50, 55, 35, anzahlMP.getPreferredSize().height);

            //---- anzahlHP ----
            anzahlHP.setText(String.valueOf(quantityHP));
            mainFrameContentPane.add(anzahlHP);
            anzahlHP.setBounds(155, 55, 35, 16);

            //---- anzahlStr ----
            anzahlStr.setText(String.valueOf(quantityStr));
            mainFrameContentPane.add(anzahlStr);
            anzahlStr.setBounds(290, 55, 35, 16);
            mainFrameContentPane.add(tfKaufen);
            tfKaufen.setBounds(30, 110, 325, 20);
            mainFrameContentPane.add(tfFeilschen);
            tfFeilschen.setBounds(30, 185, 325, 20);
            mainFrameContentPane.add(tfVerkaufen);
            tfVerkaufen.setBounds(30, 145, 325, 20);

            //---- kaufen ----
            kaufen.setText("Kaufen");
            mainFrameContentPane.add(kaufen);
            kaufen.setBounds(new Rectangle(new Point(30, 90), kaufen.getPreferredSize()));

            //---- verkaufen ----
            verkaufen.setText("Verkaufen");
            mainFrameContentPane.add(verkaufen);
            verkaufen.setBounds(30, 130, 70, 16);

            //---- feilschen ----
            feilschen.setText("Feilschen");
            mainFrameContentPane.add(feilschen);
            feilschen.setBounds(30, 170, 70, 16);

            //---- buttonKaufen ----
            buttonKaufen.setText("Kaufen");
            mainFrameContentPane.add(buttonKaufen);
            buttonKaufen.setBounds(35, 220, 100, 25);
            mainFrameContentPane.add(preisHP);
            preisHP.setBounds(140, 20, 85, 16);
            mainFrameContentPane.add(preisStr);
            preisStr.setBounds(270, 20, 85, 16);

            //---- buttonVerk ----
            buttonVerk.setText("Verkaufen");
            mainFrameContentPane.add(buttonVerk);
            buttonVerk.setBounds(135, 220, 125, 25);

            //---- burronFeilschen ----
            burronFeilschen.setText("Feilschen");
            mainFrameContentPane.add(burronFeilschen);
            burronFeilschen.setBounds(260, 220, 93, 25);
            mainFrameContentPane.add(preisMP);
            preisMP.setBounds(35, 20, 85, 16);

            //---- label1 ----
            label1.setText("MP Potion");
            label1.setIcon(new ImageIcon(getClass().getResource("/potions/flask_blue.png")));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            mainFrameContentPane.add(label1);
            label1.setBounds(0, 0, 135, 90);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < mainFrameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = mainFrameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = mainFrameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                mainFrameContentPane.setMinimumSize(preferredSize);
                mainFrameContentPane.setPreferredSize(preferredSize);
            }
            mainFrame.pack();
            mainFrame.setLocation(300, 300);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        /**
         * actionListener für items kaufen
         */
        buttonKaufen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buy manapot
                if (tfKaufen.getText().toLowerCase().matches("(.*)\\s?\\bmanapotion\\b\\s?.*")
                    && quantityMP > 0 && hero.getMoney() >= priceMP) {

                    mp = new MPPot(hero.getPainter(), hero.getBatch());
                    quantityMP -= 1;
                    anzahlMP.setText(String.valueOf(quantityMP));
                    hero.getInventory().addItem(mp);
                    hero.setMoney(hero.getMoney() - priceMP);
                    setBoughtItem(mp);
                }
                //buy hppot
                if (tfKaufen.getText().toLowerCase().matches("(.*)\\s?\\bhealthpotion\\b\\s?.*")
                    && quantityHP > 0 && hero.getMoney() >= priceHP) {

                    hp = new HPPot(hero.getPainter(), hero.getBatch());
                    quantityHP -= 1;
                    anzahlHP.setText(String.valueOf(quantityHP));
                    hero.getInventory().addItem(hp);
                    hero.setMoney(hero.getMoney() - priceHP);
                    setBoughtItem(hp);
                }
                //buy strengthorb
                if (tfKaufen.getText().toLowerCase().matches("(.*)\\s?\\bstrengthorb\\b\\s?.*")
                    && quantityStr > 0 && hero.getMoney() >= priceStr) {

                    orb = new StrengthOrb(hero.getPainter(), hero.getBatch());
                    quantityStr -= 1;
                    anzahlStr.setText(String.valueOf(quantityStr));
                    hero.getInventory().addItem(orb);
                    hero.setMoney(hero.getMoney() - priceStr);
                    setBoughtItem(orb);
                }
                if (tfKaufen.getText().toLowerCase().matches("(.*)\\s?\\bpreis\\b\\s?.*")) {
                    preisMP.setText(priceMP + " Coins");
                    preisHP.setText(priceHP + " Coins");
                    preisStr.setText(priceStr + " Coins");

                }
            }
        });

        /**
         * actionlistener für item verkaufen
         */
        buttonVerk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //sell first item
                    if (tfVerkaufen.getText().toLowerCase().matches("(.*)\\s?\\beins\\b\\s?.*") ||
                        tfVerkaufen.getText().toLowerCase().matches("(.*)\\s?\\berstes\\b\\s?.*")) {
                        hero.getInventory().equipItem(0);

                        if (hero.getInventory().getFirstItem() != null) {
                            hero.getInventory().dropItem(0);
                            hero.setMoney(hero.getMoney() + 5);
                        }

                    }
                    //sell second item
                    if (tfVerkaufen.getText().toLowerCase().matches("(.*)\\s?\\bzwei\\b\\s?.*") ||
                        tfVerkaufen.getText().toLowerCase().matches("(.*)\\s?\\bzweites\\b\\s?.*")) {
                        hero.getInventory().equipItem(1);

                        if (hero.getInventory().getSecondItem() != null) {
                            hero.getInventory().dropItem(1);
                            hero.setMoney(hero.getMoney() + 5);
                        }

                    }
                    //sell third item
                    if (tfVerkaufen.getText().toLowerCase().matches("(.*)\\s?\\bdrei\\b\\s?.*") ||
                        tfVerkaufen.getText().toLowerCase().matches("(.*)\\s?\\bdrittes\\b\\s?.*")) {
                        hero.getInventory().dropItem(2);

                        if (hero.getInventory().getThirdItem() != null) {
                            hero.getInventory().dropItem(2);
                            hero.setMoney(hero.getMoney() + 5);
                        }
                    }
                } catch (NullPointerException ignored) {
                    log.warning("Item nicht vorhanden");
                }
            }
        });

        /**
         * actionlistener für feilschen
         */
        burronFeilschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfFeilschen.getText().toLowerCase().matches("(.*)\\s?\\bfeilschen\\b\\s?.*")) {
                    if (priceStr == 1 && priceMP == 1 && priceHP == 1) {
                        priceHP = 2;
                        priceStr = 2;
                        priceMP = 2;
                    }
                    Random rand = new Random();
                    switch (rand.nextInt(2)) {
                        case 0 -> {
                            priceStr -= 1;
                            priceHP -= 1;
                            priceMP -= 1;
                        }
                        case 1 -> {
                            priceStr += 1;
                            priceHP += 1;
                            priceMP += 1;
                        }
                    }

                    preisMP.setText(priceMP + " Coins");
                    preisHP.setText(priceHP + " Coins");
                    preisStr.setText(priceStr + " Coins");
                }
            }
        });
    }

    /**
     * getter for last bought Item
     *
     * @return returns last bought Item
     */
    public Item getBoughtItem() {
        return boughtItem;
    }

    /**
     * setter for last bought Item
     *
     * @param boughtItem bought Item
     */
    public void setBoughtItem(Item boughtItem) {
        this.boughtItem = boughtItem;
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame mainFrame;
    private JLabel label2;
    private JLabel label3;
    private JLabel anzahlMP;
    private JLabel anzahlHP;
    private JLabel anzahlStr;
    private JTextField tfKaufen;
    private JTextField tfFeilschen;
    private JTextField tfVerkaufen;
    private JLabel kaufen;
    private JLabel verkaufen;
    private JLabel feilschen;
    private JButton buttonKaufen;
    private JLabel preisHP;
    private JLabel preisStr;
    private JButton buttonVerk;
    private JButton burronFeilschen;
    private JLabel preisMP;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
