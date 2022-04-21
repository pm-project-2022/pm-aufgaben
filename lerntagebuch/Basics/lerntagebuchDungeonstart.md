---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben Dungeon-Start"
author:
-   "Jan Rau (jan_niklas.rau@fh-bielefeld.de)"

-   "kenny-Joe Koch (kenny-joe.koch@fh-bielefeld.de)"

-   "Jan-Henrik Capsius (jan-henrik.capsius@fh-bielefeld.de)"
hidden: true
---

# Aufgabe

In dieser Aufgabe ging es darum ein läuffähiges Grundgesetz des Dungeons aufzusetzen, Lauf und Idle-Animationen für den Helden zu integrieren und Texturen für Spielfiguren, Monster und Items zu beschaffen.


# Ansatz und Modellierung

Bevor die die Animationen für den Helden integriert werden konnten, musste zuerst ein Grundgerüst des Spiels sowie der Held an sich implementiert werden.


# Umsetzung

Um den Dungeon aufzusetzen haben wir den Quickstart-Guide für den Dungeon benutzt. Dort wurde detailiert beschrieben wie man das Grundgerüst startet, wie man einen Helden erstellt, diesen mit einem Sprite und Idle-Animationen versieht, ihn dem Spiel hinzufügt und bewegen kann.   

Nachdem das geschafft war, haben wir uns überlegt wie der Held zwischen Idle-Animation und Laufanimation wechselt.   
Wir sind zu dem Schluss gekommen, dass wir zusätzlich zu dem im Tutorial angelegten Animationattribute `idleAnimation` weitere Animationsattribute erstellt. Diese werden wie die idleAnimation per zugehöriger Methode initialisert. Diese Methoden werden im Kontruktor `MyHero` aufgerufen.  
Zusätzlich dazu haben wir die beiden Boolean-Attribute `movementState` und `viewDirection` erstellt. Diese sollen entscheiden, welche Animation verwendet werden so.   
Mit der Methode `private boolean keyPressed()` lassen wir uns zurückgeben ob eine Taste gedrückt wird oder nicht. Diese wird in einer if-Abfrage in der Methode `public void update()` aufgerufen und entscheided ob der `movementState` true oder false ist. Sollte `movementState` true sein, wird überprüft welche Taste gedrückt wurde und der Charakter bewegt sich in die jeweilige Richtung. Sollte die `A-` oder `D-Taste` gedrückt werden, ändert sich auch die `viewDirection`. Die Taste `A` setzt die `viewDirection` auf false und der Charakter guckt und läuft nach links. Die Taste `D` setzt die `viewDirection` auf true und der Charakter guckt und läuft nach rechts. Anschließend wird noch überprüft ob man die neue Position eine gültige ist und betreten werden kann.  
Mit der Methode `public Animation getActiveAnimation()` und den beiden States geben wir an, welche Helden-Animation gerendert werden soll.


# Postmortem

Die Aufgabe wurde größtenteils problemlos bearbeiten. Es gab lediglich ein kleines Problem bei dem Rendern der richtigen Animation, aber das beruhte lediglich auf einer falschen Zuweisung der State-Attribute in der `public void update()`-Methode. Mit Hilfe eines Loggers konnte man gut erkennen bei welcher Taste das Problem auftauchte und lies sich daher schnell beheben.
