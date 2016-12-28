package sd_dtu.synergygopartner;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public class LocationPhoto extends Activity {

    protected LocationManager locationManager;
    private double latitude = 0;
    private double longitude = 0;
    TextView lat, lng,textView;
    Button refresh;
    FloatingActionButton ph;
    String fileno,agentid,Type;
    FloatingActionButton photo;
    ProgressDialog dialog;


    public static final int LOCATION_REQ_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_photo);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/OpenSans-Regular.ttf",true);

        fileno = getIntent().getStringExtra("file");
        agentid = getIntent().getStringExtra("agent");
        Type = getIntent().getStringExtra("type");

        photo = (FloatingActionButton) findViewById(R.id.pho);

        textView = (TextView) findViewById(R.id.adres);
        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                refresh();
            }
        });

        if (isNetworkAvailable(getApplicationContext())) {

//            dialog = new ProgressDialog(LocationPhoto.this);
//            dialog.show();
//            dialog.setMessage("Getting Coordinates");

            dialog = new ProgressDialog(this);
            dialog.setMessage("Getting Your location....");
            dialog.show();
            //dialog.setCancelable(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Toast.makeText(getApplicationContext(), "Taking too long", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alert = new AlertDialog.Builder(LocationPhoto.this);
                    alert.setTitle("The process was cancelled").
                            setMessage("The process was stopped by you due to any reason. \n The reason may be the " +
                                    "long time taken, this might happen if their is no proper connectivity for the process").show();
                }
            });

                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

                        if (ActivityCompat.checkSelfPermission(LocationPhoto.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(LocationPhoto.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQ_CODE);
                            return;
                        }
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, locationListener);
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, locationListener);
                        dialog.dismiss();
                    }
                else {
                    dialog.dismiss();

                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(LocationPhoto.this);
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
                }
    } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No Internet Connection...")
                    .setMessage("Click Here to set Active connection")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.error)
                    .show();
        }
    }




    protected void refresh() {

        super.onResume();
        this.onCreate(null);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_REQ_CODE){
            if(permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION)  {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)  {
                    //noinspection MissingPermission
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                }
            }
        }
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                Address address=addresses.get(0);
                String useradd="";
                for(int i=0;i<address.getMaxAddressLineIndex();i++)
                    useradd=useradd+address.getAddressLine(i).toString()+"\n";
                useradd=useradd+(address.getCountryName().toString());
                textView.setText(useradd.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

           // dialog.show();
            latitude = location.getLatitude();
            longitude =location.getLongitude();
            if (latitude != 0 && longitude != 0){

                lat.setText("Latitude is :" +location.getLatitude());
                lng.setText("Longitude is :" +location.getLongitude());

//                dialog.dismiss();
            }

            if(isNetworkAvailable(getApplicationContext())){
                DatabaseReference mref = FirebaseDatabase.getInstance().getReference();

                mref.child("Data").child(Type).child(fileno).child("Location Found").setValue(textView.getText().toString());
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                alertDialogBuilder.setTitle("No Internet Connection...")
                        .setMessage("Click Here to set Active connection")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.drawable.error)
                        .show();
            }

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderDisabled(String provider) {

           // Toast.makeText(this, "Provider Disabled!!!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }

        @Override
        public void onProviderEnabled(String provider) {

           // Toast.makeText(this, "Provider Enabled!!!", Toast.LENGTH_LONG).show();
            dialog.dismiss();

        }

    };

    public void onc(View view) {

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT , null);
//        intent.setType("image/*");
//        startActivityForResult(intent, 1);

        Intent cam_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = getFile();
     //   cam_intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
        startActivityForResult(cam_intent,1);

    }

    private File getFile()
    {
        SharedPreferences number = getPreferences(MODE_PRIVATE);
        int i = number.getInt("start",0);
        String x = Integer.toString(i);
        i++;
        File folder = new File("sdcard/synergy_partner");
        if(!folder.exists())
        {
            folder.mkdir();
        }
        File image_file = new File(folder,"image_file"+x+".jpg");
        return image_file;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setMessage("Wait while the image is uploaded....");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

            Log.d("Image URI","in here");

            Uri selectedImage = data.getData();

            Log.d("Image URI",selectedImage.toString());

            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageRef = storage.getReferenceFromUrl("gs://synergy-go.appspot.com");

            StorageReference photoRef = storageRef.child(Type).child(fileno).child(selectedImage.getLastPathSegment());
            photoRef.putFile(selectedImage);

           progressDialog.dismiss();

            Toast.makeText(this, "Image Uploaded", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LocationPhoto.this,AssignmentChooseAct.class);
            intent.putExtra("Agent",agentid);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),"Image not uploaded",Toast.LENGTH_LONG).show();
           // Intent intent = new Intent(LocationPhoto.this,LocationPhoto.class);

           // startActivity(intent);
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}



//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        if(ContextCompat.checkSelfPermission(LocationPhoto.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                Log.d("loc","Got the location");
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,100,LocationPhoto.this);
//                dialog.dismiss();
//            }
//            else {
//                Toast.makeText(getApplicationContext(),"Enable the location",Toast.LENGTH_LONG).show();
//            }
//        }

//
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            if (ActivityCompat.checkSelfPermission(Locationphoto.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED &&
//                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
//                        0, this);
//
//            }
//
//        }  if (locationManager
//                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//            locationManager.requestLocationUpdates(
//                    LocationManager.NETWORK_PROVIDER,10000,100 , this);
//        }
//        else {
//            dialog.dismiss();

//            AlertDialog.Builder builder =
//                    new AlertDialog.Builder(this);
//            final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
//            final String message = "Enable either GPS or any other location"
//                    + " service to find current location.  Click OK to go to"
//                    + " location services settings to let you do so.";
//            builder.setTitle("Enable Location");
//
//            builder.setMessage(message)
//                    .setPositiveButton("OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface d, int id) {
//                                    startActivity(new Intent(action));
//                                    d.dismiss();
//                                }
//                            })
//                    .setNegativeButton("Cancel",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface d, int id) {
//                                    d.cancel();
//                                }
//                            });
//            builder.create().show();

//            Toast.makeText(getApplicationContext(), "Enable Location", Toast.LENGTH_LONG).show();
//        }
