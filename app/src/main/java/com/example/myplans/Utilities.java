package com.example.myplans;

public class Utilities {

    public static final String TABLE_PLAN = "plans";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_GPS = "gps";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_SCHEDULE = "schedule";

    public static final String CREATE_TABLE_PLAN = "CREATE TABLE "+TABLE_PLAN+" ("+FIELD_ID+" INTEGER, "+FIELD_NAME+" TEXT, "+FIELD_DESCRIPTION+" TEXT, "+FIELD_GPS+" TEXT, "+FIELD_PHONE+" TEXT, "+FIELD_SCHEDULE+" TEXT)";
}
