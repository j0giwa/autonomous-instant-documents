# Entwicklungsprotokoll Jonas Schwind

## Gesprächsprotokolle
======================

### 1.ste Besprechung Programmersprachen II (mit Wolf)

Es wurden unsere Ideen und Ansätze bezüglich des projektes vorgestellt,
und der Meilensteilplan Präsentiert.
Diese wurden alle gut aufgeneommen.
Im abschluss wurde noch ein themenwunsch für eine evtl. Übung vogeschlagen: Java FX guis.

Unser Projektansätze:
- Programm unversal einsetzbar machen (Klausuren also nur teilfunktion)
- Automatisierung via Cronjobs.
- Chatgpt anbindung

### 2te Besprechung Programmersprachen II (mit Wolf)

Es wurde der aktuelle fortschritt bezüglich des Projektes, sowie Probleme vorgestellt,
Probleme mit den JUnit-tests konnten nach einem vorschlag ignoriert werden.

## Probleme
===========

### pfdlatex macht absolut nichts

#### Beschreibung:
Beim testen des codes der compile() methode passirte absolut gar nichts.
Es wurden nicht einmal fehler oder vergleichbares ausgegeben.

#### Lösung:
Um dem unsprung auf den grund zu gehen, wollte ich ersteimal dafür sorgen, 
das der gewohnte output von pdflatex von unserem programm ausgegeben wird.
Bei der Umsetzung wurde festgestellt das der fehlende Output an sich die ursache war,
und eben dieser nicht blockirt werden darf.
Dieses Problem hat sich mehr oder wenigen von selbst gelöst.

### pfdlatex-ausfuhrung deprecated

#### Beschreibung:
Die Art und Weise mit der wir pdflatex ausführen
(Befehl als String zusammensetzen und an einen Processrunnner übergeben), 
wurde nach einem wechsel der Java-version als deprecated markiert.

#### Lösung:
Nach einem blick in die Documentation wurde klar, 
dass jetz ein String[] vorgesehen ist,
Der ensprecende Quellcoe wurde angepasst.

### CSS-Stylesheetwhet sich gegen Regeln

#### Beschreibung:
Einige Elemente wie die icons in der Seitenleite und dei Text-Area,
wollten sich nicht den in der Große anzeigen lassen,
die ursprünglich vorgesehen war.

#### Lösung:
Etwas Nachforschung ergab, dass die Icons einge extrereel für die Sklierung benötigten.
Die Text-Area wurde nach ewigen trial and error als designentscheidung akzeptiert so geleassen.

### ChatGPT

#### Beschreibung:
ChatGPT ist bei der beantwortung von Fragen immer sehr geprächig,
Eine typische ChatGPT-Antwort siht in etwa so aus:
[Beschriebung der eigenen frage]

[Tatsächliche Antwort]

[Kurzes Fazit,oder ähnlices]

Da es selbt mit überedungversuchen keine konstante eindeutuge möglichekeit gab, 
den wichtigen Teil der Antwort zu isolieren, 
die nicht mit eimen LaTeX-Steuerzeichen verwechselbar ist,
wird es sehr schwierig dieses feature umzusetzen.

#### Lösung:
Feature wurde komplett verworfen.
