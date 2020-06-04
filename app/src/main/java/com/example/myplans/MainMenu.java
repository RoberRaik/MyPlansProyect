package com.example.myplans;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView textLocation, textTitle, textPermission, textprueba;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8;
    ImageView imageViewPlan1, imageViewPlan2, imageViewPlan3, imageViewPlan4, imageViewPlan5, imageViewPlan6, imageViewPlan7, imageViewPlan8;
    ScrollView scrollPlans;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    String city;
    int planID;
    int cityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLocation = findViewById(R.id.textViewLocation);
        textTitle = findViewById(R.id.textViewTitle);
        textPermission = findViewById(R.id.textViewPermission);
        scrollPlans = findViewById(R.id.scrollViewPlans);

        textprueba = findViewById(R.id.textViewprueba);

        imageViewPlan1 = findViewById(R.id.imageViewPlan1);
        imageViewPlan2 = findViewById(R.id.imageViewPlan2);
        imageViewPlan3 = findViewById(R.id.imageViewPlan3);
        imageViewPlan4 = findViewById(R.id.imageViewPlan4);
        imageViewPlan5 = findViewById(R.id.imageViewPlan5);
        imageViewPlan6 = findViewById(R.id.imageViewPlan6);
        imageViewPlan7 = findViewById(R.id.imageViewPlan7);
        imageViewPlan8 = findViewById(R.id.imageViewPlan8);

        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        linearLayout4 = findViewById(R.id.linearLayout4);
        linearLayout5 = findViewById(R.id.linearLayout5);
        linearLayout6 = findViewById(R.id.linearLayout6);
        linearLayout7 = findViewById(R.id.linearLayout7);
        linearLayout8 = findViewById(R.id.linearLayout8);


        scrollPlans.setVisibility(View.INVISIBLE);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Spinner_categories,
                R.layout.layout_spinner
        );
        adapter.setDropDownViewResource(R.layout.layout_spinner);

        Spinner spinnerCategories = findViewById(R.id.spinnerCategories);
        spinnerCategories.setAdapter(adapter);
        spinnerCategories.setOnItemSelectedListener(this);

        getLastLocation();

    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                    textLocation.setText("No se encuentra localizacion");
                                    textTitle.setText("Localizacion no encontrada");
                                } else {
                                    double locationLatitudeDouble = location.getLatitude();
                                    double locationLongitudeDouble = location.getLongitude();

                                    String locationLatitudeString = String.valueOf(locationLatitudeDouble);
                                    String locationLongitudeString = String.valueOf(locationLongitudeDouble);

                                    textLocation.setText(String.format("%s %s", locationLatitudeString, locationLongitudeString));

                                    if(locationLatitudeDouble >= 40.094276 && locationLatitudeDouble <= 40.113541 && locationLongitudeDouble >= -3.710897 && locationLongitudeDouble <= -3.674977){
                                        city = "Seseña";
                                        cityID = 1;
                                        scrollPlans.setVisibility(View.VISIBLE);
                                    }

                                    if(locationLatitudeDouble >= 40.120195 && locationLatitudeDouble <= 40.139784 && locationLongitudeDouble >= -3.687630 && locationLongitudeDouble <= -3.660807){
                                        city = "El Quiñón";
                                        cityID = 2;
                                        scrollPlans.setVisibility(View.VISIBLE);
                                    }

                                    if(city != null){
                                        textTitle.setText("Estás en: "+city);
                                    }else{
                                        scrollPlans.setVisibility(View.INVISIBLE);
                                        textTitle.setText("Fuera de la sagra");
                                    }
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Enciende la localizacion", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    public void buttonInfoPlan1(View v) {
        planID = 1;
        openIntent();
    }

    public void buttonInfoPlan2(View v) {
        planID = 2;
        openIntent();
    }

    public void buttonInfoPlan3(View v) {
        planID = 3;
        openIntent();
    }

    public void buttonInfoPlan4(View v) {
        planID = 4;
        openIntent();
    }

    public void buttonInfoPlan5(View v) {
        planID = 5;
        openIntent();
    }

    public void buttonInfoPlan6(View v) {
        planID = 6;
        openIntent();
    }

    public void buttonInfoPlan7(View v) {
        planID = 7;
        openIntent();
    }

    public void buttonInfoPlan8(View v) {
        planID = 8;
        openIntent();
    }

    private void openIntent() {
        Intent intentInfoPlans = new Intent(this, InfoPlans.class);
        intentInfoPlans.putExtra("planID", planID);
        startActivity(intentInfoPlans);
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
                textPermission.setText("Permiso concedido");
                scrollPlans.setForeground(null);
            } else {
                scrollPlans.setVisibility(View.VISIBLE);
                scrollPlans.setForeground(getDrawable(R.drawable.gps_disabled));
                textPermission.setText("Permiso denegado");
                textTitle.setText("Permisos denegados");
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                if (cityID == 1){
                    linearLayout1.setBackgroundResource(R.drawable.background_blur_templodelsol);
                    linearLayout2.setBackgroundResource(R.drawable.background_blur_telepizza);
                    linearLayout3.setBackgroundResource(R.drawable.background_blur_nuova);
                    linearLayout4.setBackgroundResource(R.drawable.background_blur_vega);
                    linearLayout5.setBackgroundResource(R.drawable.background_blur_forchettone);
                    linearLayout6.setBackgroundResource(R.drawable.background_blur_cabalito);
                    linearLayout7.setBackgroundResource(R.drawable.background_blur_hongkong);
                    linearLayout8.setBackgroundResource(R.drawable.background_blur_asador);

                    imageViewPlan1.setImageResource(R.drawable.logo_templodelsol);
                    imageViewPlan2.setImageResource(R.drawable.logo_telepizza);
                    imageViewPlan3.setImageResource(R.drawable.logo_nuova);
                    imageViewPlan4.setImageResource(R.drawable.logo_vega);
                    imageViewPlan5.setImageResource(R.drawable.logo_forchettone);
                    imageViewPlan6.setImageResource(R.drawable.logo_cabalito);
                    imageViewPlan7.setImageResource(R.drawable.logo_hongkong);
                    imageViewPlan8.setImageResource(R.drawable.logo_asador);

                    imageViewPlan1.setBackgroundColor(0xCD000000);

                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);
                    linearLayout7.setVisibility(View.VISIBLE);
                    linearLayout8.setVisibility(View.VISIBLE);

                    imageViewPlan1.setVisibility(View.VISIBLE);
                    imageViewPlan2.setVisibility(View.VISIBLE);
                    imageViewPlan3.setVisibility(View.VISIBLE);
                    imageViewPlan4.setVisibility(View.VISIBLE);
                    imageViewPlan5.setVisibility(View.VISIBLE);
                    imageViewPlan6.setVisibility(View.VISIBLE);
                    imageViewPlan7.setVisibility(View.VISIBLE);
                    imageViewPlan8.setVisibility(View.VISIBLE);
                }
                if (cityID == 2){
                    linearLayout1.setBackgroundResource(R.drawable.background_blur_rollodelpollo);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout3.setVisibility(View.INVISIBLE);
                    linearLayout4.setVisibility(View.INVISIBLE);
                    linearLayout5.setVisibility(View.INVISIBLE);
                    linearLayout6.setVisibility(View.INVISIBLE);
                    linearLayout7.setVisibility(View.INVISIBLE);
                    linearLayout8.setVisibility(View.INVISIBLE);

                    imageViewPlan1.setImageResource(R.drawable.logo_rollodelpollo);
                    imageViewPlan2.setVisibility(View.INVISIBLE);
                    imageViewPlan3.setVisibility(View.INVISIBLE);
                    imageViewPlan4.setVisibility(View.INVISIBLE);
                    imageViewPlan5.setVisibility(View.INVISIBLE);
                    imageViewPlan6.setVisibility(View.INVISIBLE);
                    imageViewPlan7.setVisibility(View.INVISIBLE);
                    imageViewPlan8.setVisibility(View.INVISIBLE);

                    imageViewPlan1.setBackgroundColor(0xCDFFFFFF);
                }

                break;
            case 1:
                linearLayout1.setBackgroundResource(R.drawable.background_blur_vega);
                linearLayout2.setVisibility(View.INVISIBLE);
                linearLayout3.setVisibility(View.INVISIBLE);
                linearLayout4.setVisibility(View.INVISIBLE);
                linearLayout5.setVisibility(View.INVISIBLE);
                linearLayout6.setVisibility(View.INVISIBLE);
                linearLayout7.setVisibility(View.INVISIBLE);
                linearLayout8.setVisibility(View.INVISIBLE);

                imageViewPlan1.setImageResource(R.drawable.logo_vega);
                imageViewPlan2.setVisibility(View.INVISIBLE);
                imageViewPlan3.setVisibility(View.INVISIBLE);
                imageViewPlan4.setVisibility(View.INVISIBLE);
                imageViewPlan5.setVisibility(View.INVISIBLE);
                imageViewPlan6.setVisibility(View.INVISIBLE);
                imageViewPlan7.setVisibility(View.INVISIBLE);
                imageViewPlan8.setVisibility(View.INVISIBLE);

                imageViewPlan1.setBackgroundColor(0xCD000000);
                break;
            case 2:
                linearLayout1.setBackgroundResource(R.drawable.background_blur_hostal);
                linearLayout2.setVisibility(View.INVISIBLE);
                linearLayout3.setVisibility(View.INVISIBLE);
                linearLayout4.setVisibility(View.INVISIBLE);
                linearLayout5.setVisibility(View.INVISIBLE);
                linearLayout6.setVisibility(View.INVISIBLE);
                linearLayout7.setVisibility(View.INVISIBLE);
                linearLayout8.setVisibility(View.INVISIBLE);

                imageViewPlan1.setImageResource(R.drawable.logo_hostal);
                imageViewPlan2.setVisibility(View.INVISIBLE);
                imageViewPlan3.setVisibility(View.INVISIBLE);
                imageViewPlan4.setVisibility(View.INVISIBLE);
                imageViewPlan5.setVisibility(View.INVISIBLE);
                imageViewPlan6.setVisibility(View.INVISIBLE);
                imageViewPlan7.setVisibility(View.INVISIBLE);
                imageViewPlan8.setVisibility(View.INVISIBLE);

                imageViewPlan1.setBackgroundColor(0xCDFFFFFF);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openDBManager(View v) {
        Intent intentDBManager = new Intent(this, DatabaseManager.class);
        startActivity(intentDBManager);
    }

    public void abrirIntent(View v){
        Intent intent2 = new Intent(this, DatabaseManager.class);
        startActivity(intent2);
    }
}


