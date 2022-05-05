# Aufgabe

In diesen Aufgaben ging es darum Fallen zu implementieren, 
die dem Spieler Schaden zufügen, wenn er über sie drüber läuft. Dazu soll es noch Kisten in dem Dungeon geben,
die ein Item enthalten. Zu den Kisten gibt es noch einen freundlichen NPC im Dungeon, der dem Spieler ein zufällig rumliegendes Item bringt. 
Die Aufgabe danach war es ein dynamisches HUD zu implementieren, welches alle relevanten Informationen darstellt.

# Ansatz und Modellierung

Bevor wir die ganzen Aufgaben implementiert haben, 
haben wir uns eine Struktur überlegt, wie man die Dinge im Dungeon verteilen kann. 
Dies haben wir dann mit verschiedenen Factorys gemacht.

# Umsetzung

Als erstes haben wir die Fallen implementiert. Die Falle wird aktiviert, 
wenn der Spieler auf ihr steht und bleibt danach auch. Um dem Spieler zu schaden, 
werden die Positionen von der Falle und dem Spieler verglichen. Steht der Spieler drauf, 
dann werden die HP mit einer set-Methode verringert.
Die Fallen haben wir mit einer Trapfactory zufällig im Dungeon verteilt.
Die Kisten funktionieren nach dem gleichen Prinzip. Nur, dass die in jedem fünften Level spawnen und dem Spieler nicht schaden. 
Jede Kiste hält ein Item und kann mit „T“ geöffnet werden. Die Kiste verschwindet und ein Item erscheint.
Der NPC erscheint auf die gleiche Weise im Dungeon. Mit „G“ aktiviert man seine Funktion. 
Ein Item verschwindet vom Boden und er gibt es dem Spieler in sein Inventar.
Das HUD wird dauerhaft geupdated und trackt die stats vom Spieler.


# Postmortem

Größtenteils gab es keine Probleme. Nach Optimierung des Codes konnte man schnell die Aufgaben erledigen. 
