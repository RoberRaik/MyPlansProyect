package com.example.myplans;

import androidx.appcompat.app.AppCompatActivity;

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

    String[] phoneNumbers = {"null", "tel:918941738", "tel:918013110", "tel:925099714", "tel:918088904"};
    String[] latitude = {"null", "40.1045", "40.1041", ""};
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
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }

        switch(planID) {
            case 1:
                imageMain.setImageResource(R.drawable.background_templodelsol);
                imagePhoto1.setImageResource(R.drawable.photo_templodelsol1);
                imagePhoto2.setImageResource(R.drawable.photo_templodelsol2);
                imagePhoto3.setImageResource(R.drawable.photo_templodelsol3);
                break;
            case 2:
                imageMain.setImageResource(R.drawable.background_telepizza);
                imagePhoto1.setImageResource(R.drawable.photo_telepizza1);
                imagePhoto2.setImageResource(R.drawable.photo_telepizza2);
                imagePhoto3.setImageResource(R.drawable.photo_telepizza3);
                break;
            case 3:
                imageMain.setImageResource(R.drawable.background_nuova);
                imagePhoto1.setImageResource(R.drawable.photo_telepizza1);
                imagePhoto2.setImageResource(R.drawable.photo_telepizza2);
                imagePhoto3.setImageResource(R.drawable.photo_telepizza3);
                break;
            case 4:
                imageMain.setImageResource(R.drawable.background_vega);
                imagePhoto1.setImageResource(R.drawable.photo_telepizza1);
                imagePhoto2.setImageResource(R.drawable.photo_telepizza2);
                imagePhoto3.setImageResource(R.drawable.photo_telepizza3);
                break;
        }
    }

    public void makeCall(View v) {
        Bundle extras = getIntent().getExtras();
        int planID = extras.getInt("planID");

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNumbers[planID]));
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
                .appendQueryParameter("destination", latitude[planID]+","+longitude[planID]);

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
