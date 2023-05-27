# Autonomous Instantdocument System

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)

[![shields.io](https://img.shields.io/github/license/j0giwa/automomous-instantdokument-system)](https://img.shields.io/github/license/j0giwa/automomous-instantdokument-system)
[![sields.io](https://img.shields.io/github/stars/j0giwa/automomous-instantdokument-system)](https://img.shields.io/github/stars/j0giwa/automomous-instantdokument-system)
[![shields.io](https://img.shields.io/github/issues/j0giwa/automomous-instantdokument-system)](https://img.shields.io/github/issues/j0giwa/automomous-instantdokument-system)

## Features
<details>
<summary>Click to expand!</summary>

- Generate LaTeX documents
- Usable via GUI/TUI and CLI

### Planned Features
<details>
<summary>Click to expand!</summary>

- Customisable LaTeX snippets (preconfigured to produce exams and answers)
- ChatGPT integration to automatically generate new snippets (API-key required)
- Snippet statistics are saved in a database (for example how often a snipped has been used)
- Export database to csv

</details>
</details>

## Requirements
<details>
<summary>Click to expand!</summary>

- Java17
- JavaFx
- pdflatex
- an SQL-Server

</details>

## Install
<details>
<summary>Click to expand!</summary>

### Prebuild jar
COMMING SOON

### Build it yourself
``` bash
mvn package
```
</details>

## How to use
<details>
<summary>Click to expand!</summary>

`automomous-instantdocument-system` without options launches in a grafical mode.

### Flags
| flag          | function                           |
| ------------- | ---------------------------------- |
| -t --type <type> | Specifies the desired Document type e.g. "exam". |
| -c --chapters <chapters> | Specifies the amount of chapters per document. 
| -a --amount <amount> | Specifies how many Documents should be generated.
| -ns --noshuffle | Turns of shuffle mode. |
| -h --help | Show summary of options. |
| -v --version | Print version number and exit. |

</details>
