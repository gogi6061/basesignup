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
            assert connection != null;

            statemaent = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("SQLException : " + e.getMessage());
        }


    }


    public void create(String name, String password, String seesionId) throws SQLException {

        String s = String.format("""
                INSERT INTO "users" (name, password,sessionid)( 
                                        VALUES ('%s','%s','%s') 
                                    );""", name, password, seesionId);

        statemaent.addBatch(s);
        statemaent.execute(s);


    }

    public String getState(String s1, String s2) throws SQLException {
        String s = String.format("""
                SELECT * FROM "users"
                 WHERE NAME = '%s' OR PASSWORD = '%s' """, s1, s2);

        //statemaent.addBatch(s);

        ResultSet resultSet = statemaent.executeQuery(s);



        if (resultSet.next()) {
            return String.format("%s %s", resultSet.getString("name"), resultSet.getString("password"));
        } else {
            return "пустая строка";
        }
    }

    public String getSessionID(String s1, String s2) throws SQLException {
        String s = String.format("""
                SELECT SESSIONID FROM "USERS"
                WHERE NAME = '%s' and PASSWORD = '%s' """, s1, s2);

        ResultSet sessionId = statemaent.executeQuery(s);

        if(sessionId.next()){
            return sessionId.getString("sessionid");
        }else{
            return "пустая строка";
        }



    }


}
