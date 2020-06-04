package com.example.myplans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseManager extends AppCompatActivity {

    EditText fieldID, fieldName, fieldDescription, fieldGPS, fieldPhone, fieldSchedule;
    Button buttonSend, buttonQuery, buttonDelete, buttonModify;

    ConnectionSQLiteHelper conn;

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
        buttonQuery = findViewById(R.id.buttonQuery);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonModify = findViewById(R.id.buttonModify);

        conn = new ConnectionSQLiteHelper(getApplicationContext(), "bd_plans", null, 1);

    }

    public void registerPlans(View view) {
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
        clean();
    }

    public void queryPlans(View view) {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] fields = {Utilities.FIELD_NAME, Utilities.FIELD_DESCRIPTION, Utilities.FIELD_GPS, Utilities.FIELD_PHONE, Utilities.FIELD_SCHEDULE};
        String[] parameter = {fieldID.getText().toString()};

        try{
            Cursor cursor = db.query(Utilities.TABLE_PLAN, fields, Utilities.FIELD_ID+"=?", parameter, null, null, null);
            cursor.moveToFirst();
            fieldName.setText(cursor.getString(0));
            fieldDescription.setText(cursor.getString(1));
            fieldGPS.setText(cursor.getString(2));
            fieldPhone.setText(cursor.getString(3));
            fieldSchedule.setText(cursor.getString(4));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No hay datos para este plan", Toast.LENGTH_LONG).show();
            clean();
        }
    }

    public void updatePlans(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parameter = {fieldID.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilities.FIELD_NAME,fieldName.getText().toString());
        values.put(Utilities.FIELD_DESCRIPTION,fieldDescription.getText().toString());
        values.put(Utilities.FIELD_GPS,fieldGPS.getText().toString());
        values.put(Utilities.FIELD_PHONE,fieldPhone.getText().toString());
        values.put(Utilities.FIELD_SCHEDULE,fieldSchedule.getText().toString());

        db.update(Utilities.TABLE_PLAN,values,Utilities.FIELD_ID+"=?",parameter);
        Toast.makeText(getApplicationContext(), "Actualizacion realizada correctamente", Toast.LENGTH_SHORT).show();
        db.close();
        clean();
    }

    public void deletePlans(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parameter = {fieldID.getText().toString()};

        db.delete(Utilities.TABLE_PLAN,Utilities.FIELD_ID+"=?",parameter);
        Toast.makeText(getApplicationContext(), "Plan eliminado correctamente", Toast.LENGTH_SHORT).show();
        db.close();
        clean();
    }

    public void clearFields(View view) {
        clean();
    }

    private void clean() {
        fieldID.setText("");
        fieldName.setText("");
        fieldDescription.setText("");
        fieldGPS.setText("");
        fieldPhone.setText("");
        fieldSchedule.setText("");
    }
}
