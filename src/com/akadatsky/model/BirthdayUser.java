package com.akadatsky.model;

public class BirthdayUser {

    private String id;
    private String dataBirthday;
    private String nameUser;

    public BirthdayUser(String id, String dataBirthday, String nameUser) {
        this.id = id;
        this.dataBirthday = dataBirthday;
        this.nameUser = nameUser;
    }


    public String getId() {
        return id;
    }

    public String getNameUser() {
        return nameUser;
    }
    public String getDataBirthday() {
        return dataBirthday;
    }

    public void setNameUser(String groupId) {
        this.nameUser = nameUser;
    }

    @Override
    public String toString() {
        return "{" +
                "nameUser='" + nameUser + '\'' +
                ", dataBirthday=" + dataBirthday +
                '}';
    }
}
