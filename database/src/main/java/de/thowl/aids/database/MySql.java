package de.thowl.aids.database;

/*
 * Klasse erstellt und Geschrieben von Martin Boschmann
 * Die Klasse MySql
 * enthält Sämmtliche befehle, die im zusammenhang mit der Datenbank
 * arbeiten.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.thowl.aids.core.OperatingSystem;

public class MySql {

  String url; // table details
  String userName;
  String password;
  Connection con;
  String Su = "subjekt";

  // Main driver method
  /**
   * Der Konstruktor enthält Wiederholt genutzte Variablen, wie z.b. Die zur
   * Verbindung mit der Datenbank notwendigen Daten.
   */
  public MySql() throws Exception {
    url = "jbdc:mysql://localhost:3306/aids"; // table details
    userName = "root";
    password = "";
    String query = "select *from datenbank";
    con = DriverManager.getConnection(url, userName, password);
    Class.forName("com.mysql.cj.jdbc.Driver");
    System.out.println("Connection Succsesfully Ethablished");
  }

  /*
   * Diese Methode ist dazu gedachte gezielt Neue Fäche / Module für eine Bessere
   * Übersicht manuell eintragen zu können.
   * Diese Methode wird in der Aktuellen version des Programmes jedoch noch Nicht
   * benutzt.
   * Es kann daher sein das die Befehle für den INSERT nichtmehr mit der Aktuellen
   * Datenbank Übereinstimmen.
   * Die Methode wurde nicht entfernt das die Überlegung das Feature noch
   * fertigzustellen
   * noch im raum steht.
   */
  private void addSubjekt(String subjektToAdd) throws Exception {
    String url = "jbdc:mysql://localhost:3306/aids"; // table details
    String userName = "root";
    String password = "";
    String SA = subjektToAdd;
    String query = ("INSERT INTO `subjekts`(`Modul`) VALUES ('" + subjektToAdd + "')");

    Class.forName("com.myql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, userName, password);
    System.out.println("Connection Succsesfully Ethablished");
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    rs.next();

    System.out.println("The Subjekt " + subjektToAdd + " was added Succsesfully");
    st.close();
    con.close();
    System.out.println("Connection Closed");
  }

  /*
   * Eine Methode die Nicht in der Vorhandenen Version des Programmes verwendet
   * wird. Die idee hinter der Methode,
   * war Das Modul zu den einzelnen Fragen etc Ausgeben lassen zu können, um wen
   * die Tabelle z.b. in eine CSV Datei Exportiert wird
   * Direkt hinter Den Dateien auch Sehen zu können zu welchem Modul sie gehöhren.
   */
  private String getSubjekt() throws Exception {
    String result = "";
    String query = ("Select *From `subjekts`");
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    while (rs.next()) {
      // Retrieve by column name
      result = rs.getString("subjekt");
    }
    st.close();
    con.close();
    return result;
  }

  /*
   * Neue Dateien werden in die Datenbank eingelesen und die Informationen die die
   * Entsprechende Spalte eingetragen.
   */
  public void snippetVerarbeitung() {
    OperatingSystem operatingSystem = new OperatingSystem();
    String verzeichnisPfad = operatingSystem.getHomeDir();

    try {
      Connection verbindung = DriverManager.getConnection(url, userName, password);

      File verzeichnis = new File(verzeichnisPfad);
      File[] dateien = verzeichnis.listFiles();

      if (dateien != null) {
        int nummer = 1;
        for (File datei : dateien) {
          if (datei.isFile() && datei.getName().endsWith(".tex")) {
            String dateiPfad = datei.getAbsolutePath();
            String datenName = datei.getName().replace(".tex", "");

            String insertSQL = "INSERT INTO `snippets`(`DataNumber`, `DataName`, `DataPath`) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = verbindung.prepareStatement(insertSQL);
            preparedStatement.setInt(1, nummer);
            preparedStatement.setString(2, dateiPfad);
            preparedStatement.setString(3, datenName);

            nummer++;
          }
        }
      }
      verbindung.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /*
   * Methode zum Exportieren von Tabellendaten in eine CSV. Es wird die Gesammte
   * Tabelle mit
   * Datei Namen, Dateinummer und Dateipfad Exportiert.
   */
  public void exportToCSV(String filepath) {
    try {
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();
      String query = "SELECT *FROM SNIPPETS";
      ResultSet rs = st.executeQuery(query);
      BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
      String line;

      int columnCount = rs.getMetaData().getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
        writer.write(rs.getMetaData().getColumnName(i));
        if (i < columnCount) {
          writer.write(",");
        }
      }
      writer.newLine();
      while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
          writer.write(rs.getString(i));
          if (i < columnCount) {
            writer.write(",");
          }
        }
        writer.newLine();
      }
      writer.close();
      rs.close();
      st.close();
      con.close();
      System.out.println("Datei wurde erfolgreich Exportiert");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * Import Methode. Hiermit werden zu Importierende CSV Daten ausgelesen und
   * anschließend
   * in die Entsprechende Tabelle eingelesen.
   */
  public void ImportCSVData(String filepath) {
    try {
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();

      BufferedReader reader = new BufferedReader(new FileReader(filepath));
      String line;
      // Es wird davon ausgegangen das die erste Zeile der CSV Spaltenüberschriften
      // enthält
      String[] head = reader.readLine().split(",");

      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        String query = "INSERT INTO Snippets (";// Richtiger behehl ist noch einzufügen!!!!!
        for (int i = 0; i < head.length; i++) {
          query += head[i];
          if (i < head.length - 1) {
            query += ",";
          }
        }
        query += ") VALUES (";
        for (int i = 0; i < data.length; i++) {
          query += "'" + data[i] + "'";
          if (i < data.length - 1) {
            query += ",";
          }
        }
        query += ")";
        st.executeUpdate(query);
      }
      reader.close();
      st.close();
      con.close();
      System.out.println("Die Datei wurde erfolgreich Importiert");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}