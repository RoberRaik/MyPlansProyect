package com.example.myplans;

public class Plans {
    private Integer id;
    private String name;
    private String description;
    private String gps;
    private String phone;
    private String schedule;

    public Plans(Integer id, String name, String description, String gps, String phone, String schedule) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gps = gps;
        this.phone = phone;
        this.schedule = schedule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
