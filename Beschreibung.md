## Algorithmen Hausaufgabe 11
### Gruppe 27 Christian Thelen, Laura Mey, Josha Bartsch
#### Schriftlicher Teil (Aufgabe 1a)

#### Welche zusätzlichen Informationen müssen an den Knoten des Suchbaums gespeichert werden?

An den Knoten des Suchbaums sollte zusätzlich die Summe aller Unterknoten sowie die Anzahl aller Unterknoten gespeichert werden. Daraus kann dann, wenn der Knoten (die Wurzel des Unterbaums) bekannt ist, einfach über Summe/Anzahl der Mittelwert berechet werden. 

#### Was muss beim Einfügen und Löschen von Knoten beachtet werden, damit die Information immer korrekt ist?

Wird ein neuer Knoten erzeugt, so sollten seine Attribute

* branchSize mit 1
* branchSum mit seinem Wert

initialisiert werden.

#### Beschreiben Sie die Lösch- und Einfügeoperation schriftlich als Text

Einfüge- und Löschoperationen gehen immer von einer definierten Wurzel aus, in der vorliegenden Implementation ist dies immer die Wurzel des BST. Sie traversieren iterativ oder rekursiv den Baum bis sie an den Punkt kommen an dem der Knoten liegen muss, jeder Knoten der nicht dem Wert entspricht bzw nicht leer ist wird "passiert": Er wird normalerweise nicht beachtet, er bildet aber immer eine Wurzel für den betrachteten Knoten.

Wird unser Knoten jetzt in einen bestehenden binären Suchbaum eingefügt, so muss für jeden Knoten der "passiert" wird die Anzahl seiner Unterknoten um 1 und die Summe seiner Unterknoten um den Wert des einzusetzenden Knotens erhöht werden.

Analog verläuft eine Löschoperation:  
Wird der Knoten aus einem binären Suchbaum entfernt, so muss für jeden Knoten der "passiert" wird die Anzahl seiner Unterknoten um 1 und die Summe seiner Unterknoten um den Wert des einzusetzenden Knotens reduziert werden.
Die weitere Löschoperation, sobald der fragliche Knoten erreicht wurde ist unverändert.

Die vorgegebenen iterativen Lösch- und Einfügeoperationen werden somit in ihrer Laufzeit nicht verändert.