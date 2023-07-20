# Ausarbeitung Programiersprachen II

## Analyse

### Anforderungen

#### Erforderlich

-   Das Programm soll aus vorgefertigten (anpassbaren) Schnipseln ein LaTeX-dokument erstellen.
-   Die Schnipssel werden in einer MySQL Datenbank verwaltet, diese soll sich zu einer CSV datei exportieren lassen.
-   Andere Einstellungen sollen in einer JSON verwaltet werden.
-   Die Konfigurationsdateien (Schnipsel eigeschlossen) sollen an dem Ort gespeichert werden, der vom Betriebssystem für diesen Zweck vogesehen ist.
-   Es soll eine Grafische Oberfläche für den gemeinen Nutzer, und eine CLI-Schnittstelle geben. Letztere emöglicht z.b. eine Automatisierung via cron.
-   Es soll eine Detailierte Anwenderdokumentation geben, welche auf github.com und in Form einer manpage (oder funktional vergleichbares) einsehbar ist.

#### Optional

-   Das Programm sollte universell einsetzbar sein.
    Das beduetet dass das Programm nicht zwingend für Klausuren eingesetzt werden muss, und auch für andere Dokumente verwendet werden kann.
-   <del>Generierung neuer Schnippsel durch ChatGPT</del> (verworfen)

### Werkzeuge

Im folgenden wird eine Liste von Werkzeugen aufgeführt, die bei der entwickung genutzt werden.

#### VS Code

VS Code ist ein Open-source Code-editor von Microsoft, 
welcher mit einem Plugin auch die programmierung in Java ermöglicht.

#### DOOM Emacs

DOOM Emacs ist eine Distrubution von Emacs.
Es bietet einige vorkonfigurierte Pakte und Shortcuts, die das Programmieren erleichtern.

#### git

git ist ein gängiges Versionskontrollsystem.
Es ermöglicht die Zumammenarbeit von mehreren Entwicklern am selben Projekt zu arbeiten.

#### Javascript Object Notation

Javascript Object Notation (JSON) ist ein Datenformat welches wir dazu verwenden, 
Einstellungen des Progamms persistent zu halten.
Die Einstellungen werden in einer Datei gespeichert und bei Bedarf geladen.

#### plantuml

plantuml ist ein textbasiertes Werkzeug zur erstellung von UML Digrammen.
Die Diagramme werden mit einer Syntax definiert die sich leicht in Quellcode übertragen lässt.

#### maven

Maven ist ein Build-Management-Tool für Java. 
Es automatisiert das verwalten von externen Librarys, das complilieren des Codes,
und das testen mit Unittests.

## Entwurf

### Architektur

TODO: Diagramme einfügen

### Entwurf der Benutzerschnittstellen

#### CLI

Es wurden folgende Argumente für die CLI-Schnittstelle festgelegt.

| flag                      | funktion                                    |
| ------------------------- | ------------------------------------------- |
| -t  --type <type>         | Legt den Dokumenttyp fest.                  |
| -c  --chapters <chapters> | Legt die Kaptielanzahl pro Dokument fest.   |
| -a  --amount <amount>     | Legt die Anzahl der DokumentInstanzen fest. |
| -ns --noshuffle           | Schaltet den Shuffle modus aus.             |
| -h  --help                | Zeigt eine Hilfe an.                        |
| -v  --version             | Zibt die versionsnummer aus.                |

Diese flags können auch in der Anwenderdokumentation nachgelesen werden.

#### GUI

TODO: jpg einfügen

## Gesprächsprotokolle

Im folgenden werden nur die Gespräche mit den Dozenten Aufgeführt,
Alles weitere wurde bei Bedarf auf WhatsApp geklärt.

### 1ste Besprechung

Es wurden unsere Ideen und Ansätze bezüglich des Projektes vorgestellt,
und der Meilensteinplan Präsentiert.
Diese wurden alle gut aufgenommen.
Im Abschluss wurde noch ein Themenwunsch für eine evtl. Vorlesung vogeschlagen: JavaFX.

### 2te Besprechung

Es wurde der aktuelle Fortschritt bezüglich des Projektes, sowie Probleme vorgestellt.
Probleme mit den JUnit-tests konnten nach einem Vorschlag ignoriert werden (Diese werden daher auch nicht weiter erwähnt).

## Implementierung (Probleme und Lösungen)

### pfdlatex macht absolut nichts

Beim Testen der `compile()` Methode passierte absolut gar nichts.
Es wurden keine Fehler oder vergleichbares ausgegeben, und von einer PDF fehlte jede Spur.
Es gab also keinen hinweis darauf, dass `pdflatex` ausgeführt wurde.

Um dem Ursprung auf den Grund zu gehen, sollte ersteimal dafür gesorgt werden,
das der gewohnte output von pdflatex von unserem Programm ausgegeben wird, um Fehler erkennen zu können.
Bei der Umsetzung wurde festgestellt das der fehlende Output an sich die Ursache war, und eben dieser nicht blockiert werden darf.
Dieses Problem hat sich mehr oder weniger von selbst gelöst.

### pfdlatex-ausführung deprecated

Um `pdflatex` auszuführen, setzten wir den Befehl als String zusammensetzen und übergaben ihn an einen einen Processrunnner.
Diese Art und Weise wurde plötzlich als deprecated markiert.

Nach einem Blick in die Documentation wurde klar, dass ein `String[]` vorgesehen ist.
Unser Ansatz sollte durch häufigen missbrauch (nicht näher beschrieben) aus Java entfernt werden.
Der ensprecende Quellcode wurde stante pede angepasst.

\### CSS-stylesheet ignoriert einige regeln

Einige Elemente wie die Icons in der Seitenleite und die Text-Area,
wollten sich nicht den in der Große anzeigen lassen, die ursprünglich vorgesehen war.

Etwas Nachforschung ergab, dass die Icons einige extraregeln für die Skalierung benötigten.
Da die Text-Area nach ewigen trial-and-error immer noch nicht die geplanten Dimensionen aufwies, 
und die optische schönheit aus zeitgründen nicht priorisiert wurde, wurde dies als &ldquo;Designentscheidung&rdquo; angesehen und so belassen.

### ChatGPT

ChatGPT ist bei der beantwortung von Anfragen immer übermäßig verbos,
Eine typische ChatGPT-Antwort sieht in etwa so aus:

> [Beschriebung der eigenen Frage]
> [Tatsächliche Antwort]
> [Kurzes Fazit, oder ähnliches]

Da ChatGPT durch einen Bug bei zu vielen ähnlichen Fragen in einen Zustand wechselt, indem die eigenen Rahmenbedingungen ignoriert werden, gibt es selbst mit Überedungversuchen keine zuverlässe möglichkeit, den wichtigen Teil der Antwort zu isolieren. 
Diese feature umzusetzen würde sich als sehr schwierig erweisen und vermutlich in krytpischen, unwartbaren regex-masken ausarten.

Das Feature wurde verworfen, es war ohnehin nur als optionle Anforderung geplant.
