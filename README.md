# WebEngineering Module, Graded Exercise

## Commit Proposal

Matriculation Number: 10082269

Project idea short description:
Meine Idee ist inspiriert von https://nomadlist.com/. Gleich wie bei Nomadlist möchte ich eine solche Auflistung machen,
bei mir aber zum Thema Arbeiten in Cafés. Die Seite listet dann die besten Cafés auf, in welchen man arbeiten kann. 
Dann werde ich 3 Städte anbieten: Zürich, Basel und Bern, welche man selektieren kann und dann nur Cafés in der selektierten
Stadt angezeigt werden. Weiter soll man als normaler User neue Cafés erfassen können und als Admin kann man die Cafés 
dann zusätzlich auch wieder löschen. Wenn die Zeit reicht, möchte ich zusätzlich ein Rating für die einzelnen Cafés hinzufügen,
mit welchem man dem Café einen Score von 1-5 geben kann. Es wird dann ein Average Score ausgerechnet und für das spezifische Café angezeigt.


## Project confirmation

Eine interessante Idee. Sie können anfangen. Viel Erfolg!


## Project delivery <to be filled by student>

How to start the project: `./mvnw spring-boot:run` oder direkt via IntelliJ

How to test the project: `./mvnw verify` oder direkt via IntelliJ

Hand-written, static HTML 
project description: `index.html` in project root directory

External contributions: 
- Using HtmlUnitDriver found on Stackoverflow in `IndexIT`: https://stackoverflow.com/questions/7926246/why-doesnt-htmlunitdriver-execute-javascript/10810612#10810612
- Using axios library in `deletePlace.js` for sending delete: https://github.com/axios/axios
- LoginPage and LoginController implementation inspired from security-example in school: https://github.com/WebEngineering-FHNW/contact-list-security-netopyr/blob/master/src/main/java/ch/fhnw/webec/contactlistsecurity/controller/LoginController.java


Other comments: 

I'm particular proud of:
- **Design:** Das Design habe ich zuerst in Figma geplant und dann in Htlm und css umgesetzt. Schlussendlich war das Design relativ zeitaufwendig, ich bin aber zufrieden mit dem Resultat und hoffe deshalb auck auf die Artisic Value Punkte.
- **Average Rating:** Ich musste eine Methode finden, damit ich anstatt nur 1 Rating anzuzeigen, ein Average Rating für jedes Kriterium anzeigen kann. Da habe mich dafür entschieden im Service eine Methode zu schreiben, welche diese Averages berechnet und dann im Controller auf der Place Entity das Value setzt. In einer weiteren Version könnte man dies vielleicht direkt auf Entity level oder im Repository mit etwas custom SQL berechnen.
- **Javascript Rating update and delete:** Damit ich für den Rating Input eine Zahl anzeigen konnte, brauchte ich etwas custom javascript. Zudem habe ich das DELETE vom einem Place mit der axios library gelöst. Eine Herausforderung dort war zudem einen Integration test zu schreiben, welcher mit Javascript funktioniert.
- **Verschachtelte OneToMany Beziehungen:** City kann mehrere Places haben und Places können mehrere Ratings haben. Da wir in der Schule nur Beispiele mit einem OneToMany hatten, fand ich es schwierig zu verstehen, wie ich einer City einen Place hinzufügen kann. Schlussendlich habe ich aber eine Lösung gefunden, welche gut funktioniert.

## Project grading 

Die Datei index.html ist vorhanden, sehr gut ausgearbeitet und besteht aus validem HTML.

Die Applikation funktioniert.

Sie haben eine interaktive CRUD-Applikation mit drei Domainklassen, Custom-Styling, Input-Validierung und Security geschrieben.

Die Tests sind sauber geschrieben und ausführlich. Idealerweise hätten Sie noch Tests für Spezialfälle hinzufügen können (z.B. für den fall, dass CityRepository.findFirstByKey() keine City findet).

Die Kommentare an den Java-Klassen hätten Sie besser als JavaDocs verfasst inkl. Beschreibung der Parameter. Dann lässt sich auch einfach eine Dokumentation Ihrer API erzeugen.

Das Hinzufügen der Axios-Library wäre meiner Meinung nicht nötig gewesen. Der fetch-Befehl hätte ausgereicht.

Extrapunkte habe ich keine vergeben, da Sie bereits die volle Punktzahl erreicht haben. (Das Design hätte natürlich einige Extrapunkte bekommen.) ;)

Herzlichen Glückwunsch!
Sie haben in einem recht knappen Zeitrahmen eine voll funktionierende, verteilte, interaktive Mehrbenutzerapplikation erstellt inklusive einer Datenbankanbindung und ausgiebigen Tests.
Damit haben Sie solide Kenntnisse von WebMVC und Engineering-Praktiken gezeigt.
Ein beeindruckendes Ergebnis!

Grade: 6.0
