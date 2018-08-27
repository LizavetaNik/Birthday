package com.akadatsky.Dao;
import com.akadatsky.model.BirthdayUser;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserDao() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/newmy", "Liza", "123");
        //maybeCreateUsersTable();
    }

    //создать таблицу в базе данных
//    private void maybeCreateUsersTable() throws SQLException {
//        try (Statement statement = connection.createStatement()) {
//            statement.execute("CREATE TABLE IF NOT EXISTS groups (\n" +
//                    "_id uuid PRIMARY KEY,\n" +
//                    "name varchar(100)\n" +
//                    ");");
//        }
//    }


    //что удаляет данный код: строку в таблице или таблицу?
    public void clean() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM groups;");
            System.out.println("Deleted " + count + " rows from table groups");
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM users;");
            System.out.println("Deleted " + count + " rows from table users");
        }

    }


    //что делает этот метод?
    private void insertUser(BirthdayUser birthdayUser) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO users VALUES ('%s', '%s', '%s', '%d');", birthdayUser.getId(), birthdayUser.getDataBirthday(), birthdayUser.getNameUser());
            statement.execute(request);
        }
    }



    public List<BirthdayUser> getNameByBirthdayUserId(String dataBirthday) throws SQLException {
        List<BirthdayUser> names = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM tablemy WHERE datemy = '%s';", dataBirthday);
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nameUser = resultSet.getString("fio");
                String data = resultSet.getString("datamy");
                names.add(new BirthdayUser(dataBirthday, nameUser, id));
            }
        }
        return names;
    }

    public List<BirthdayUser> getALL() throws SQLException {
        List<BirthdayUser> names = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM tablemy;");
            ResultSet resultSet = statement.executeQuery(request);
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String nameUser = resultSet.getString("fio");
//                String data = resultSet.getString("datamy");
//                names.add(new BirthdayUser(dataBirthday, nameUser, id));
//            }
        }
        return names;
    }

    /*
        SELECT column-list
        FROM table_name
        [WHERE condition]
        [ORDER BY column1, column2, .. columnN] [ASC | DESC];
     */


}

