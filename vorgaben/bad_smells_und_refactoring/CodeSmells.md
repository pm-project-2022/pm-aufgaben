# Welche Bad Smells können Sie hier identifizieren?

## Bikes
- Duplizierter Code. In den Klassen Brompton, EBike und Mountainbike werden jeweils die Attribute maxSpeed, rearGearsCount und frontGearsCount angelegt. Da jede dieser Klassen von Bike erbt, reicht es dort die Attribute anzulegen. Des selbe gilt für die Methode `public int getMaxSpeed`. Außerdem ist es sinnvoller die Methode "getGearsCount" in der Oberklasse zu implementieren anstatt in den Subklassen zu überschreiben. (Push Up) 

- Attribut nur für Subklasse relevant. Die Batteriekapazität der Fahrräder ist nur relevant für die Ebike, daher wird dort das Attribute `Integer batteryCapacity` angelegt und in den Kontruktor übernommen. (Push Down)
   
- Fehlende Kapselung. Die Attribute sind public. Zugriff erfolgt über Getter und Setter  (Encapsulate Field)

---
## Articles
- Fehlende Kapselung. Die Attribute sind public. Zugriff erfolgt über Setter und Getter.(Encapsulate Field) 

---
## Bill
-Single Responsibility. Eine Rechnung sollte nicht die um die Einzelheiten des Aufbaues der Kundendaten kümmern.
(Extract Method/Class und Move Field/Method)

---
## CustomerData
- Fehlende Kapselung. Die Attribute sind public. Zugriff erfolgt über Setter und Getter.(Encapsulate Field)