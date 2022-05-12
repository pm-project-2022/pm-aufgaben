---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben items, Inventar, Monster und Nahkampf"
author:
-   "Jan Rau (jan_niklas.rau@fh-bielefeld.de)"
-   "kenny-Joe Koch (kenny-joe.koch@fh-bielefeld.de)"
-   "Jan-Henrik Capsius (jan-henrik.capsius@fh-bielefeld.de)"
    hidden: true
---

# Aufgabe
In dieser Aufgabe ging es darum, ein Graphical User Interface für die Auswahl einer Charakterklasse
zu erstellen. Dieses GUI soll die Texturen der Charakterklassen, die Stats als Text
und die Stats als Diagramm zeigen. Zudem soll ein Soundsystem implementiert werden. Jeder Aktion wird also
ein Sound zugewiesen.

# Ansatz und Modellierung
Da wir bereits ein kleines Graphical User Interface geschrieben hatten, haben wir dieses
komplett überarbeitet. Davor haben wir uns aber erst ein Layout ausgedacht, wie es später
aussehen sollte. Die Sounds, die benutzt werden sollen haben wir aus dem Internet.
Diese wurden frei zur Verfügung gestellt.

# Umsetzung
Der erste Schritt war es das Layout für die MainGUI umzusetzen. Dafür haben wir uns ein
Bild von einem Pixelwald geholt. Mit Gimp haben wir dann die Texturen der Charakterklassen
eingefügt und auf eine bestimmt Größe angepasst. Zunächst habe ich das mainGUI Layout mit Swing
implementiert. Das Layout besteht nun aus den Texturen in dem Pixelwald und Buttons
um sich eine Charakterklasse auszuwählen. Zudem haben wir uns noch passende Beschreibungen
für die Charakterklassen ausgedacht und diese dann auch implementiert. Der nächste Schritt war 
es für jede Charakterklasse ein eigenes Fenster zu erstellen. Zuerst war der Knight dran.
Diese Fenster wurden auch wie die GUI mit Swing gemacht. Jedes Unterfenster besteht aus der Textur
der Charakterklasse in dem Wald, einer Stat-Beschreibung in Textform und einer 
Stat-Beschreibung in Diagrammform. Die gleiche Vorgehensweise gilt auch für die Fenster von
dem Wizard und dem Hunter. Anders sind nur die Stats und die Textur. Der Kern bleibt bestehen.
Die Unterfenster haben jeweils einen Confirm-Button und einen Decline-Button. Der Confirm-Button lässt
den Charakter im Dungeon spawnen, der Decline-Button bringt den User zurück in die mainGUI.
Wird ein Charakter ausgesucht, dann wird ein Wert in Verbindung zu dem Charakter gesetzt. Knight hat 
den Wert 1, Wizard den Wert 2 und Hunter hat den Wert 3. In MyGame wird so lange auf eine Eingabe gewartet und
prüft nach der Eingabe den übergebenen Wert. Stimmt der Wert mit einem Charakter überein, dann wird der
Charakter auch im Dungeon erscheinen.

Die Sounds haben wir mit Hilfe der LibGDX Sound-Funktion implementiert.
Als erstes wurden alle Aktionen, die in dem Dungeon vorhanden sind gesucht und 
jeder Aktion wurde ein Sound zugewiesen. Da das Spiel aber auf 30 FPS läuft und manche Sounds,
wie z.B. das Gehen nicht 30 mal pro Sekunde ausgeführt werden sollen, haben wir uns eine 
Methode überlegt, um die Frames zu zählen. Diese Anzahl der Frames haben wir dann mit dem 
modulo-Operator verknüpft. So kann man sagen, dass ein Sound nur alle 0,5 Sek etc. ausgeführt werden soll.

# Postmortem
Wirkliche Probleme gab es nicht, die Implementierung des GUI hat nur sehr viel
Zeit gekostet, da alles angepasst werden musste. Zudem kam dann noch die Bildbearbeitung,

