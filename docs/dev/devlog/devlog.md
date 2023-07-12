# Ausarbeitung Programiersprachen II

## Analyse

### Anforderungen

#### Erforderlich
- Das Programm soll aus vorgefertigten (anpassbaren) Schnipseln ein LaTeX-dokument erstellen.
- Die Schnipssel werden in einer MySQL Datenbank verwaltet, diese sollte sich zu einer CSV exportieren lassen.
- Andere einstellungen sollen in einer JSon verwaltet werden.
- Die configdatein (Schnipsel eigeschlossen) werden an dem Ort gespeichert der vom OS für diesen Zweck vogesehen ist.
- Grafische Oberfläche für den gemeinen Nutzer.
- CLI Schnittstelle. (Automatisierung via cron)
- Detailierte Anwenderdokumentation (GitHub, manpage)

#### Optional
- Das Programm solle universell einsetzbar sein. 
Das beduetet dass das Programm nicht zwingend für Klausuren eingesetzt werden muss.
- ~~Generierung neuer schnippsel durch ChatGPT~~ (verworfen)

## Werkzeuge
Im folgenden wird eine Liste von Werkzeugen aufgeführt, die bei der entwickung genutzt werden.

### VS Code
VS Code ist ein Open-source Code-editor von Microsoft, 
welcher mit einem Plugin auch die programmierung in Java ermöglicht.

### DOOM Emacs
DOOM Emacs ist eine Distrubution von Emacs.
Es bietet einige vorkonfigurierte Pakte und Shortcuts, die das programmieren erleichtern.

### git
git ist ein gängiges Versionskontrollsystem. Es ermöglicht die Zumammenarbeit von mehreren Entwicklern am selben Projekt zu arbeiten.

### Javascript Object Notation
Javascript Object Notation (JSon) ist ein Datenformat welches wir dazu verwenden, 
Einstellungen des Progamms persistent zu halten.
Die einstellungen werden in einer Datei gespeichert und bei Bedarf geladen.

### plantuml
plantuml ist ein texpabsiertes Werkzeug zur erstellung von UML-digrammen.
Die Diagramme werden mit einer syntac definiert die sich leicht in Quellcode übertragen lässt.

### maven
Maven ist ein Build-Management-Tool für Java. 
Es automatisiert das verwalten von librarys, das complilieren des Codes,
und das testen mit Unittests.

## Entwurf

### Architektur 
TODO: Diagramme einfügen

### Entwurf der Benutzerschnittstellen

#### CLI

Es wurden folgende flags für die cli-schnittstelle festgelegt
 
| flag                      | funktion                                    |
| ------------------------- | ------------------------------------------- |
| -t  --type <type>         | Legt den Dokumenttyp fest.                  |
| -c  --chapters <chapters> | Legt die Kaptielanzahl pro Dokument fest.   |
| -a  --amount <amount>     | Legt die Anzahl der DokumentInstanzen fest. |
| -ns --noshuffle           | Schaltet den Shuffle modus aus.             |
| -h  --help                | Zeigt eine Hilfe an.                        |
| -v  --version             | Zibt die versionsnummer aus.                |

Diese flags können auch in der manpage nachgelese werden.

#### GUI
TODO: jpg einfügen

## Gesprächsprotokolle

Im folgenden werden nur die Gespräche mit den Dozenten Aufgeführt,
Alles weitere wurde bei bedarf auf WhatsApp geklärt.

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

Beim testen der `compile() ` methode passierte absolut gar nichts.
Es wurden keine Fehler oder vergleichbares ausgegeben, und von einer Pdf fehlte jeder Spur.
Es gab also hinweis darauf dass `pdflatex` überhaupt ausgeführt wurde.

#### Lösung

Um dem Ursprung auf den Grund zu gehen, sollte ersteimal dafür gesorgt werden,
das der gewohnte output von pdflatex von unserem Programm ausgegeben wird, um Fehler erkennen zu können.
Bei der Umsetzung wurde festgestellt das der fehlende Output an sich die Ursache war, und eben dieser nicht blockiert werden darf.
Dieses Problem hat sich mehr oder weniger von selbst gelöst.

### pfdlatex-ausführung deprecated

#### Beschreibung

Die Art und Weise mit der wir pdflatex ausführten (Befehl als String zusammensetzen und an einen Processrunnner übergeben),
wurde nach einem wechsel der Java version als deprecated markiert.

#### Lösung

Nach einem blick in die Documentation wurde klar, dass ein `String[]` vorgesehen ist.
Unser Ansatz soll durch häufigen missbrauch (nicht näher beschrieben) aus Java entfernt werden.
Der ensprecende Quellcode wurde stante pede angepasst.

### CSS-stylesheet ignoiert einige regeln

#### Beschreibung

Einige Elemente wie die icons in der Seitenleite und die Text-Area,
wollten sich nicht den in der Große anzeigen lassen, die ursprünglich vorgesehen war.

#### Lösung

Etwas Nachforschung ergab, dass die Icons einge extraregeln für die Skalierung benötigten.
Da dei Text-Area nach ewigen trial and error immer noch nicht die geplanten dimensionen aufwies, 
und die optische schönheit aus zeitgründen nicht priorisiert wurde, wurde als "designentscheidung" angesehen und so beleassen.

### ChatGPT

#### Beschreibung

ChatGPT ist bei der beantwortung von Anfragen immer übermäßig verbos,
Eine typische ChatGPT-Antwort sieht in etwa so aus:

> [Beschriebung der eigenen Frage]
> [Tatsächliche Antwort]
> [Kurzes Fazit, oder ähnliches]

Da ChatGPT durch einen Bug bei zu vielen ähnlichen Fragen in einen zustand wechselt, indem die eigenen Rahmenbedingungen ignoriert werden.
gibt es selbst mit überedungversuchen keine zuverlässe möglicekeit, den wichtigen Teil der Antwort zu isolieren. 
Diese feature umzusetzen würde sich als sehr schwierig erweisen und vermutlich in krypichen, unwartaren regexmasken ausarten.

#### "Lösung"
Das Feature wurde verworfen, es war ohnehina als optionla Anforderung geplant.
