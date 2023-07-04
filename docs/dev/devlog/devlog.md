# Entwicklungsprotokoll

## Anfordungsanalyse

- Das Programm soll aus Vorgefertigten (anpassbaren) snipseln ein LaTeX document erstellen
- ~~Generierung neuer schnippsel durch ChatGPT~~ (verworfen)
- Die Schnipssel werden in einer MySQL Datenbank verwaltet, diese sollte sich zu einer CSV exportieren lassen
- Andere einstellungen sollten in eine JSon verwaltet werden.
- Das Programm solle universell einsetzbar sein (Klausuren also nur ein möglicher Verwendungzweck)
- Grafische Oberfläche für den gemeinen Nutzer
- CLI-schnittstelle (Gewährleisted Automatisierung via cron)
- Detailierte Anwenderdokumentation(GitHub, manpage)

## Archtekturentwurf

TODO: Diagramme einfügen

## Entwurf der Benutzerschnittstelle

TODO: jpg einfügen

## Gesprächsprotokolle

### 1ste Besprechung

Es wurden unsere Ideen und Ansätze bezüglich des Projektes vorgestellt,
und der Meilensteilplan Präsentiert.
Diese wurden alle gut aufgeneommen.
Im abschluss wurde noch ein themenwunsch für eine evtl. Vorlesung vogeschlagen: JavaFX.

### 2te Besprechung

Es wurde der aktuelle Fortschritt bezüglich des Projektes, sowie Probleme vorgestellt.
Probleme mit den JUnit-tests konnten nach einem Vorschlag ignoriert werden (Diese werden daher auch nicht weiter erwähnt).

## Implementierung (Probleme und Lösungen)

### pfdlatex macht absolut nichts

#### Beschreibung

Beim testen des codes der compile() methode passierte absolut gar nichts.
Es wurden keine Fehler oder vergleichbares ausgegeben,
es gab also keinen eindeutigen Beweis das pdflatex wirklich ausgeführt wird.

#### Lösung

Um dem Ursprung auf den Grund zu gehen, sollte ersteimal dafür gesorgt werden,
das der gewohnte output von pdflatex von unserem Programm ausgegeben wird, um Fehler erkennen zu können.
Bei der Umsetzung wurde festgestellt das der fehlende Output an sich die Ursache war, und eben dieser nicht blockiert werden darf.
Dieses Problem hat sich mehr oder weniger von selbst gelöst.

### pfdlatex-ausfuhrungsmethode deprecated

#### Beschreibung

Die Art und Weise mit der wir pdflatex ausführten (Befehl als String zusammensetzen und an einen Processrunnner übergeben),
wurde nach einem wechsel der Java version als deprecated markiert.

#### Lösung

Nach einem blick in die Documentation wurde klar,
dass ein String[] vorgesehen ist,
und unserer Ansatz durch häufigen missbrauch (nicht näher beschrieben) entfernt werden sollte.
Der ensprecende Quellcode wurde stante pede angepasst.

### CSS-Stylesheet ignoiert einige regeln

#### Beschreibung

Einige Elemente wie die icons in der Seitenleite und die Text-Area,
wollten sich nicht den in der Große anzeigen lassen, die ursprünglich vorgesehen war.

#### Lösung

Etwas Nachforschung ergab, dass die Icons einge extra regeln für die Skalierung benötigten.
Die Text-Area wurde nach ewigen trial and error als designentscheidung akzeptiert so geleassen.

### ChatGPT

#### Beschreibung

ChatGPT ist bei der beantwortung von Anfragen immer übermäßig verbos,
Eine typische ChatGPT-Antwort sieht in etwa so aus:

[Beschriebung der eigenen Frage]

[Tatsächliche Antwort]

[Kurzes Fazit, oder ähnliches]

Da es selbt mit überedungversuchen keine konstante eindeutige möglichekeit gab,
den wichtigen Teil der Antwort zu isolieren,
die nicht mit eimen LaTeX-Steuerzeichen verwechselbar ist,
wird es sehr schwierig dieses feature umzusetzen.
Hinzu kommt das problem das chatGPT bei zu vielen ähnlichen Fragen in einen zustand wechselt,
indem die eigenen Rahmenbedingungen ignoriert werden.

#### Lösung

Feature wurde ersatzlos verworfen.
