-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Sep 2023 um 01:57
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `aids`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `modules`
--

CREATE TABLE `modules` (
  `Modul` text NOT NULL,
  `IdentificationNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `snippets`
--

CREATE TABLE `snippets` (
  `DataNumber` int(11) NOT NULL,
  `DataName` text NOT NULL,
  `DataPath` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `studiengang`
--

CREATE TABLE `studiengang` (
  `Wirtschafts_Informatik` int(11) NOT NULL,
  `Agrar_Informatik` int(11) NOT NULL,
  `Garten_Landschaftsbau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`IdentificationNumber`),
  ADD UNIQUE KEY `Modul` (`Modul`) USING HASH;

--
-- Indizes für die Tabelle `snippets`
--
ALTER TABLE `snippets`
  ADD PRIMARY KEY (`DataNumber`);

--
-- Indizes für die Tabelle `studiengang`
--
ALTER TABLE `studiengang`
  ADD UNIQUE KEY `Wirtschafts_Informatik` (`Wirtschafts_Informatik`,`Agrar_Informatik`,`Garten_Landschaftsbau`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `snippets`
--
ALTER TABLE `snippets`
  ADD CONSTRAINT `snippets_ibfk_1` FOREIGN KEY (`DataNumber`) REFERENCES `modules` (`IdentificationNumber`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
