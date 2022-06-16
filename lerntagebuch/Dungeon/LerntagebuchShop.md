---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben items, Inventar, Monster und Nahkampf"
author:
-   "Jan Rau (jan_niklas.rau@fh-bielefeld.de)"
-   "kenny-Joe Koch (kenny-joe.koch@fh-bielefeld.de)"
-   "Jan-Henrik Capsius (jan-henrik.capsius@fh-bielefeld.de)"
hidden: true
---

# Aufgabe
Diese Woche ging es darum ein Shop mit einer grafischen Oberfläche in dem Dungeon zu implementieren. Dafür soll es eine Art Währungssystem geben.
Zudem sollen die Käufe und Verkäufe per Regex stattfinden und das Kaufen soll teurer sein als das Verkaufen an Münzen bringt.

# Ansatz und Modellierung

Der Shop soll mit Swing umgesetzt werden. Dafür haben wir uns als erstes die Anforderungen an den Shop angeguckt und 
danach das Layout nach den Anforderungen umgesetzt. Für jede Option (Kaufen, Verkaufen, Feilschen) soll ein Textfeld existieren.
Es sollen drei Buttons für jedes Textfeld existieren und Labels um die Informationen darzustellen.

# Umsetzung

Als erstes haben wir die Grafische Oberfläche mit Swing umgesetzt. Zuerst wurden alle nötigen Komponenten deklariert und initialisiert. 
Die Komponente wurden dann mit den bestimmten Positionen auf dem Panel positioniert. Die Labels und Items sind ganz oben, die Textfelder für 
das Regex sind in der Mitte und die Buttons für das Kaufen, verkaufen und feilschen sind ganz unten. Der Preis der Items wird durch ein Regex
angezeigt. Ist das Wort "Preis" in dem Satz vorhanden, dann erscheint der Preis jedes Items auf den Labels. Die Icons von den Items
werden in der Mitte des Labels gesetzt. Die Abfrage für den Preis findet in dem "Kaufen"-Textfeld statt. In diesem Textfeld werden 
auch die Namen der Items erwartet. Erscheint ein Name im Textfeld und der "Kaufen"-Button wird gedrückt, dann wird geprüft, ob der User
genug Münzen hat. Ist das der Fall, dann benutzt der User diese Münzen um sich ein Item zu kaufen und das Item erscheint in seinem Inventar. Zudem
wird die Anzahl des Items im Shop um 1 verringert. Beim Verkauf muss man die Position des Items angeben, was man verkaufen will. Jedes verkaufte Item
gibt 5 Münzen. Beim Verkauf wird einfach die Drop-Methode aufgerufen, die das Item aus dem Inventar wirft. Das droppen wird auch durch
ein Regex aufgerufen. Beim Feilschen besteht eine 50% Chance, dass die Items im Shop teurer oder billiger werden. Hat man Glück, dann werden
die Items um eine Münze billiger. Andersrum werden sie um eine Münze teurer. Das Feilschen erfolgt auch über ein Regex


# Postmortem

Probleme gab es beim Anzeigen der Items, nach dem Kauf, da sie zu dem Entitycontroller hinzugefügt werden müssen. Um das zu lösen
haben wir eine updateShop-Methode implementiert, die nach jedem Kauf das zuletzt gekaufte Item zu dem Entitycontroller hinzufügt.
Bei der Gui gab es keine Probleme.
