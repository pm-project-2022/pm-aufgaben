---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben items, Inventar, Monster und Nahkampf"
author:
-   "Jan Rau (jan_niklas.rau@fh-bielefeld.de)"
-   "kenny-Joe Koch (kenny-joe.koch@fh-bielefeld.de)"
-   "Jan-Henrik Capsius (jan-henrik.capsius@fh-bielefeld.de)"
hidden: true
---

# Aufgabe

Diese Woche haben wir ein Magie und Erfahrungspunktesystem entwickelt und zusätzlich ein Game-Over Szenario abgefangen. Das Magiesystem beinhaltetzwei Zauber, die jede Klasse nutzen kann um sich das überleben im Dungeon zu erleichtern. Das Erfahrungspunktesystem lässt durch das Töten von Monstern den Helden im Level steigen. Wird das Game-Over Szenario getriggert, wird der Spieler gefragt, ob er das Spiel neu starten möchte.
In diesen Aufgaben ging es darum Fallen zu implementieren, die dem Spieler Schaden zufügen, wenn er über sie drüber läuft. Dazu soll es noch Kisten in dem Dungeon geben, die ein Item enthalten. Zu den Kisten gibt es noch einen freundlichen NPC im Dungeon, der dem Spieler ein zufällig rumliegendes Item bringt. Die Aufgabe danach war es ein dynamisches HUD zu implementieren, welches alle relevanten Informationen darstellt.

# Ansatz und Modellierung

Da wir bereits ein Attribut-System und Items implementiert haben, konnten wir dieses in Verbindung mit Vererbung nutzen um um ein Magie und Erfahrungspunktesystem zu realisieren und das Game-Over Szenario zu triggern. 
Bevor wir die ganzen Aufgaben implementiert haben, haben wir uns eine Struktur überlegt, wie man die Dinge im Dungeon verteilen kann. Dies haben wir dann mit verschiedenen Factorys gemacht.

# Umsetzung

Da ein Attribut-System schon vorhanden war, mussten wir bei dem Tod eines Monsters lediglich die Erfahrungspunkte zu dem Hero transportieren. Bei einem Levelaufstieg werden die Erfahrungpunkte zurückgesetzt, die Basisattribute erhöht und ein neues Exp-Cap für das nächste Level gesetzt. Wenn die Lebenspunkte des Charakters auf 0 sinken, wird das Game-Over-Szenario getriggert. Dabei steht quasi der Dungeon still und ein neues Spiel kann durch drücken der "r"-Taste gestartet werden. Da wird Items schon implementiert hatten, basiert unser Magiesystem auf magische Items. Diese kann mal als Loot finden und wenn man genügend aktuelles Mana hat, kann man diese einsetzen. 
Als erstes haben wir die Fallen implementiert. Die Falle wird aktiviert, wenn der Spieler auf ihr steht und bleibt danach auch. Um dem Spieler zu schaden, werden die Positionen von der Falle und dem Spieler verglichen. Steht der Spieler drauf, dann werden die HP mit einer set-Methode verringert.
Die Fallen haben wir mit einer Trapfactory zufällig im Dungeon verteilt.
Die Kisten funktionieren nach dem gleichen Prinzip. Nur, dass die in jedem fünften Level spawnen und dem Spieler nicht schaden. Jede Kiste hält ein Item und kann mit „T“ geöffnet werden. Die Kiste verschwindet und ein Item erscheint.
Der NPC erscheint auf die gleiche Weise im Dungeon. Mit „G“ aktiviert man seine Funktion. Ein Item verschwindet vom Boden und er gibt es dem Spieler in sein Inventar.
Das HUD wird dauerhaft geupdated und trackt die stats vom Spieler.


# Postmortem

Größtenteils gab es dabei keine Probleme, da unsere Arbeits-Basis nach dem Overhaul sehr solide ist und leicht erweiterbar ist.

