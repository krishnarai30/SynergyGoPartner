package sd_dtu.synergygopartner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Res1Act extends AppCompatActivity {

    EditText name,noFamilyMem,workingMem,dependMem,children,spouseEmp,registration,carpetArea,politicalInflu,otherRemarks;
    String sname,snoFamilyMem,sworkingMem,sdependMem,schildren,sspouseEmp,sresidence,smaritalStatus,slocality;
    Spinner residence,maritalStatus,locality;
    ArrayAdapter<CharSequence> residenceadapter;
    ArrayAdapter<CharSequence> maritaladapter;
    ArrayAdapter<CharSequence> localityadapter;
    DatabaseReference databaseReference;


    String sregistration,scarpetArea,spoliticalInflu,sotherRemarks,svehicle;
    Spinner vehicle;
    String filestr,agentid;
    ArrayAdapter<CharSequence> vehicleadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_forms);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/RobotoCondensed-Regular.ttf",true);

        getSupportActionBar().setTitle("Fill the Details");

        name= (EditText)findViewById(R.id.Personnameet);
        noFamilyMem=(EditText)findViewById(R.id.FamilymemeditText);
        workingMem=(EditText)findViewById(R.id.workingmemeditText);
        dependMem=(EditText)findViewById(R.id.dependenteditText);
        children=(EditText)findViewById(R.id.ChildreneditText);
        spouseEmp=(EditText)findViewById(R.id.SpouseeditText);
        residence =(Spinner) findViewById(R.id.ResStatusSpinner);
        maritalStatus=(Spinner) findViewById(R.id.MaritalStatusSpinner);
        locality=(Spinner) findViewById(R.id.Localityspinner);
        registration= (EditText)findViewById(R.id.RegNoeditText);
        carpetArea= (EditText)findViewById(R.id.CarpetAreaeditText);
        politicalInflu= (EditText)findViewById(R.id.PoliticaleditText);
        otherRemarks= (EditText)findViewById(R.id.OtherRemarkseditText);
        vehicle=(Spinner) findViewById(R.id.Vehiclespinner);


        agentid = getIntent().getStringExtra("agent");

        databaseReference=FirebaseDatabase.getInstance().getReference();

        residenceadapter=ArrayAdapter.createFromResource(this,R.array.resstatus,R.layout.support_simple_spinner_dropdown_item);
        residenceadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        residence.setAdapter(residenceadapter);

        residence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        sresidence="Self Owned";
                        break;
                    case 1:
                        sresidence="Owned By Relatives";
                        break;
                    case 2:
                        sresidence=" Rented";
                        break;
                    case 3:
                        sresidence="Paying Guest";
                        break;
                    case 4:
                        sresidence="Owned by Parents";
                        break;
                    case 5:
                        sresidence=" Owned by Friends";
                        break;
                    case 6:
                        sresidence="Company Accommodation";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        vehicleadapter=ArrayAdapter.createFromResource(this,R.array.vehicle,R.layout.support_simple_spinner_dropdown_item);
        vehicleadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        vehicle.setAdapter(vehicleadapter);

        vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        svehicle="Two Wheeler";
                        break;
                    case 1:
                        svehicle="Car";
                        break;
                    case 2:
                        svehicle="other";

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        maritaladapter=ArrayAdapter.createFromResource(this,R.array.marital,R.layout.support_simple_spinner_dropdown_item);
        maritaladapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        maritalStatus.setAdapter(maritaladapter);

        maritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        smaritalStatus = "Single";
                        break;
                    case 1:
                        smaritalStatus = "Married";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        localityadapter=ArrayAdapter.createFromResource(this,R.array.locality,R.layout.support_simple_spinner_dropdown_item);
        localityadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locality.setAdapter(localityadapter);

        locality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {

                    case 0:
                        slocality="Posh Locality";
                        break;
                    case 1:
                        slocality="Village Area";
                        break;
                    case 2:
                        slocality="Lodging";
                        break;
                    case 3:
                        slocality="Upper Middle Class";
                        break;
                    case 4:
                        slocality="Lower Middle Class";
                        break;
                    case 5:
                        slocality="Slum Locality";
                        break;
                    case 6:
                        slocality="Middle Class";
                        break;
                    case 7:
                        slocality="Residential Complex";
                        break;
                    case 8:
                        slocality="Others";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }



    public void onClicknextsr1(View view){


        filestr=getIntent().getExtras().getString("file");
        sname=name.getText().toString().trim();
        snoFamilyMem=noFamilyMem.getText().toString().trim();
        sworkingMem=workingMem.getText().toString().trim();
        sdependMem=dependMem.getText().toString().trim();
        schildren=children.getText().toString().trim();
        sspouseEmp=spouseEmp.getText().toString().trim();

        sregistration=registration.getText().toString().trim();
        scarpetArea=carpetArea.getText().toString().trim();
        spoliticalInflu=politicalInflu.getText().toString().trim();
        sotherRemarks=otherRemarks.getText().toString().trim();

        if(TextUtils.isEmpty(sregistration)||TextUtils.isEmpty(scarpetArea)||TextUtils.isEmpty(spoliticalInflu)||TextUtils.isEmpty(sotherRemarks)) {
            Toast.makeText(getApplicationContext(),"Please enter all fields..",Toast.LENGTH_LONG).show();
        }

        if(isNetworkAvailable(getApplicationContext())) {

            if(TextUtils.isEmpty(sname)||TextUtils.isEmpty(snoFamilyMem)||TextUtils.isEmpty(sworkingMem)||TextUtils.isEmpty(sdependMem)
                    ||TextUtils.isEmpty(schildren)||TextUtils.isEmpty(sspouseEmp)) {
                Toast.makeText(getApplicationContext(),"Please fill all FIELDS !",Toast.LENGTH_LONG).show();
            } else {


                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Data").child("Residence").child(filestr).child("Name of Person Contacted").setValue(sname);
                databaseReference.child("Data").child("Residence").child(filestr).child("Residential Status").setValue(sresidence);
                databaseReference.child("Data").child("Residence").child(filestr).child("Marital Status").setValue(smaritalStatus);
                databaseReference.child("Data").child("Residence").child(filestr).child("No Of Family Members").setValue(snoFamilyMem);
                databaseReference.child("Data").child("Residence").child(filestr).child("Working Members").setValue(sworkingMem);
                databaseReference.child("Data").child("Residence").child(filestr).child("Dependent Members").setValue(sdependMem);
                databaseReference.child("Data").child("Residence").child(filestr).child("Children").setValue(schildren);
                databaseReference.child("Data").child("Residence").child(filestr).child("Spouse Emp").setValue(sspouseEmp);
                databaseReference.child("Data").child("Residence").child(filestr).child("Locality").setValue(slocality);
                databaseReference.child("Data").child("Residence").child(filestr).child("Vehicle Seen").setValue(svehicle);
                databaseReference.child("Data").child("Residence").child(filestr).child("Registeration No").setValue(sregistration);
                databaseReference.child("Data").child("Residence").child(filestr).child("Carpet Area").setValue(scarpetArea);
                databaseReference.child("Data").child("Residence").child(filestr).child("Political Influence").setValue(spoliticalInflu);
                databaseReference.child("Data").child("Residence").child(filestr).child("Other Remarks").setValue(sotherRemarks);



                Intent intent = new Intent(Res1Act.this, LocationPhoto.class);
                intent.putExtra("file", filestr);
                intent.putExtra("agent", agentid);
                intent.putExtra("type","Residence");
                startActivity(intent);
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

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
