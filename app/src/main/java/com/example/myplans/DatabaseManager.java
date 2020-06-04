package com.example.myplans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseManager extends AppCompatActivity {

    EditText fieldID, fieldName, fieldDescription, fieldGPS, fieldPhone, fieldSchedule;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_manager);

        fieldID = findViewById(R.id.editTextID);
        fieldName = findViewById(R.id.editTextName);
        fieldDescription = findViewById(R.id.editTextDescription);
        fieldGPS = findViewById(R.id.editTextGPS);
        fieldPhone = findViewById(R.id.editTextPhone);
        fieldSchedule = findViewById(R.id.editTextSchedule);
        buttonSend = findViewById(R.id.buttonSend);

    }

    public void onClick(View view){
        registerPlans();
    }

    private void registerPlans() {
        ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(this, "bd_plans",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilities.FIELD_ID, fieldID.getText().toString());
        values.put(Utilities.FIELD_NAME, fieldName.getText().toString());
        values.put(Utilities.FIELD_DESCRIPTION, fieldDescription.getText().toString());
        values.put(Utilities.FIELD_GPS, fieldGPS.getText().toString());
        values.put(Utilities.FIELD_PHONE, fieldPhone.getText().toString());
        values.put(Utilities.FIELD_SCHEDULE, fieldSchedule.getText().toString());

        Long idResult = db.insert(Utilities.TABLE_PLAN,Utilities.FIELD_ID,values);

        Toast.makeText(getApplicationContext(), "Id Registro: "+idResult,Toast.LENGTH_SHORT).show();
        db.close();
    }
}
