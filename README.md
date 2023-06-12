# Autonomous Instantdocument System

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)

[![shields.io](https://img.shields.io/github/license/j0giwa/automomous-instantdokument-system)](https://img.shields.io/github/license/j0giwa/automomous-instantdokument-system)
[![sields.io](https://img.shields.io/github/stars/j0giwa/automomous-instantdokument-system)](https://img.shields.io/github/stars/j0giwa/automomous-instantdokument-system)
[![shields.io](https://img.shields.io/github/issues/j0giwa/automomous-instantdokument-system)](https://img.shields.io/github/issues/j0giwa/automomous-instantdokument-system)

Table of Contents
=============================

<details open="open">
<summary>Table of Contents</summary>

- [About The Project](#about-the-project)
  - [Features](#features)
  - [Planned](#planned)
- [Getting started](#getting-started)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Build it yourself](#build-it-yourself)
    - [Windows](#windows)
    - [Linux](#linux)
- [Usage](#usage)

</details>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

About The Project
=============================

## Features

- Generate LaTeX documents
- Usable via GUI, TUI (UNIX OS only) and CLI

## Planned

- Customisable LaTeX snippets (preconfigured for exams and answers)
- Snippet usage saved in a database
- Export database to csv
- ~~ChatGPT integration to automatically generate new snippets (API-key required)~~

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

Getting started
=============================

## Requirements

- java20
- javafx
- pdflatex
- whiptail (for TUI)
- maven (if you want to build from source)

## Installation

### Build it yourself

#### Windows

``` bat
mvn package
xcopy ".\target\automomousInstantdocumentSystem-*-jar-with-dependencies.jar" "C:\Program Files\automomous-instantdocument-system.jar" /Y
xcopy ".\assets\defaults\*" "%appdata%\aids" /E /Y
```

It is recommended that you define an alias If you want to launch from the commandline

``` bat
doskey automomous-instantdocument-system="java -jar C:\Program Files\automomous-instantdocument-system.jar"
```

#### Linux

``` shell
mvn package
sudo cp ./target/automomousInstantdocumentSystem-*-jar-with-dependencies.jar /opt/automomous-instantdocument-system/automomous-instantdocument-system.jar
sudo cp ./automomous-instantdocument-system-cli /usr/local/bin
gzip docs/man/autonomous-instantdocument-system.1
sudo cp .docs/man/autonomous-instantdocument-system.1.gz /usr/share/man/man1/
```

It is recommended that you define an alias If you want to launch from the commandline

``` shell
alias automomous-instantdocument-system="java -jar /opt/automomous-instantdocument-system/automomous-instantdocument-system.jar"
```

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

Usage
=============================

Running `automomous-instantdocument-system` without options launches a grafical interface.

### Flags

| flag          | function                           |
| ------------- | ---------------------------------- |
| -t --type <type> | Specifies the desired Document type e.g. "exam". |
| -c --chapters <chapters> | Specifies the amount of chapters per document.
| -a --amount <amount> | Specifies how many Documents should be generated.
| -ns --noshuffle | Turns of shuffle mode. |
| -h --help | Show summary of options. |
| -v --version | Print version number and exit. |
