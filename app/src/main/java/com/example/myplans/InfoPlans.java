package com.example.myplans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoPlans extends AppCompatActivity {

    TextView textTitle, textDescription, textGPS, textPhone, textSchedule;
    ImageView imageMain, imagePhoto1, imagePhoto2, imagePhoto3;

    ConnectionSQLiteHelper conn;

    String phoneNumbers;
    String[] latitude = {"null", "40.1045", "40.1041"};
    String[] longitude = {"null", "-3.6911", "-3.6916"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_plans);

        Bundle extras = getIntent().getExtras();
        int planID = extras.getInt("planID");

        conn = new ConnectionSQLiteHelper(getApplicationContext(), "bd_plans", null, 1);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        imageMain = findViewById(R.id.imageViewMain);
        imagePhoto1 = findViewById(R.id.imageViewPhoto1);
        imagePhoto2 = findViewById(R.id.imageViewPhoto2);
        imagePhoto3 = findViewById(R.id.imageViewPhoto3);
        textTitle = findViewById(R.id.textViewTitle);
        textDescription = findViewById(R.id.textViewDescription);
        textGPS = findViewById(R.id.textViewGPS);
        textPhone = findViewById(R.id.textViewPhone);
        textSchedule = findViewById(R.id.textViewSchedule);

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] fields = {Utilities.FIELD_NAME, Utilities.FIELD_DESCRIPTION, Utilities.FIELD_GPS, Utilities.FIELD_PHONE, Utilities.FIELD_SCHEDULE};
        String planIDString = String.valueOf(planID);
        String[] parameter = {planIDString};

        try{
            Cursor cursor = db.query(Utilities.TABLE_PLAN, fields, Utilities.FIELD_ID+"=?", parameter, null, null, null);
            cursor.moveToFirst();
            textTitle.setText(cursor.getString(0));
            textDescription.setText(cursor.getString(1));
            textGPS.setText("Ubicación: "+cursor.getString(2));
            textPhone.setText("Teléfono: "+cursor.getString(3));
            textSchedule.setText("Horario: "+cursor.getString(4));
            cursor.moveToFirst();
            phoneNumbers = cursor.getString(3);
            cursor.close();


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No hay datos para este plan", Toast.LENGTH_LONG).show();
            limpiar();
        }

        Context c = getApplicationContext();
        int idMain = c.getResources().getIdentifier("drawable/"+"background_"+planID, null, c.getPackageName());
        int idFirst = c.getResources().getIdentifier("drawable/"+"photo_first_"+planID, null, c.getPackageName());
        int idSecond = c.getResources().getIdentifier("drawable/"+"photo_second_"+planID, null, c.getPackageName());
        int idThird = c.getResources().getIdentifier("drawable/"+"photo_third_"+planID, null, c.getPackageName());

        imageMain.setImageResource(idMain);
        imagePhoto1.setImageResource(idFirst);
        imagePhoto2.setImageResource(idSecond);
        imagePhoto3.setImageResource(idThird);
    }

    public void makeCall(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phoneNumbers));
        startActivity(intent);
    }

    public void openMap(View v) {
        Bundle extras = getIntent().getExtras();
        int planID = extras.getInt("planID");

        Uri.Builder directionsBuilder = new Uri.Builder()
                .scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", latitude[planID-1000]+","+longitude[planID-1000]);

        startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));

    }

    private void limpiar() {
        textTitle.setText("");
        textDescription.setText("");
        textGPS.setText("");
        textPhone.setText("");
        textSchedule.setText("");
    }
}
