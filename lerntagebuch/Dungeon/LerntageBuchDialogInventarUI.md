---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben items, Inventar, Monster und Nahkampf"
author:
-   "Jan Rau (jan_niklas.rau@fh-bielefeld.de)"
-   "kenny-Joe Koch (kenny-joe.koch@fh-bielefeld.de)"
-   "Jan-Henrik Capsius (jan-henrik.capsius@fh-bielefeld.de)"
hidden: true
---

# Aufgabe
Diese Woche ging es darum ein Dialogsystem und die Grafische Oberfläche für das Inventar zu implementieren. Das Dialogsystem soll aus zwei Rätseln
und einem Tipp bestehen. Die Lösung des Rätsels wird mit Regex abgefragt. Das Wort muss also in einem Satz vorkommen, egal wie der Satz gestellt ist.
Die Items des Inventars sollen grafisch dargestellt werden und durch einen Button können sie gedroppt/zerstört werden.

# Ansatz und Modellierung

Das Dialogsystem und Inventar soll mit Swing umgesetzt werden. Für die beiden haben wir vorher geplant, welches Layout wir benutzen sollen
und welche Komponenten die Gui haben soll.

# Umsetzung

Als erstes haben wir das Dialogsystem umgesetzt. Dafür habe ich die Funktion eines bereits existierenden NPCs umgeschrieben. Spricht man 
mit dem NPC, dann erscheint eine GUI. Diese besteht aus einem Label, was den aktuellen Status anzeigt. Zum Beispiel das aktuelle Rätsel
oder den Tipp, den man von ihm bekommen kann oder auch seine Ausgangsfrage, wenn man ihn anspricht. Das Textfeld ist dafür da, dass man 
seine jeweiligen Antworten eingeben kann. Einmal für die Auswahl ob Rätsel oder Tipp und einmal für die Antwortmöglichkeiten auf die Rätsel.
Die Antworten werden alle durch ein Regex abgefragt. Dabei kann der Satz egal wie aufgebaut sein. Das wichtigste ist, dass das Wort enthalten
ist. Zum Beispiel "Raetsel". Bei dem Raetsel wird also genau auf die gleiche Weise geprüft. Ist die Antwort richtig, dann ändert das Label 
mit dem Rätsel den Text zu "Richtig" und ist sie falsch, dann zu "Falsch". 

Das Inventar besteht auch aus einer Swing GUI. Da haben wir zuerst eine Methode hinzugefügt, die den Texturenpfad der einzelnen Items holt.
Dieser Texturenpfad wird dann als Icon in ein JLabel geladen, damit die Items grafisch dargestellt werden können. Zudem haben wir noch drei
Buttons implementiert. Jeder Button ist zum droppen eines Items da. Hierbei wird die dropItem Methode mit einem Index aufgerufen.
Der Index ist durch die Zahl des Buttons bestimmt.


# Postmortem

Größtenteils gab es keine Probleme bei der Implementierung.
