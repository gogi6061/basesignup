package com.servlet.database;

import java.sql.*;

public class DataBaseDAO {
    static Connection connection;
    private static DataBaseDAO DbDAO = new DataBaseDAO();
    private static Statement statemaent;


    public static DataBaseDAO returnDbDAO() {
        return DbDAO;
    }


    public void setConnectionToDB() throws SQLException {


        try {


            String url;
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ezlifefogg21KK@");
            if (connection == null)
                System.err.println("Нет соединения с БД!");
            else {
                System.out.println("Cоединения с БД установлено!");
            }


            statemaent = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("SQLException : " + e.getMessage());
        }


    }


    public void create(String name, String password, String sessionId) throws SQLException {

        String s = String.format("""
                INSERT INTO "users" (name, password, sessionid)( 
                                        VALUES ('%s','%s','%s') 
                                    );""", name, password, sessionId);

        statemaent.addBatch(s);
        statemaent.execute(s);


    }

    public void createToStates(String name, String password) throws SQLException {

        String s = String.format("""
                INSERT INTO "users" (name, password)( 
                                        VALUES ('%s','%s') 
                                    );""", name, password);

        statemaent.addBatch(s);
        statemaent.execute(s);


    }

    public String getState(String s1, String s2) throws SQLException {
        String s = String.format("""
                SELECT * FROM "users"
                 WHERE NAME = '%s' OR PASSWORD = '%s' """, s1, s2);


        ResultSet resultSet = statemaent.executeQuery(s);


        if (resultSet.next()) {
            return String.format("%s %s", resultSet.getString("name"), resultSet.getString("password"));
        } else {
            return "пустая строка";
        }
    }

    public boolean checkSessionID(String s1) throws SQLException {
        String s = String.format("""
                SELECT * FROM "users"
                WHERE sessionid = '%s' """, s1);

        ResultSet resultSet = statemaent.executeQuery(s);


        return resultSet.next();


    }

    public void updateSessionID(String s1, String s2, String s3) throws SQLException {
        String s = String.format("""
                UPDATE USERS SET SESSIONID = '%s'
                WHERE NAME = '%s' AND PASSWORD = '%s'
                                
                """, s3, s1, s2);

        statemaent.execute(s);

    }


}
