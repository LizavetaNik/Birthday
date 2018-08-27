package com.akadatsky;

import com.akadatsky.Dao.UserDao;
import com.akadatsky.model.BirthdayUser;
import com.akadatsky.model.Message;
import com.akadatsky.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.acl.Group;
import java.sql.SQLException;
import java.util.List;


public class Main {

    private static String getADate() {
        System.out.println("Please enter a date in format dd.MM.yy (01.12.17): ");
        String result = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    private static final String URL = "http://127.0.0.1:12345/my_api/";

    private static Gson gson = new GsonBuilder().create();

    public static void main(String[] args) throws IOException {

        //первая часть - смс
        Message messageForSend = new Message("Liza");
        String json = gson.toJson(messageForSend);

        try {
            String response = sendPostRequest(json);
            Message receivedMessage = gson.fromJson(response, Message.class);
            System.out.println("Message from server: " + receivedMessage.getText());
        } catch (Exception e) {
            System.out.println("Can't parse response from server");
        }

        //вторая часть - по дате вернуть список имен
        String urlSecond = "1987-01-01"; //getADate();

        try {
            UserDao userDao = new UserDao();

            //List<BirthdayUser> users = userDao.getNameByBirthdayUserId(urlSecond);
            List<BirthdayUser> users = userDao.getALL();
            System.out.println("В дату " + urlSecond + " родились: " + users);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String sendPostRequest(String json) {
        return HttpUtil.sendRequest(URL, null, json);
    }

}