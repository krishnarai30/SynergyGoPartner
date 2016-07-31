package sd_dtu.synergygopartner;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SubmitionAct extends Activity implements LocationListener {
    protected LocationManager locationManager;
    private double latitude = 0;
    private double longitude = 0;
    TextView lat, lng;
    Button refresh;
    ProgressDialog dialog;
    Button nxtbtn;
    DatabaseReference mDatabase;
    public  String file;
    String personcntctd,nofm,nowfm,nodfm,children,pensioner,empspouse,resstatusstr,vehiclestr,localitystr;
    EditText personcntctdet,nofmet,nowfmet,nodfmet,childrenet,pensioneret,empspouseet;
    ArrayAdapter<CharSequence> resstatusaa;
    ArrayAdapter<CharSequence> localityaa;
    ArrayAdapter<CharSequence> vehicleaa;
    Spinner resstatus,vehicle,locality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submition);

        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                refresh();
            }
        });

        dialog = new ProgressDialog(SubmitionAct.this);
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

        nxtbtn=(Button)findViewById(R.id.nxtbtn);
        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRSub1();
            }
        });
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

            mDatabase.child("reply").child("file").child(file).child("latitude").setValue(latitude);
            mDatabase.child("reply").child("file").child(file).child("longitude").setValue(longitude);

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
    public void onClickRSub1(){

        resstatus=(Spinner) findViewById(R.id.resstatusspinner);
        vehicle=(Spinner) findViewById(R.id.vehiclespinner);
        locality=(Spinner) findViewById(R.id.localityspinner);
        personcntctdet=(EditText)findViewById(R.id.persontactedet);
        nofmet=(EditText)findViewById(R.id.nofmet);
        nowfmet=(EditText)findViewById(R.id.nowfmet);
        nodfmet=(EditText)findViewById(R.id.nodfmet);
        childrenet=(EditText)findViewById(R.id.childrenet);
        pensioneret=(EditText)findViewById(R.id.pensioneret);
        empspouseet=(EditText)findViewById(R.id.empspouseet);


        personcntctd=personcntctdet.getText().toString();
        nofm=nofmet.getText().toString().trim();
        nowfm=nowfmet.getText().toString();
        nodfm=nodfmet.getText().toString().trim();
        children=childrenet.getText().toString().trim();
        pensioner=pensioneret.getText().toString();
        empspouse=empspouseet.getText().toString();

        resstatusaa=ArrayAdapter.createFromResource(this,R.array.resstatus,R.layout.support_simple_spinner_dropdown_item);
        resstatusaa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        resstatus.setAdapter(resstatusaa);
        resstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        resstatusstr="Self Owned";
                        break;
                    case 1:
                        resstatusstr="Owned By Relatives";
                        break;
                    case 2:
                        resstatusstr="Rented";
                        break;
                    case 3:
                        resstatusstr="Paying Guest";
                        break;
                    case 4:
                        resstatusstr="Owned by Parents";
                        break;
                    case 5:
                        resstatusstr="Owned by Friends";
                        break;
                    case 6:
                        resstatusstr="Company Accommodation";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                resstatusstr="other";
            }
        });

        localityaa=ArrayAdapter.createFromResource(this,R.array.locality,R.layout.support_simple_spinner_dropdown_item);
        localityaa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locality.setAdapter(localityaa);
        locality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        localitystr="Posh Locality";
                        break;
                    case 1:
                        localitystr="Village Area";
                        break;
                    case 2:
                        localitystr="Lodging";
                        break;
                    case 3:
                        localitystr="Upper Middle Class";
                        break;
                    case 4:
                        localitystr="Lower Middle Class";
                        break;
                    case 5:
                        localitystr="Slum Locality";
                        break;
                    case 6:
                        localitystr="Middle Class";
                        break;
                    case 7:
                        localitystr="Residential Complex";
                        break;
                    case 8:
                        localitystr="Others";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                localitystr="other";
            }
        });

        vehicleaa=ArrayAdapter.createFromResource(this,R.array.vehicle,R.layout.support_simple_spinner_dropdown_item);
        vehicleaa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        vehicle.setAdapter(vehicleaa);
        vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        vehiclestr="Two Wheeler";
                        break;
                    case 1:
                        vehiclestr="car";
                        break;
                    case 2:
                        vehiclestr="other";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                vehiclestr="other";
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();



        mDatabase.child("file").child("1").child("File").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                file=dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("reply").child("file").child(file).child("Address Type").setValue(personcntctd);
        mDatabase.child("reply").child("file").child(file).child("Agent ID").setValue(nofm);
        mDatabase.child("reply").child("file").child(file).child("Applicant's name").setValue(nowfm);
        mDatabase.child("reply").child("file").child(file).child("Contact Primary").setValue(nodfm);
        mDatabase.child("reply").child("file").child(file).child("Contact Secondary").setValue(children);
        mDatabase.child("reply").child("file").child(file).child("Address").setValue(pensioner);
        mDatabase.child("reply").child("file").child(file).child("Landmark").setValue(empspouse);
        mDatabase.child("reply").child("file").child(file).child("Locality").setValue(localitystr);
        mDatabase.child("reply").child("file").child(file).child("Vehicle Type").setValue(vehiclestr);
        mDatabase.child("reply").child("file").child(file).child("Residential Status").setValue(resstatusstr);



        Intent intent=new Intent(SubmitionAct.this,ResSub2.class);
        startActivity(intent);
    }
}