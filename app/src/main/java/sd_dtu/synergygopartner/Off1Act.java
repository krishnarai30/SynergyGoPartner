package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Off1Act extends AppCompatActivity {

    EditText name,designation,mobile,joinDate,desigApp,noYears;
    String sname,sdesignation,smobile,sjoinDate,sdesigApp,snoYears;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off1);

        name=(EditText)findViewById(R.id.personconteditText);
        designation=(EditText)findViewById(R.id.desigPCeditText);
        mobile=(EditText)findViewById(R.id.mobPCeditText);
        joinDate=(EditText)findViewById(R.id.DateAppjoineditText);
        desigApp=(EditText)findViewById(R.id.DesgAppeditText);
        noYears=(EditText)findViewById(R.id.yearseditText);
    }
    public void onClickNextso1(View view){
        sname=name.getText().toString().trim();
        sdesignation=designation.getText().toString().trim();
        smobile=mobile.getText().toString().trim();
        sjoinDate=joinDate.getText().toString().trim();
        sdesigApp=desigApp.getText().toString().trim();
        snoYears=noYears.getText().toString().trim();


        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("file").child("Office").child("Name of Person Contacted").setValue(sname);
        databaseReference.child("file").child("Office").child("Designation of Person Contacted").setValue(sdesignation);
        databaseReference.child("file").child("Office").child("Mobile").setValue(smobile);
        databaseReference.child("file").child("Office").child("Date of Joining of Applicant").setValue(sjoinDate);
        databaseReference.child("file").child("Office").child("Designation of Applicant").setValue(sdesigApp);
        databaseReference.child("file").child("Office").child("No. of years in present Employment ").setValue(snoYears);



        Intent intent=new Intent(Off1Act.this,Off2Act.class);
        startActivity(intent);
    }
}
