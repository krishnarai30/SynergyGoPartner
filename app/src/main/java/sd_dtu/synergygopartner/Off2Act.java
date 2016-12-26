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

public class Off2Act extends AppCompatActivity {
    EditText companyNature,remarks;
    String scompanyNature,sremarks,sjobType,sworkOrg,sjobTransfer;
    String filestr,agentid;
    Spinner  jobType,workOrg,jobTransfer;
    ArrayAdapter<CharSequence> jobtypeadapter;
    ArrayAdapter<CharSequence> workorgadapter;
    ArrayAdapter<CharSequence> jobtransferadapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off2);

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


    public void onClickFinishso(View view){
        filestr=getIntent().getExtras().getString("file");
        scompanyNature=companyNature.getText().toString().trim();
        sremarks=remarks.getText().toString().trim();

        if(TextUtils.isEmpty(scompanyNature)||TextUtils.isEmpty(sremarks)) {
            Toast.makeText(getApplicationContext(),"Fields are Empty !!",Toast.LENGTH_LONG).show();
        } else {

            if (isNetworkAvailable(getApplicationContext())) {

                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Data").child("Office").child(filestr).child("Nature of Companies Business").setValue(scompanyNature);
                databaseReference.child("Data").child("Office").child(filestr).child("Type of Job").setValue(sjobType);
                databaseReference.child("Data").child("Office").child(filestr).child("Working in Organisation as").setValue(sworkOrg);
                databaseReference.child("Data").child("Office").child(filestr).child("Job Transferable").setValue(sjobTransfer);
                databaseReference.child("Data").child("Office").child(filestr).child("Other Remarks").setValue(sremarks);


                Intent intent = new Intent(Off2Act.this, LocationPhoto.class);
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
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
