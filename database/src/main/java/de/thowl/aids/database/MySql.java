package de.thowl.database;

import java.io.*;
import java.sql.*;
import de.thowl.aids.core.OperatingSystem;

class MySql {

  String url; // table details
  String userName;
  String password;
  Connection con;
  String Su = "subjekt";

  // Main driver method
  /**
  * @param args
  * @throws Exception
  */
  public MySql() throws Exception
   {
    url = "jbdc:mysql://localhost:3306/aids"; // table details
    userName = "root";
    password = "";
    String query = "select *from datenbank";
    con = DriverManager.getConnection(url, userName, password);
    Class.forName("com.mysql.cj.jdbc.Driver");
    System.out.println("Connection Succsesfully Ethablished");
   


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

  private String getSubjekt() throws Exception 
  {
    String result = "";
    String query = ("Select *From `subjekts`");
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    while (rs.next()) {
      //Retrieve by column name
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

      if(dateien != null) {
        int nummer = 1;
        for(File datei : dateien) {
          if(datei.isFile() && datei.getName().endsWith(".tex")) {
            String dateiPfad = datei.getAbsolutePath();
            String datenName = datei.getName().replace(".tex", "");

            String insertSQL = "INSERT INTO `snippet`(`SnippedNummer`, `SnippetName`, `FilePath`) VALUES (?, ?, ?)";
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
  
}

