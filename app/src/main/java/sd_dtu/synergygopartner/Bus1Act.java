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

public class Bus1Act extends AppCompatActivity {

    EditText name,desig,contact,offTele,bussNature,YearCompany,noEmployee;
    String sname,sdesig,scontact,soffTele,sbussNature,sYearCompany,snoEmployee;
    Spinner typeCompany;
    String stypeCompany;
    ArrayAdapter<CharSequence> typecompadapter;
    DatabaseReference databaseReference;
    int i=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_1);

        name= (EditText)findViewById(R.id.nameeditText);
        desig= (EditText)findViewById(R.id.designationeditText);
        contact= (EditText)findViewById(R.id.phoneeditText);
        offTele= (EditText)findViewById(R.id.offteleditText);
        bussNature= (EditText)findViewById(R.id.busnatureeditText);
        YearCompany= (EditText)findViewById(R.id.yearseditText);
        noEmployee= (EditText)findViewById(R.id.EmpeditText);
        typeCompany= (Spinner) findViewById(R.id.spinnecompanytyper);

     databaseReference.child("file").child("Continue").child("Bussiness").addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             i=dataSnapshot.getValue(int.class);
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });


        typecompadapter = ArrayAdapter.createFromResource(this,R.array.company,R.layout.support_simple_spinner_dropdown_item);
        typecompadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeCompany.setAdapter(typecompadapter);

        typeCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        stypeCompany="C1";
                        break;
                    case 1:
                        stypeCompany="C2";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void onClickNextb1(View view){


            sname = name.getText().toString().trim();
            sdesig = desig.getText().toString().trim();
            scontact = contact.getText().toString().trim();
            soffTele = offTele.getText().toString().trim();
            sbussNature = bussNature.getText().toString().trim();
            sYearCompany = YearCompany.getText().toString().trim();
            snoEmployee = noEmployee.getText().toString().trim();


            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("file").child("Bussiness").child(" "+i).child("Person Contacted").setValue(sname);
            databaseReference.child("file").child("Bussiness").child(" "+i).child("Designation").setValue(sdesig);
            databaseReference.child("file").child("Bussiness").child(" "+i).child("Contact No.").setValue(scontact);
            databaseReference.child("file").child("Bussiness").child(" "+i).child("Office Telephone No.").setValue(soffTele);
            databaseReference.child("file").child("Bussiness").child(" "+i).child("Nature Of Business").setValue(sbussNature);
            databaseReference.child("file").child("Bussiness").child(" "+i).child("No. of Years of Company").setValue(sYearCompany);
            databaseReference.child("file").child("Bussiness").child(" "+i).child("No. of Employees").setValue(snoEmployee);
            databaseReference.child("file").child("Bussiness").child(" "+i).child("Type of Company").setValue(stypeCompany);

        databaseReference.child("file").child("Continue").child("Bussiness").setValue(i);
        i++;


            Intent intent = new Intent(Bus1Act.this, LocationPhoto.class);
            startActivity(intent);


    }
}
