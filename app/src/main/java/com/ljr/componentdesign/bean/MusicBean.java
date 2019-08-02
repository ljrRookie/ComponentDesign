package com.ljr.componentdesign.bean;

public class MusicBean {
    private String title;
    private String person;
    private String size;

    public MusicBean(String title, String person, String size) {
        this.title = title;
        this.person = person;
        this.size = size;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getPerson() {
        return person == null ? "" : person;
    }

    public void setPerson(String person) {
        this.person = person == null ? "" : person;
    }

    public String getSize() {
        return size == null ? "" : size;
    }

    public void setSize(String size) {
        this.size = size == null ? "" : size;
    }
}
