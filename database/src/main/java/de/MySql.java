package de.thowl.automomousinstantdocumentsystem.model;


import java.io.*;
import java.sql.*;
// Importing required classes
import java.util.*;
 
// Main class
class MySQLTEST {
// Main driver method
/**
 * @param args
 * @throws Exception
 */
public static void main(String[] args) throws Exception
{
    String url = "jbdc:mysql://localhost:3306/aids"; // table details
    String userName = "root";
    String password = "";
    String query = "select *from datenbank";

    Class.forName("com.myql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, userName, password);
    System.out.println("Connection Succsesfully Ethablished");
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    rs.next();
    String Subjekt = rs.getString("subjekt");

    System.out.println(Subjekt);
    st.close();
    con.close();
    System.out.println("Connection Closed");

}




