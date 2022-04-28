---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben items, Inventar, Monster und Nahkampf"
author:
-   "Jan Rau (jan_niklas.rau@fh-bielefeld.de)"
-   "kenny-Joe Koch (kenny-joe.koch@fh-bielefeld.de)"
-   "Jan-Henrik Capsius (jan-henrik.capsius@fh-bielefeld.de)"
hidden: true
---

# Aufgabe

In dieser Aufgabe ging es darum ein lauffähiges textbasiertes Inventar für den Helden des Dungeons aufzusetzen. Davor müssen Items implementiert werden, die verwendet, ausgerüstet, weggeworfen und grafisch dargestellt werden können. Mindestens 3 Itemgruppen mit jeweils 2 konkreten Items.
Zusätzlich ein HUD für den Anfang erstellen.  
Außerdem wurden verschiedene Monster mit verschiedenen Eigenschaften implementiert, die sich mit verschiedenen Movement-Behaviour durch den Dungeon bewegen. Außerdem werden bei jedem neuen Levelload ein neue verschiedene Monster gespawned. Zu guter letzt galt es ein rudimentäres Nahkampfsystem zu schaden. Dabei kommt es zum Kampf wenn sich ein Monster und der Held dem selben Feld befinden. Dabei sollen die Trefferchance und der Schaden ermittelt werden. Bei erfolgreichen Treffer wird das Monster, sofern möglich, nach hinten geschleudert. 

# Ansatz und Modellierung

Bevor wir die Items, Monster oder das Inventar implementiert habe, haben wir uns eine Klassenstruktur überlegt. Die Struktur besteht hauptsächlich aus Vererbung an die Subitems und Monster. Das Inventar besteht aus einem einfachen Array. Das HUD besteht aus mehreren Klassen, die die Texturen der Komponenten speichern.

# Umsetzung

Um die Items zu implementieren habe ich als erstes die Oberklasse `Item` erstellt und passende Attribute hinzugefügt. Diese erbt von `Animatable` für späteren Nutzen. Da das Item an dem Helden gerendert werden soll, habe ich die Position des Items gleich die Position des Helden gesetzt. Die Variable `public float xOffset` ist dafür da, dass das Item nicht direkt auf dem Helden, sondern ein wenig neben dem Helden gerendert wird. Der Held hat deshalb eine neue Methode `public boolean lookingLeft` bekommen, die zurückgibt, ob er nach links oder rechts guckt, damit das Item auf der linken oder rechten Seite erscheint. Die nächste Aufgabe war es, die Sprites der Items zu wechseln, wenn eine bestimmt Taste (Y, X, C) gedrückt wird. Die Anzahl der Tasten repräsentiert gleichzeitig die Größe des Inventars. Um das umzusetzen habe ich die überschriebene Methode `public boolean removable` benutzt. Diese gibt zurück, ob ein Item sichtbar ist oder nicht. Auf Knopfdruck wird ein Item sichtbar und das Item, welches davor sichtbar war, wird unsichtbar. So wechseln sich die Items auf dem Bildschirm aus und überlappen nicht. Die verschiedenen Items können auch benutzt werden. Hat man einen Heiltrank ausgerüstet und drückt "E", dann benutzt man das Item und die HP des Helden werden erhöht. Damit der benutzte Trank entfernt wird, wird wieder die Methode `public boolean removable`. Um Items in dem Dungeon erscheinen zu lassen, habe ich eine neue Klasse `DungeonLoot` implementiert. Alle Items, die in dem Dungeon erscheinen sollen, erben von dieser Klasse. In dieser Klasse wird auch für jedes Item eine zufällige Position im Dungeon ausgewählt `getRandomFloorTile().getCoordinate().toPoint()`. In den init-Methoden werden Items aus einer Liste gewählt, die zuvor hinzugefügt worden sind` z.B. SpeedOrbList` und im aktuellen Level sichtbar gesetzt. Für das Interagieren mit Items in dem Dungeon habe ich eine Methode `private void onPickUp` implementiert. Diese Methode prüft, ob der Held auf dem gleichen Tile wie ein Item steht. Ist das der Fall, dann hat man die Möglichkeit mit der Taste "F" das Item aufzuheben. Wird das Item aufgehoben, dann wird es automatisch zu dem Inventar hinzugefügt und die Textur wird aus dem Dungeon entfernt. Das Inventar besteht aus den Methoden `public void add(Item item)`, welche ein Item in das Inventar hinzufügt, `public void removeItem(Item item)`, welche ein Item droppt/entfernt, `public void showInventory()`, welche das Inventar auf der Konsole ausgibt, `public void equipped()`, welche erlaubt Items auszurüsten. Mit den Tasten "1-6" können verschiedene Items zum Inventar hinzugefügt werden. "Y, X und C" rüsten die Items aus. "Q" entfernt das aktuelle Item aus dem Inventar und das Standarditem wird ausgerüstet.
Ist das Inventar voll und der Benutzer versucht ein neues Item hinzuzufügen/aufzuheben, dann wird durch einen Logger eine Warnung ausgegeben.
Die Implementierung des HUDs hat nur wenige Minuten gedauert, da es bis jetzt noch statisch ist und nicht aktualisiert wird. Mit Ausnahme des Dungeonlevels.
 

Die Erstellung der Monster fing mit der Implementierung einer allgemeinen Monsterklasse an, die die wichtigsten Attribute wie Animationen, Movement-Behaviour und Attribute des Monster enhält. Davon erben alle Monsterklassen die das Monster näher bestimmen (kleines Monster/normales Monster) und davon die finalen Monsterklassen. Diese enthalten die Methoden zum initialisieren der Animationen. Die Klassen `MonsterNameList` und `Monsterquatity` folgen der selben Struktur. Schlussendlich wurde eine Klasse `MonsterFactory` erstellt, die nach der `Factory-Method Pattern` implementiert wurde um mit dem nehmen der Monster diese zu erstellen.  

Da die Monster auch ein Bewegungsverhalten haben sollten, wurde zuerst das Interface `IMonsterbehaviour` angelegt. Dieses wurde von den Klassen `PatrolY` und `PatrolX` implementiert. Wie die Namen schon verraten, lassen die Klassen die Monster entlang Y oder X Achse patrollieren.   

Nachdem die Monster implementiert waren, wurde das rudimentäre Nahkampffeature in Angriff genommen. Dabei kommt es zu einem Kampf, wenn ein Monster und der Held den weg Kreuzen. Dabei wird die Klasse `Fight` mit der Methode `fight` aufgerufen und führt einen Angriff des Helden durch.  
Zuerst wird die Hitchance ermittelt. Hat der Held mehr `Accurancy` als das Monster `Evasion` hat wird der Schaden ermittelt. Hat der Held genausoviel oder weniger `Accurancy`, geht der Angriff nur zu 50%.  
In der Schadensberechnung wird ermittelt, ob der Held mehr `Strength` als das Monster `Defense`. Sollte das eintreten, wird der Schaden berechnet. Andernfalls macht der Held dem Monster per default lediglich 1 Schaden.  
Sollte der Angriff erfolgreich gewesen sein, wird das Monster zurückgeworfen (sofern möglich) und wechselt in das Idle-Behaviour. 

Wir haben am Anfang der Woche angefangen und daran jeden Tag für mehrere Stunden zu arbeiten. 


# Postmortem

Größtenteils gab es Probleme bei dem Wechseln der Item Texturen und dem Aufheben von Items aus dem Dungeon. Es gab viele Komplikationen mit der `private Item activeItem` Variable und der `public boolean removable` Methode. Oftmals haben sich die Texturen überlappt oder sie wurden nicht angezeigt, da der zurückgegebene Wert der `public boolean removable` Methode an vielen Stellen falsch war. Durch Variablen umsetzen und boolean-Werte gezielter setzen habe ich das Problem gelöst bekommen. Zudem musste ich noch das Problem mit den Dungeon Items und dem Inventar lösen, da das Inventar die Klasse `Item` als Datentyp hat und die Dungeon Items `DungeonLoot` als Klasse haben. Dieses Problem habe ich behoben, indem ich die aufgehobenen Items aus dem Dungeon entfernt und ein neues Objekt desselben Typs erstellt hab. Dieses neue Objekt habe ich folgend zu dem Inventar hinzugefügt.

Das Implementieren der Monster und des Kampfverhaltens war an sich relativ einfach wenn man erstmal verstanden hat, was die Library von einem will. Ab dem Punkt bedurfte es nur noch Fein-Tuning.

