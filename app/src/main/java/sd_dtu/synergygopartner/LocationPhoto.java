package sd_dtu.synergygopartner;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LocationPhoto extends Activity implements LocationListener {

    protected LocationManager locationManager;
    private double latitude = 0;
    private double longitude = 0;
    TextView lat, lng;
    Button refresh;
    Button photo;
    ProgressDialog dialog;

    Button nbtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_photo);

        nbtn=(Button)findViewById(R.id.nbtn);
        nbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        photo=(Button)findViewById(R.id.pho);

        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                refresh();
            }
        });

        dialog = new ProgressDialog(LocationPhoto.this);
        dialog.show();
        dialog.setMessage("Getting Coordinates");

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {


                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
                        100, this);

            }

        }  if (locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,10000,100 , this);
        }
        else {
            dialog.dismiss();

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);
            final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            final String message = "Enable either GPS or any other location"
                    + " service to find current location.  Click OK to go to"
                    + " location services settings to let you do so.";
            builder.setTitle("Enable Location");

            builder.setMessage(message)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface d, int id) {
                                    startActivity(new Intent(action));
                                    d.dismiss();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface d, int id) {
                                    d.cancel();
                                }
                            });
            builder.create().show();

            Toast.makeText(getApplicationContext(), "Enable Location", Toast.LENGTH_LONG).show();
        }


    }
    protected void refresh() {

        super.onResume();
        this.onCreate(null);

    }

    @Override
    public void onLocationChanged(Location location) {

        dialog.show();
        latitude = location.getLatitude();
        longitude =location.getLongitude();
        if (latitude != 0 && longitude != 0){

            lat.setText("Latitude is :" +location.getLatitude());
            lng.setText("Longitude is :" +location.getLongitude());

            dialog.dismiss();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {


        Toast.makeText(this, "Provider Disabled!!!", Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(this, "Provider Enabled!!!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }


    public void onc(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT , null);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 1 && resultCode == RESULT_OK && data != null ){

            Uri selectedImage = data.getData();

            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageRef = storage.getReferenceFromUrl("gs://synergy-go.appspot.com");

            StorageReference photoRef = storageRef.child(selectedImage.getLastPathSegment());
            photoRef.putFile(selectedImage);

            Toast.makeText(this, "image uploaded", Toast.LENGTH_SHORT).show();

        }
    }



}



