#!/bin/sh

whipTilte="Autonomous-Instantdocument-System"

# Welcomescreen
whiptail --msgbox "Autonomous Instantdocument System -- Automatically generate LaTeX Documents" --title $whipTilte 0 0

# Get data
documentType="$(whiptail --inputbox "Document Type" --backtitle $whipTilte 0 0 3>&1 1>&2 2>&3)"
documentAmmount="$(whiptail --inputbox "Ammount" --backtitle $whipTilte 0 0 3>&1 1>&2 2>&3)"
documentDestination="$(whiptail --inputbox "Destination" --backtitle $whipTilte 0 0 3>&1 1>&2 2>&3)"

# run command
autonomous-instantdocument-system-cli  --type "$documentType" --amount "$documentAmmount" --destination "$documentDestination"  
