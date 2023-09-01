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
    - [Windows](#windows)
    - [UNIX (BSD, GNU/Linux)](#UNIX-BSD-GNULinux)
- [Usage](#usage)

</details>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

About The Project
=============================

## Features

- Generate LaTeX documents
- Usable via GUI, TUI (UNIX OS only) and CLI

## Planned

- Customizable LaTeX snippets (preconfigured for exams and answers)
- Snippet usage saved in a database
- Export database to CSV
- ~~ChatGPT integration to automatically generate new snippets (API-key required)~~

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

Getting started
=============================

## Requirements

- [maven](https://maven.apache.org/)
- [java20](https://jdk.java.net/20/)
- [JavaFX](https://jdk.java.net/javafx20/)
- [pdflatex](https://www.latex-project.org/)
- whiptail (for TUI)

## Installation

### Windows

Build and install with these commands.

``` bat
mvn package -DskipTests
xcopy "distribution\target\automomous-instantdocument-system.jar" "C:\Program Files\automomous-instantdocument-system.jar" /Y
xcopy "distribution\assets\defaults\*" "%appdata%\aids" /E /Y
```

> NOTE: -DskipTests is not necessary, it's just making the process go much faster.
> If you want to run tests anyway, you need to install the config-files first.

It is recommended that you define an alias If you want to launch from the command line

``` bat
doskey automomous-instantdocument-system="java -jar C:\Program Files\automomous-instantdocument-system.jar"
```

### UNIX (BSD, GNU/Linux)

Build and install with these commands.

``` shell
mvn package -DskipTests
sudo cp distribution/target/automomousInstantdocumentSystem.jar /opt/automomous-instantdocument-system/automomous-instantdocument-system.jar
sudo cp distribution/automomous-instantdocument-system-cli /usr/local/bin # optional
gzip -c docs/man/autonomous-instantdocument-system.1 | sudo tee /usr/share/man/man1/autonomous-instantdocument-system.1.gz >/dev/null
```

> NOTE: -DskipTests is not necessary, it's just making the process go much faster.
> If you want to run tests anyway, you need to install the config-files first.

It is recommended that you define an alias If you want to launch from the command line

``` shell
alias automomous-instantdocument-system="java -jar /opt/automomous-instantdocument-system/automomous-instantdocument-system.jar":
```

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

Usage
=============================

Running `automomous-instantdocument-system` without options launches a graphical interface.

### Flags

| flag          | function                           |
| ------------- | ---------------------------------- |
| -t --type <type> | Specifies the desired Document type, e.g. "exam". |
| -c --chapters <chapters> | Specifies the amount of chapters per document.
| -a --amount <amount> | Specifies how many Documents should be generated.
| -s --shuffle | Turns the shuffle mode on. |
| -h --help | Show summary of options. |
| -v --version | Print version number and exit. |
