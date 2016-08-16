package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Res1Act extends AppCompatActivity {

    EditText name,noFamilyMem,workingMem,dependMem,children,spouseEmp;
    String sname,snoFamilyMem,sworkingMem,sdependMem,schildren,sspouseEmp,sresidence,smaritalStatus,slocality;
    Spinner residence,maritalStatus,locality;
    ArrayAdapter<CharSequence> residenceadapter;
    ArrayAdapter<CharSequence> maritaladapter;
    ArrayAdapter<CharSequence> localityadapter;
    DatabaseReference databaseReference;
    long xyz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res1);

        name= (EditText)findViewById(R.id.Personnameet);
        noFamilyMem=(EditText)findViewById(R.id.FamilymemeditText);
        workingMem=(EditText)findViewById(R.id.workingmemeditText);
        dependMem=(EditText)findViewById(R.id.dependenteditText);
        children=(EditText)findViewById(R.id.ChildreneditText);
        spouseEmp=(EditText)findViewById(R.id.SpouseeditText);
        residence =(Spinner) findViewById(R.id.ResStatusSpinner);
        maritalStatus=(Spinner) findViewById(R.id.MaritalStatusSpinner);
        locality=(Spinner) findViewById(R.id.Localityspinner);



        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("file").child("Continue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                xyz= dataSnapshot.child("Residence").getChildrenCount()+1;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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


        sname=name.getText().toString().trim();
        snoFamilyMem=noFamilyMem.getText().toString().trim();
        sworkingMem=workingMem.getText().toString().trim();
        sdependMem=dependMem.getText().toString().trim();
        schildren=children.getText().toString().trim();
        sspouseEmp=spouseEmp.getText().toString().trim();




        databaseReference= FirebaseDatabase.getInstance().getReference();
       long xxyz=xyz+1;
        databaseReference.child("file").child("Residence").child(" "+xxyz+1).child("Name of Person Contacted").setValue(sname);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("Residential Status").setValue(sresidence);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("Marital Status").setValue(smaritalStatus);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("No Of Family Members").setValue(snoFamilyMem);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("Working Members").setValue(sworkingMem);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("Dependent Members").setValue(sdependMem);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("Children").setValue(schildren);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("Spouse Emp").setValue(sspouseEmp);
        databaseReference.child("file").child("Residence").child(" "+xxyz).child("Locality").setValue(slocality);







        Intent intent=new Intent(Res1Act.this,SerRes2Act.class);
        intent.putExtra("choice",xxyz);
        startActivity(intent);
    }
}
