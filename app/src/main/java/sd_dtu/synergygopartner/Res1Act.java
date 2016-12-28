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

public class Res1Act extends AppCompatActivity {

    EditText name,noFamilyMem,workingMem,dependMem,children,spouseEmp;
    String sname,snoFamilyMem,sworkingMem,sdependMem,schildren,sspouseEmp,sresidence,smaritalStatus,slocality;
    String filestr,agentid;
    Spinner residence,maritalStatus,locality;
    ArrayAdapter<CharSequence> residenceadapter;
    ArrayAdapter<CharSequence> maritaladapter;
    ArrayAdapter<CharSequence> localityadapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res1);

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


                Intent intent = new Intent(Res1Act.this, SerRes2Act.class);
                intent.putExtra("file", filestr);
                intent.putExtra("agent", agentid);
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
