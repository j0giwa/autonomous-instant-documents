#!/bin/sh

JARNAME="automomous-instantdocument-system.jar"
OPTDIR="/opt/automomous-instantdocument-system"
BINDIR="/usr/local/bin"
MANDIR="/usr/share/man/man1"
echo "Installing automomous-instantdocument system V:$VERSION"
mkdir $OPTDIR
cp ./automomousInstantdocumentSystem-*-jar-with-dependencies.jar $OPTDIR/$JARNAME
cp .man/autonomous-instantdocument-system.1.gz $MANDIR
cp -r ./bini/* $BINDIR
cp -r defaults/* /home/$SUDO_USER/.config/aids
echo "Done"
