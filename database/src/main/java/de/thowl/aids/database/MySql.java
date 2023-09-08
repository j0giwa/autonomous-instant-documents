package de.thowl.aids.database;

import java.io.*;
import java.sql.*;
import de.thowl.aids.core.OperatingSystem;


class MySql {

  String url; // table details
  String userName;
  String password;
  Connection con;
  String Su = "subjekt";
  String CSV_FILE_PATH;

  // Main driver method
  /**
   * @param args
   * @throws Exception
   */
  public MySql() throws Exception {
    url = "jbdc:mysql://localhost:3306/aids"; // table details
    userName = "root";
    password = "";
    String query = "select *from datenbank";
    con = DriverManager.getConnection(url, userName, password);
    Class.forName("com.mysql.cj.jdbc.Driver");
    System.out.println("Connection Succsesfully Ethablished");
    CSV_FILE_PATH = "DateiPfad zur CSV Datei";

  }

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

  private void closeConnection() throws Exception {
    con.close();
    System.out.println("Connection Closed");
  }

  private void openConnection() throws Exception {
    Class.forName("com.myql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, userName, password);
    System.out.println("Connection Succsesfully Ethablished");
  }

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

  public void exportToCSV() {
    try {
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();
      String query = "SELECT *FROM SNIPPETS";
      ResultSet rs = st.executeQuery(query);
      BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH));
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
  
  public void ImportCSVData() {
    try {
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();

      BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
      String line;
      // Es wird davon ausgegangen das die erste Zeile der CSV SPaltenüberschriften enthält
      String[] head = reader.readLine().split(",");

      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        String query = "INSERT INTO Snippets (";//Richtiger behehl ist noch einzufügen!!!!!
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
  
  public void CSVPortDesision(String optionSet) {
    String option = optionSet;
    

    if (option.equals("Export")) {
      exportToCSV();
    } else if (option.equals("Import")) {
      ImportCSVData();
    } else {
      System.out.println("Auswahl ungültig");
    }
  }
}


