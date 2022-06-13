package Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DialogGui extends JFrame{
    int currentCase;
    public void initGui(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel labelRiddle = new JLabel();
        JTextField tfAnswer = new JTextField();
        JButton buttonConfirm = new JButton("Confirm");
        JButton buttonConfirm2 = new JButton("Confirm");

        frame.setSize(400,300);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelRiddle);

        tfAnswer.setPreferredSize(new Dimension(800,40));
        tfAnswer.setMaximumSize(tfAnswer.getPreferredSize());

        buttonConfirm.setPreferredSize(new Dimension(370,40));
        buttonConfirm.setMaximumSize(buttonConfirm.getPreferredSize());

        buttonConfirm2.setPreferredSize(new Dimension(370,40));
        buttonConfirm2.setMaximumSize(buttonConfirm.getPreferredSize());

        panel.add(tfAnswer);
        panel.add(buttonConfirm);

        frame.setVisible(true);

        labelRiddle.setText("<html>Was moechtest du?<br/>Einen Tipp<br/>Ein Raetsel\n<html>");

        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfAnswer.getText().matches("(.*)\\s?\\bRaetsel\\b\\s?.*") ||
                    tfAnswer.getText().matches("(.*)\\s?\\braetsel\\b\\s?.*")){
                    tfAnswer.setText("");
                    Random random = new Random();
                    switch (random.nextInt(2)) {
                        case 0 -> {
                            labelRiddle.setText("""
                                <html>
                                Gehe fuenf Schritte nach vorne. Drehe dich 90 Grad nach rechts und gehe weitere fuenf Schritte vor.
                                Nun drehe dich ein weiteres Mal nach rechts und gehe 10 Schritte weiter.
                                Jetzt 90 Grad nach links und 3 Schritte nach vorne. Anschliessend ein weiteres Mal nach links und erneut 5 Schritte vor.
                                Zum Schluss ein letztes Mal nach links drehen und acht Schritte nach vorne laufen. Wo befindest du dich jetzt?

                                Was ist deine Antwort?<html>""");
                            panel.remove(buttonConfirm);
                            panel.add(buttonConfirm2);
                            setCurrentCase(0);
                        }
                        case 1 -> {
                            labelRiddle.setText("""
                                <html>
                                Drei Soehne zogen aus, ihr Glueck zu machen und jeder versuchte verschiedene Sachen.
                                Ihr weg dahin muendete in drei neue Pfade und jeder folgte einem in andere Gestade.

                                Der Mittlere Sohn nahm den linken Pfad, mit Traeumen von Reichtum im Kopfe parat.
                                Der Juengste nahm den Weg in der Mitte, ein Weib zu finden, war seine Bitte.
                                Des Aeltesten Wahl auf den rechten Weg fiel, ein wunderbar Haeuschen war dabei sein Ziel.

                                Ihre Wegen wanden sich eng und verschlungen, doch keiner der drei hat das Ersehnte errungen.
                                Um diesem Schicksal zu entgehen, solltest du auf ihr Alter nur sehen.

                                INFO: Vor dir sind drei Schalter. In welcher Reihenfolge betaetigst du sie?
                                Bsp: Links Mitte Rechts<html>""");
                            panel.remove(buttonConfirm);
                            panel.add(buttonConfirm2);
                            setCurrentCase(1);
                        }
                    }
                }
                if (tfAnswer.getText().matches("(.*)\\s?\\bTipp\\b\\s?.*") ||
                    tfAnswer.getText().matches("(.*)\\s?\\btipp\\b\\s?.*")){
                    panel.remove(buttonConfirm);
                    labelRiddle.setText("Auf jeder 5. Stage befindet sich eine Truhe");
                    tfAnswer.setEditable(false);
                }
            }
        });

        buttonConfirm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(buttonConfirm2);
                if(currentCase == 0){
                    if (tfAnswer.getText().matches("(.*)\\s?\\bAusgangspunkt\\b\\s?.*") ||
                        tfAnswer.getText().matches("(.*)\\s?\\bausgangspunkt\\b\\s?.*")) {
                        labelRiddle.setText("Richtig!");
                    } else {
                        labelRiddle.setText("Falsch!");
                    }
                }

                if(currentCase == 1){
                    if (tfAnswer.getText().matches("(.*)\\s?\\bMitte Links Rechts\\b\\s?.*") ||
                        tfAnswer.getText().matches("(.*)\\s?\\bmitte links rechts\\b\\s?.*")) {

                        labelRiddle.setText("Richtig!");
                    } else {
                        labelRiddle.setText("Falsch!");
                    }
                }
                tfAnswer.setEditable(false);
            }
        });
    }

    public void setCurrentCase(int currentCase){
        this.currentCase = currentCase;
    }

}
