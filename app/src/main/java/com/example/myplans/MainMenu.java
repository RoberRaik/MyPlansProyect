package com.example.myplans;

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

    TextView textLocation, textTitle, textPermission, textprueba, textCity;
    LinearLayout planSeseñaViejo1, planSeseñaViejo2, planSeseñaViejo3, planSeseñaViejo4, planSeseñaViejo5, planSeseñaViejo6, planSeseñaViejo7, planSeseñaViejo8, planQuiñon1, planQuiñon2, planQuiñon3, planQuiñon4, planQuiñon5, planQuiñon6, planQuiñon7, planQuiñon8, planSeseñaNuevo1, planSeseñaNuevo2, planSeseñaNuevo3, planSeseñaNuevo4, planSeseñaNuevo5, planSeseñaNuevo6, planSeseñaNuevo7, planSeseñaNuevo8;
    ImageView imageViewPlanSeseñaViejo1, imageViewPlanSeseñaViejo2, imageViewPlanSeseñaViejo3, imageViewPlanSeseñaViejo4, imageViewPlanSeseñaViejo5, imageViewPlanSeseñaViejo6, imageViewPlanSeseñaViejo7, imageViewPlanSeseñaViejo8, imageViewPlanQuiñon1, imageViewPlanQuiñon2, imageViewPlanQuiñon3, imageViewPlanQuiñon4, imageViewPlanQuiñon5, imageViewPlanQuiñon6, imageViewPlanQuiñon7, imageViewPlanQuiñon8, imageViewPlanSeseñaNuevo1, imageViewPlanSeseñaNuevo2, imageViewPlanSeseñaNuevo3, imageViewPlanSeseñaNuevo4, imageViewPlanSeseñaNuevo5, imageViewPlanSeseñaNuevo6, imageViewPlanSeseñaNuevo7, imageViewPlanSeseñaNuevo8;
    ScrollView scrollViewPlansSeseñaViejo, scrollViewPlansSeseñaNuevo, scrollViewPlansQuiñon;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    String city;
    int planID;
    int cityID;

    double locationLatitudeDouble, locationLongitudeDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLocation = findViewById(R.id.textViewLocation);
        textCity = findViewById(R.id.textViewCity);
        textTitle = findViewById(R.id.textViewTitle);
        textPermission = findViewById(R.id.textViewPermission);
        scrollViewPlansSeseñaViejo = findViewById(R.id.scrollViewPlansSeseñaViejo);
        scrollViewPlansSeseñaNuevo = findViewById(R.id.scrollViewPlansSeseñaNuevo);
        scrollViewPlansQuiñon = findViewById(R.id.scrollViewPlansQuiñon);

        textprueba = findViewById(R.id.textViewprueba);

        imageViewPlanSeseñaViejo1 = findViewById(R.id.imageViewPlanSeseñaViejo1);
        imageViewPlanSeseñaViejo2 = findViewById(R.id.imageViewPlanSeseñaViejo2);
        imageViewPlanSeseñaViejo3 = findViewById(R.id.imageViewPlanSeseñaViejo3);
        imageViewPlanSeseñaViejo4 = findViewById(R.id.imageViewPlanSeseñaViejo4);
        imageViewPlanSeseñaViejo5 = findViewById(R.id.imageViewPlanSeseñaViejo5);
        imageViewPlanSeseñaViejo6 = findViewById(R.id.imageViewPlanSeseñaViejo6);
        imageViewPlanSeseñaViejo7 = findViewById(R.id.imageViewPlanSeseñaViejo7);
        imageViewPlanSeseñaViejo8 = findViewById(R.id.imageViewPlanSeseñaViejo8);

        planSeseñaViejo1 = findViewById(R.id.planSeseñaViejo1);
        planSeseñaViejo2 = findViewById(R.id.planSeseñaViejo2);
        planSeseñaViejo3 = findViewById(R.id.planSeseñaViejo3);
        planSeseñaViejo4 = findViewById(R.id.planSeseñaViejo4);
        planSeseñaViejo5 = findViewById(R.id.planSeseñaViejo5);
        planSeseñaViejo6 = findViewById(R.id.planSeseñaViejo6);
        planSeseñaViejo7 = findViewById(R.id.planSeseñaViejo7);
        planSeseñaViejo8 = findViewById(R.id.planSeseñaViejo8);

        imageViewPlanQuiñon1 = findViewById(R.id.imageViewPlanQuiñon1);
        imageViewPlanQuiñon2 = findViewById(R.id.imageViewPlanQuiñon2);
        imageViewPlanQuiñon3 = findViewById(R.id.imageViewPlanQuiñon3);
        imageViewPlanQuiñon4 = findViewById(R.id.imageViewPlanQuiñon4);
        imageViewPlanQuiñon5 = findViewById(R.id.imageViewPlanQuiñon5);
        imageViewPlanQuiñon6 = findViewById(R.id.imageViewPlanQuiñon6);
        imageViewPlanQuiñon7 = findViewById(R.id.imageViewPlanQuiñon7);
        imageViewPlanQuiñon8 = findViewById(R.id.imageViewPlanQuiñon8);

        planQuiñon1 = findViewById(R.id.planQuiñon1);
        planQuiñon2 = findViewById(R.id.planQuiñon2);
        planQuiñon3 = findViewById(R.id.planQuiñon3);
        planQuiñon4 = findViewById(R.id.planQuiñon4);
        planQuiñon5 = findViewById(R.id.planQuiñon5);
        planQuiñon6 = findViewById(R.id.planQuiñon6);
        planQuiñon7 = findViewById(R.id.planQuiñon7);
        planQuiñon8 = findViewById(R.id.planQuiñon8);

        imageViewPlanSeseñaNuevo1 = findViewById(R.id.imageViewPlanSeseñaNuevo1);
        imageViewPlanSeseñaNuevo2 = findViewById(R.id.imageViewPlanSeseñaNuevo2);
        imageViewPlanSeseñaNuevo3 = findViewById(R.id.imageViewPlanSeseñaNuevo3);
        imageViewPlanSeseñaNuevo4 = findViewById(R.id.imageViewPlanSeseñaNuevo4);
        imageViewPlanSeseñaNuevo5 = findViewById(R.id.imageViewPlanSeseñaNuevo5);
        imageViewPlanSeseñaNuevo6 = findViewById(R.id.imageViewPlanSeseñaNuevo6);
        imageViewPlanSeseñaNuevo7 = findViewById(R.id.imageViewPlanSeseñaNuevo7);
        imageViewPlanSeseñaNuevo8 = findViewById(R.id.imageViewPlanSeseñaNuevo8);

        planSeseñaNuevo1 = findViewById(R.id.planSeseñaNuevo1);
        planSeseñaNuevo2 = findViewById(R.id.planSeseñaNuevo2);
        planSeseñaNuevo3 = findViewById(R.id.planSeseñaNuevo3);
        planSeseñaNuevo4 = findViewById(R.id.planSeseñaNuevo4);
        planSeseñaNuevo5 = findViewById(R.id.planSeseñaNuevo5);
        planSeseñaNuevo6 = findViewById(R.id.planSeseñaNuevo6);
        planSeseñaNuevo7 = findViewById(R.id.planSeseñaNuevo7);
        planSeseñaNuevo8 = findViewById(R.id.planSeseñaNuevo8);


        scrollViewPlansSeseñaViejo.setVisibility(View.INVISIBLE);
        scrollViewPlansSeseñaNuevo.setVisibility(View.INVISIBLE);
        scrollViewPlansQuiñon.setVisibility(View.INVISIBLE);

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
                                    requestNewLocationData();
                                    locationLatitudeDouble = location.getLatitude();
                                    locationLongitudeDouble = location.getLongitude();
                                    updateScrollViews();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Activa la localizacion", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    public void updateScrollViews(){
        String locationLatitudeString = String.valueOf(locationLatitudeDouble);
        String locationLongitudeString = String.valueOf(locationLongitudeDouble);

        textLocation.setText(String.format("%s %s", locationLatitudeString, locationLongitudeString));

        if(locationLatitudeDouble >= 40.094276 && locationLatitudeDouble <= 40.113541 && locationLongitudeDouble >= -3.710897 && locationLongitudeDouble <= -3.674977){
            city = "Seseña Viejo";
            cityID = 1;
        }

        if(locationLatitudeDouble >= 40.120195 && locationLatitudeDouble <= 40.139784 && locationLongitudeDouble >= -3.687630 && locationLongitudeDouble <= -3.660807){
            city = "El Quiñón";
            cityID = 2;
        }

        if(locationLatitudeDouble >= 40.0990 && locationLatitudeDouble <= 40.1163 && locationLongitudeDouble >= -3.6669 && locationLongitudeDouble <= -3.6301){
            city = "Seseña Nuevo";
            cityID = 3;
        }

        if(city != null){
            textTitle.setText("Estás en: "+city);
            String cityString = String.valueOf(cityID);
            textCity.setText(cityString);
            switch(cityID) {
                case 1:
                    scrollViewPlansSeseñaViejo.setVisibility(View.VISIBLE);
                    scrollViewPlansSeseñaNuevo.setVisibility(View.GONE);
                    scrollViewPlansQuiñon.setVisibility(View.GONE);
                    break;
                case 2:
                    scrollViewPlansSeseñaViejo.setVisibility(View.GONE);
                    scrollViewPlansSeseñaNuevo.setVisibility(View.GONE);
                    scrollViewPlansQuiñon.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    scrollViewPlansSeseñaViejo.setVisibility(View.GONE);
                    scrollViewPlansSeseñaNuevo.setVisibility(View.VISIBLE);
                    scrollViewPlansQuiñon.setVisibility(View.GONE);
                    break;
            }

        }else{
            scrollViewPlansSeseñaViejo.setVisibility(View.INVISIBLE);
            scrollViewPlansSeseñaNuevo.setVisibility(View.INVISIBLE);
            scrollViewPlansQuiñon.setVisibility(View.INVISIBLE);
            textTitle.setText("Fuera de la sagra");
        }
    }

    public void refresh(View v){
        getLastLocation();
    }

    public void buttonInfoPlan1(View v) {
        switch(cityID) {
            case 1:
                planID = 1001;
                break;
            case 2:
                planID = 2001;
                break;
            case 3:
                planID = 3001;
                break;
        }
        openIntent();
    }

    public void buttonInfoPlan2(View v) {
        switch(cityID) {
            case 1:
                planID = 1002;
                break;
            case 2:
                planID = 2002;
                break;
            case 3:
                planID = 3002;
                break;
        }
        openIntent();
    }

    public void buttonInfoPlan3(View v) {
        switch(cityID) {
            case 1:
                planID = 1003;
                break;
            case 2:
                planID = 2003;
                break;
            case 3:
                planID = 3003;
                break;
        }
        openIntent();
    }

    public void buttonInfoPlan4(View v) {
        switch(cityID) {
            case 1:
                planID = 1004;
                break;
            case 2:
                planID = 2004;
                break;
            case 3:
                planID = 3004;
                break;
        }
        openIntent();
    }

    public void buttonInfoPlan5(View v) {
        switch(cityID) {
            case 1:
                planID = 1005;
                break;
            case 2:
                planID = 2005;
                break;
            case 3:
                planID = 3005;
                break;
        }
        openIntent();
    }

    public void buttonInfoPlan6(View v) {
        switch(cityID) {
            case 1:
                planID = 1006;
                break;
            case 2:
                planID = 2006;
                break;
            case 3:
                planID = 3006;
                break;
        }
        openIntent();
    }

    public void buttonInfoPlan7(View v) {
        switch(cityID) {
            case 1:
                planID = 1007;
                break;
            case 2:
                planID = 2007;
                break;
            case 3:
                planID = 3007;
                break;
        }
        openIntent();
    }

    public void buttonInfoPlan8(View v) {
        switch(cityID) {
            case 1:
                planID = 1008;
                break;
            case 2:
                planID = 2008;
                break;
            case 3:
                planID = 3008;
                break;
        }
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
            } else {
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openDBManager(View v) {
        Intent intentDBManager = new Intent(this, DatabaseManager.class);
        startActivity(intentDBManager);
    }

}


