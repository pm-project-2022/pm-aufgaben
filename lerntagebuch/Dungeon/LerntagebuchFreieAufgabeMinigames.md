---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben items, Inventar, Monster und Nahkampf"
author:
-   "Jan Rau (jan_niklas.rau@fh-bielefeld.de)"
-   "kenny-Joe Koch (kenny-joe.koch@fh-bielefeld.de)"
-   "Jan-Henrik Capsius (jan-henrik.capsius@fh-bielefeld.de)"
    hidden: true
---

# Aufgabe
In dieser Aufgabe ging es darum mehrere Minigames zu implementieren, die durch einen NPC ausgelöst werden. Hierzu braucht man eine bestimmte Anzahl an Coins,
welche man als "Eintritt" in das Minigame bezahlt. Hat man zu wenig Coins, dann wird auch nichts ausgeführt. Die Anzahl der Coins wird auf dem HUD angezeigt.
Die Minigames, die implementiert werden sollen sind "Schere Stein Papier", "Hangman" und "Tic Tac Toe". TicTacToe soll ein Graphical User Interface 
bekommen, indem der User gegen den NPC spielt.

# Ansatz und Modellierung
Der User soll durch das Töten von Monstern Münzen bekommen, die er bei einem NPC gegen Minigames eintauschen kann. Wenn man gewinnt, dann bekommt man
mehr Münzen zurück als man eingezahlt hat. Der NPC steht zufällig irgendwo auf der Stage. Der NPC gibt die verschiedenen Minigame-Möglichkeiten vor
und der User darf sich dann eine davon auswählen.

# Umsetzung
Als erstes haben iwir uns an TicTacToe gesetzt. Dem User soll ein Spielfeld als Graphical User Interface angezeigt werden. Bei TicTacToe ist das ganz einfach,
nämlich ein 3x3 Gridlayout. Die Koordinaten werden in einem Zweidimensionalen Array gespeichert, also ist jeder Index [1,1] zB. Anfangs sind die Buttons Empty (""). 
Durch einen Klick werden sie gefüllt. Entweder mit "X" oder mit "O". Kommt darauf an, ob der User oder der NPC gerade am Zug ist. Nach jedem Klick wird gecheckt, ob 
es eine Reihe von 3 gleichen Symbolen gibt. Vertikal, Horizontal und Diagonal. Das kann man mit den Indizes des Arrays und dem Wert des Symbols prüfen.
Der bestmögliche Zug des NPCs wird dabei immer berechnet. Dieser Zug ist der, der den Gegner daran
hindert zu gewinnen. Jeder Button kann nur einmal gedrückt werden. Der jeweilige Zug von Spieler oder NPC wurde durch enums
realisiert. ONE ist der User und TWO ist der NPC. Bei keinem klaren Gewinner wird ein Unentschieden ausgelöst.
Schere Stein Papier war das nächste, das wir implementiert haben. Dazu gibt es nicht viel zu schreiben. Der User gibt entweder Schere,
Stein oder Papier ein. Wird etwas anderes eingegeben, dann erscheint die Aufforderung für die Einagebe nochmal. 
Der Computer sucht sich zufällig einen Wert zwischen 1 und 3. Durch einen switch-case wird dann der jeweilige Wert: Schere, Stein oder Papier gesetzt.
Danach wird jeder Fall, der auftreten kann verglichen. Gewonnen, Verlieren und Unentschieden.
Die letzte Aufgabe war Hangman. In dem Spiel wird ein Wort nach und nach durch Buchstaben erraten. Man hat eine bestimmt Anzahl an Fehlversuchen, bevor man das
Spiel verliert. Dazu habe ich eine .txt Datei mit zufällig generierten Wörtern erstellt und in ein Array geladen. Danach zählt eine while-Schleife
die Anzahl der Versuche. In der while-Schleife findet auch die Abfrage für die Eingabe statt. Das Wort, was gesucht wird, wird mit * dargestellt.
Wird ein Buchstabe richtig erraten, dann wird das * durch den jeweiligen Buchstaben ersetzt. Wird falsch geraten, dann erscheint ein Teil einer
Grafik. Werden alle Buchstaben richtig erraten, dann gewinnt man, hängt das Männchen, verliert man. 

# Postmortem
Wirkliche Probleme gab es nicht. Nur kleinere Probleme, wie das zurücksetzen, nachdem man ein Spiel zueende gespielt hat.
Das konnte aber halbwegs schnell behoben werden.
