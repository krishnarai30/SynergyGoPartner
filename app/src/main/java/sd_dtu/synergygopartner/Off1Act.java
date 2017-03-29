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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Off1Act extends AppCompatActivity {

    EditText name,designation,mobile,joinDate,desigApp,noYears,companyNature,remarks;
    String sname,sdesignation,smobile,sjoinDate,sdesigApp,snoYears,scompanyNature,sremarks,sjobType,sworkOrg,sjobTransfer;
    String filestr,agentid;
    Spinner jobType,workOrg,jobTransfer;
    ArrayAdapter<CharSequence> jobtypeadapter;
    ArrayAdapter<CharSequence> workorgadapter;
    ArrayAdapter<CharSequence> jobtransferadapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_forms_2);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/RobotoCondensed-Regular.ttf",true);
        getSupportActionBar().setTitle("Record the responses");

        name=(EditText)findViewById(R.id.personconteditText);
        designation=(EditText)findViewById(R.id.desigPCeditText);
        mobile=(EditText)findViewById(R.id.mobPCeditText);
        joinDate=(EditText)findViewById(R.id.DateAppjoineditText);
        desigApp=(EditText)findViewById(R.id.DesgAppeditText);
        noYears=(EditText)findViewById(R.id.yearseditText);
        companyNature=(EditText)findViewById(R.id.CompanyNatureeditText);
        remarks=(EditText)findViewById(R.id.OtherRemarkseditText);
        jobType=(Spinner)findViewById(R.id.jobtypespinner);
        workOrg=(Spinner)findViewById(R.id.workingAsspinner);
        jobTransfer=(Spinner)findViewById(R.id.transferspinner);


        agentid = getIntent().getStringExtra("agent");

        jobtypeadapter=ArrayAdapter.createFromResource(this,R.array.jobtype,R.layout.support_simple_spinner_dropdown_item);
        jobtypeadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobType.setAdapter(jobtypeadapter);

        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {

                    case 0:
                        sjobType="Permanent";
                        break;
                    case 1:
                        sjobType="Probation";
                        break;
                    case 2:
                        sjobType="Contract Worker";
                        break;
                    case 3:
                        sjobType="Temporary Worker";
                        break;
                    case 4:
                        sjobType="Others";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        workorgadapter=ArrayAdapter.createFromResource(this,R.array.workorg,R.layout.support_simple_spinner_dropdown_item);
        workorgadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        workOrg.setAdapter(workorgadapter);

        workOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        sworkOrg="Typist";
                        break;
                    case 1:
                        sworkOrg="Stenographer";
                        break;
                    case 2:
                        sworkOrg="Supervisor";
                        break;
                    case 3:
                        sworkOrg="Junior Management";
                        break;
                    case 4:
                        sworkOrg="Middle Management";
                        break;
                    case 5:
                        sworkOrg="Senior Management";
                        break;
                    case 6:
                        sworkOrg="Other Management";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        jobtransferadapter=ArrayAdapter.createFromResource(this,R.array.transfer,R.layout.support_simple_spinner_dropdown_item);
        jobtransferadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobTransfer.setAdapter(jobtransferadapter);

        jobTransfer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i)
                {
                    case 0:
                        sjobTransfer="Yes";
                        break;
                    case 1:
                        sjobTransfer="No";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void onClickNextso1(View view){
        filestr=getIntent().getStringExtra("file");
        sname=name.getText().toString().trim();
        sdesignation=designation.getText().toString().trim();
        smobile=mobile.getText().toString().trim();
        sjoinDate=joinDate.getText().toString().trim();
        sdesigApp=desigApp.getText().toString().trim();
        snoYears=noYears.getText().toString().trim();
        scompanyNature=companyNature.getText().toString().trim();
        sremarks=remarks.getText().toString().trim();

//        if(TextUtils.isEmpty(sname)||TextUtils.isEmpty(sdesignation)||TextUtils.isEmpty(smobile)||TextUtils.isEmpty(sjoinDate)
//                ||TextUtils.isEmpty(sdesigApp)||TextUtils.isEmpty(snoYears)) {
//            Toast.makeText(getApplicationContext(),"Enter all the fields .. . ",Toast.LENGTH_LONG).show();
//        } else {

            if (isNetworkAvailable(getApplicationContext())) {
                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Data").child("Office").child(filestr).child("Name of Person Contacted").setValue(sname);
                databaseReference.child("Data").child("Office").child(filestr).child("Designation of Person Contacted").setValue(sdesignation);
                databaseReference.child("Data").child("Office").child(filestr).child("Mobile").setValue(smobile);
                databaseReference.child("Data").child("Office").child(filestr).child("Date of Joining of Applicant").setValue(sjoinDate);
                databaseReference.child("Data").child("Office").child(filestr).child("Designation of Applicant").setValue(sdesigApp);
                databaseReference.child("Data").child("Office").child(filestr).child("Number of years in present Employment ").setValue(snoYears);
                databaseReference.child("Data").child("Office").child(filestr).child("Nature of Companies Business").setValue(scompanyNature);
                databaseReference.child("Data").child("Office").child(filestr).child("Type of Job").setValue(sjobType);
                databaseReference.child("Data").child("Office").child(filestr).child("Working in Organisation as").setValue(sworkOrg);
                databaseReference.child("Data").child("Office").child(filestr).child("Job Transferable").setValue(sjobTransfer);
                databaseReference.child("Data").child("Office").child(filestr).child("Other Remarks").setValue(sremarks);

                Intent intent = new Intent(Off1Act.this, LocationPhoto.class);
                intent.putExtra("file", filestr);
                intent.putExtra("agent", agentid);
                intent.putExtra("type","Office");
                startActivity(intent);
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
       // }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
