package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SerOff1Act extends AppCompatActivity {

    EditText name,designation,mobile,dateJoining,designationApplicant,noOfYears;
     String sname,sdesignation,smobile,sdateJoining,sdesignationApplicant,snoOfYears;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_off1);

        name =(EditText)findViewById(R.id.personconteditText);
        designation=(EditText)findViewById(R.id.desigPCeditText);
        mobile=(EditText)findViewById(R.id.mobPCeditText);
        dateJoining=(EditText)findViewById(R.id.DateAppjoineditText);
        designationApplicant=(EditText)findViewById(R.id.DesgAppeditText);
        noOfYears=(EditText)findViewById(R.id.yearseditText);

    }
    public void onClickNextso1(View view){


        sname=name.getText().toString().trim();
        sdesignation=designation.getText().toString().trim();
        smobile=mobile.getText().toString().trim();
        sdateJoining=dateJoining.getText().toString().trim();
        sdesignationApplicant=designationApplicant.getText().toString().trim();
        snoOfYears=noOfYears.getText().toString().trim();


        Intent intent=new Intent(SerOff1Act.this,LocationPhoto.class);
        startActivity(intent);
    }
}
