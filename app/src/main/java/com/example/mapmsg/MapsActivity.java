package com.example.mapmsg;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnSms;
    LocationManager locationManager;
    Geocoder geocoder;
    List<Address> addresList;
  int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnSms = (Button)findViewById(R.id.send_msg);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this, Locale.getDefault());
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            mMap.setMyLocationEnabled(true);
        }

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Location locationManagerSP = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

        if(locationGPS != null){
            final double lat = locationGPS.getLatitude();
            final double lng = locationGPS.getLongitude();

            btnSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        addresList = geocoder.getFromLocation(lat,lng,1);

                        String address = addresList.get(0).getAddressLine(0);
                        String area = addresList.get(0).getLocality();
                        String city = addresList.get(0).getAdminArea();
                        String country = addresList.get(0).getCountryName();
                        String pin = addresList.get(0).getPostalCode();

                        String fulladd = address+", "+area+", "+city+", "+country+", "+pin;

                        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage("1234567890", null, "HELP !\n"+fulladd, pi,null);
                        Toast.makeText(MapsActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(MapsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(locationNetwork != null){
            final double lat = locationNetwork.getLatitude();
            final double lng = locationNetwork.getLongitude();

            btnSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        addresList = geocoder.getFromLocation(lat,lng,1);

                        String address = addresList.get(0).getAddressLine(0);
                        String area = addresList.get(0).getLocality();
                        String city = addresList.get(0).getAdminArea();
                        String country = addresList.get(0).getCountryName();
                        String pin = addresList.get(0).getPostalCode();

                        String fulladd = address+", "+area;
                        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage("9110113248", null, "HELP !\n"+fulladd, pi,null);
                        Toast.makeText(MapsActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(MapsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(locationManagerSP != null){
            final double lat = locationManagerSP.getLatitude();
            final double lng = locationManagerSP.getLongitude();
            btnSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        addresList = geocoder.getFromLocation(lat,lng,1);

                        String address = addresList.get(0).getAddressLine(0);
                        String area = addresList.get(0).getLocality();
                        String city = addresList.get(0).getAdminArea();
                        String country = addresList.get(0).getCountryName();
                        String pin = addresList.get(0).getPostalCode();

                        String fulladd = address+", "+area;
                        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage("9110113248", null, "HELP !\n"+fulladd, pi,null);
                        Toast.makeText(MapsActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(MapsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            btnSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Toast.makeText(MapsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();

                }
            });
            Toast.makeText(this, "Not able to fetch location", Toast.LENGTH_SHORT).show();
        }


    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action, keycode;
        action = event.getAction();
        keycode = event.getKeyCode();

        switch(keycode){
            case KeyEvent.KEYCODE_VOLUME_UP:
            {
                if(KeyEvent.ACTION_UP==action){
                    count++;
                    if (count > 0) {

                        try{
                            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                            PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                            SmsManager sms=SmsManager.getDefault();
                            sms.sendTextMessage("1234567890", null, " here will be your message", pi,null);
                            Toast.makeText(MapsActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(MapsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                break;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}

